package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO;

import java.io.Serializable;

public class Grupo implements Serializable{
    private int curso;
    private String grupo;

    public Grupo(int curso, String grupo) {
        this.curso = curso;
        this.grupo = grupo;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

}
