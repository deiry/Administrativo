package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.vistasMetas.PrincipalMetas;

public class PantallaPpal extends AppCompatActivity {

    private Grupo grupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ppal);


        //obtener extra del grupo que esta seleccionado
        Intent intent = getIntent();
        grupo = (Grupo) intent.getSerializableExtra("GRUPO");

        //Voy a probar una pantalla en el botón de asistencia que nos corresponde :D
        Button asistencia = (Button) findViewById(R.id.btn_asistencia);
        asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(PantallaPpal.this, PantallaConfiguracion.class);
                startActivity(ingresar);
            }
        });

    }

    public void ClckIrSeguimientoCognitivo(View view)
    {
        Intent intent = new Intent(this,SeguimientoCognitivo.class);
        intent.putExtra("GRUPO",grupo);
        startActivity(intent);
    }

    public void ClckIrSeguimientoEtico(View view)
    {
        //se crea la intencion
        //DE LA VISTA SE ASIGNA EL ONCLICK
    }

    public void ClickIrAsistencia(View view)
    {
        Intent ingresar = new Intent(PantallaPpal.this, Asistencia.class);
        startActivity(ingresar);
    }

    public void ClckIrMetas(View view)
    {
        Intent intencion = new Intent(this, PrincipalMetas.class);
        intencion.putExtra("GRUPO",grupo);
        startActivity(intencion);
    }
}









