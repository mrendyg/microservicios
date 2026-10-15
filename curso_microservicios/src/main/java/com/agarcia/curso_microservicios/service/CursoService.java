package com.agarcia.curso_microservicios.service;

import com.agarcia.alumnos_commons.models.entity.Alumno;
import com.agarcia.commons_microservicios.services.CommonService;
import com.agarcia.curso_microservicios.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {

    public Curso findCursoByAlumnoId(Long id);
    
    public Iterable<Long> obtenerExamenesIdsConRespuestasByAlumno(Long alumnoId);

    public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids);
    
    public void eliminarCursoAlumnoPorId(Long id);

}
