package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLInput;
import java.text.DateFormat;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.*;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.EstudianteAdapter;

/**
 * Clase auxiliar que implementa a {@link ManejaSQL} para llevar a cabo el CRUD
 * sobre las entidades existentes.
 * ¡¡¡¡¡¡IMPORTANTE FALTA IMPLEMENTAR LAS OPERACIONES CORRESPONDIENTES A LAS TABLAS DE
 *  MATERIASXESTUDIANTE!!!!!!!
 */


public final class OperacionesBaseDeDatos {

    private static ManejaSQL baseDatos;

    private static OperacionesBaseDeDatos instancia = new OperacionesBaseDeDatos();

    private OperacionesBaseDeDatos() {
    }

    public static OperacionesBaseDeDatos obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new ManejaSQL(contexto);
        }
        return instancia;
    }

    /**
     * Método para agregar estudiantes a la tabala de estudiantes
     * */

    public void insertarEstudiante(co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante estudiante ){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Estudiantes.EST_IDENTIFICACION,estudiante.getIdentificacion());
        valores.put(ContratoEscuela.Estudiantes.EST_NOMBRES,estudiante.getNombres());
        valores.put(ContratoEscuela.Estudiantes.EST_APELLIDOS,estudiante.getApellidos());
        valores.put(ContratoEscuela.Estudiantes.EST_FOTO,estudiante.getFoto());
        valores.put(ContratoEscuela.Estudiantes.EST_GRP_CURSO,estudiante.getCurso());
        valores.put(ContratoEscuela.Estudiantes.EST_GRP_GRUPO,estudiante.getGrupo());
        valores.put(ContratoEscuela.Estudiantes.EST_POS_COL,estudiante.getPosColumna());
        valores.put(ContratoEscuela.Estudiantes.EST_POS_FILA,estudiante.getPosFila());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_ESTUDIANTE,null,valores);
    }

       /**
     * Método para agregar grupos a la tabla de grupos
     * */

    public void insertarGrupo(Grupo grupo){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Grupos.GRP_CURSO,grupo.getCurso());
        valores.put(ContratoEscuela.Grupos.GRP_GRUPO,grupo.getGrupo());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_GRUPO,null,valores);
    }

    /**
     * Método para agregar categorias a la tabla de categorias
     * */

    public void insertarCategorias(Categoria categoria){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Categorias.CAT_ID,categoria.getId());
        valores.put(ContratoEscuela.Categorias.CAT_NOMBRE,categoria.getNombre());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_CATEGORIAS,null,valores);
    }

    /**
     * Método para insertar subcategorias en la tabla correspondiente
     * */
    public void insertarSubCategorias(Subcategoria subcategoria){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Subcategorias.SUBC_ID,subcategoria.getId());
        valores.put(ContratoEscuela.Subcategorias.SUBC_CAT_ID,subcategoria.getIdCat());
        valores.put(ContratoEscuela.Subcategorias.SUBC_NOMBRE,subcategoria.getNombre());
        valores.put(ContratoEscuela.Subcategorias.SUBC_CAT_ID,subcategoria.getId());
        valores.put(ContratoEscuela.Subcategorias.SUBC_ICONO,subcategoria.getIcono());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_SUBCATEGORIAS,null,valores);
    }


    /**
     * Método para materias a la base de datos
     * */

    public void insertarMaterias(Materia materia){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Materias.MTA_ID,materia.getId());
        valores.put(ContratoEscuela.Materias.MTA_NOMBRE,materia.getNombre());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_MATERIAS,null,valores);
    }

    /**
     * Método para agregar asistencia a la tlaba de asistencia
     * */
    public void insertarAsistencia(Asistencia asistencia){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Asistencia.AST_EST_ID,asistencia.getIdEstudiante());
        valores.put(ContratoEscuela.Asistencia.AST_FECHA, asistencia.getFecha().toString());
        valores.put(ContratoEscuela.Asistencia.AST_ASISTENCIA,asistencia.getAsistencia());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_ASISTENCIA,null,valores);
    }

    /**
     * Método para agregar una nueva meta a la lista de metas (tbl_lista_metas)
     * */
    public void agregarMeta(ListaMetas nuevaMeta){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String id = ContratoEscuela.ListaMetas.generarIdListaMetas();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.ListaMetas.LISTMET_ID,nuevaMeta.getId());
        valores.put(ContratoEscuela.ListaMetas.LISTMET_NOMBRE,nuevaMeta.getNombre());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_LISTA_METAS,null,valores);
    }

    /**
     * Método para asignar una meta a un estudiante (tbl_metas)
     */
    public void asignarMeta(Meta meta){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Metas.MET_ID, meta.getId());
        valores.put(ContratoEscuela.Metas.EST_ID, meta.getEstudianteId());
        valores.put(ContratoEscuela.Metas.LISTMETA_ID, meta.getListaMetasId());
        valores.put(ContratoEscuela.Metas.MET_FECHA_INICIO, String.valueOf(meta.getFechaInicio()));
        valores.put(ContratoEscuela.Metas.MET_DURACION, meta.getDuracion());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_METAS,null,valores);
    }

    /**
     *  Método para insertar seguimiento en la tabla de seguimiento
     * */
    public void insertarSeguimiento(Seguimiento seguimiento){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Seguimiento.SEG_ID,seguimiento.getIdSeg());
        valores.put(ContratoEscuela.Seguimiento.SEG_SUBC_ID,seguimiento.getIdSubSeg());
        valores.put(ContratoEscuela.Seguimiento.SEG_EST_ID,seguimiento.getIdEst());
        valores.put(ContratoEscuela.Seguimiento.SEG_ESTADO,seguimiento.getEstado());
        valores.put(ContratoEscuela.Seguimiento.SEG_FECHA,seguimiento.getFecha().toString());
        valores.put(ContratoEscuela.Seguimiento.SEG_TIPO,seguimiento.getTipo());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_SEGUIMIENTO,null,valores);
    }

    /**
     *  Método para obtener datos de la base de datos es necesario pasar por parametro
     *  la secuencia que indica que datos se deberían retornar.
     * */
    public Cursor obtenerDataDB(String sentence){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        return db.rawQuery(sentence,null);
    }

    public ArrayList<Estudiante> obtenerEstudiantesDB(Grupo grupo){
        String consulta;
        if(grupo != null){
        consulta = String.format("SELECT %s.* FROM %s WHERE (%s=%s AND %s='%s')",ManejaSQL.Tablas.TBL_ESTUDIANTE
                ,ManejaSQL.Tablas.TBL_ESTUDIANTE,
                ContratoEscuela.Estudiantes.EST_GRP_CURSO,grupo.getCurso(),
                ContratoEscuela.Estudiantes.EST_GRP_GRUPO,grupo.getGrupo());
        }else{
         consulta = String.format("SELECT * FROM %s", ManejaSQL.Tablas.TBL_ESTUDIANTE);
        }
        Cursor estudiantes = obtenerDataDB(consulta);
        estudiantes.getCount();
        Estudiante estudiante;
        ArrayList<Estudiante> estudiantesAL = new ArrayList<>();
        estudiantesAL.clear();
        while (estudiantes.moveToNext()){
            estudiante = new Estudiante(estudiantes.getInt(0),estudiantes.getString(1),estudiantes.getString(2),
                    estudiantes.getBlob(3),estudiantes.getInt(4),estudiantes.getString(5),estudiantes.getInt(6),
                    estudiantes.getInt(7));
            estudiantesAL.add(estudiante);

        }
        return estudiantesAL;
    }

    public ArrayList<Grupo> obtenerGruposDB(){
        String consulta = "SELECT * FROM tbl_grupo";
        Cursor grupos = obtenerDataDB(consulta);
        Grupo grupo;
        ArrayList<Grupo> grupoAL = new ArrayList<>();
        if(grupos.moveToFirst()){
            do{
                grupo = new Grupo(grupos.getInt(0),grupos.getString(1));
                grupoAL.add(grupo);
            }while(grupos.moveToNext());
        }
        return grupoAL;
    }

    public boolean borrarEstudiante(String idEstudiante){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = String.format("%s=?", ContratoEscuela.Estudiantes.EST_IDENTIFICACION);
        String[] whereArgs = {idEstudiante};
        int resultado = db.delete(ManejaSQL.Tablas.TBL_ESTUDIANTE, whereClause, whereArgs);
        return resultado > 0;
    }


    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }
}
