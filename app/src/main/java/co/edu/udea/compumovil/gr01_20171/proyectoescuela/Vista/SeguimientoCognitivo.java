package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.ContratoEscuela;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Categoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class SeguimientoCognitivo extends Activity {

    private ArrayList<Estudiante> estudiantes;
    private Grupo grupo;

    private OperacionesBaseDeDatos manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_cognitivo);



        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        grupo = (Grupo) getIntent().getSerializableExtra("GRUPO");
        Grupo grupo = new Grupo(1,"A");
        getApplicationContext().deleteDatabase("pedidos.db");
        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());


        insertarCategoriasCognitivas();
    }





     //   TituloGrupoFragment fragment = new TituloGrupoFragment();

      //  fragmentTransaction.commit();


    private void crearGridView() {


        EstudianteAdapter adapter = new EstudianteAdapter(this, estudiantes);

        GridView gridEstudiante = (GridView) findViewById(R.id.grid_view_ubicacion);

        gridEstudiante.setAdapter(adapter);

        gridEstudiante.setNumColumns(4);

        gridEstudiante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SeguimientoCognitivo.this, String.valueOf(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SeguimientoCognitivo.this, SegCogEstudiante.class);
                intent.putExtra("id",estudiantes.get(position).getIdentificacion());
                startActivity(intent);

            }
        });




    }

    @Override
    protected void onResume() {
        estudiantes = manager.obtenerEstudiantesDB(grupo);

        crearGridView();
        super.onResume();
    }

    private void insertarCategoriasCognitivas() {
        //Categoria aplicar = new Categoria(getResources().getString(R.string.aplicar),1);
        //manager.insertarCategorias(aplicar);

        ArrayList<Categoria> categorias = manager.obtenerCategorias();


    }

}
