package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class DialogAgregarGrupos extends AppCompatActivity {

    OperacionesBaseDeDatos datos;
    Button guardarGp = (Button)findViewById(R.id.btn_guardarGrupo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_agregar_grupos);
        FloatingActionButton subirE = (FloatingActionButton)findViewById(R.id.btn_subirEstudiantes);
        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        subirE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(DialogAgregarGrupos.this,AgregarEstudiantes.class);
                startActivity(ingresar);
            }
        });


        guardarGp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                datos.getDb().beginTransaction();
                Grupo grupo = new Grupo(2,"2");
                    datos.insertarGrupo(grupo);
                    datos.getDb().setTransactionSuccessful();
                }

                catch(Exception e){e.printStackTrace();}
                finally{
                    datos.getDb().endTransaction();
                }

            }
        });
    }
}
