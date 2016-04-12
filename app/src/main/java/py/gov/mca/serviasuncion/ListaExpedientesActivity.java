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

import py.gov.mca.serviasuncion.entidades.Expediente;
import py.gov.mca.serviasuncion.fragments.ListaExpedientesFragment;

public class ListaExpedientesActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private List<Expediente> expedientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_lista_expedientes);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //FRAGMENT
        ListaExpedientesFragment frag = (ListaExpedientesFragment) getSupportFragmentManager().findFragmentByTag("ListaExpedientesFrag");
        if (frag == null) {
            frag = new ListaExpedientesFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "ListaExpedientesFrag");
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

    public List<Expediente> getSetExpedientesList() throws ParseException {
        Expediente e1 = new Expediente(1, "e1");
        Expediente e2 = new Expediente(2, "e2");

        expedientes = new ArrayList<>();
        expedientes.add(e1);
        expedientes.add(e2);

        return expedientes;
    }
}
