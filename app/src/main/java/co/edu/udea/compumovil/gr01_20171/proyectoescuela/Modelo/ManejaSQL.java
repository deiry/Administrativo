package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

/**
 * Created by Alejandro on 27/02/2017.
 */

public class ManejaSQL extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "escuela.db";
    private static final int VERSION_ACTUAL = 1;
    private final Context contexto;

    public ManejaSQL(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }

    interface Tablas{
        String TBL_ESTUDIANTE = "tbl_estudiante";
        String TBL_GRUPO = "tbl_grupo";
        String TBL_MATERIAS = "tbl_materias";
        String TBL_MATERIAS_ESTUDIANTE = "tbl_materias_estudiante";
        String TBL_SEGUIMIENTO = "tbl_seguimiento";
        String TBL_SUBCATEGORIAS = "tbl_subcategorias";
        String TBL_CATEGORIAS = "tbl_categorias";
        String TBL_ASISTENCIA = "tbl_asistencia";
        String TBL_META = "tbl_meta";
        String TBL_LISTA_METAS = "tbl_lista_metas";
        String TBL_GRUPOS_LISTA_ESTUDIANTES = "tbl_grupos_lista_estudiantes";
        String TBL_GRUPOS_ESTUDIANTES = "tbl_grupos_estudiantes";
    }

    interface Referencias{

        String ID_CATEGORIAS = String.format("REFERENCES %s(%s)",
                Tablas.TBL_CATEGORIAS, ContratoEscuela.Categorias.CAT_ID);

        String ID_SUBCATEGORIAS = String.format("REFERENCES %s(%s)",
                Tablas.TBL_SUBCATEGORIAS, ContratoEscuela.Subcategorias.SUBC_CAT_ID);

        String ID_SEGUIMIENTO = String.format("REFERENCES %s(%s)",
                Tablas.TBL_SEGUIMIENTO, ContratoEscuela.Seguimiento.SEG_ID);

        String ID_LISTA_METAS = String.format("REFERENCES %s(%s)",
                Tablas.TBL_LISTA_METAS, ContratoEscuela.ListaMetas.LISTMET_ID);

        String ID_METAS = String.format("REFERENCES %s(%s)",
                Tablas.TBL_META, ContratoEscuela.Metas.MET_ID);

        String ID_GRUPOS_ESTUDIANTES = String.format("REFERENCES %s(%s)",
                Tablas.TBL_GRUPOS_ESTUDIANTES, ContratoEscuela.GrupoEstudiantes.GPEST_ID);

        String ID_MATERIAS = String.format("REFERENCES %s(%s)",
                Tablas.TBL_MATERIAS, ContratoEscuela.Materias.MTA_ID);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        columnas materias ColumnasMateriasEstudiantes  ColumnasCategorias ColumnasListaMetas  ColumnasListaGrupoEstudiantes
        ColumnasGrupoEstudiantes
        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER ," +
                        "%s VARCHAR(2) )",
                Tablas.TBL_GRUPO, ContratoEscuela.Grupo.GRP_CURSO, ContratoEscuela.Grupo.GRP_GRUPO));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL )",
                Tablas.FORMA_PAGO, BaseColumns._ID,
                FormasPago.ID_FORMA_PAGO, FormasPago.NOMBRE));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_ASISTENCIA);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_CATEGORIAS);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_ESTUDIANTE);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_GRUPO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_GRUPOS_ESTUDIANTES);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_GRUPOS_LISTA_ESTUDIANTES);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_LISTA_METAS);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_MATERIAS);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_MATERIAS_ESTUDIANTE);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_META);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_SEGUIMIENTO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_SUBCATEGORIAS);

        onCreate(db);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }
}
