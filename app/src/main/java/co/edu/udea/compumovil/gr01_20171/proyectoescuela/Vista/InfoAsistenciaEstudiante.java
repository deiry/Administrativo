package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Asistencia;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class InfoAsistenciaEstudiante extends AppCompatActivity {

    Estudiante estudiante;
    Intent intent;
    Bundle bundle;
    OperacionesBaseDeDatos datos ;
    ArrayList<Asistencia> asistencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_asistencia_estudiante);
        intent = getIntent();
        bundle = intent.getExtras();
        estudiante = (Estudiante) intent.getSerializableExtra("ESTUDIANTE");
        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        TextView txt_name_est = (TextView) findViewById(R.id.txt_name_est);
        ImageView imageView = (ImageView) findViewById(R.id.iv_ima_est);
        txt_name_est.setText(estudiante.getNombres());
        Uri uri = pathToUri(estudiante.getFoto());
        imageView = (ImageView)findViewById(R.id.imaEstA);
        EditText faltaset = (EditText) findViewById(R.id.et_nroFaltas);
        asistencias = retornaAsistencia(estudiante.getIdentificacion());
        faltaset.setText(contarFaltas(asistencias));
        EditText tarde = (EditText) findViewById(R.id.et_nroTarde);
        tarde.setText(contarTarde(asistencias));

        if (!uri.equals(Uri.EMPTY)){
            imageView.setImageURI(pathToUri(estudiante.getFoto()));
        }else{
            imageView.setImageResource(R.mipmap.ic_launcher);
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

    private int contarFaltas(ArrayList<Asistencia> asistencias){
        int count=0;
        for (int i=0;i<asistencias.size();i++){
            if(asistencias.get(i).getAsistencia().equals("faltó")){
                count++;
            }
        }
        return count;
    }

    private int contarTarde(ArrayList<Asistencia> asistencias){
        int count=0;
        for (int i=0;i<asistencias.size();i++){
            if(asistencias.get(i).getAsistencia().equals("tarde")){
                count++;
            }
        }
        return count;
    }

    private ArrayList<Asistencia> retornaAsistencia(int estId){
        ArrayList<Asistencia> asisten= new ArrayList<>();
        try {

            datos.getDb().beginTransaction();
            asisten= datos.obtenerAsistenciaEstudiante(Integer.toString(estId));
            datos.getDb().setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            datos.getDb().endTransaction();
        }
        return asisten;
    }




}

