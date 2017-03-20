package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO;

public class GestionEstudianteMeta {
    private boolean estado; // Correspone a si el estudiante esta seleccionado en la vista
    private int duracionMeta;

    public boolean estado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getDuracionMeta() {
        return duracionMeta;
    }

    public void setDuracionMeta(int duracionMeta) {
        this.duracionMeta = duracionMeta;
    }
}
