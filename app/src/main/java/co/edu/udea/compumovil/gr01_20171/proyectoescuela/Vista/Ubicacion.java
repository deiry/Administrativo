package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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

    private View lastView = null;
    private GridView gv_ubicacion;

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

        for(int i = 0; i < estudiantes.size();i++)
        {
            estudiantes.get(i).setPosColumna(i+1);
        }

        estudiantes = completarEstudiantes(estudiantes,filas*columnas);


        int a = estudiantes.size();

        gv_ubicacion = (GridView) findViewById(R.id.grid_view_ubicacion_estudiante);
        gv_ubicacion.setNumColumns(filas);

        EstudianteAdapter adapter = new EstudianteAdapter(this,estudiantes);

        gv_ubicacion.setAdapter(adapter);

        eventosGridViewItem();
    }

    private ArrayList<Estudiante> completarEstudiantes(ArrayList<Estudiante> estudiantes, int n)
    {
        int contador = estudiantes.size();
        for (int i = contador ; i < n ; i++)
        {
                Estudiante e = new Estudiante(0,"","","",0,"",i+1,0);
                estudiantes.add(e);
        }
        return estudiantes;
    }

    private void eventosGridViewItem()
    {
        gv_ubicacion.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                CardView c = (CardView) view.findViewById(R.id.cv_item_estudiante);

                c.setCardBackgroundColor(getColor(R.color.background_card_view));

                if(lastView != null)
                {

                }
                else
                {

                }
                return false;
            }
        });
    }


}
