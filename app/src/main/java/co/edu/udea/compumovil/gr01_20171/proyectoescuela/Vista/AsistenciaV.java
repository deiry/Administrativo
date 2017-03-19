package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.ArrayList;
import java.util.Date;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Asistencia;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class AsistenciaV extends AppCompatActivity {

    private ArrayList<Estudiante> estudiantes;
    private Grupo grupo;
    private int[] contadores;
    OperacionesBaseDeDatos manager;
    Intent intent;
    Bundle bundle;
    OperacionesBaseDeDatos datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_cognitivo);
        intent = getIntent();
        bundle = intent.getExtras();
        grupo = (Grupo) intent.getSerializableExtra("GRUPO");
        getApplicationContext().deleteDatabase("pedidos.db");
        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        estudiantes = manager.obtenerEstudiantesDB(grupo);

        CrearGridView();

    }


    private void CrearGridView() {

        contadores = new int[estudiantes.size()];
        for (int i = 0; i < contadores.length;i++)
        {
            contadores[i] = 0;
        }

        final AsistenciaEstudianteAdapter adapte = new AsistenciaEstudianteAdapter(this,estudiantes);


        final GridView gridEstudiante = (GridView) findViewById(R.id.grid_view_ubicacion);

        gridEstudiante.setAdapter(adapte);

        gridEstudiante.setNumColumns(4);

        gridEstudiante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AsistenciaV.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
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
                        ll.setBackground(getDrawable(R.drawable.yellow_color));
                        break;
                    }
                    case 3:
                    {
                        ll.setBackground(getDrawable(R.color.transparente));
                        contadores[position] = 0;
                        break;
                    }
                }




            }
        });


    }
    public String giveDate() {
        Calendar cal = Calendar.getInstance();
        /*SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
        return sdf.format(cal.getTime());*/

        int dia =cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH)+1;
        int año = cal.get(Calendar.YEAR);
        String fecha = dia+"/"+mes+"/"+año;
        return fecha;
    }

    public void agregarAsistencia(int indicador,int posicionEst){
        co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Asistencia asistencia;

        String estado = "";

        String date = giveDate();

        switch (indicador){
            case 1:
                estado = "faltó";
                break;
            case 2:
                estado = "tarde";
                break;
        }
        asistencia = new co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Asistencia(date,
                estudiantes.get(posicionEst).getIdentificacion(),estado );
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        try{
            datos.getDb().beginTransaction();
            datos.insertarAsistencia(asistencia);
            datos.getDb().setTransactionSuccessful();
            Toast.makeText(getApplicationContext(),"Estudiante agregado",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Se ha producido un error",Toast.LENGTH_SHORT).show();
        }finally{
            datos.getDb().endTransaction();
        }

    }
}
