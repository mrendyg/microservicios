package com.agarcia.curso_microservicios.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agarcia.alumnos_commons.models.entity.Alumno;
import com.agarcia.commons_microservicios.controllers.CommonController;
import com.agarcia.curso_microservicios.models.entity.Curso;
import com.agarcia.curso_microservicios.models.entity.CursoAlumno;
import com.agarcia.curso_microservicios.service.CursoService;
import com.agarcia.examenes_commons.models.entity.Examen;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
public class CursoController extends CommonController<Curso, CursoService> {

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    @DeleteMapping("/eliminar-alumno/{id}")
    public ResponseEntity<?> eliminarCursoAlumnoPorId(@PathVariable Long id) {
        commonService.eliminarCursoAlumnoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Curso> cursos = ((List<Curso>) commonService.findAll()).stream().map(c -> {
            c.getCursoAlumnos().forEach(ca -> {
                Alumno alumno = new Alumno();
                alumno.setId(ca.getAlumnoId());
                c.addAlumno(alumno);
            });
            return c;
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(cursos);
    }

    @GetMapping("/pagina")
    @Override
    public ResponseEntity<?> listar(Pageable pageable) {
        Page<Curso> cursos = commonService.findAll(pageable).map(curso -> {
            curso.getCursoAlumnos().forEach(ca -> {
                Alumno alumno = new Alumno();
                alumno.setId(ca.getAlumnoId());
                curso.addAlumno(alumno);
            });
            return curso;
        });
        return ResponseEntity.ok().body(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Curso> o = commonService.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso curso = o.get();

        if (curso.getCursoAlumnos().isEmpty() == false) {

            List<Long> ids = curso.getCursoAlumnos().stream().map(ca -> ca.getAlumnoId())
                    .collect(Collectors.toList());

            List<Alumno> alumnos = (List<Alumno>) commonService.obtenerAlumnosPorCurso(ids);

            curso.setAlumnos(alumnos);

        }

        return ResponseEntity.ok().body(curso);
    }

    // Test de balanceador de carga
    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest() {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("balanceador", balanceadorTest);
        response.put("cursos", commonService.findAll());
        return ResponseEntity.ok(response);
    }

    // editar el curso segun el id
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return this.validar(result);
        }

        Optional<Curso> o = this.commonService.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDB = o.get();

        // Validar que no vengan datos nulos si tu BD no los permite
        if (curso.getNombre() != null)
            cursoDB.setNombre(curso.getNombre());

        return ResponseEntity.status(201).body(this.commonService.save(cursoDB));
    }

    // asignar un alumno o varios al curso
    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
        Optional<Curso> o = this.commonService.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDB = o.get();

        alumnos.forEach(a -> {
            CursoAlumno cursoAlumno = new CursoAlumno();
            cursoAlumno.setAlumnoId(a.getId());
            cursoAlumno.setCurso(cursoDB);
            cursoDB.addCursoAlumno(cursoAlumno);
        });
        return ResponseEntity.status(201).body(this.commonService.save(cursoDB));
    }

    // Eliminar un alumno del curso desde el id
    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
        Optional<Curso> o = this.commonService.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDB = o.get();
        CursoAlumno cursoAlumno = new CursoAlumno();
        cursoAlumno.setAlumnoId(alumno.getId());
        cursoDB.removeCursoAlumno(cursoAlumno);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.commonService.save(cursoDB));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id) {
        Curso curso = commonService.findCursoByAlumnoId(id);

        if (curso != null) {
            List<Long> examenesIds = Optional.ofNullable(
                (List<Long>) commonService.obtenerExamenesIdsConRespuestasByAlumno(id)
            ).orElse(Collections.emptyList());

            List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
                if (examenesIds.contains(examen.getId())) {
                    examen.setRespondido(true);
                }
                return examen;
            }).collect(Collectors.toList());

            curso.setExamenes(examenes);
            return ResponseEntity.ok(curso);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamen(@RequestBody List<Examen> examenes, @PathVariable Long id) {
        Optional<Curso> o = this.commonService.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDB = o.get();

        examenes.forEach(e -> {
            cursoDB.addExamen(e);
        });
        return ResponseEntity.status(201).body(this.commonService.save(cursoDB));
    }

    @PutMapping("/{id}/eliminar-examenes")
    public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id) {
        Optional<Curso> o = this.commonService.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDB = o.get();

        if (!cursoDB.removeExamen(examen)) {
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("mensaje", "El examen no está asignado a este curso"));
        }

        return ResponseEntity.ok(this.commonService.save(cursoDB));
    }

}
