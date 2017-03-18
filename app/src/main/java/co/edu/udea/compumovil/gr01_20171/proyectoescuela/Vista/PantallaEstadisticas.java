package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Controlador.ControladorServiciosGenerales;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PantallaEstadisticas extends AppCompatActivity {

    Intent ingresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_estadisticas);

    }

    public void clickEstadAsistencia(View v){
        ingresar = new Intent(this,EstadisticasAsistencia.class);
        startActivity(ingresar);
    }

}

