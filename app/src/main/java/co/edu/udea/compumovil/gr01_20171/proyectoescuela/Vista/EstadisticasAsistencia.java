package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Controlador.ControladorServiciosGenerales;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class EstadisticasAsistencia extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    Grupo grupo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_asistencia);
        intent = getIntent();
        bundle = intent.getExtras();
        grupo = (Grupo) intent.getSerializableExtra("GRUPO");
    }

    public void clickResultadosGrupo(View view){
        intent = new Intent(this,InfoAsistenciaGrupo.class);
        //intent.putExtra("GRUPO",grupo);
        startActivity(intent);
    }
}
