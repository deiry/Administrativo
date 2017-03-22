package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Categoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Subcategoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class CategoriaAdapter extends BaseAdapter {

    private  Context context;
    private ArrayList<Subcategoria> subcategorias;
    private ArrayList<Subcategoria> subcategoriasEnCategoria;
    private ArrayList<Categoria> categorias;
    private Subcategoria subcategoria;
    private Categoria categoria;
    private OperacionesBaseDeDatos manager;
    private ListView lv_subcategorias_etico;
    private String[] nombreSubcategorias;
    private TextView nombreCategoria;
    private TextView nombreSubcategoria;
    private Button cantidad;


    public CategoriaAdapter(Context context, ArrayList<Subcategoria> subcategorias, Categoria categoria)
    {
        this.context = context;
        this.subcategorias = subcategorias;
        this.categoria = categoria;

    }

    @Override
    public int getCount()
    {
        return subcategorias.size();
    }

    @Override
    public Object getItem(int position) {
        return subcategorias.get(position);
    }

    @Override
    public long getItemId(int i) { return subcategorias.get(i).getId(); }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_categoria_adapter,parent,false);
        }

        nombreCategoria = (TextView) convertView.findViewById(R.id.subCategoria_nombre_categoria);
        nombreSubcategoria = (TextView) convertView.findViewById(R.id.Subcategoria_nombre_subcategoria);
        cantidad = (Button) convertView.findViewById(R.id.btn_cantidad_subcategoria);

        nombreCategoria.setText(categoria.getNombre());
        nombreSubcategoria.setText(subcategorias.get(position).getNombre());
        cantidad.setText("");


        return convertView;
    }

    private void clicbtnCantidad(View view){
        cantidad = (Button) view.findViewById(R.id.btn_cantidad_subcategoria);

        cantidad.setText(cantidad.getText().toString()+"*");
    }


}
