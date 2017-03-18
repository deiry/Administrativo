package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PantallaConfiguracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_configuracion);

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
                Intent vistaPrueba = new Intent(PantallaConfiguracion.this,ListarEstudiantes.class);
                startActivity(vistaPrueba);
            }
        });
    }
}
