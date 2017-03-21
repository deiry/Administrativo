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

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.ContratoEscuela;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PantallaProfesor extends AppCompatActivity {

    OperacionesBaseDeDatos datos;
    FloatingActionButton agregar;
    EditText grado ;
    EditText ngrupo;
    FloatingActionButton subirE;
    FloatingActionButton agregarM;
    ArrayList<String> gruposString = new ArrayList<>();
    ArrayList<Grupo> grupos = new ArrayList<>();
    ArrayList<Grupo> totalGrupos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_profesor);

        grado = (EditText)findViewById(R.id.numGrado);
        ngrupo = (EditText)findViewById(R.id.idenGrupo);
        subirE=(FloatingActionButton) findViewById(R.id.btn_subirEstudiantes);
        agregarM=(FloatingActionButton)findViewById(R.id.btn_materias);
        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        totalGrupos= datos.obtenerGruposDB();



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
                ingresar.putExtra("GRUPO",grupos.get(which));
                startActivity(ingresar);
            }
        });

        return builder.create();
    }

    public ArrayList<String> convertirGrupos (ArrayList<Grupo> a){
        ArrayList<String> gruposs = new ArrayList<>();
        Grupo aux;
        for (int i = 0; i < a.size(); i++){
            aux = a.get(i);
            gruposs.add(String.valueOf(aux.getCurso()) + "-" + aux.getGrupo());
        }
        return gruposs;
    }

    public void clickAgregarGrupo(View v){
        Intent ingresar = new Intent(PantallaProfesor.this,DialogAgregarGrupos.class);
        startActivity(ingresar);
    }

    public void clickSubirEstudiantes(View v){
        grupos= datos.obtenerGruposDB();
        gruposString=convertirGrupos(grupos);
        AlertDialog dialog = listarGrupos(gruposString);
        dialog.show();
    }

    public void clickMaterias(View view)
    {
        Intent intent = new Intent(this, MateriaAdicion.class);
        startActivity(intent);
    }



}
