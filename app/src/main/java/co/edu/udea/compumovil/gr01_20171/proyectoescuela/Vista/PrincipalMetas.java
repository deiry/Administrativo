package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PrincipalMetas extends AppCompatActivity {

    static ArrayList<Estudiante> estudiantes;
    Grupo grupo;
    ListView list;
    static OperacionesBaseDeDatos datos;
    Intent intent;
    Bundle bundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_metas);
        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        intent = getIntent();
        bundle = intent.getExtras();
        grupo = (Grupo) intent.getSerializableExtra("GRUPO");
        estudiantes = retornaEstudiantes(grupo);
        list = (ListView)findViewById(R.id.list_metas);
        final CustomListAdapterM customListAdapter = new CustomListAdapterM(getApplicationContext()
                ,R.layout.activity_list_metas);
        customListAdapter.clear();
        if(estudiantes != null){
            for(Estudiante estudiante : estudiantes){
                customListAdapter.add(estudiante);
            }
        }
        customListAdapter.notifyDataSetChanged();
        if(customListAdapter != null && list!=null && estudiantes!=null ){
            list.setAdapter(customListAdapter);
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
