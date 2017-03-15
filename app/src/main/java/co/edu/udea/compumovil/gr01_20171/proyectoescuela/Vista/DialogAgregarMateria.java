package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class DialogAgregarMateria extends AppCompatActivity {
    Button seleccionarGrupos;
    String[] listaItems;
    TextView mostrar;
    boolean[] checkedItems;

    OperacionesBaseDeDatos datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_agregar_materia);

        ListView chl=(ListView)findViewById(R.id.lista_grupos);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayList<String> grupos = new ArrayList<>();
        //Crar clase que maneje estos metodos, porque me cansé
        PantallaProfesor p = new PantallaProfesor();

        grupos= p.convertirGrupos(datos.obtenerGruposDB());



    }
}
