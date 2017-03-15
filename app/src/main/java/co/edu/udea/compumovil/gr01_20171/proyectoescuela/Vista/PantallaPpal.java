package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;


import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.ContratoEscuela;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PantallaPpal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_pantalla_ppal);


        setContentView(R.layout.activity_pantalla_ppal);

        setContentView(R.layout.activity_pantalla_ppal);

        //Voy a probar una pantalla en el botón de asistencia que nos corresponde :D
        Button asistencia = (Button)findViewById(R.id.btn_asistencia);
        asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(PantallaPpal.this,PantallaConfiguracion.class);
                startActivity(ingresar);
            }
        });
    }

    public void ClckIrSeguimientoCognitivo(View view)
    {
        Intent intent = new Intent(this,SeguimientoCognitivo.class);
        startActivity(intent);
    }

    public void ClckIrSeguimientoEtico(View view)
    {
        //se crea la intencion
        //DE LA VISTA SE ASIGNA EL ONCLICK
    }

    public void ClckIrAsistencia(View view)
    {
        //se crea la intencion
//DE LA VISTA SE ASIGNA EL ONCLICK
    }

    public void ClckIrMetas(View view)
    {
        //se crea la intencion
//DE LA VISTA SE ASIGNA EL ONCLICK
    }





    }









