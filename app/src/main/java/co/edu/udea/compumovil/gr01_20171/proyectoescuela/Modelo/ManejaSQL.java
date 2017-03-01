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
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
