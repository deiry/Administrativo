package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.DialogInterface;
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

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PantallaProfesor extends AppCompatActivity {
    RadioButton especifico;
    OperacionesBaseDeDatos datos;
    RadioButton director;
    TextView agregarMateria;
    FloatingActionButton agregar;
    EditText grado ;
    EditText ngrupo;

    ArrayList<String> grupos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_profesor);
        grado = (EditText)findViewById(R.id.numGrado);
        ngrupo = (EditText)findViewById(R.id.idenGrupo);
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
        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());

       // grupos=toGrupos(datos.obtenerGruposDB());
            grupos.add("2-A");
            grupos.add("3-B");

        //Funcionalidad del botón para agregar grupo.
        FloatingActionButton mostrarDialogGrupo = (FloatingActionButton) findViewById(R.id.btn_agregarGrupo);
        mostrarDialogGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(PantallaProfesor.this,DialogAgregarGrupos.class);
                  startActivity(ingresar);
            }
        });
        //Funcionalidad del botón subir estudiantes
        FloatingActionButton subirEstudiantes = (FloatingActionButton) findViewById(R.id.btn_subirEstudiantes);
        subirEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog dialog = listarGrupos(grupos);
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


    public AlertDialog listarGrupos(ArrayList<String> a){
        AlertDialog.Builder builder = new AlertDialog.Builder(PantallaProfesor.this);
        final CharSequence[] items = new CharSequence[a.size()];
        for (int i = 0; i < a.size(); i++){
            items[i] = a.get(i);
        }
        builder.setTitle("Grupos actuales").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent ingresar = new Intent(PantallaProfesor.this,AgregarEstudiantes.class);
                startActivity(ingresar);
            }
        });

        return builder.create();
    }

    public ArrayList<String> toGrupos (ArrayList<Grupo> a){
        ArrayList<String> gruposs = new ArrayList<>();
        Grupo aux;
        for (int i = 0; i < a.size(); i++){
            aux = a.get(i);
            gruposs.add(String.valueOf(aux.getCurso()) + "-" + aux.getGrupo());
        }
        return gruposs;
    }

}
