package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class SeguimientoCognitivo extends Activity {

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

/*
        String[] estudiantes = new String[]{"Estudiante1","Estudiante2","Estudiante1","Estudiante2","Estudiante1","Estudiante2","Estudiante1","Estudiante2","Estudiante1","Estudiante2"};
        String[] estudiantes2 = new String[]{"Estu1","Estu2","Estu1","Estu2","Estu1","Estu2","Estu1","Estu2","Estu1","Estu2","Estu1","Estu2",};
*/



        EstudianteAdapter adapter = new EstudianteAdapter(this, estudiantes);

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

                Intent intent = new Intent(SeguimientoCognitivo.this, SegCogEstudiante.class);
                startActivity(intent);

            }
        });


    }

}
