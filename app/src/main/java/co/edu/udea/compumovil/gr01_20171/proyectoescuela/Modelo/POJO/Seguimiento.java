package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO;

import java.util.Date;

public class Seguimiento {
    private int idSeg;
    private int idSubSeg;
    private int idEst;
    private String estado;
    private Date fecha;
    private String tipo;

    public int getIdSeg() {
        return idSeg;
    }

    public void setIdSeg(int idSeg) {
        this.idSeg = idSeg;
    }

    public int getIdSubSeg() {
        return idSubSeg;
    }

    public void setIdSubSeg(int idSubSeg) {
        this.idSubSeg = idSubSeg;
    }

    public int getIdEst() {
        return idEst;
    }

    public void setIdEst(int idEst) {
        this.idEst = idEst;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
