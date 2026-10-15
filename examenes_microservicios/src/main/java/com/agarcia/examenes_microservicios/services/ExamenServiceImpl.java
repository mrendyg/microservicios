package com.agarcia.examenes_microservicios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agarcia.commons_microservicios.services.CommonServiceImpl;
import com.agarcia.examenes_commons.models.entity.Asignatura;
import com.agarcia.examenes_commons.models.entity.Examen;
import com.agarcia.examenes_microservicios.models.repository.AsignaturaRepository;
import com.agarcia.examenes_microservicios.models.repository.ExamenRepository;

@Service 
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {


    @Autowired 
    private AsignaturaRepository asignaturaRepository;

    @Override 
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String term) {
        return repository.findByNombre(term);
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> preguntasIds) {
        return repository.findExamenesIdsConRespuestasByPreguntaIds(preguntasIds);
    }


}
