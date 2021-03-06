package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.vistasMetas;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.ManejaBDMetas;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.CumplimientoMeta;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Meta;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class Estadistica extends Activity{

    private BarChart chart;
    float barWidth;
    float barSpace;
    float groupSpace;
    Bundle bundle;
    private Intent intencion;
    ArrayList<CumplimientoMeta> meta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_estadistica);
        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;
        intencion = getIntent();
        bundle = intencion.getExtras();
        int idMeta = bundle.getInt("IDMETA");

        chart = (BarChart)findViewById(R.id.barChart);
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);



        ArrayList xVals = new ArrayList();

        ArrayList<Meta> metaPorEstudiante = ManejaBDMetas.retornarDatos(OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext()),2,idMeta);
        //recuperar estudiantes que tengan idMetaporestudiante
        int groupCount = metaPorEstudiante.size();
        ArrayList<ArrayList<CumplimientoMeta>> listarCumplimiento = new ArrayList<>();
        meta = ManejaBDMetas.recuperaCumplimientos(OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext()),idMeta);

        for (int i = 0; i < groupCount; i++) {
            listarCumplimiento.add(ManejaBDMetas.recuperaCumplimientos(OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext()),metaPorEstudiante.get(i).getId()));
        }

        for (int i = 0; i < listarCumplimiento.size(); i++) {

        }

        groupCount = 12;

        xVals.add("Andres");
        xVals.add("Juan");
        xVals.add("Mar");
        xVals.add("Apr");
        xVals.add("May");
        xVals.add("Jun");

        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        yVals1.add(new BarEntry(1, (float) 1));
        yVals2.add(new BarEntry(1, (float) 2));
        yVals1.add(new BarEntry(2, (float) 3));
        yVals2.add(new BarEntry(2, (float) 4));
        yVals1.add(new BarEntry(3, (float) 5));
        yVals2.add(new BarEntry(3, (float) 6));
        yVals1.add(new BarEntry(4, (float) 7));
        yVals2.add(new BarEntry(4, (float) 8));
        yVals1.add(new BarEntry(5, (float) 9));
        yVals2.add(new BarEntry(5, (float) 10));
        yVals1.add(new BarEntry(6, (float) 11));
        yVals2.add(new BarEntry(6, (float) 12));

        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "A");
        set1.setColor(Color.GREEN);
        set2 = new BarDataSet(yVals2, "B");
        set2.setColor(Color.RED);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        //X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
//Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);
    }
}
