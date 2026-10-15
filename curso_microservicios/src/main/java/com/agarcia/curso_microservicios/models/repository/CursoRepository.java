package com.agarcia.curso_microservicios.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.agarcia.curso_microservicios.models.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long>, PagingAndSortingRepository<Curso, Long> {

    @Query("select distinct c from Curso c join fetch c.cursoAlumnos a where a.alumnoId = ?1")
    List<Curso> findCursosByAlumnoId(Long id);

    @Modifying 
    @Query ("delete from CursoAlumno ca where ca.alumnoId=?1")
    public void eliminarCursoAlumnoPorId(Long id);
    
}
