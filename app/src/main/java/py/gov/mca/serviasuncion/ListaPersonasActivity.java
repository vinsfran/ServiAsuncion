package py.gov.mca.serviasuncion;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import py.gov.mca.serviasuncion.entidades.Sempersona;
import py.gov.mca.serviasuncion.fragments.ListaPersonasFragment;

public class ListaPersonasActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private List<Sempersona> sempersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_lista_personas);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //FRAGMENT
        ListaPersonasFragment frag = (ListaPersonasFragment) getSupportFragmentManager().findFragmentByTag("ListaPersonasFrag");
        if (frag == null) {
            frag = new ListaPersonasFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container_personas, frag, "ListaPersonasFrag");
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_menu_expedientes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Vuelve a la pantalla anterior
                // NavUtils.navigateUpFromSameTask(this);
                super.onBackPressed();
                return true;
           /* case R.id.btnRenovarBanca:
                renovarBanca(this);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public List<Sempersona> getSetPersonasList() throws ParseException {
       /* Sempersona p1 = new Sempersona(1, "p1");
        Sempersona p2 = new Sempersona(2, "p2");

        sempersonas = new ArrayList<>();
        sempersonas.add(p1);
        sempersonas.add(p2);*/

        return sempersonas;
    }
}
