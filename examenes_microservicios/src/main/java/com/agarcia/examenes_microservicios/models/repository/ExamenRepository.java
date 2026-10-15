package com.agarcia.examenes_microservicios.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import com.agarcia.examenes_commons.models.entity.Examen;

public interface ExamenRepository extends CrudRepository<Examen, Long>, PagingAndSortingRepository<Examen, Long> {

    @Query("select e from Examen e where lower(e.nombre) like lower(concat('%', ?1, '%'))")
    public List<Examen> findByNombre(String term);
    
    @Query("select distinct e.id from Pregunta p join p.examen e where p.id in ?1 group by e.id")
    public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> alumnoId);

}
