package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class DialogAgregarGrupos extends AppCompatActivity {

    OperacionesBaseDeDatos datos;
    Button guardarGp,finalizar;
    EditText grado,ngrupo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_agregar_grupos);
        guardarGp = (Button)findViewById(R.id.btn_agregarGrupo);
        finalizar=(Button)findViewById(R.id.btn_finalizar_grupos);
        grado = (EditText)findViewById(R.id.numGrado);
        ngrupo = (EditText)findViewById(R.id.idenGrupo);



       getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());


        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public  void clickGuardarGrupo(View v){
        if (!grado.getText().toString().isEmpty() && !ngrupo.getText().toString().isEmpty()) {
            try{
                datos.getDb().beginTransaction();

                Grupo grupo = new Grupo(Integer.parseInt(grado.getText().toString()),ngrupo.getText().toString());
                datos.insertarGrupo(grupo);
                datos.getDb().setTransactionSuccessful();
                Toast.makeText(getApplicationContext(),"Grupo agregado",Toast.LENGTH_SHORT).show();
                grado.setText("");
                ngrupo.setText("");

            }

            catch(Exception e){e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Se ha producido un error",Toast.LENGTH_SHORT).show();}
            finally{
                datos.getDb().endTransaction();

            }

        }else{
            Toast.makeText(getApplicationContext(),"Llene todos los campos",Toast.LENGTH_SHORT).show();
        }

    }




}
