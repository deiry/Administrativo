package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

/**
 * Created by Estefany on 08/03/2017.
 */

public class PantallaProfesor extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_profesor);
        //modifica el custom dialog a mostrar mediante el boton
        Button agregarGrup = (Button) findViewById(R.id.btn_agregarGrupo);
        agregarGrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the custom
                showDialog();
            }
        });
    }

    private void showDialog() {
        final Dialog addGrupo = new Dialog(this);
        //set title
        addGrupo.setTitle("Agregar grupo");
        //inflate layout
        //addGrupo.setContentView(R.layout.activity_agregar_grupo_alert);

        // Button guardar = (Button)addGrupo.findViewBy(R.id.btn_guardarGrupo);    }
        addGrupo.show();
    }

}
