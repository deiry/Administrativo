package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
import java.util.List;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class EstadisticaModel extends AppCompatActivity {
    private BarChart chart;
    float barWidth;
    float barSpace;
    float groupSpace;
    List<String> valX;
    List<Integer> valSI, valNo;

    public List getValSI() {
        return valSI;
    }

    public void setValSI(List valSI) {
        this.valSI = valSI;
    }

    public List getValNo() {
        return valNo;
    }

    public void setValNo(List valNo) {
        this.valNo = valNo;
    }

    public List getValX() {
        return valX;
    }

    public void setValX(ArrayList<String> valX) {
        this.valX = valX;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_model);
        valX = (ArrayList<String>) getIntent().getSerializableExtra("valX");
        valSI =(List)getIntent().getSerializableExtra("valSI");
        valNo =(List)getIntent().getSerializableExtra("valNo");
        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;
        chart = (BarChart)findViewById(R.id.barChart);
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        int groupCount = 6;

  /*      ArrayList xVals = new ArrayList();

        xVals.add("Recordar");
        xVals.add("Comprender");
        xVals.add("Aplicar");
        xVals.add("Analizar");
        xVals.add("Evaluar");
        xVals.add("Crear");
*/
        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        for(int i=1;i<=valX.size();i++){
            yVals1.add(new BarEntry(i, (float)valSI.get(i-1)));
            yVals2.add(new BarEntry(i, (float)valNo.get(i-1)));

        }

        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "SI");
        set1.setColor(Color.GREEN);
        set2 = new BarDataSet(yVals2, "NO");
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
        xAxis.setAxisMaximum(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(valX));
//Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);
    }



}