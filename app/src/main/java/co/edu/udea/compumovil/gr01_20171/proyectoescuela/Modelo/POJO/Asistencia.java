package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO;

import java.util.Date;

public class Asistencia {
    private Date fecha;
    private int idEstudiante;
    private String asistencia;

<<<<<<< HEAD
    public Date getFecha() {
=======
    public Asistencia() {
    }

    public Asistencia(String fecha, int idEstudiante, String asistencia) {
        this.fecha = fecha;
        this.idEstudiante = idEstudiante;
        this.asistencia = asistencia;
    }

    public String getFecha() {
>>>>>>> testConfiguracion
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }
    
}
