package com.agarcia.respuestas_microservicios.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.agarcia.examenes_commons.models.entity.Examen;

@FeignClient (name = "examenes-microservicios")
public interface ExamenFeignClient {

    @GetMapping ("/api/examenes/{id}")
    public Examen obtenerExmanePorId(@PathVariable Long id);

    @GetMapping ("/api/examenes//respondidos-por-preguntas")
    public List<Long> obtenerExamenesIdporPreguntasIdRespondidas(@RequestParam List<Long> preguntasIds);

}
