package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.vistasMetas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.IdRes;
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
import java.util.ArrayList;

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
    private ArrayList<Boolean> selectionCumplio;
    private ArrayList<Boolean> selectionNoCumplio;
    Estudiante est;

    public CustomListAdapterMC(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        selectionCumplio = new ArrayList<Boolean>();
        selectionNoCumplio = new ArrayList<Boolean>();
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
            selectionCumplio.add(false);
            selectionNoCumplio.add(false);
            opcionCumplio = (RadioButton) convertView.findViewById(R.id.cumplio);
            opcionNoCumplio = (RadioButton) convertView.findViewById(R.id.noCumplio);
            opciones=(RadioGroup) convertView.findViewById(R.id.grupoOpcionesC);
            opciones.setTag(position);
            opciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    RadioButton seleccionado = (RadioButton)group.findViewById(checkedId);
                    boolean cumplio;
                    /*if(seleccionado.getText().toString().compareTo(opcionCumplio.getText().toString())==0){
                        selectionCumplio.set(position, opcionCumplio.isChecked());
                        selectionNoCumplio.set(position, opcionNoCumplio.isChecked());
                        cumplio = true;
                    }else{
                        selectionNoCumplio.set(position, opcionNoCumplio.isChecked());
                        selectionCumplio.set(position, opcionNoCumplio.isChecked());
                        cumplio = false;
                    }*/

                    RadioButton btn1 = (RadioButton)group.getChildAt(0);
                    RadioButton btn2 = (RadioButton)group.getChildAt(1);
                    if(checkedId == btn1.getId()){
                        selectionCumplio.set(position, btn1.isChecked());
                        selectionNoCumplio.set(position, btn2.isChecked());
                        cumplio = true;
                    }else{
                        selectionNoCumplio.set(position, btn2.isChecked());
                        selectionCumplio.set(position, btn1.isChecked());
                        cumplio = false;
                    }

                    for(int i=0 ; i<getCount() ; i++){
                        if(i==position){
                            est = getItem(i);
                            break;
                        }
                    }
                    if(cumplio) {
                        est.getGestorMetas().setCumplimiento(true);
                        est.getGestorMetas().setAsignacionCumplimiento(true);
                    }else {
                        est.getGestorMetas().setCumplimiento(false);
                        est.getGestorMetas().setAsignacionCumplimiento(true);
                    }
                }
            });

            /*opciones.setOnClickListener(
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
            )*/;}catch (Exception e){e.printStackTrace();}

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
        opciones = (RadioGroup)convertView.findViewById(R.id.grupoOpcionesC);
        opcionCumplio = (RadioButton)convertView.findViewById(R.id.cumplio);
        opcionNoCumplio = (RadioButton)convertView.findViewById(R.id.noCumplio);

        opcionCumplio.setChecked(selectionCumplio.get(position));
        opcionNoCumplio.setChecked(selectionNoCumplio.get(position));

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
