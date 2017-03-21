package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Categoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Materia;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Subcategoria;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class SeguimientoCognitivo extends Activity {

    private ArrayList<Estudiante> estudiantes;
    private Grupo grupo;
    private int tipoVista;
    private ArrayList<Categoria> categorias;
    private ArrayList<Subcategoria> subcategorias;
    private int idEstudiante;
    private Intent intent;


    private OperacionesBaseDeDatos manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_cognitivo);

        manager = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        grupo = (Grupo) getIntent().getSerializableExtra("GRUPO");
        tipoVista = (int) getIntent().getSerializableExtra("tipoVista");
        //El 1 represente el valor de tipo cognitivo
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

                    intent = new Intent(SeguimientoCognitivo.this, SegCogEstudiante.class);
                    intent.putExtra("id",estudiantes.get(position).getIdentificacion());
                    startActivity(intent);

                }else if(tipoVista == 2){
                    AlertDialog dialog = listarOpcionesMatGral();
                    dialog.show();
                   /* intent = new Intent(SeguimientoCognitivo.this, EstadisticaModel.class);


             //       intent.putStringArrayListExtra("valX",listarCategorias(categorias));
                    intent.putStringArrayListExtra("valX",listarSubCategorias(categorias.get(1)));
                    intent.putExtra("valSI", asignarValoresSi(subcategorias));
                    intent.putExtra("valNo", asignarValoresNo(subcategorias));

                    startActivity(intent);*/

                }


            }
        });
    }

    public AlertDialog listarOpcionesMatGral(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayList<Materia> materias =  manager.obtenerMaterias();
        final CharSequence[] items = new CharSequence[materias.size()];


            for (int i=0; i<items.length;i++){
                items[i] = materias.get(i).getNombre();
            }



        intent = new Intent(this, EstadisticaModel.class);
        builder.setTitle("Seleccione una opción").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
           /*     intent.putStringArrayListExtra("valX",listarCategorias(categorias));
                intent.putStringArrayListExtra("valX",listarSubCategorias(categorias.get(1)));
                intent.putExtra("valSi", asignarValoresSi(subcategorias));
                intent.putExtra("valNo", asignarValoresNo(subcategorias));*/

                intent.putStringArrayListExtra("valX", listarCategorias(categorias));
                intent.putExtra("valSI",asignarCategoriaSI(categorias));
                intent.putExtra("valNo", asignarCategoriaNO(categorias));
                startActivity(intent);
            }
        });

        return builder.create();


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

    /**
     *
     * @param cat
     * @return
     */
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
         subcategorias.get(i).setValorNo(valNo);
         valsNo.add(valNo);
     }
     return valsNo;

    }

    public ArrayList<Integer> asignarValoresSi(ArrayList<Subcategoria> subcategorias){
       ArrayList<Integer> valsSi = new ArrayList<>();
        for (int i=0; i< subcategorias.size();i++){
            int valSi = (int)manager.countSeguimientoFromIdSubcategoriIdEstudiante(subcategorias.get(i).getId(),idEstudiante,"si");
            subcategorias.get(i).setValorSi(valSi);
            valsSi.add(valSi);
        }
        return valsSi;

    }



    public int asignarSubcategoriaSI(ArrayList<Subcategoria> subcategorias){

        int gano=0;
        for (int i=0; i< subcategorias.size();i++){
            int si=subcategorias.get(i).getValorSi();
            int no=subcategorias.get(i).getValorNo();
            if (si>=no){
                gano++;
            }
        }

        return gano;
    }

    public int asignarSubcategoriaNO(ArrayList<Subcategoria> subcategorias){

        int perdio=0;
        for (int i=0; i< subcategorias.size();i++){
            int si=subcategorias.get(i).getValorSi();
            int no=subcategorias.get(i).getValorNo();

            if (no>si){
                perdio++;
            }
        }

        return perdio;
    }
    public ArrayList<Integer> asignarCategoriaSI(ArrayList<Categoria> categorias){
        ArrayList<Integer> estadoSi = new ArrayList<>();

        for (int i=0; i<categorias.size();i++){
            subcategorias = manager.obtenerSubCategoriasFromCategoriaId(categorias.get(i).getId());

            estadoSi.add(asignarSubcategoriaSI(subcategorias));
        }

        return estadoSi;
    }

    public ArrayList<Integer> asignarCategoriaNO(ArrayList<Categoria> categorias){
        ArrayList<Integer> estadoNo = new ArrayList<>();
        ArrayList<Subcategoria> subcategorias;
        for (int i=0; i<categorias.size();i++){
            subcategorias = manager.obtenerSubCategoriasFromCategoriaId(categorias.get(i).getId());

            estadoNo.add(asignarSubcategoriaNO(subcategorias));
        }

        return estadoNo;
    }


}
