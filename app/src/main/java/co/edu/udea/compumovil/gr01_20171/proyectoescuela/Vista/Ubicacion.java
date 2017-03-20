package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class Ubicacion extends AppCompatActivity {

    private OperacionesBaseDeDatos manager;
    private ArrayList<Estudiante> estudiantes;
    private String GRUPO = "GRUPO";
    private int filas;
    private int columnas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);

        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());

        init();
    }

    private void init()
    {
        filas = 6;
        columnas = 6;

        estudiantes = manager.obtenerEstudiantesDB((Grupo) getIntent().getSerializableExtra(GRUPO));

        int a = estudiantes.size();


    }
}
