package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo;

import android.content.Context;
import android.graphics.Region;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.CumplimientoMeta;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Grupo;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.ListaMetas;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Meta;

public class ManejaBDMetas {

    public static void agregarRegistro(OperacionesBaseDeDatos operador,ListaMetas nuevaMeta){
        try{
        operador.getDb().beginTransaction();
        operador.agregarMeta(nuevaMeta);
        operador.getDb().setTransactionSuccessful();
        }catch (Exception e){e.printStackTrace();}
        finally {operador.getDb().endTransaction();}
    }

    public static void agregarRegistro(OperacionesBaseDeDatos operador, Meta meta){
        try{
            operador.getDb().beginTransaction();
            operador.asignarMeta(meta);
            operador.getDb().setTransactionSuccessful();
        }catch (Exception e){e.printStackTrace();}
        finally {operador.getDb().endTransaction();}
    }

    public static void agregarRegistro(OperacionesBaseDeDatos operador, CumplimientoMeta cumplimiento){
        try{
            operador.getDb().beginTransaction();
            operador.asignarCumplimiento(cumplimiento);
            operador.getDb().setTransactionSuccessful();
        }catch (Exception e){e.printStackTrace();}
        finally {operador.getDb().endTransaction();}
    }

    public static void borrarMeta(OperacionesBaseDeDatos operador, int idMeta){
        try {
            operador.getDb().beginTransaction();
            operador.borrarMeta(idMeta + "");
            operador.getDb().setTransactionSuccessful();
        }catch (Exception e){e.printStackTrace();}
        finally {operador.getDb().endTransaction();}
    }

    public static ArrayList<ListaMetas> listarMetas(OperacionesBaseDeDatos operador){
        return(operador.listarMetas());
    }

    public static ArrayList retornarDatos(OperacionesBaseDeDatos operador, int identeficacion){
        switch (identeficacion){
            // Se ejecutara listarMetas
            case 0: return (operador.listarMetas());
        }
        return (null);
    }
}