package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;


import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.ContratoEscuela;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PantallaPpal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ppal);
    }



    public void clickSeguimientoCognitivo(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SeguimientoCognitivo.class);
        startActivity(intent);
    }

    public void clickSeguimientoEtico(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SeguimientoCognitivo.class);
        startActivity(intent);
    }

    public void clickSeguimientoAsistencia(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SeguimientoCognitivo.class);
        startActivity(intent);
    }

    public void clickSeguimientoMetas(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SeguimientoCognitivo.class);
        startActivity(intent);
    }

    public void clickConfiguracion(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SeguimientoCognitivo.class);
        startActivity(intent);
    }



}
