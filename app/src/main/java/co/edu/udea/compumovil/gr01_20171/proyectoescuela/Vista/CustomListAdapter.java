package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

/**
 * Created by Andrés Moreno on 14/03/2017.
 */

public class CustomListAdapter extends ArrayAdapter<Estudiante> {

    ArrayList<Estudiante> estudiantes;
    Context context;
    int resource;

    public CustomListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Estudiante> objects) {
        super(context, resource, objects);
        this.estudiantes = objects;
        this.context = context;
        this.resource = resource;
    }

    public CustomListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_layout, null, true);
        }
        Estudiante estudiante = getItem(position);
        ImageView imageEst = (ImageView)convertView.findViewById(R.id.imgEst);
        imageEst.setImageBitmap(byteToImageView(estudiante.getFoto()));
        TextView nombreEst = (TextView)convertView.findViewById(R.id.txtNombre);
        nombreEst.setText(estudiante.getNombres());
        TextView apellidoEst = (TextView)convertView.findViewById(R.id.txtApellido);
        apellidoEst.setText(estudiante.getApellidos());
        TextView idEst = (TextView)convertView.findViewById(R.id.txtIde);

        //idEst.setText(estudiante.getIdentificacion());
        return convertView;
    }

    private Bitmap byteToImageView(byte[] bytes){
         return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
