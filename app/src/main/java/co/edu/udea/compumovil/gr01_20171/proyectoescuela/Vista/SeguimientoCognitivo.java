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
import android.widget.LinearLayout;
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
    private int hola = 0;
    private int[] contadores;

    private OperacionesBaseDeDatos manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_cognitivo);



        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        grupo = (Grupo) getIntent().getSerializableExtra("GRUPO");

    }


    private void crearGridView()
    {
        estudiantes = manager.obtenerEstudiantesDB(grupo);

        contadores = new int[estudiantes.size()];

        for (int i = 0; i < contadores.length;i++)
        {
            contadores[i] = 0;
        }

        EstudianteAdapter adapter = new EstudianteAdapter(this, estudiantes);

        GridView gridEstudiante = (GridView) findViewById(R.id.grid_view_ubicacion);

        gridEstudiante.setAdapter(adapter);

        gridEstudiante.setNumColumns(4);

        gridEstudiante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(SeguimientoCognitivo.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                contadores[position]++;
                LinearLayout ll = (LinearLayout) view.findViewById(R.id.contenedor_item_estudiante);
                switch (contadores[position])
                {
                    case 1:
                    {
                        ll.setBackground(getDrawable(R.color.colorAccent));
                        break;
                    }
                    case 2:
                    {
                        ll.setBackground(getDrawable(R.color.colorPrimary));
                        break;
                    }
                    case 3:
                    {
                        ll.setBackground(getDrawable(R.color.cardview_light_background));
                        contadores[position] = 0;
                        break;
                    }
                }


                Intent intent = new Intent(SeguimientoCognitivo.this, SegCogEstudiante.class);
                intent.putExtra("id",estudiantes.get(position).getIdentificacion());
                //startActivity(intent);


            }
        });
    }

    @Override
    protected void onResume() {
        estudiantes = manager.obtenerEstudiantesDB(grupo);

        crearGridView();
        super.onResume();
    }


}
