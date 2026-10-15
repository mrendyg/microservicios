package com.agarcia.respuestas_microservicios.models.entity;

import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agarcia.alumnos_commons.models.entity.Alumno;
import com.agarcia.examenes_commons.models.entity.Pregunta;

@Document(collection = "respuestas")
public class Respuesta {

    @Id 
    private String id;

    private String texto;

    //@Transient 
    private Alumno alumno;

    private Long alumnoId;

    //@Transient 
    private Pregunta pregunta;

    private Long preguntaId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public Respuesta(String id, String texto, Alumno alumno, Long alumnoId, Pregunta pregunta, Long preguntaId) {
        this.id = id;
        this.texto = texto;
        this.alumno = alumno;
        this.alumnoId = alumnoId;
        this.pregunta = pregunta;
        this.preguntaId = preguntaId;
    }

    public Respuesta() {
    }
    


}
