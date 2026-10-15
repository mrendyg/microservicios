package com.agarcia.usuarios_microservicios.services;

import java.util.List;

import com.agarcia.alumnos_commons.models.entity.Alumno;
import com.agarcia.commons_microservicios.services.CommonService;


public interface AlumnoService  extends CommonService<Alumno> {

    public List<Alumno> findByNombreOrApellido(String term);

    public Iterable<Alumno> findAllById(Iterable<Long> ids);

    public void eliminarCursoAlumnoPorId(Long id);

}
