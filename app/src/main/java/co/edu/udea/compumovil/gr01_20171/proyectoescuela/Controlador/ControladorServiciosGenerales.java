package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Controlador;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.InicioActivity;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.PantallaEstadisticas;

/**
 * Created by ACER on 18/03/2017.
 */

public class ControladorServiciosGenerales{


    public ArrayList<String> convertirGrupos (ArrayList<Grupo> a){
        ArrayList<String> gruposs = new ArrayList<>();
        Grupo aux;
        for (int i = 0; i < a.size(); i++){
            aux = a.get(i);
            gruposs.add(String.valueOf(aux.getCurso()) + "-" + aux.getGrupo());
        }
        return gruposs;
    }


}
