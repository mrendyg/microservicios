package com.agarcia.curso_microservicios.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.agarcia.alumnos_commons.models.entity.Alumno;
import com.agarcia.examenes_commons.models.entity.Examen;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @Column (name = "created_at")
    private Date createdAt;

    @JsonIgnoreProperties (value={"curso"}, allowSetters=true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy="curso", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<CursoAlumno> cursoAlumnos;

    //@OneToMany(fetch= FetchType.LAZY)
    @Transient 
    private List<Alumno> alumnos;

   // @JsonIgnore 
    @ManyToMany(fetch= FetchType.LAZY)
    private List<Examen> examenes;

    @PrePersist 
    public void prePersist() {
        this.createdAt = new Date();
    }

    public void addAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public boolean removeAlumno(Alumno alumno) {
        return this.alumnos.remove(alumno);
    }

    public void addExamen(Examen examen) {
        this.examenes.add(examen);
    }

    public boolean removeExamen(Examen examen) {
        return this.examenes.remove(examen);
    }

    
    //constructors
    public Curso(Long id, @NotEmpty String nombre, Date createdAt, List<CursoAlumno> cursoAlumnos, List<Alumno> alumnos,
            List<Examen> examenes) {
        this.id = id;
        this.nombre = nombre;
        this.createdAt = createdAt;
        this.cursoAlumnos = cursoAlumnos;
        this.alumnos = alumnos;
        this.examenes = examenes;
    }

    public Curso() {
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
        this.cursoAlumnos = new ArrayList<>();
    }



    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<CursoAlumno> getCursoAlumnos() {
        return cursoAlumnos;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
    }
    
    public void setCursoAlumnos(List<CursoAlumno> cursoAlumnos) {
        this.cursoAlumnos = cursoAlumnos;
    }

    public void addCursoAlumno(CursoAlumno cursoAlumnos) {
        this.cursoAlumnos.add(cursoAlumnos);
    }

    public void removeCursoAlumno(CursoAlumno cursoAlumnos) {
        this.cursoAlumnos.remove(cursoAlumnos);
    }

}
