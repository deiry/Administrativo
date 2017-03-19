package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO;

import java.util.Date;

public class CumplimientoMeta {

    private int id;
    private int metaId;
    private Date fecha;
    private int estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMetaId() {
        return metaId;
    }

    public void setMetaId(int metaId) {
        this.metaId = metaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
