package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.vistasMetas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Meta;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class Cumplimiento extends AppCompatActivity {

    private Intent intenncion;
    private Bundle bundle;
    private int idMeta;
    private ArrayList<Estudiante> estudiantes;
    private EditText campo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_cumplimiento);
        intenncion = getIntent();
        bundle = intenncion.getExtras();
        idMeta = bundle.getInt("META");
        cargarTextos();

        //estudiantes = obtenerListaEstudiantes();
    }


    private void cargarTextos(){
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int año = calendar.get(Calendar.YEAR);
    }

    private void obtenerListaEstudiantes(){

    }
}
