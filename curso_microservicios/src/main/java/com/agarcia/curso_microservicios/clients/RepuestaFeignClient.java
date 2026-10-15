package com.agarcia.curso_microservicios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "respuestas-microservicios")
public interface RepuestaFeignClient {

    //en esta consulta se debe colocar el prefijo de la ruta del controlador del microservicio de respuestas
    @GetMapping("/api/respuestas/alumno/{alumnoId}/examenes-respondidos")
    public Iterable<Long> obtenerExamenesIdsConRespuestasByAlumno(@PathVariable Long alumnoId);

}