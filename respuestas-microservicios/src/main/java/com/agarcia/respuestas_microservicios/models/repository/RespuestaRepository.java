package com.agarcia.respuestas_microservicios.models.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.agarcia.respuestas_microservicios.models.entity.Respuesta;

public interface RespuestaRepository extends MongoRepository<Respuesta, String> {

    @Query("{ 'alumnoId': ?0, 'preguntaId': { $in: ?1 } }")
    public Iterable<Respuesta> findByRespuestaByAlumnoByPregunta(Long alumnoId, List<Long> preguntasId);

    @Query("{'alumnoId': ?0}")
    public Iterable<Respuesta> findByAlumnoId(Long alumnoId);

    //@Query("select r from Respuesta r join fetch r.pregunta p join fetch p.examen e where r.alumnoId=?1 and e.id=?2")
    //public Iterable<Respuesta> findByRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

    //@Query("select distinct e.id from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1")
    //public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);

    @Query("{'alumnoId': ?0, 'pregunta.examen.id': ?1}")
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

    @Query(value = "{'alumnoId': ?0}", fields ="{'pregunta.examen.id': 1}")
    public Iterable<Respuesta> findEfindExamenesIdsConRespuestasByAlumno(Long alumnoId);

}