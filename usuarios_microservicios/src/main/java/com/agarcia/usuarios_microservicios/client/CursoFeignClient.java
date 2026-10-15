package com.agarcia.usuarios_microservicios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "curso-microservicios")
public interface CursoFeignClient {

    @DeleteMapping ("/api/cursos/eliminar-alumno/{id}")
    public void eliminarCursoAlumnoPorId(@PathVariable Long id);
    
}
