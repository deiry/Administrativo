package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO;

public class ListaMetas {
    private int id;
    private String nombre;
    private String tipo;

    public ListaMetas(String nombre, String tipo){
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
