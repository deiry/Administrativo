package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class SeguimientoCognitivo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_cognitivo);

        agregarFragmentNombreGrupo();
        //agregarFragmentUbicacion();
        CrearGridView();
    }


    /**
     * este metodo asigna al contenerdor de ubicacion el fragment de ubicacion
     */
    private void agregarFragmentUbicacion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        UbicacionFragment fragment = new UbicacionFragment();

        fragmentTransaction.add(R.id.fragment_contenedor_funcionalidad, fragment);

        fragmentTransaction.commit();
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

        gridEstudiante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SeguimientoCognitivo.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                agregarFragmentUbicacion();
            }
        });


    }

}
