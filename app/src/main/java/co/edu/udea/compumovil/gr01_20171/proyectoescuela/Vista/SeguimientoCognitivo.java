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

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class SeguimientoCognitivo extends AppCompatActivity {

    UbicacionFragment fragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_cognitivo);

        CrearGridView();

    }




    private void CrearGridView() {

        String[] estudiantes = new String[]{"Estudiante1","Estudiante2","Estudiante1","Estudiante2","Estudiante1","Estudiante2","Estudiante1","Estudiante2","Estudiante1","Estudiante2"};
        String[] estudiantes2 = new String[]{"Estu1","Estu2","Estu1","Estu2","Estu1","Estu2","Estu1","Estu2","Estu1","Estu2","Estu1","Estu2",};


        EstudianteAdapter adapter = new EstudianteAdapter(this, estudiantes, estudiantes2);

        //ArrayAdapter<String> adapter;

        //adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,estudiantes);/**/
        // adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_estudiante,estudiantes);

        GridView gridEstudiante = (GridView) findViewById(R.id.grid_view_ubicacion);

        gridEstudiante.setAdapter(adapter);

        gridEstudiante.setNumColumns(4);

        gridEstudiante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SeguimientoCognitivo.this, String.valueOf(position), Toast.LENGTH_SHORT).show();

            }
        });


    }

}
