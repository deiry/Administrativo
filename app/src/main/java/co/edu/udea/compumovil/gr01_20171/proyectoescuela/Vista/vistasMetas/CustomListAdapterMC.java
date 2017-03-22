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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class CustomListAdapterMC extends ArrayAdapter<Estudiante>{

    Context context;
    int resource;
    ImageView imageEst;
    TextView nombreEst;
    TextView apellidoEst;
    RadioButton opcionCumplio, opcionNoCumplio;
    RadioGroup opciones;

    public CustomListAdapterMC(@NonNull Context context, @LayoutRes int resource) {
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
            convertView = layoutInflater.inflate(R.layout.activity_list_metas_cumplimiento, null, true);
        }

        try{
            opcionCumplio = (RadioButton) convertView.findViewById(R.id.cumplio);
            opcionNoCumplio = (RadioButton) convertView.findViewById(R.id.noCumplio);
            opciones=(RadioGroup) convertView.findViewById(R.id.grupoOpcionesC);
            opciones.setTag(position);
            opciones.setOnClickListener(
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
                            if(opcionCumplio.isChecked()) {
                                est.getGestorMetas().setCumplimiento(true);
                                est.getGestorMetas().setAsignacionCumplimiento(true);
                            }else if(opcionNoCumplio.isChecked()){
                                est.getGestorMetas().setCumplimiento(false);
                                est.getGestorMetas().setAsignacionCumplimiento(true);
                            }else
                                est.getGestorMetas().setAsignacionCumplimiento(false);
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
