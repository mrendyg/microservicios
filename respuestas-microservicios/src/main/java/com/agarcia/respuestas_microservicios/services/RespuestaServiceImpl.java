package com.agarcia.respuestas_microservicios.services;

//import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.agarcia.examenes_commons.models.entity.Examen;
//import com.agarcia.examenes_commons.models.entity.Pregunta;
// import com.agarcia.respuestas_microservicios.clients.ExamenFeignClient;
import com.agarcia.respuestas_microservicios.models.entity.Respuesta;
import com.agarcia.respuestas_microservicios.models.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    // @Autowired
    // private ExamenFeignClient examenClient;

    @Override
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
        return respuestaRepository.saveAll(respuestas);
    }

    @Override
    public Iterable<Respuesta> findByRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
        /*
         * Examen examen = examenClient.obtenerExmanePorId(examenId);
         * List<Pregunta> preguntas = examen.getPreguntas();
         * List<Long> preguntasId = preguntas.stream().map(p -> p.getId())
         * .collect(Collectors.toList());
         * List<Respuesta> respuestas = (List<Respuesta>)
         * respuestaRepository.findByRespuestaByAlumnoByPregunta(alumnoId,
         * preguntasId);
         * respuestas = respuestas.stream().map(r -> {
         * preguntas.forEach(p -> {
         * if (p.getId() == r.getPreguntaId()) {
         * r.setPregunta(p);
         * }
         * });
         * return r;
         * }).collect(Collectors.toList());
         */
        List<Respuesta> respuestas = (List<Respuesta>) respuestaRepository.findRespuestaByAlumnoByExamen(alumnoId,
                examenId);
        return respuestas;
    }

    @Override
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
        /*
         * List<Respuesta> respuestasAlumno = (List<Respuesta>)
         * respuestaRepository.findByAlumnoId(alumnoId);
         * List<Long> examenIds = Collections.emptyList();
         * if (respuestasAlumno.size() > 0) {
         * List<Long> preguntasIds = respuestasAlumno.stream().map(r ->
         * r.getPreguntaId())
         * .collect(Collectors.toList());
         * examenIds =
         * examenClient.obtenerExamenesIdporPreguntasIdRespondidas(preguntasIds);
         * }
         */
        List<Respuesta> respuestasAlumno = (List<Respuesta>) respuestaRepository
                .findEfindExamenesIdsConRespuestasByAlumno(alumnoId);
        List<Long> examenIds = respuestasAlumno.stream().map(r -> r.getPregunta().getExamen().getId()).distinct()
                .collect(Collectors.toList());
        return examenIds;
    }

    @Override
    public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
        return respuestaRepository.findByAlumnoId(alumnoId);
    }

}
