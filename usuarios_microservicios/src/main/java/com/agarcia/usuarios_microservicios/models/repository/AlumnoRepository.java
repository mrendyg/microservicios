package com.agarcia.usuarios_microservicios.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.agarcia.alumnos_commons.models.entity.Alumno;


public interface AlumnoRepository extends CrudRepository<Alumno, Long>, PagingAndSortingRepository<Alumno, Long> {

    @Query("select a from Alumno a where lower(a.nombre) like lower(concat('%', ?1, '%')) " +
           "or lower(a.apellido) like lower(concat('%', ?1, '%'))")
    List<Alumno> findByNombreOrApellido(String term);
    
}
