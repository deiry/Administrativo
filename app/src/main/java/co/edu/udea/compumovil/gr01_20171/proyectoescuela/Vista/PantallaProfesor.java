package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PantallaProfesor extends AppCompatActivity {
    RadioButton especifico;
    RadioButton director;
    TextView agregarMateria;
    FloatingActionButton agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_profesor);
        especifico = (RadioButton) findViewById(R.id.p_especif);
        director = (RadioButton) findViewById(R.id.p_director);
        agregarMateria = (TextView) findViewById(R.id.agregar_m);
        agregar = (FloatingActionButton) findViewById(R.id.btn_agregarMateria);
        agregar.setVisibility(View.INVISIBLE);
        agregarMateria.setVisibility(View.INVISIBLE);
        //Activar RadioButtons
        especifico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar.setVisibility(View.VISIBLE);
                agregarMateria.setVisibility(View.VISIBLE);
            }
        });
        director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar.setVisibility(View.INVISIBLE);
                agregarMateria.setVisibility(View.INVISIBLE);
            }
        });


        //Funcionalidad del botón para agregar grupo.
        FloatingActionButton mostrarDialogGrupo = (FloatingActionButton) findViewById(R.id.btn_agregarGrupo);
        mostrarDialogGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(PantallaProfesor.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_dialog_agregar_grupos, null);
                FloatingActionButton subirEstudiantes = (FloatingActionButton) mView.findViewById(R.id.btn_subirEstudiantes);
                Button guardar = (Button) mView.findViewById(R.id.btn_guardarGrupo);
                final EditText numGrado = (EditText) mView.findViewById(R.id.numGrado);
                final EditText identGrupo = (EditText) mView.findViewById(R.id.idenGrupo);
                //Subir Estudiantes
                subirEstudiantes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent ingresar = new Intent(PantallaProfesor.this,AgregarEstudiantes.class);
                        startActivity(ingresar);
                    }
                });

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!numGrado.getText().toString().isEmpty() && !identGrupo.getText().toString().isEmpty()) {
                            Toast.makeText(PantallaProfesor.this, R.string.grupo_guardado_msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
        //Funcionalidad del botón agregar materia
        FloatingActionButton mostrarDialogMateria = (FloatingActionButton) findViewById(R.id.btn_agregarMateria);
        mostrarDialogMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mtBuilder = new AlertDialog.Builder(PantallaProfesor.this);
                View mtView = getLayoutInflater().inflate(R.layout.activity_dialog_agregar_materia, null);
                mtBuilder.setView(mtView);
                AlertDialog dialogo = mtBuilder.create();
                dialogo.show();


            }
        });


    }
}
