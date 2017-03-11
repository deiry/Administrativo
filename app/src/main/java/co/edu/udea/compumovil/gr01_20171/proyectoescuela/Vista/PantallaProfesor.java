package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

/**
 * Created by Estefany on 08/03/2017.
 */

public class PantallaProfesor extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_profesor);

        FloatingActionButton mostrar = (FloatingActionButton) findViewById(R.id.btn_agregarGrupo);
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(PantallaProfesor.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_agregar_grupo, null);
                FloatingActionButton subirEstudiantes = (FloatingActionButton) mView.findViewById(R.id.btn_subirEstudiantes);
                Button guardar = (Button) mView.findViewById(R.id.btn_guardarGrupo);
                final EditText numGrado = (EditText) mView.findViewById(R.id.numGrado);
                final EditText identGrupo = (EditText) mView.findViewById(R.id.idenGrupo);
               //Subir Estudiantes
                subirEstudiantes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Colocar condiciones
                        Toast.makeText(PantallaProfesor.this, R.string.estudiantes_guardadps_msg,Toast.LENGTH_SHORT).show();
                    }
                });

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!numGrado.getText().toString().isEmpty() && !identGrupo.getText().toString().isEmpty()){
                        Toast.makeText(PantallaProfesor.this, R.string.grupo_guardado_msg,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
            }





}
