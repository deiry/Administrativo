package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.CustomListAdapter;

public class ListarEstudiantesAsistencia extends AppCompatActivity {

    static ArrayList<Estudiante> estudiantes;
    Grupo grupo;
    ListView list;
    static OperacionesBaseDeDatos datos;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_estudiantes_asistencia);
        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        intent = getIntent();
        bundle = intent.getExtras();
        grupo = (Grupo) intent.getSerializableExtra("GRUPO");
        estudiantes = retornaEstudiantes(grupo);
        list = (ListView)findViewById(R.id.list);
        final AdapterListaEstudiantesAsistencia adapterListaEstudiantesAsistencia = new AdapterListaEstudiantesAsistencia(getApplicationContext()
                ,R.layout.activity_adapter_lista_estudiantes_asistencia);
        adapterListaEstudiantesAsistencia.clear();
        if(estudiantes != null){
            for(Estudiante estudiante : estudiantes){
                adapterListaEstudiantesAsistencia.add(estudiante);
            }
        }
        adapterListaEstudiantesAsistencia.notifyDataSetChanged();
        if(adapterListaEstudiantesAsistencia != null && list!=null && estudiantes!=null ){
            list.setAdapter(adapterListaEstudiantesAsistencia);
        }

    }

    private static ArrayList<Estudiante> retornaEstudiantes(Grupo grupo){
        try {
            datos.getDb().beginTransaction();
            estudiantes = datos.obtenerEstudiantesDB(grupo);
            datos.getDb().setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            datos.getDb().endTransaction();
        }
        return estudiantes;
    }

 }


