package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.ContratoEscuela;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Asistencia;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class InfoAsistenciaGrupo extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    Grupo grupo;
    OperacionesBaseDeDatos datos;
    EditText faltasMes;
    EditText diaMayorFaltas;
    EditText tardesMes;
    EditText diaMayorTarde;
    EditText cantidadEstTarde;
    EditText cantidadEstFalta;
    ArrayList<Estudiante> estudiantes;
    ArrayList<Asistencia>faltasA;
    ArrayList<Asistencia> faltasmes;
    ArrayList<Asistencia>tardes;
    ArrayList<Asistencia> tardeMes;
    ArrayList<Asistencia> asistencia;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_asistencia_grupo);
        getApplicationContext().deleteDatabase("pedidos.db");
        datos = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        intent = getIntent();
        bundle = intent.getExtras();
        grupo = (Grupo) intent.getSerializableExtra("GRUPO");

        estudiantes = new ArrayList<>();
        faltasA= new ArrayList<>();
        faltasmes= new ArrayList<>();
        tardes= new ArrayList<>();
        tardeMes= new ArrayList<>();
        asistencia= new ArrayList<>();
        faltasMes= (EditText)findViewById(R.id.falta_utm_mes);
        diaMayorFaltas=(EditText)findViewById(R.id.dia_mayor_faltas);
        tardesMes=(EditText)findViewById(R.id.llegadas_tarde_mes);
        diaMayorTarde=(EditText)findViewById(R.id.dia_llegadas_tarde);
        cantidadEstFalta=(EditText)findViewById(R.id.cant_est_fmes);
        cantidadEstTarde=(EditText)findViewById(R.id.cant_est_tmes);
        asistencia=obtenerAsistenciaGrupo();
       llenar();




    }
    private  ArrayList<Estudiante> retornaEstudiantes(Grupo grupo){
        try {
            datos.getDb().beginTransaction();
            estudiantes = datos.obtenerEstudiantesDB(grupo);
            datos.getDb().setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            datos.getDb().endTransaction();
        }
        return estudiantes;
    }
   public void llenar(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        String fecha= sdf.format(cal.getTime());

            faltasA = faltas(obtenerAsistenciaGrupo());
            faltasmes = faltasUltimoMes(faltasA);
            faltasMes.setText(Integer.toString(faltasmes.size()));
          if(faltasmes.size()!=0) {
               if(diaMayorFaltas(faltasmes)==0){
                   diaMayorFaltas.setText("Hay varios dias");
               }else {
                   diaMayorFaltas.setText(Integer.toString(diaMayorFaltas(faltasmes)) + "/" + fecha);
               }
            }else{
                diaMayorFaltas.setText("No hay");
            }
            tardes = llegadasTarde(obtenerAsistenciaGrupo());
            tardeMes = llegadasTardeUltimoMes(tardes);
            tardesMes.setText(Integer.toString(tardeMes.size()));
           if(tardeMes.size()!=0) {
                if(diaMayorLlegadas(tardeMes)==0){
                    diaMayorTarde.setText("Hay varios días");
                }else {
                    diaMayorTarde.setText(Integer.toString(diaMayorLlegadas(tardeMes)) + "/" + fecha);
                }
            }else{
                diaMayorTarde.setText("No hay");
            }
            cantidadEstFalta.setText(Integer.toString(cantidadEstudiantesFalta()));
            cantidadEstTarde.setText(Integer.toString(cantidadEstudiantesTarde()));

    }


    public ArrayList<Asistencia> retornaAsistencia(int estId){
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

   public ArrayList<Asistencia> obtenerAsistenciaGrupo(){
       ArrayList<Asistencia>asistenciaGrupo = new ArrayList<>();
       estudiantes = retornaEstudiantes(grupo);
            for(int i = 0;i<estudiantes.size();i++) {
            Estudiante estudiante =estudiantes.get(i);
            ArrayList<Asistencia> asistenciaE= retornaAsistencia(estudiante.getIdentificacion());
            if(asistenciaE.size()!=0){
                for (int j=0;j<asistenciaE.size();j++){
                    Asistencia asist = asistenciaE.get(j);
                    asistenciaGrupo.add(asist);
                }
            }
        }
        return asistenciaGrupo;
    }



 //Arroja todas las faltas del array de asistencia

    public ArrayList<Asistencia> faltas(ArrayList<Asistencia> asistencia){

        ArrayList<Asistencia> faltas = new ArrayList<>();
        for(int i=0; i<asistencia.size();i++){
            Asistencia a=asistencia.get(i);
            if(a.getAsistencia().equals("faltó")){
                faltas.add(a);
            }
        }
        return faltas;
    }
    //Arroja todas las llegadas tarde del array de asistencia
    public ArrayList<Asistencia> llegadasTarde(ArrayList<Asistencia> asistencia){

        ArrayList<Asistencia> llegadasTardes= new ArrayList<>();
        for(int i=0; i<asistencia.size();i++){
           Asistencia a=asistencia.get(i);
            if(a.getAsistencia().equals("tarde")){
                llegadasTardes.add(a);
            }
        }
        return llegadasTardes;
    }

    //Arroja todas las falta del mes actual
    public ArrayList<Asistencia> faltasUltimoMes(ArrayList<Asistencia> asistenciaFaltas){
        ArrayList<Asistencia> faltasUltimomes=new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String mesActual= sdf.format(cal.getTime());
        String mesFecha;
        String fecha="";
        for (int i=0; i<asistenciaFaltas.size();i++) {
            fecha = asistenciaFaltas.get(i).getFecha();
            mesFecha = ""+fecha.charAt(3) + fecha.charAt(4);
            if(mesFecha.equals(mesActual)){
                faltasUltimomes.add(asistenciaFaltas.get(i));
            }
        }
        return faltasUltimomes;
    }
    //Le pasa por parámetro lo que arrojó el metodo llegadas tarde
    public  ArrayList<Asistencia> llegadasTardeUltimoMes(ArrayList<Asistencia> asisLlegadasTarde){
        Calendar cal = Calendar.getInstance();
        ArrayList<Asistencia> llegadasTarde=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String mesActual= sdf.format(cal.getTime());
        String mesFecha="";
        String fecha="";
        for (int i=0; i<asisLlegadasTarde.size();i++) {
            fecha = asisLlegadasTarde.get(i).getFecha();
            mesFecha = ""+fecha.charAt(3)+fecha.charAt(4);
            if(mesFecha.equals(mesActual)){
                llegadasTarde.add(asisLlegadasTarde.get(i));
            }
        }
        return llegadasTarde;
    }
    //el array del parametro es el de faltas ultimo mes
    public  int diaMayorFaltas(ArrayList<Asistencia> faltas) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String dia = sdf.format(cal.getTime());

            int[] faltasdias = new int[Integer.parseInt(dia)];
            for (int i = 0; i < faltasdias.length; i++) {
                faltasdias[i] = 0;
            }
            for (int i = 0; i < faltas.size(); i++) {
                Asistencia a = faltas.get(i);
                char b = a.getFecha().charAt(0);
                char c = a.getFecha().charAt(1);
                String diaFalta = "" + b + c;
                faltasdias[Integer.parseInt(diaFalta) - 1] += 1;
            }
            int mayor = 0;
            int indice = 0;
            for (int i = 0; i < faltasdias.length; i++) {
                if (mayor < faltasdias[i]) {
                    mayor = faltasdias[i];
                    indice = i;
                }
            }
        int count=0;
        for (int i=0;i<faltasdias.length;i++) {

            if (mayor ==faltasdias[i]&& i!=indice){
                count+=1;
            }
        }
        if(count!=0){
            return 0;
        }else{
            return indice+1;
        }

    }

        public  int diaMayorLlegadas(ArrayList<Asistencia> llegadasTardes) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            String dia = sdf.format(cal.getTime());
            int[] tardeDias = new int[Integer.parseInt(dia)];
            for (int i = 0; i < tardeDias.length; i++) {
                tardeDias[i] = 0;
            }
            for (int i = 0; i < llegadasTardes.size(); i++) {
                Asistencia a = llegadasTardes.get(i);
                char b = a.getFecha().charAt(0);
                char c = a.getFecha().charAt(1);
                String diaTarde = "" + b + c;
                tardeDias[Integer.parseInt(diaTarde) - 1] += 1;
            }
            int mayor = 0;
            int indice = 0;
            for (int i = 0; i < tardeDias.length; i++) {
                if (mayor < tardeDias[i]) {
                    mayor = tardeDias[i];
                    indice = i;
                }
            }
            int count = 0;
            for (int i = 0; i < tardeDias.length; i++) {

                if (mayor == tardeDias[i]&& i!=indice) {
                    count += 1;
                }
            }
            if (count != 0) {
                return 0;
            } else {
                return indice + 1;
            }

        }

        public int cantidadEstudiantesFalta(){
            int cantidadEstFalta=0;
            for(int i=0;i<estudiantes.size();i++) {
                Estudiante estudiante = estudiantes.get(i);
                ArrayList<Asistencia> asistenciaE = retornaAsistencia(estudiante.getIdentificacion());
                ArrayList<Asistencia> faltasMes=new ArrayList<>();
                if(asistenciaE.size()!=0){
                    ArrayList<Asistencia> faltas= faltas(asistenciaE);
                    faltasMes= faltasUltimoMes(faltas);
                }
                if (faltasMes.size()!=0){
                    cantidadEstFalta+=1;
                }
            }
            return cantidadEstFalta;
        }


    public int cantidadEstudiantesTarde(){
        int cantidadEstTarde=0;
        for(int i=0;i<estudiantes.size();i++) {
            Estudiante estudiante = estudiantes.get(i);
            ArrayList<Asistencia> asistenciaE = retornaAsistencia(estudiante.getIdentificacion());
            ArrayList<Asistencia> tardesMes=new ArrayList<>();
            if(asistenciaE.size()!=0){
                ArrayList<Asistencia> tardes= llegadasTarde(asistenciaE);
                tardesMes= llegadasTardeUltimoMes(tardes);
            }
            if (tardesMes.size()!=0){
                cantidadEstTarde+=1;
            }
        }
        return cantidadEstTarde;
    }

}
