package com.agarcia.curso_microservicios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agarcia.alumnos_commons.models.entity.Alumno;
import com.agarcia.commons_microservicios.services.CommonServiceImpl;
import com.agarcia.curso_microservicios.clients.AlumnoFeignClient;
import com.agarcia.curso_microservicios.clients.RepuestaFeignClient;
import com.agarcia.curso_microservicios.models.entity.Curso;
import com.agarcia.curso_microservicios.models.repository.CursoRepository;

@Service 
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Autowired
    private RepuestaFeignClient client;

    @Autowired 
    private AlumnoFeignClient clientAlumno;

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursosByAlumnoId(id).get(0);
    }

    @Override
    public Iterable<Long> obtenerExamenesIdsConRespuestasByAlumno(Long alumnoId) {
        return client.obtenerExamenesIdsConRespuestasByAlumno(alumnoId);
    }

    @Override
    public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids) {
        return clientAlumno.obtenerAlumnosPorCurso(ids);
    }

    @Override
    @Transactional 
    public void eliminarCursoAlumnoPorId(Long id) {
        repository.eliminarCursoAlumnoPorId(id);
    }

}
