package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

/**
 * Created by ACER on 17/03/2017.
 */

public class AsistenciaEstudianteAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Estudiante> estudiantes;

    /*variable temporales*/
    private String[] nombres;
    private String[] apellidos;
    private String[] fotos;
    public AsistenciaEstudianteAdapter(Context context, ArrayList<Estudiante> estudiantes)
    {
        //public EstudianteAdapter(Context context, String[] nombres, String[] apellidos) {
        this.context = context;
        this.estudiantes = estudiantes;
        /*this.nombres = nombres;
        this.apellidos = apellidos;*/
    }
    @Override
    public int getCount() {
        //return nombres.length;
        return estudiantes.size();
    }

    @Override
    public Object getItem(int position) {
        //return nombres[position];
        return estudiantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return nombres[position].hashCode();
        return ((long) estudiantes.get(position).getIdentificacion());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_estudiante,parent,false);
        }

        TextView tvNombre = (TextView) convertView.findViewById(R.id.tv_item_estudiante_nombre);
        TextView tvApellido = (TextView) convertView.findViewById(R.id.tv_item_estudiante_apellido);

        //tvNombre.setText(this.nombres[position]);
        //tvApellido.setText(this.apellidos[position]);

        tvNombre.setText(this.estudiantes.get(position).getNombres());
        tvApellido.setText(this.estudiantes.get(position).getApellidos());

        return convertView;
    }
}
