package com.agarcia.examenes_microservicios.services;

import java.util.List;

import com.agarcia.commons_microservicios.services.CommonService;
import com.agarcia.examenes_commons.models.entity.Asignatura;
import com.agarcia.examenes_commons.models.entity.Examen;


public interface ExamenService extends CommonService<Examen>{

    public List<Examen> findByNombre(String term);

    public Iterable<Asignatura> findAllAsignaturas();

    public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> alumnoId);

}
