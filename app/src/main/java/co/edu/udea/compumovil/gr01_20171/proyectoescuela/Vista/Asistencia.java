package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class Asistencia extends AppCompatActivity {
    ArrayList<Estudiante> estudiantes;
    private OperacionesBaseDeDatos manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_cognitivo);
        Grupo grupo = new Grupo(1,"A");
        getApplicationContext().deleteDatabase("pedidos.db");
        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        estudiantes = manager.obtenerEstudiantesDB(grupo);

        CrearGridView();

    }


    //   TituloGrupoFragment fragment = new TituloGrupoFragment();

    //  fragmentTransaction.commit();


    private void CrearGridView() {


        final AsistenciaEstudianteAdapter adapte = new AsistenciaEstudianteAdapter(this,estudiantes);

        //ArrayAdapter<String> adapter;

        //adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,estudiantes);/**/
        // adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_estudiante,estudiantes);

        final GridView gridEstudiante = (GridView) findViewById(R.id.grid_view_ubicacion);

        gridEstudiante.setAdapter(adapte);

        gridEstudiante.setNumColumns(4);

        gridEstudiante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Asistencia.this, String.valueOf(position), Toast.LENGTH_SHORT).show();



            }
        });


    }


}
