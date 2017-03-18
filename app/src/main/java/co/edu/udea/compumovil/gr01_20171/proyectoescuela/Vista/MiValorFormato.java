package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Context;
import android.widget.Toast;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.HashMap;

/**
 * Created by DEIRY on 18/03/2017.
 */

public class MiValorFormato implements IAxisValueFormatter {
    private HashMap mValores;
    private int key;
private Context context;
    public MiValorFormato(HashMap values) {

        this.mValores = values;
        key = 0;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        key++;
        if(key<=mValores.size()) {
            return (String) mValores.get(key);
        }
        key=1;
        return (String) mValores.get(key);

    }
}
