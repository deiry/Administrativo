package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.ListaMetas;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PrincipalMetas extends AppCompatActivity {

    TextView t1,t2,t3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas);


        t1 = (TextView)findViewById(R.id.id);
        t2 = (TextView)findViewById(R.id.nombre);
        t3 = (TextView)findViewById(R.id.tipo);
    }

    public void ejecutarPrueba(View vista) {
        ListaMetas m1 = new ListaMetas(3,"Meta3","Comp");
        OperacionesBaseDeDatos op = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        op.getDb().beginTransaction();
        op.agregarMeta(m1);
        ArrayList<ListaMetas> lista = op.listarMetas();
        ListaMetas m2 = lista.get(1);
        op.getDb().setTransactionSuccessful();
        op.getDb().endTransaction();;
        t1.setText(Integer.toString(m2.getId()));
        t2.setText(m2.getNombre());
        t3.setText(m2.getTipo());
    }
}
