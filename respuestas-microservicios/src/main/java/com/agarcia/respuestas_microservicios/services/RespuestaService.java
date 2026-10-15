package com.agarcia.respuestas_microservicios.services;


import com.agarcia.respuestas_microservicios.models.entity.Respuesta;

public interface RespuestaService {

    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);

    public Iterable<Respuesta> findByRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);

    public Iterable<Respuesta> findByAlumnoId(Long alumnoId);
    
}
