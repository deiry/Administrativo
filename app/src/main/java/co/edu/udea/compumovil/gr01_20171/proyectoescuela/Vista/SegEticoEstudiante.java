package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Categoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Materia;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Subcategoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

import static co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.ListarEstudiantes.estudiantes;

public class SegEticoEstudiante extends Activity {

    private ArrayList<Categoria> categorias;
    private ArrayList<Subcategoria> subcategorias;

    private Estudiante estudiante;
    private OperacionesBaseDeDatos manager;

    private ImageView iv_foto;
    private TextView tv_nombre;
    private TextView tv_apellido;
    private TextView tv_identificacion;

    private String[] nombreCategorias;
    private String[] nombreSubcategorias;
    private int[] contadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seg_etico_estudiante);
        int id = getIntent().getIntExtra("id",0);
        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        estudiante = manager.obtenerEstudiante(id);
        llenarDatosEstudiante();
        llenarGridViewCategorias();
        llenarGridViewSubcategorias();

    }

    @Override
    protected void onResume() {
        llenarGridViewCategorias();
        llenarGridViewSubcategorias();
        super.onResume();
    }

    private void llenarGridViewCategorias() {
        categorias = manager.obtenerCategorias(2);
        if(categorias == null){
           return;
        }
        nombreCategorias = new String[categorias.size()];

        int size=categorias.size();
        for(int x=0;x<size;x++) {
            nombreCategorias[x]= categorias.get(x).getNombre();
        }


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreCategorias);
        GridView gridEstudiante = (GridView) findViewById(R.id.grid_view_categorias);
        gridEstudiante.setAdapter(adaptador);
        gridEstudiante.setNumColumns(nombreCategorias.length);

    }


    private  void llenarGridViewSubcategorias(){
        ArrayList<Subcategoria> todasSubcategorias = new ArrayList<Subcategoria>();
        categorias = manager.obtenerCategorias(2);
        if(categorias == null){
            return;
        }
        nombreCategorias = new String[categorias.size()];

        int size=categorias.size();
        for(int x=0;x<size;x++) {
            nombreCategorias[x]= categorias.get(x).getNombre();
        }
        for(int x=0;x<size;x++) {
            subcategorias = manager.obtenerSubCategoriasFromCategoriaId(categorias.get(x).getId());
            for (int y=0;y<subcategorias.size();y++){
                todasSubcategorias.add(subcategorias.get(y));
            }
        }

        CategoriaAdapter adaptador = new CategoriaAdapter(this, todasSubcategorias,categorias.get(0));

        final GridView gridEstudiante = (GridView) findViewById(R.id.grid_view_subcategorias);
        gridEstudiante.setAdapter(adaptador);
        gridEstudiante.setNumColumns(nombreCategorias.length);

        /*gridEstudiante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"El estudiante ha realizado "+nombreSubcategorias[position],Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void llenarDatosEstudiante(){

        tv_nombre = (TextView) findViewById(R.id.nombre_estudiante_etico);
        tv_apellido = (TextView) findViewById(R.id.apellido_estudiante_etico);
        tv_identificacion = (TextView) findViewById(R.id.identificacion_estudiante_etico);
        iv_foto = (ImageView) findViewById(R.id.foto_estudiante_etico);

        tv_nombre.setText(estudiante.getNombres());
        tv_apellido.setText(estudiante.getApellidos());
        tv_identificacion.setText(Integer.toString(estudiante.getIdentificacion()));
        Uri uri = pathToUri(estudiante.getFoto());
        if (!uri.equals(Uri.EMPTY))
        {
            iv_foto.setImageURI(pathToUri(estudiante.getFoto()));
        }
        else
        {
            iv_foto.setImageResource(R.mipmap.ic_launcher);
        }
    }

    private Uri pathToUri(String imgPath){
        File imgFile = new File(imgPath);
        if(imgFile.exists())
        {
            return Uri.fromFile(imgFile);

        }
        return Uri.EMPTY;
    }

    public void clickIrAgregarCategoria(View view){
        Intent intent = new Intent(this,AgregarCategoriaEtico.class);
        startActivity(intent);
    }

    public void clickIrAgregarSubcategoria(View view){
        Intent intent = new Intent(this,AgregarSubcategoriaEtico.class);
        startActivity(intent);
    }
}
