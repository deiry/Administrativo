package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.vistasMetas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.OperacionesBaseDeDatos;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class CustomListAdapterM extends ArrayAdapter<Estudiante> {

    Context context;
    int resource;
    ImageView imageEst;
    TextView nombreEst;
    TextView apellidoEst;
    CheckBox seleccion;
    EditText duracionMeta;

    public CustomListAdapterM(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_list_metas, null, true);
        }

        try{
            seleccion = (CheckBox) convertView.findViewById(R.id.estudianteSeleccionado);
            duracionMeta = (EditText)convertView.findViewById(R.id.duracion);
            seleccion.setTag(position);
            seleccion.setOnClickListener(
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Estudiante est = null;
                            for(int i=0 ; i<getCount() ; i++){
                                if(i==position){
                                    est = getItem(i);
                                    break;
                                }
                            }
                            if(est.getGestorMetas().estado()){
                                est.getGestorMetas().setEstado(false);
                            }else est.getGestorMetas().setEstado(true);
                            String textoDuracion = duracionMeta.getText().toString();
                            // MODIFICAR
                            if(textoDuracion.compareTo("")!=0)
                                est.getGestorMetas().setDuracionMeta(Integer.parseInt(textoDuracion));
                            else est.getGestorMetas().setDuracionMeta(1);
                        }
                    }
            );}catch (Exception e){e.printStackTrace();}

        Estudiante estudiante = getItem(position);

        Uri uri = pathToUri(estudiante.getFoto());
        imageEst = (ImageView)convertView.findViewById(R.id.imgEstM);

        if (!uri.equals(Uri.EMPTY)){
            imageEst.setImageURI(pathToUri(estudiante.getFoto()));
        }else{
            imageEst.setImageResource(R.mipmap.ic_launcher);
        }

        nombreEst = (TextView)convertView.findViewById(R.id.txtNombreM);
        nombreEst.setText(estudiante.getNombres());
        apellidoEst = (TextView)convertView.findViewById(R.id.txtApellidoM);
        apellidoEst.setText(estudiante.getApellidos());
        nombreEst.setTextColor(Color.BLACK);
        apellidoEst.setTextColor(Color.BLACK);
        seleccion = (CheckBox)convertView.findViewById(R.id.estudianteSeleccionado);
        duracionMeta = (EditText)convertView.findViewById(R.id.duracion);
        return convertView;
    }

    private Uri pathToUri(String imgPath){
        File imgFile = new File(imgPath);
        if(imgFile.exists())
        {
            return Uri.fromFile(imgFile);

        }
        return Uri.EMPTY;
    }
}
