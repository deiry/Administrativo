package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO;

public class Estudiante {
    private int identificacion;
    private String nombres;
    private String apellidos;
    private String foto;
    private int curso;
    private String grupo;
    private int posFila;
    private int posColumna;

    public Estudiante(String nombres, String apellidos,String foto,int identificacion){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.foto = foto;

    }

    public Estudiante(int identificacion, String nombres, String apellidos, String foto, int curso, String grupo, int posFila, int posColumna) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.foto = foto;
        this.curso = curso;
        this.grupo = grupo;
        this.posFila = posFila;
        this.posColumna = posColumna;
    }

    public Estudiante(int identificacion, String nombres, String apellidos,String foto, int curso, String grupo) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.foto = foto;
        this.curso = curso;
        this.grupo = grupo;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public int getPosFila() {
        return posFila;
    }

    public void setPosFila(int posFila) {
        this.posFila = posFila;
    }

    public int getPosColumna() {
        return posColumna;
    }

    public void setPosColumna(int posColumna) {
        this.posColumna = posColumna;
    }
    
}
