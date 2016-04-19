package py.gov.mca.serviasuncion;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import py.gov.mca.serviasuncion.fragments.ListaMovimientosFragment;

public class ListaMovimientosActivity extends AppCompatActivity {

    // Formato para SQL date time
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Toolbar mToolbar;

    private TextView tvExpAnio;
    private TextView tvNroDoc;
    private TextView tvRecurrente;
    private TextView tvDesExp;
    private TextView tvEstado;

    private String moviJson;
    private List<Sedmovexp> sedmovexps;

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

        moviJson = (String) getIntent().getExtras().getSerializable("moviJson");

        //FRAGMENT
        ListaMovimientosFragment frag = (ListaMovimientosFragment) getSupportFragmentManager().findFragmentByTag("ListaMovimientosFrag");
        if (frag == null) {
            frag = new ListaMovimientosFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container_movimientos, frag, "ListaMovimientosFrag");
            ft.commit();
        }
        asignaciones();
    }

    private void asignaciones() {
        tvExpAnio = (TextView) findViewById(R.id.tv_exp_anio);
        tvNroDoc = (TextView) findViewById(R.id.tv_nro_doc);
        tvRecurrente = (TextView) findViewById(R.id.tv_recurrente);
        tvDesExp = (TextView) findViewById(R.id.tv_des_exp);
        tvEstado = (TextView) findViewById(R.id.tv_estado);

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

    public List<Sedmovexp> getSetExpedientesList() throws ParseException, JSONException {

        sedmovexps = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(moviJson);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonMovimiento = jsonArray.getJSONObject(i);
                JSONObject jsonCodDepen = jsonMovimiento.getJSONObject("codDepen");
                JSONObject jsonNroEstexp = jsonMovimiento.getJSONObject("nroEstexp");

                Sedmovexp movimiento = new Sedmovexp();
                movimiento.setCodDepen(new Semdepen());
                movimiento.getCodDepen().setCodDepen(jsonCodDepen.getString("codDepen"));
                movimiento.getCodDepen().setDesDepen(jsonCodDepen.getString("desDepen"));

                movimiento.setNroEstexp(new Sebestexp());
                movimiento.getNroEstexp().setNroEstexp(jsonNroEstexp.getInt("nroEstexp"));
                movimiento.getNroEstexp().setDesEstexp(jsonNroEstexp.getString("desEstexp"));

                JSONObject jsonNroExpediente = jsonMovimiento.getJSONObject("nroExpediente");
                Semexpediente nroExpediente = new Semexpediente();
                nroExpediente.setNroCarpeta(jsonNroExpediente.getInt("nroCarpeta"));
                nroExpediente.setIndEjefiscar(jsonNroExpediente.getInt("indEjefiscar"));
                nroExpediente.setDesExpediente(jsonNroExpediente.getString("desExpediente"));
                JSONObject jsonEstadoExpediente = jsonNroExpediente.getJSONObject("nroEstexp");
                Sebestexp estadoExpediente = new Sebestexp();
                estadoExpediente.setNroEstexp(jsonEstadoExpediente.getInt("nroEstexp"));
                estadoExpediente.setDesEstexp(jsonEstadoExpediente.getString("desEstexp"));
                nroExpediente.setNroEstexp(estadoExpediente);
                JSONObject jsonNroTitular = jsonNroExpediente.getJSONObject("nroTitular");
                Sempersona nroTitular = new Sempersona();
                nroTitular.setNroDocide(jsonNroTitular.getString("nroDocide"));
                nroTitular.setDesPersona(jsonNroTitular.getString("desPersona"));
                nroExpediente.setNroTitular(nroTitular);
                movimiento.setNroExpediente(nroExpediente);

                JSONObject jsonNroTipmov = jsonMovimiento.getJSONObject("nroTipmov");
                Sebtipmov nroTipmov = new Sebtipmov();
                nroTipmov.setNroTipmov(jsonNroTipmov.getInt("nroTipmov"));
                nroTipmov.setDesTipmov(jsonNroTipmov.getString("desTipmov"));
                movimiento.setNroTipmov(nroTipmov);

                movimiento.setFecMovexp(dateFormat.parse(jsonMovimiento.getString("fecMovexp")));


                sedmovexps.add(movimiento);

                Log.d("-----------", "");
                Log.d("N° Exp. / Año:", movimiento.getNroExpediente().getNroCarpeta() + " / " + movimiento.getNroExpediente().getIndEjefiscar());
                Log.d("Nro. Documento: ", movimiento.getNroExpediente().getNroTitular().getNroDocide());
                Log.d("Recurrente:", movimiento.getNroExpediente().getNroTitular().getDesPersona());
                Log.d("Des. Exp.:", movimiento.getNroExpediente().getDesExpediente());
                Log.d("Estado:", movimiento.getNroExpediente().getNroEstexp().getDesEstexp());
                Log.d("Dependencia", movimiento.getCodDepen().getDesDepen());
                Log.d("Movimiento", movimiento.getNroTipmov().getDesTipmov());
                Log.d("Fecha", movimiento.getFecMovexp().toString());
                Log.d("-----------", "");
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        tvExpAnio.setText(sedmovexps.get(0).getNroExpediente().getNroCarpeta() + " / " + sedmovexps.get(0).getNroExpediente().getIndEjefiscar());
        tvNroDoc.setText(sedmovexps.get(0).getNroExpediente().getNroTitular().getNroDocide());
        tvRecurrente.setText(sedmovexps.get(0).getNroExpediente().getNroTitular().getDesPersona());
        tvDesExp.setText(sedmovexps.get(0).getNroExpediente().getDesExpediente());
        tvEstado.setText(sedmovexps.get(0).getNroExpediente().getNroEstexp().getDesEstexp());

        return sedmovexps;
    }
}
