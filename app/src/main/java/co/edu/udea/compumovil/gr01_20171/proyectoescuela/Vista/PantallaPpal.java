package co.edu.udea.compumovil.gr01_20171.proyectoescuela.Vista;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import co.edu.udea.compumovil.gr01_20171.proyectoescuela.R;

public class PantallaPpal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ppal);

        agregarFragmentNombreGrupo();
    }

    private void agregarFragmentNombreGrupo() {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        TituloGrupoFragment fragment = new TituloGrupoFragment();

        fragmentTransaction.add(R.id.fragment_contenedor, fragment);

        fragmentTransaction.commit();
    }
}
