package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnGrupo, btnEst, btnConf;
    ArrayList<String> cadenaGrupos = new ArrayList<>();
    ArrayList<Grupo> grupos = new ArrayList<>();
    Intent intent, intent2;
    OperacionesBaseDeDatos datos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());

        btnGrupo = (ImageButton)findViewById(R.id.btnGrupo);
        btnGrupo.setOnClickListener(this);
        btnEst = (ImageButton)findViewById(R.id.btnEstadisticas);
        btnEst.setOnClickListener(this);
        btnConf = (ImageButton)findViewById(R.id.btnConfiguracion);
        btnConf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //Intent intent;
            case R.id.btnGrupo:
                grupos = datos.obtenerGruposDB();
                cadenaGrupos = convertirGrupos(grupos);
                AlertDialog dialog = listarGrupos(cadenaGrupos);
                dialog.show();
                break;
            case R.id.btnEstadisticas:
                intent2 = new Intent(this, EstadisticaModel.class);
                startActivity(intent2);
                break;
            case R.id.btnConfiguracion:
                intent = new Intent(this,PantallaConfiguracion.class);
                startActivity(intent);
                break;
        }
    }

    public AlertDialog listarGrupos(ArrayList<String> a){
        AlertDialog.Builder builder = new AlertDialog.Builder(InicioActivity.this);
        final CharSequence[] items = new CharSequence[a.size()];
        for (int i = 0; i < a.size(); i++){
            items[i] = a.get(i);
        }
        intent = new Intent(this,PantallaPpal.class);
        builder.setTitle("Grupos actuales").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent.putExtra("GRUPO",grupos.get(which));
                startActivity(intent);
            }
        });

        return builder.create();
    }

    public ArrayList<String> convertirGrupos(ArrayList<Grupo> a){
        ArrayList<String> gruposs = new ArrayList<>();
        Grupo aux;
        for (int i = 0; i < a.size(); i++){
            aux = a.get(i);
            gruposs.add(String.valueOf(aux.getCurso()) + "-" + aux.getGrupo());
        }
        return gruposs;
    }
}
