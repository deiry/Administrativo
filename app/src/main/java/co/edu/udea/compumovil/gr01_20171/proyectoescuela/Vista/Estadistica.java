package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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

        bar_grafico = (BarChart) findViewById(R.id.barGrafico);
        bar_grafico.getDescription().setEnabled(false);
        bar_grafico.setVisibleXRange(6,6);
       // ArrayList<Categoria> categorias = manager.obtenerCategorias(1);

        //ArrayList<String> categorias = new ArrayList<>();
        //categorias.add("Recordar");
        //categorias.add("Comprender");
        //categorias.add("Aplicar");
        //categorias.add("Analizar");
        //categorias.add("Evaluar");
        //categorias.add("Crear");

        //String[] mValores = {"Recordar","Comprender","Aplicar","Analizar", "Evaluar", "Crear"};


        ArrayList<BarEntry> barEntradas = new ArrayList<>();
        barEntradas.add(new BarEntry(2f,80,"a"));
        barEntradas.add(new BarEntry(5f,40,"b"));
        barEntradas.add(new BarEntry(8f,20,"c"));
        barEntradas.add(new BarEntry(11f,10,"d"));
        barEntradas.add(new BarEntry(14f,40,"e"));
        barEntradas.add(new BarEntry(17f,20,"f"));

      /*  IAxisValueFormatter formatter = new DefaultAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis){
             //   return barEntradas.get((int)value).getPlayerName();
                return "hola";
            }
            @Override
            public int getDecimalDigits(){return 0;}
        };*/
       // ArrayList<BarEntry> barEntradasDos = new ArrayList<>();
        //barEntradasDos.add(new BarEntry(1f,30));
        //barEntradasDos.add(new BarEntry(4f,30));
        //barEntradasDos.add(new BarEntry(7f,20));
        //barEntradasDos.add(new BarEntry(10f,40));
        //barEntradasDos.add(new BarEntry(13f,50));
        //barEntradasDos.add(new BarEntry(16f,70));




        BarDataSet bar_categorias1 = new BarDataSet(barEntradas,"SI");
        bar_categorias1.setColor(Color.rgb(71,255,10));
        //BarDataSet bar_categorias2 = new BarDataSet(barEntradasDos,"NO");
        //bar_categorias2.setColor(Color.rgb(255,10,71));

        //String[] cat = {"hola","hola2","hola3","hola4","hola5","hola6"};
        //final HashMap<Integer,String>numMap = new HashMap<>();
        //numMap.put(1,"Recordar");
        //numMap.put(2,"Comprender");
        //numMap.put(3,"Aplicar");
        //numMap.put(4,"Analizar");
        //numMap.put(5,"Evaluar");
        //numMap.put(6,"Crear");
        //IAxisValueFormatter xAxisFormatter = new MiValorFormato (numMap);

        //XAxis xAxis = bar_grafico.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        //xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


       // xAxis.setEnabled(false);
        //xAxis.setDrawGridLines(false);
        //xAxis.setGranularityEnabled(true);
        //xAxis.setGranularity(0.5f); // only intervals of 1 day
        //xAxis.setValueFormatter(new MiValorFormato(numMap));

        BarData bar_datos = new BarData();
        bar_datos.addDataSet(bar_categorias1);
        //bar_datos.addDataSet(bar_categorias2);

        bar_grafico.setData(bar_datos);


        bar_grafico.setTouchEnabled(true);
        bar_grafico.setDragEnabled(true);
        bar_grafico.setScaleEnabled(true);
    }



    }

