package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO;

import java.util.Date;

public class Meta {

    private int id;
    private int estudianteId;
    private int listaMetasId;
    private Date fechaInicio;
    private int duracion;

    public Meta(int id, int estudianteId, int listaMetasId, Date fechaInicio, int duracion){
        this.id = id;
        this.estudianteId = estudianteId;
        this.listaMetasId = listaMetasId;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
    }

    public Meta(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public int getListaMetasId() {
        return listaMetasId;
    }

    public void setListaMetasId(int listaMetasId) {
        this.listaMetasId = listaMetasId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
