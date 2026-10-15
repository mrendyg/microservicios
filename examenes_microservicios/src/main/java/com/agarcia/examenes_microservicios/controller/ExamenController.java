package com.agarcia.examenes_microservicios.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agarcia.commons_microservicios.controllers.CommonController;
import com.agarcia.examenes_commons.models.entity.Examen;
import com.agarcia.examenes_microservicios.services.ExamenService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/examenes")
public class ExamenController extends CommonController<Examen, ExamenService>{

    @Autowired 
    private ExamenService examenService;

    @GetMapping ("/respondidos-por-preguntas")
    public ResponseEntity<?> obtenerExamenesIdporPreguntasIdRespondidas(@RequestParam List<Long> preguntasIds){
        return ResponseEntity.ok().body(commonService.findExamenesIdsConRespuestasByPreguntaIds(preguntasIds));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()){
            return this.validar(result);
        }

        Optional<Examen> o = commonService.findById(id);
        
        if(!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Examen examenDb = o.get();
        examenDb.setNombre(examen.getNombre());

        examenDb.getPreguntas().stream()
        .filter(pdb -> !examen.getPreguntas().contains(pdb));

        examenDb.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(201).body(commonService.save(examenDb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term){
        return ResponseEntity.ok(examenService.findByNombre(term));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsingaturas() {
        return ResponseEntity.ok(examenService.findAllAsignaturas());
    }


}
