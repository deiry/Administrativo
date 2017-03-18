package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Categoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Subcategoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class SegCogEstudiante extends Activity {

    private ListView lv_aplicar;
    private ListView lv_analizar;
    private ListView lv_comprender;
    private ListView lv_crear;
    private ListView lv_recordar;
    private ListView lv_evaluar;

    private ImageView iv_foto;
    private TextView tv_nombre_apellido;

    private Estudiante estudiante;
    private OperacionesBaseDeDatos manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seg_cog_estudiante_activity);
        int id = getIntent().getIntExtra("id",0);
        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        estudiante = manager.obtenerEstudiante(id);

        incializarComponente();

    }

    private void incializarComponente() {


       String[] datos = {"1","2","3","4","5","1","2","3","4","5","1","2","3","4","5"};

        lv_aplicar  = (ListView) findViewById(R.id.lv_aplicar);
        lv_analizar  = (ListView) findViewById(R.id.lv_analizar);
        lv_comprender  = (ListView) findViewById(R.id.lv_compreder);
        lv_crear  = (ListView) findViewById(R.id.lv_crear);
        lv_recordar  = (ListView) findViewById(R.id.lv_recordar);
        lv_evaluar  = (ListView) findViewById(R.id.lv_evaluar);

        iv_foto = (ImageView) findViewById(R.id.iv_seg_cog_foto_estudiante);
        tv_nombre_apellido = (TextView) findViewById(R.id.tv_seg_cog_nombre_estudiante);

        crearListView(getResources().getString(R.string.aplicar),R.id.contenedor_seg_cog_aplicar,lv_aplicar);


        tv_nombre_apellido.setText(estudiante.getNombres()+" "+ estudiante.getApellidos());
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


    private void setDataListView(ListView lv,String[] data)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,data);

        lv.setAdapter(adapter);
    }

    /*eventos de agregar subcategoria*/
    public void clickAgregarSubAplicar(View view)
    {
        OpenDialogSubCategorias(getResources().getString(R.string.aplicar));
    }


    public void clickAgregarSubAnalizar(View view)
    {
        OpenDialogSubCategorias(getResources().getString(R.string.analizar));
    }

    public void clickAgregarSubComprender(View view)
    {
        OpenDialogSubCategorias(getResources().getString(R.string.comprender));
    }

    public void clickAgregarSubCrear(View view)
    {
        OpenDialogSubCategorias(getResources().getString(R.string.crear));
    }

    public void clickAgregarSubRecordar(View view)
    {
        OpenDialogSubCategorias(getResources().getString(R.string.recordar));
    }

    public void clickAgregarSubEvaluar(View view)
    {
        OpenDialogSubCategorias(getResources().getString(R.string.evaluar));
    }

    private Uri pathToUri(String imgPath){
        File imgFile = new File(imgPath);
        if(imgFile.exists())
        {
            return Uri.fromFile(imgFile);

        }
        return Uri.EMPTY;
    }

    /**
     *
     * @param nombreCategoria
     */
    private void OpenDialogSubCategorias(String nombreCategoria) {
        //obtenemos el id de la categoria
        Categoria categoria = manager.obtenerCategoria(1,nombreCategoria);
        int id = categoria.getId();

        //creamos los argumentos y le inyectamos el id de la categoria para enviarla al dialog
        Bundle args = new Bundle();
        args.putInt("idCategoria",id);
        args.putString("titulo",nombreCategoria);

        FragmentManager fm = getFragmentManager();
        DialogFragment dialogFragment = new DialogSubCategoria();
        dialogFragment.setArguments(args);
        dialogFragment.show(fm,"dialogAplicar");
    }

    private void crearListView(String nombreCategoria, int idContenedor, ListView lv)
    {
        Categoria categoria = manager.obtenerCategoria(1,nombreCategoria);
        int id = categoria.getId();
        ArrayList<Subcategoria> subcategorias = manager.obtenerSubCategoriasFromCategoriaId(id);

        SubCategoriaAdapter adapter = new SubCategoriaAdapter(this, subcategorias);

        lv.setAdapter(adapter);
    }



}
