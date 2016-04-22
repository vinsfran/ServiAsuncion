package py.gov.mca.serviasuncion;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import py.gov.mca.serviasuncion.entidades.Sebestexp;
import py.gov.mca.serviasuncion.entidades.Sebtipmov;
import py.gov.mca.serviasuncion.entidades.Sedmovexp;
import py.gov.mca.serviasuncion.entidades.Semdepen;
import py.gov.mca.serviasuncion.entidades.Semexpediente;
import py.gov.mca.serviasuncion.entidades.Sempersona;
import py.gov.mca.serviasuncion.fragments.ListaExpedientesFragment;

public class ListaExpedientesActivity extends AppCompatActivity {

    // Formato para SQL date time
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Toolbar mToolbar;

    private String expeJson;
    private List<Semexpediente> semexpedientes;

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

        expeJson = (String) getIntent().getExtras().getSerializable("expeJson");

        //FRAGMENT
        ListaExpedientesFragment frag = (ListaExpedientesFragment) getSupportFragmentManager().findFragmentByTag("ListaExpedientesFrag");
        if (frag == null) {
            frag = new ListaExpedientesFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container_expedientes, frag, "ListaExpedientesFrag");
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

    public List<Semexpediente> getSetExpedientesList() throws ParseException, JSONException {
        semexpedientes = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(expeJson);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonNroExpediente = jsonArray.getJSONObject(i);

                Semexpediente nroExpediente = new Semexpediente();
                nroExpediente.setNroCarpeta(jsonNroExpediente.getInt("nroCarpeta"));
                nroExpediente.setIndEjefiscar(jsonNroExpediente.getInt("indEjefiscar"));
                nroExpediente.setDesExpediente(jsonNroExpediente.getString("desExpediente"));

                JSONObject jsonCodDepenExpe = jsonNroExpediente.getJSONObject("codDepen");
                Semdepen codDepenMovi = new Semdepen();
                codDepenMovi.setCodDepen(jsonCodDepenExpe.getString("codDepen"));
                codDepenMovi.setDesDepen(jsonCodDepenExpe.getString("desDepen"));
                nroExpediente.setCodDepen(codDepenMovi);

                nroExpediente.setFecUltmov(dateFormat.parse(jsonNroExpediente.getString("fecUltmov")));

                semexpedientes.add(nroExpediente);

               /* Log.d("-----------", "");
                Log.d("N° Exp. / Año:", movimiento.getNroExpediente().getNroCarpeta() + " / " + movimiento.getNroExpediente().getIndEjefiscar());
                Log.d("Nro. Documento: ", movimiento.getNroExpediente().getNroTitular().getNroDocide());
                Log.d("Recurrente:", movimiento.getNroExpediente().getNroTitular().getDesPersona());
                Log.d("Des. Exp.:", movimiento.getNroExpediente().getDesExpediente());
                Log.d("Estado:", movimiento.getNroExpediente().getNroEstexp().getDesEstexp());
                Log.d("Dependencia", movimiento.getCodDepen().getDesDepen());
                Log.d("Movimiento", movimiento.getNroTipmov().getDesTipmov());
                Log.d("Fecha", movimiento.getFecMovexp().toString());
                Log.d("-----------", "");*/
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return semexpedientes;
    }
}
