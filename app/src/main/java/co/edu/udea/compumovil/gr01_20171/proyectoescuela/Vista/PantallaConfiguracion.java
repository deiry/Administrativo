package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PantallaConfiguracion extends AppCompatActivity {

    ArrayList<String> gruposString = new ArrayList<>();
    ArrayList<Grupo> grupos = new ArrayList<>();
    Intent intent;
    OperacionesBaseDeDatos datos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_configuracion);
        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());

        Button ingresoProfesor = (Button)findViewById(R.id.btn_ingreso_profe);
        ingresoProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vistaProfe = new Intent(PantallaConfiguracion.this,PantallaProfesor.class);
                startActivity(vistaProfe);
            }
        });
        Button prueba = (Button) findViewById(R.id.pruebita);
        prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grupos= datos.obtenerGruposDB();
                gruposString=convertirGrupos(grupos);
                AlertDialog dialog = listarGrupos(gruposString);
                dialog.show();
            }
        });
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

    public AlertDialog listarGrupos(ArrayList<String> a){
        AlertDialog.Builder builder = new AlertDialog.Builder(PantallaConfiguracion.this);
        final CharSequence[] items = new CharSequence[a.size()];
        for (int i = 0; i < a.size(); i++){
            items[i] = a.get(i);
        }
        builder.setTitle("Grupos actuales").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent listar = new Intent(PantallaConfiguracion.this,ListarEstudiantes.class);
                listar.putExtra("GRUPO",grupos.get(which));
                startActivity(listar);
            }
        });

        return builder.create();
    }

}
