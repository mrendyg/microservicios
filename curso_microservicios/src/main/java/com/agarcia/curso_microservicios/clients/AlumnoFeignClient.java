package com.agarcia.curso_microservicios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agarcia.alumnos_commons.models.entity.Alumno;

@FeignClient (name = "usuarios-microservicios")
public interface AlumnoFeignClient {

    @GetMapping ("/api/alumnos/alumnos-por-curso")
    public Iterable<Alumno> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);

}
