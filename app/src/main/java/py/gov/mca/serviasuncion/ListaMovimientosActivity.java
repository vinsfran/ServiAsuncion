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

import py.gov.mca.serviasuncion.entidades.Movimiento;
import py.gov.mca.serviasuncion.fragments.ListaMovimientosFragment;

public class ListaMovimientosActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private List<Movimiento> movimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_lista_movimientos);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //FRAGMENT
        ListaMovimientosFragment frag = (ListaMovimientosFragment) getSupportFragmentManager().findFragmentByTag("ListaMovimientosFrag");
        if (frag == null) {
            frag = new ListaMovimientosFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "ListaMovimientosFrag");
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

    public List<Movimiento> getSetExpedientesList() throws ParseException {
        Movimiento m1 = new Movimiento(1, "m1");
        Movimiento m2 = new Movimiento(2, "m2");

        movimientos = new ArrayList<>();
        movimientos.add(m1);
        movimientos.add(m2);

        return movimientos;
    }
}
