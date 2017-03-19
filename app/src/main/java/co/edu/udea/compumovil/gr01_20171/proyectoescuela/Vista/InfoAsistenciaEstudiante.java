package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Asistencia;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class InfoAsistenciaEstudiante extends AppCompatActivity {

    Estudiante estudiante;
    ImageView imageView ;
    Intent intent;
    Bundle bundle;
    OperacionesBaseDeDatos datos ;
    ArrayList<Asistencia> asistencias;
    ArrayList<Asistencia> tardes;
    ArrayList<Asistencia> faltasGA;
    ArrayAdapter<String> adapterfaltas;
    ArrayList<String> fechasFaltas;
    ArrayList<String> fechasTarde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_asistencia_estudiante);
        intent = getIntent();
        bundle = intent.getExtras();
        estudiante = (Estudiante) intent.getSerializableExtra("ESTUDIANTE");

        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        asistencias = retornaAsistencia(estudiante.getIdentificacion());

        TextView txt_name_est = (TextView) findViewById(R.id.txt_name_est);
        txt_name_est.setText(estudiante.getNombres());

        Uri uri = pathToUri(estudiante.getFoto());
        imageView = (ImageView)findViewById(R.id.iv_ima_est);
        if (!uri.equals(Uri.EMPTY)){
            imageView.setImageURI(pathToUri(estudiante.getFoto()));
        }else{
            imageView.setImageResource(R.mipmap.ic_launcher);
        }

        fechasTarde = new ArrayList<>();
        fechasFaltas = new ArrayList<>();
        tardes = new ArrayList<>();
        faltasGA = new ArrayList<>();

        EditText faltaset = (EditText) findViewById(R.id.et_nroFaltas);
        faltaset.setText(String.valueOf(contarFaltas(asistencias)));

        EditText tarde = (EditText) findViewById(R.id.et_nroTarde);
        tarde.setText(String.valueOf(contarTarde(asistencias)));

        EditText faltasLM = (EditText)findViewById(R.id.et_faltasMes);
        faltasLM.setText(String.valueOf(faltasUltimoMes().size()));

        EditText tardeLM = (EditText) findViewById(R.id.et_tardeMes);
        tardeLM.setText(String.valueOf(llegadasTardeUltimoMes().size()));

        ListView lv_faltas = (ListView) findViewById(R.id.lv_faltas);
        ListView lv_tarde = (ListView) findViewById(R.id.lv_tarde);



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
                faltasGA.add(asistencias.get(i));
                fechasFaltas.add(asistencias.get(i).getFecha());
                count++;
            }
        }
        return count;
    }

    private int contarTarde(ArrayList<Asistencia> asistencias){
        int count=0;
        for (int i=0;i<asistencias.size();i++){
            if(asistencias.get(i).getAsistencia().equals("tarde")){
                tardes.add(asistencias.get(i));
                fechasTarde.add(asistencias.get(i).getFecha());
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

    private ArrayList<Asistencia> faltasUltimoMes(){
        Calendar cal = Calendar.getInstance();
        ArrayList<Asistencia> faltasUltimomes = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String mesActual= sdf.format(cal.getTime());
        String mesFecha;
        String fecha="";
        for (int i=0; i<faltasGA.size();i++) {
            fecha = faltasGA.get(i).getFecha();
            mesFecha = ""+fecha.charAt(3) + fecha.charAt(4);
            if(mesFecha.equals(mesActual)){
                faltasUltimomes.add(faltasGA.get(i));
            }
        }
        return faltasUltimomes;
    }

    private ArrayList<Asistencia> llegadasTardeUltimoMes(){
        Calendar cal = Calendar.getInstance();
        ArrayList<Asistencia> llegadasTarde = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String mesActual= sdf.format(cal.getTime());
        String mesFecha="";
        String fecha="";
        for (int i=0; i<tardes.size();i++) {
            fecha = tardes.get(i).getFecha();
            mesFecha = ""+fecha.charAt(3)+fecha.charAt(4);
            if(mesFecha.equals(mesActual)){
                llegadasTarde.add(tardes.get(i));
            }
        }
        return llegadasTarde;
    }


}

