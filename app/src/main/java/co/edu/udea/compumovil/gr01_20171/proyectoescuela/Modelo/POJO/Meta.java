package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO;

import java.util.Date;

public class Meta {
    private int id;
    private int idListMet;
    private int idGpEst;
    private Date fechaInicio;
    private Date fechaCump;
    private int duracion;
    private boolean estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdListMet() {
        return idListMet;
    }

    public void setIdListMet(int idListMet) {
        this.idListMet = idListMet;
    }

    public int getIdGpEst() {
        return idGpEst;
    }

    public void setIdGpEst(int idGpest) {
        this.idGpEst = idGpest;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCump() {
        return fechaCump;
    }

    public void setFechaCump(Date fechaCump) {
        this.fechaCump = fechaCump;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
