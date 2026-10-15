package com.agarcia.examenes_microservicios.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.agarcia.examenes_commons.models.entity.Asignatura;

public interface AsignaturaRepository extends CrudRepository <Asignatura, Long>, PagingAndSortingRepository<Asignatura, Long> {

}
