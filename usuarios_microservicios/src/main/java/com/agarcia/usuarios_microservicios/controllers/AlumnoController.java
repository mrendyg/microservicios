package com.agarcia.usuarios_microservicios.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agarcia.commons_microservicios.controllers.CommonController;
import com.agarcia.alumnos_commons.models.entity.Alumno;
import com.agarcia.usuarios_microservicios.services.AlumnoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @GetMapping ("/alumnos-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(commonService.findAllById(ids));
    }

    @GetMapping("/uploads/img/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id){
        
        Optional<Alumno> o = commonService.findById(id);

        if(o.isEmpty() || o.get().getFoto() == null){
            return ResponseEntity.notFound().build();
        }

        Resource imagen = new ByteArrayResource(o.get().getFoto());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imagen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()){
            return this.validar(result);
        }

        Optional<Alumno> o = commonService.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Alumno alumnoDB = o.get();
        
        if (alumno.getNombre() != null) alumnoDB.setNombre(alumno.getNombre());
        if (alumno.getApellido() != null) alumnoDB.setApellido(alumno.getApellido());
        if (alumno.getEmail() != null) alumnoDB.setEmail(alumno.getEmail());

        try {
            Alumno alumnoGuardado = commonService.save(alumnoDB);
            return ResponseEntity.ok(alumnoGuardado); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el alumno: " + e.getMessage());
        }
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term) {
        return ResponseEntity.ok(commonService.findByNombreOrApellido(term));
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto (@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException{
        if(!archivo.isEmpty()){
            alumno.setFoto(archivo.getBytes());
        }
        return super.crear(alumno, result);
    }

    @PutMapping("/editar-con-foto/{id}")
    public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id, 
                                            @RequestParam(required = false) MultipartFile archivo) throws IOException {
        if (result.hasErrors()){
            return this.validar(result);
        }

        Optional<Alumno> o = commonService.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Alumno alumnoDB = o.get();

        alumnoDB.setNombre(alumno.getNombre());
        alumnoDB.setApellido(alumno.getApellido());
        alumnoDB.setEmail(alumno.getEmail());
        if (archivo != null && !archivo.isEmpty()){
            alumnoDB.setFoto(archivo.getBytes());
        }

        return ResponseEntity.ok(commonService.save(alumnoDB));
    }

}
