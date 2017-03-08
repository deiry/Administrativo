package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;


import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.UbicacionFragment;

public class PantallaPpal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ppal);

        agregarFragmentNombreGrupo();
        agregarFragmentUbicacion();
        CrearGridView();
    }

    /**
     * este metodo asigna al contenerdor de ubicacion el fragment de ubicacion
     */
    private void agregarFragmentUbicacion() {
        /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        UbicacionFragment fragment = new UbicacionFragment();

        fragmentTransaction.add(R.id.fragmet_contenedor_ubicacion, fragment);

        fragmentTransaction.commit();*/
    }

    /**
     * esta funcion asigna el fragment del titulo al lado de las operaciones principales
     */
    private void agregarFragmentNombreGrupo() {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        TituloGrupoFragment fragment = new TituloGrupoFragment();

        fragmentTransaction.add(R.id.fragment_contenedor, fragment);

        fragmentTransaction.commit();
    }


    private void CrearGridView() {
        String[] estudiantes = new String[]{"Estudiante1","Estudiante2","Estudiante1","Estudiante2",
                "Estudiante1","Estudiante2","Estudiante1","Estudiante2",
                "Estudiante1","Estudiante2","Estudiante1","Estudiante2",
                "Estudiante1","Estudiante2","Estudiante1","Estudiante2"};

        ArrayAdapter<String> adapter;

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,estudiantes);
       // adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_estudiante,estudiantes);

        GridView gridEstudiante = (GridView) findViewById(R.id.grid_view_ubicacion);

        gridEstudiante.setAdapter(adapter);

        gridEstudiante.setNumColumns(6);


    }
}
