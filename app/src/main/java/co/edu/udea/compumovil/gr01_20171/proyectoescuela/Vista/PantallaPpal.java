package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


<<<<<<< HEAD
=======
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.ContratoEscuela;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Categoria;
>>>>>>> testConfiguracion
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Subcategoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.vistasMetas.PrincipalMetas;

public class PantallaPpal extends AppCompatActivity {

    private Grupo grupo;

    int tipoVista;

    private OperacionesBaseDeDatos manager;
    private String GRUPO = "GRUPO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ppal);
<<<<<<< HEAD
=======
        intent = getIntent();
        bundle = intent.getExtras();
>>>>>>> testConfiguracion

        grupo = (Grupo) intent.getSerializableExtra(GRUPO);
        tipoVista = (int) intent.getIntExtra("tipoVista",0);

        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());

        //obtener extra del grupo que esta seleccionado
        Intent intent = getIntent();
        grupo = (Grupo) intent.getSerializableExtra(GRUPO);

/*
        //Voy a probar una pantalla en el botón de asistencia que nos corresponde :D
        Button asistencia = (Button) findViewById(R.id.btn_asistencia);
        asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(PantallaPpal.this, AsistenciaV.class);
                ingresar.putExtra("GRUPO",grupo);
                startActivity(ingresar);
            }
        });

*/

    }

    public void ClckIrSeguimientoCognitivo(View view)
    {

            Intent intent = new Intent(this,SeguimientoCognitivo.class);
            intent.putExtra("GRUPO",grupo);
            intent.putExtra("tipoVista",tipoVista);
            startActivity(intent);



    }

    public void ClckIrSeguimientoEtico(View view)
    {
        //se crea la intencion
        //DE LA VISTA SE ASIGNA EL ONCLICK
    }

<<<<<<< HEAD
    public void ClickIrAsistencia(View view)
    {
        Intent ingresar = new Intent(PantallaPpal.this, Asistencia.class);
        startActivity(ingresar);
=======

    public void ClickIrAsistencia(View view) {

        if (tipoVista == 1)
        {
            intent = new Intent(PantallaPpal.this, AsistenciaV.class);
            intent.putExtra("GRUPO",grupo);
                 
        }
        else if (tipoVista == 2)
        {
            intent = new Intent(this,ListarEstudiantesAsistencia.class);
            intent.putExtra("GRUPO",grupo);
        }
        startActivity(intent);
>>>>>>> testConfiguracion
    }

    public void ClckIrMetas(View view)
    {
<<<<<<< HEAD
        Intent intencion = new Intent(this, PrincipalMetas.class);
        intencion.putExtra("GRUPO",grupo);
        startActivity(intencion);
    }
=======
        //se crea la intencion
        //DE LA VISTA SE ASIGNA EL ONCLICK
    }

    public void ClickIrUbicacion(View view)
    {
        intent = new Intent(this,Ubicacion.class);
        intent.putExtra(GRUPO,grupo);
        startActivity(intent);
    }



>>>>>>> testConfiguracion
}









