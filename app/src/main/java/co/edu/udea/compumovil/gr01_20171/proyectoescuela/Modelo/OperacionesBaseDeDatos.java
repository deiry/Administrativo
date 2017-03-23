package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLInput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.*;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.EstudianteAdapter;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.vistasMetas.Cumplimiento;

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
     */

    public void insertarEstudiante(co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.Estudiante estudiante) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Estudiantes.EST_IDENTIFICACION, estudiante.getIdentificacion());
        valores.put(ContratoEscuela.Estudiantes.EST_NOMBRES, estudiante.getNombres());
        valores.put(ContratoEscuela.Estudiantes.EST_APELLIDOS, estudiante.getApellidos());
        valores.put(ContratoEscuela.Estudiantes.EST_FOTO, estudiante.getFoto());
        valores.put(ContratoEscuela.Estudiantes.EST_GRP_CURSO, estudiante.getCurso());
        valores.put(ContratoEscuela.Estudiantes.EST_GRP_GRUPO, estudiante.getGrupo());
        valores.put(ContratoEscuela.Estudiantes.EST_POS_COL, estudiante.getPosColumna());
        valores.put(ContratoEscuela.Estudiantes.EST_POS_FILA, estudiante.getPosFila());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_ESTUDIANTE, null, valores);
    }

    /**
     * Método para agregar grupos a la tabla de grupos
     */

    public void insertarGrupo(Grupo grupo) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Grupos.GRP_CURSO, grupo.getCurso());
        valores.put(ContratoEscuela.Grupos.GRP_GRUPO, grupo.getGrupo());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_GRUPO, null, valores);
    }

    /**
     * Método para agregar categorias a la tabla de categorias
     */

    public void insertarCategorias(Categoria categoria) {

        if(obtenerCategoria(categoria.getTipo(),categoria.getNombre()) == null)
        {
            SQLiteDatabase db = baseDatos.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(ContratoEscuela.Categorias.CAT_NOMBRE, categoria.getNombre());
            valores.put(ContratoEscuela.Categorias.CAT_TIPO,categoria.getTipo());
            //db.insertOrThrow(ManejaSQL.Tablas.TBL_CATEGORIAS, null, valores);
            long l = db.insert(ManejaSQL.Tablas.TBL_CATEGORIAS,null,valores);
            long d = l;
        }


    }

    /**
     * Método para insertar subcategorias en la tabla correspondiente
     */
    public boolean insertarSubCategorias(Subcategoria subcategoria) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Subcategorias.SUBC_CAT_ID, subcategoria.getIdCat());
        valores.put(ContratoEscuela.Subcategorias.SUBC_NOMBRE, subcategoria.getNombre());
        valores.put(ContratoEscuela.Subcategorias.SUBC_ICONO, subcategoria.getIcono());
        long response = db.insertOrThrow(ManejaSQL.Tablas.TBL_SUBCATEGORIAS, null, valores);

        if(response != -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Método para materias a la base de datos
     */

    public void insertarMaterias(Materia materia) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Materias.MTA_ID, materia.getId());
        valores.put(ContratoEscuela.Materias.MTA_NOMBRE, materia.getNombre());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_MATERIAS, null, valores);
    }

    /**
     * Método para agregar asistencia a la tlaba de asistencia
     */
    public void insertarAsistencia(Asistencia asistencia) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Asistencia.AST_EST_ID, asistencia.getIdEstudiante());
        valores.put(ContratoEscuela.Asistencia.AST_FECHA, asistencia.getFecha().toString());
        valores.put(ContratoEscuela.Asistencia.AST_ASISTENCIA, asistencia.getAsistencia());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_ASISTENCIA, null, valores);
    }

    /*
     *Método para agregar una nueva meta a la lista de metas (tbl_lista_metas)
     */
    public void agregarMeta(ListaMetas nuevaMeta) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.ListaMetas.LISTMET_NOMBRE,nuevaMeta.getNombre());
        valores.put(ContratoEscuela.ListaMetas.MET_TIPO, nuevaMeta.getTipo());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_LISTA_METAS,null,valores);
    }

    /**
     * Método para asignar una meta a un estudiante (tbl_metas)
     */
    public void asignarMeta(Meta meta){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Metas.EST_ID, meta.getEstudianteId());
        valores.put(ContratoEscuela.Metas.LISTMETA_ID, meta.getListaMetasId());
        valores.put(ContratoEscuela.Metas.MET_FECHA_INICIO, String.valueOf(meta.getFechaInicio()));
        valores.put(ContratoEscuela.Metas.MET_DURACION, meta.getDuracion());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_METAS,null,valores);
    }

    /**
     * Método para asignar un cumplimiento a una meta
     */
    public void asignarCumplimiento(CumplimientoMeta cumplimiento){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.CumplimientoMetas.MET_ID, cumplimiento.getMetaId());
        valores.put(ContratoEscuela.CumplimientoMetas.MET_FECHA, String.valueOf(cumplimiento.getFecha()));
        valores.put(ContratoEscuela.CumplimientoMetas.MET_ESTADO, cumplimiento.getEstado());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_CUMPLIMIENTO_METAS,null,valores);
    }

    /**
     * Método para borrar una meta de la tabla ListaMetas
     */
    public boolean borrarMeta(String idMeta) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = String.format("%s=?", ContratoEscuela.ListaMetas.LISTMET_ID);
        String[] whereArgs = {idMeta};
        int resultado = db.delete(ManejaSQL.Tablas.TBL_LISTA_METAS, whereClause, whereArgs);
        return resultado > 0;
    }

    /**
     * Método para insertar seguimiento en la tabla de seguimiento
     */
    public void insertarSeguimiento(Seguimiento seguimiento) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.Seguimiento.SEG_ID, seguimiento.getIdSeg());
        valores.put(ContratoEscuela.Seguimiento.SEG_SUBC_ID, seguimiento.getIdSubSeg());
        valores.put(ContratoEscuela.Seguimiento.SEG_EST_ID, seguimiento.getIdEst());
        valores.put(ContratoEscuela.Seguimiento.SEG_ESTADO, seguimiento.getEstado());
        valores.put(ContratoEscuela.Seguimiento.SEG_FECHA, seguimiento.getFecha().toString());
        valores.put(ContratoEscuela.Seguimiento.SEG_TIPO, seguimiento.getTipo());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_SEGUIMIENTO, null, valores);
    }

    /**
     * Método para obtener datos de la base de datos es necesario pasar por parametro
     * la secuencia que indica que datos se deberían retornar.
     */
    public Cursor obtenerDataDB(String sentence) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        return db.rawQuery(sentence, null);
    }

    public ArrayList<Estudiante> obtenerEstudiantesDB(Grupo grupo) {
        String consulta;

        if(grupo != null){

        consulta = String.format("SELECT %s.* FROM %s WHERE (%s=%s AND %s='%s')",ManejaSQL.Tablas.TBL_ESTUDIANTE
                ,ManejaSQL.Tablas.TBL_ESTUDIANTE,
                ContratoEscuela.Estudiantes.EST_GRP_CURSO,grupo.getCurso(),
                ContratoEscuela.Estudiantes.EST_GRP_GRUPO,grupo.getGrupo());

        }else{
         consulta = String.format("SELECT * FROM %s", ManejaSQL.Tablas.TBL_ESTUDIANTE);}

        Cursor estudiantes = obtenerDataDB(consulta);

        estudiantes.getCount();
        Estudiante estudiante;
        ArrayList<Estudiante> estudiantesAL = new ArrayList<>();
        estudiantesAL.clear();

        if(estudiantes.moveToFirst()){
            do{
            estudiante = new Estudiante(estudiantes.getInt(0),estudiantes.getString(1),estudiantes.getString(2),
                    estudiantes.getString(3),estudiantes.getInt(4),estudiantes.getString(5),estudiantes.getInt(6),

                    estudiantes.getInt(7));
            estudiantesAL.add(estudiante);
            }while(estudiantes.moveToNext());
        }
          return estudiantesAL;
        }


    public ArrayList<Grupo> obtenerGruposDB() {
        String consulta = "SELECT * FROM tbl_grupo";
        Cursor grupos = obtenerDataDB(consulta);
        Grupo grupo;
        ArrayList<Grupo> grupoAL = new ArrayList<>();
        if (grupos.moveToFirst()) {
            do {
                grupo = new Grupo(grupos.getInt(0), grupos.getString(1));
                grupoAL.add(grupo);
            } while (grupos.moveToNext());
        }
        return grupoAL;
    }

    public boolean borrarEstudiante(String idEstudiante) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = String.format("%s=?", ContratoEscuela.Estudiantes.EST_IDENTIFICACION);
        String[] whereArgs = {idEstudiante};
        int resultado = db.delete(ManejaSQL.Tablas.TBL_ESTUDIANTE, whereClause, whereArgs);
        return resultado > 0;
    }

    // Recuperar Lista de Metas Creadas
    public ArrayList<ListaMetas> listarMetas(){
        String consulta = "SELECT * FROM tbl_lista_metas";
        Cursor cursor = obtenerDataDB(consulta);
        ListaMetas meta;
        ArrayList<ListaMetas> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                meta = new ListaMetas(cursor.getString(1), cursor.getString(2));
                meta.setId(cursor.getInt(0));
                lista.add(meta);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    // Recuperar Lista de Metas Asignadas
    public ArrayList<Meta> listarMetasEstudiante(){
        String consulta = "SELECT * FROM tbl_meta";
        Cursor cursor = obtenerDataDB(consulta);
        Meta meta;
        ArrayList<Meta> metasEstudiante = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                meta = new Meta(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)), new Date(),
                        Integer.parseInt(cursor.getString(4)));

                String s= cursor.getString(3);
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
                Date d=new Date();
                try {
                    d=  dateFormat.parse(s);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                meta.setFechaInicio(d);
                metasEstudiante.add(meta);
            }while(cursor.moveToNext());
        }
        return metasEstudiante;
    }

    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }

    public ListaMetas obtenerMeta(int id) {
        String consulta;
        consulta = String.format("SELECT * FROM tbl_lista_metas WHERE ("+
                ContratoEscuela.ColumnasListaMetas.LISTMET_ID+" = %s)", id);
        Cursor cursor = obtenerDataDB(consulta);
        ListaMetas meta;
        if(cursor.getCount() == 1) {
            cursor.moveToFirst();
            meta = new ListaMetas(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));}
        else meta = null;
        return(meta);
    }

    // Se recuperan los cumplimientos asociados a una meta
    public ArrayList<CumplimientoMeta> recuperarCumplimientos(int idMeta){
        String consulta = String.format("SELECT FROM tbl_cumplimiento_metas WHERE ("+
                ContratoEscuela.ColumnasCumplimientoMetas.MET_ID+" = %s)", idMeta);
        Cursor cursor = obtenerDataDB(consulta);
        CumplimientoMeta cumplimiento;
        ArrayList<CumplimientoMeta> lista = new ArrayList<CumplimientoMeta>();
        if(cursor.moveToFirst()){
            do{
                cumplimiento = new CumplimientoMeta(
                        Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        new Date(),
                        Integer.parseInt(cursor.getString(3)));
                String s= cursor.getString(2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
                Date d = new Date();
                try {
                    d = dateFormat.parse(s);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cumplimiento.setFecha(d);
                lista.add(cumplimiento);
            }while(cursor.moveToNext());
        }
        return (lista);
    }


    //se recuperan las metas que tengan un id de lista metas
    public ArrayList<Meta> obtenerMetasPorIdListaMtas(int idListaMetas){
        String consulta = String.format("SELECT * FROM tbl_meta WHERE ("+
                ContratoEscuela.ColumnasMetas.LISTMETA_ID+" = %s)", idListaMetas);
        Cursor cursor = obtenerDataDB(consulta);
        Meta meta;
        ArrayList<Meta> listarMetas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                meta = new Meta(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)), new Date(),
                        Integer.parseInt(cursor.getString(4)));
                String s = cursor.getString(3);
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);

                Date d = new Date();
                try {
                    d = dateFormat.parse(s);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                meta.setFechaInicio(d);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(meta.getFechaInicio());
                calendar.add(Calendar.DAY_OF_YEAR,meta.getDuracion());
                if(calendar.getTime().compareTo(Calendar.getInstance().getTime()) >= 0){
                    listarMetas.add(meta);
                }

            }while(cursor.moveToNext());
        }
        return listarMetas;
    }

    //se recuperan las metas que tengan un id de lista metas
    public ArrayList<Meta> validarMetaAEstudiante(int idListaMetas, int idEstudiante){
        String consulta = String.format("SELECT * FROM tbl_meta WHERE ("+
                        ContratoEscuela.ColumnasMetas.LISTMETA_ID+" = %s AND "+ContratoEscuela.ColumnasMetas.EST_ID+" = %s)",
                idListaMetas,idEstudiante);
        Cursor cursor = obtenerDataDB(consulta);
        Meta meta;
        ArrayList<Meta> listarMetas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                meta = new Meta(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)), new Date(),
                        Integer.parseInt(cursor.getString(4)));
                String s = cursor.getString(3);
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);

                Date d = new Date();
                try {
                    d = dateFormat.parse(s);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                meta.setFechaInicio(d);
                //borrarMetaPorIdListaMetas(idListaMetas);
                listarMetas.add(meta);
            }while(cursor.moveToNext());
        }
        return listarMetas;
    }

    //borra de la tabla tabla_meta
    public boolean borrarMetaPorIdListaMetas(int idMeta) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = String.format("%s=?", ContratoEscuela.ColumnasMetas.LISTMETA_ID);
        String[] whereArgs = {idMeta+""};
        int resultado = db.delete(ManejaSQL.Tablas.TBL_METAS, whereClause, whereArgs);
        return resultado > 0;
    }

    public Estudiante obtenerEstudiante(int id) {
        String consulta;
            consulta = String.format("SELECT * FROM tbl_estudiante WHERE ("+
                    ContratoEscuela.ColumnasEstudiante.EST_IDENTIFICACION+" = %s)", id);

        Cursor cursor = obtenerDataDB(consulta);
        Estudiante estudiante;
        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            estudiante = new Estudiante(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getInt(7));
        }
        else
        {
            estudiante = null;
        }


        return estudiante;
    }

    public Categoria obtenerCategoria(int tipo, String nombre) {
        String consulta;

        consulta = String.format("SELECT * FROM %s WHERE (%s = %s AND %s = '%s')",

                ManejaSQL.Tablas.TBL_CATEGORIAS,
                ContratoEscuela.Categorias.CAT_TIPO,
                tipo,
                ContratoEscuela.Categorias.CAT_NOMBRE,
                nombre);

        Cursor cursor = obtenerDataDB(consulta);
        Categoria categoria;
        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            categoria = new Categoria(cursor.getString(1),cursor.getInt(2),cursor.getInt(0));
        }
        else
        {
            categoria = null;
        }

        return categoria;
    }

    public ArrayList<Categoria> obtenerCategorias(int id) {
        String consulta;
        consulta = String.format("SELECT * FROM %s WHERE %s = %s",

                ManejaSQL.Tablas.TBL_CATEGORIAS,
                ContratoEscuela.ColumnasCategorias.CAT_TIPO,
                id);

        Cursor cursor = obtenerDataDB(consulta);

        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount();i++)
        {
            Categoria categoria = new Categoria(cursor.getString(1),cursor.getInt(2),cursor.getInt(0));
            categorias.add(categoria);
            if (!cursor.isLast())
            {
                cursor.moveToNext();
            }

        }


        return categorias;
    }

    public ArrayList<Subcategoria> obtenerSubCategoriasFromCategoriaId(int id) {
        String consulta;
        consulta = String.format("SELECT * FROM %s WHERE (%s = %s)",
                ManejaSQL.Tablas.TBL_SUBCATEGORIAS,
                ContratoEscuela.ColumnasSubcategorias.SUBC_CAT_ID,
                id);

        Cursor cursor = obtenerDataDB(consulta);

        ArrayList<Subcategoria> subcategorias = new ArrayList<Subcategoria>();
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount();i++)
        {
            Subcategoria subcategoria = new Subcategoria(cursor.getInt(1),cursor.getString(2));
            subcategoria.setId(cursor.getInt(0));
            subcategorias.add(subcategoria);
            if (!cursor.isLast())
            {
                cursor.moveToNext();
            }
        }

        return subcategorias;
    }
}
