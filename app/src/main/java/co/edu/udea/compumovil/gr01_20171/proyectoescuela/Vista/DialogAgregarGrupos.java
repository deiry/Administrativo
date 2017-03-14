package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class DialogAgregarGrupos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_agregar_grupos);
        FloatingActionButton subirE = (FloatingActionButton)findViewById(R.id.btn_subirEstudiantes);
        subirE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(DialogAgregarGrupos.this,AgregarEstudiantes.class);
                startActivity(ingresar);
            }
        });
    }
}
