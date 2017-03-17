package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Categoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

/**
 * Created by DEIRY on 17/03/2017.
 */

public class Estadistica extends Activity {

    private OperacionesBaseDeDatos manager;
    private BarChart bar_grafico;
    private BarDataSet bar_categorias;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);

    }

    private void init(){

        manager = OperacionesBaseDeDatos.obtenerInstancia(this.getApplicationContext());
        bar_grafico = (BarChart) findViewById(R.id.estadisticaBarra);


        ArrayList<Categoria> categorias = manager.obtenerCategorias();
        ArrayList<BarEntry> barEnradas = new ArrayList<>();
        ArrayList<String> nombreDato = new ArrayList<>();
        for (int i =0; i<8; i++){
            barEnradas.add(new BarEntry(33f,i));
            nombreDato.add(categorias.get(i).getNombre());
        }
        bar_categorias = new BarDataSet(barEnradas,"Categorias");

        BarData bar_datos = new BarData();
        bar_datos.addDataSet(bar_categorias);
        bar_grafico.setData(bar_datos);

        bar_grafico.setTouchEnabled(true);
        bar_grafico.setDragEnabled(true);
        bar_grafico.setScaleEnabled(true);
    }
}
