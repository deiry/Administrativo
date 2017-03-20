package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Categoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Subcategoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class SeguimientoCognitivo extends Activity {

    private ArrayList<Estudiante> estudiantes;
    private Grupo grupo;
    private int tipoVista;
    private ArrayList<Categoria> categorias;
    private ArrayList<Subcategoria> subcategorias;
    private int idEstudiante;


    private OperacionesBaseDeDatos manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_cognitivo);

        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        grupo = (Grupo) getIntent().getSerializableExtra("GRUPO");
        tipoVista = (int) getIntent().getSerializableExtra("tipoVista");
        categorias = manager.obtenerCategorias(1);

    }


    private void crearGridView()
    {
        estudiantes = manager.obtenerEstudiantesDB(grupo);


        EstudianteAdapter adapter = new EstudianteAdapter(this, estudiantes);

        GridView gridEstudiante = (GridView) findViewById(R.id.grid_view_ubicacion);

        gridEstudiante.setAdapter(adapter);

        gridEstudiante.setNumColumns(4);

        gridEstudiante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idEstudiante = estudiantes.get(position).getIdentificacion();
                //Toast.makeText(SeguimientoCognitivo.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                if(tipoVista == 1){

                    Intent intent = new Intent(SeguimientoCognitivo.this, SegCogEstudiante.class);
                    intent.putExtra("id",estudiantes.get(position).getIdentificacion());
                    startActivity(intent);

                }else if(tipoVista == 2){
                    Intent intent = new Intent(SeguimientoCognitivo.this, EstadisticaModel.class);


             //       intent.putStringArrayListExtra("valX",listarCategorias(categorias));
                    intent.putStringArrayListExtra("valX",listarSubCategorias(categorias.get(1)));
                    intent.putExtra("valSI", asignarValoresSi(subcategorias));
                    intent.putExtra("valNo", asignarValoresNo(subcategorias));

                    startActivity(intent);

                }


            }
        });
    }

    @Override
    protected void onResume() {
        estudiantes = manager.obtenerEstudiantesDB(grupo);

        crearGridView();
        super.onResume();
    }

    public ArrayList<String> listarCategorias(ArrayList<Categoria> categorias){
        ArrayList<String> nombreCategorias = new ArrayList<>();
        for (int i=0;i< categorias.size();i++){
            nombreCategorias.add(categorias.get(i).getNombre());

        }
        return nombreCategorias;
    }

    public ArrayList<String> listarSubCategorias(Categoria cat){
        ArrayList<String> nombreSubcategoria = new ArrayList<>();
        subcategorias = manager.obtenerSubCategoriasFromCategoriaId(cat.getId());

        for (int i=0;i< subcategorias.size();i++){
            nombreSubcategoria.add(subcategorias.get(i).getNombre());

        }

        return  nombreSubcategoria;
    }

    public ArrayList<Integer> asignarValoresNo(ArrayList<Subcategoria> subcategorias){
        ArrayList<Integer> valsNo = new ArrayList<>();
     for (int i=0; i< subcategorias.size();i++){
         int valNo = (int)manager.countSeguimientoFromIdSubcategoriIdEstudiante(subcategorias.get(i).getId(),idEstudiante,"no");
         valsNo.add(valNo);
     }
     return valsNo;

    }

    public ArrayList<Integer> asignarValoresSi(ArrayList<Subcategoria> subcategorias){
       ArrayList<Integer> valsSi = new ArrayList<>();
        for (int i=0; i< subcategorias.size();i++){
            int x =idEstudiante;
            int y = subcategorias.get(i).getId();
            int valSi = (int)manager.countSeguimientoFromIdSubcategoriIdEstudiante(subcategorias.get(i).getId(),idEstudiante,"si");
            valsSi.add(valSi);
        }
        return valsSi;

    }

}
