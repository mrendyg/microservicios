package com.agarcia.usuarios_microservicios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agarcia.alumnos_commons.models.entity.Alumno;
import com.agarcia.commons_microservicios.services.CommonServiceImpl;
import com.agarcia.usuarios_microservicios.client.CursoFeignClient;
import com.agarcia.usuarios_microservicios.models.repository.AlumnoRepository;

@Service 
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

    @Autowired 
    private CursoFeignClient clientCurso;    

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term);
    }

    @Transactional(readOnly=true)
    public Iterable<Alumno> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void eliminarCursoAlumnoPorId(Long id) {
        clientCurso.eliminarCursoAlumnoPorId(id);
    }

    @Override
    @Transactional 
    public void deleteById(Long id) {
        super.deleteById(id);
        this.eliminarCursoAlumnoPorId(id);
    }
    
}
