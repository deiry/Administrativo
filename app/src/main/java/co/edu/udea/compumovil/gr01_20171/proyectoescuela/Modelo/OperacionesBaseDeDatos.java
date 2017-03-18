package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLInput;
import java.text.DateFormat;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo.POJO.*;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;
import co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista.EstudianteAdapter;

/**
 * Clase auxiliar que implementa a {@link ManejaSQL} para llevar a cabo el CRUD
 * sobre las entidades existentes.
 * ¡¡¡¡¡¡IMPORTANTE FALTA IMPLEMENTAR LAS OPERACIONES CORRESPONDIENTES A LAS TABLAS DE
 * GRUPO LISTA ESTUDIANTES , MATERIASXESTUDIANTE Y METAS!!!!!!!
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

    /**
     * Método para inserta una meta en la lista de metas
     */
    public void insertarTablaListaDeMetas(ListaMetas listaMetas) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String id = ContratoEscuela.ListaMetas.generarIdListaMetas();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.ListaMetas.LISTMET_ID, listaMetas.getId());
        valores.put(ContratoEscuela.ListaMetas.LISTMET_NOMBRE, listaMetas.getNombre());
        db.insertOrThrow(ManejaSQL.Tablas.TBL_LISTA_METAS, null, valores);
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
     * Método para insertar un grupo de estudiantes en la tabla correspondiente
     */

    public void insertarGruposEstudiante(GrupoEstudiantes grupoEstudiantes) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoEscuela.GrupoEstudiantes.GPEST_ID, grupoEstudiantes.getId());
        valores.put(ContratoEscuela.GrupoEstudiantes.GPEST_NOMBRE, grupoEstudiantes.getNombre());
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

    public ArrayList<Asistencia> obtenerAsistencia(){
        String consulta = String.format("SELECT * FROM %s",ManejaSQL.Tablas.TBL_ASISTENCIA);
        Cursor asistencia = obtenerDataDB(consulta);
        Asistencia asist;
        ArrayList<Asistencia> asistenciaAL = new ArrayList<>();
        if(asistencia.moveToFirst()){
            do{
                asist = new Asistencia(asistencia.getString(0),asistencia.getInt(1),asistencia.getString(2));
                asistenciaAL.add(asist);
            }while(asistencia.moveToNext());
        }
        return asistenciaAL;
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

<<<<<<< HEAD
    /**
     * obtiene todas las materias desde la table de materias y las lleva a un ArrayList
     * @return retorna ArrayList con todas las Materias en la base de datos
     */
    public ArrayList<Materia> obtenerMaterias() {
        String consulta;
        consulta = String.format("SELECT * FROM %s",
                ManejaSQL.Tablas.TBL_MATERIAS);

        Cursor cursor = obtenerDataDB(consulta);

        ArrayList<Materia> materias = new ArrayList<Materia>();
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount();i++)
        {
            Materia materia = new Materia(cursor.getString(1));
            materia.setId(cursor.getInt(0));
            materias.add(materia);
            if (!cursor.isLast())
            {
                cursor.moveToNext();
            }
        }

        return materias;
    }

    public ArrayList<Seguimiento> obtenerSeguimientoFromIdCategoriaIdEstudiante(int idCategoria,int idEstudiante)
    {
        ArrayList<Seguimiento> seguimientos = new ArrayList<Seguimiento>();
        String query = String.format("SELECT  * FROM tbl_seguimiento  " +
                "INNER JOIN tbl_subcategorias ON tbl_seguimiento.seg_subc_id = " +
                "tbl_subcategorias.subc_id WHERE tbl_subcategorias.subc_cat_id = %s AND " +
                "tbl_seguimiento.",idCategoria);

        Cursor cursor = obtenerDataDB(query);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount();i++)
        {
            Seguimiento seguimiento = new Seguimiento(cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6));
            seguimientos.add(seguimiento);

            if (!cursor.isLast())
            {
                cursor.moveToNext();
            }
        }

        return seguimientos;
    }

    public int countSeguimientoFromIdSubcategoriIdEstudiante(int idSubCategoria,int idEstudiante)
    {
        String query = String.format("SELECT  * FROM %s WHERE %s = %s AND %s = %s",
                ManejaSQL.Tablas.TBL_SEGUIMIENTO,
                ContratoEscuela.ColumnasSeguimiento.SEG_EST_ID,
                idEstudiante,
                ContratoEscuela.ColumnasSeguimiento.SEG_SUBC_ID,
                idSubCategoria);
        Cursor cursor = obtenerDataDB(query);

        return cursor.getCount();
    }


=======
>>>>>>> origin/AlejandroMarin


}
