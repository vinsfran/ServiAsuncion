package py.gov.mca.serviasuncion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;
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
    private ProgressDialog pDialog;
    private MaterialDialog mMaterialDialog;

    private TextView tvExpAnio;
    private TextView tvNroDoc;
    private TextView tvRecurrente;
    private TextView tvDesExp;
    private TextView tvEstado;

    private String moviJson;
    private List<Sedmovexp> sedmovexps;

    private FloatingActionButton fabEnviarMail;

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
        fabEnviarMail = (FloatingActionButton) findViewById(R.id.fab_enviar_mail);
        fabEnviarMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mostrarDialogoEnvioMail(view);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        fabEnviarMail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.txt_enviar_correo, Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    private void asignaciones() {
        tvExpAnio = (TextView) findViewById(R.id.tv_exp_anio);
        tvNroDoc = (TextView) findViewById(R.id.tv_nro_doc);
        tvRecurrente = (TextView) findViewById(R.id.tv_recurrente);
        tvDesExp = (TextView) findViewById(R.id.tv_des_exp);
        tvEstado = (TextView) findViewById(R.id.tv_estado);

    }

    protected void mostrarDialogoEnvioMail(View v) throws ParseException {
        LayoutInflater li = LayoutInflater.from(v.getContext());
        View promptsView = li.inflate(R.layout.dialogo_enviar_mail, null);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.et_mail);
        final TextView mensaje = (TextView) promptsView.findViewById(R.id.tv_mensaje);
        mMaterialDialog = new MaterialDialog(this)
                .setTitle(R.string.txt_enviar_correo)
                .setContentView(promptsView)
                .setPositiveButton(R.string.txt_aceptar, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userInput.getText().toString().trim().equals("")) {
                            mensaje.setText(R.string.txt_mensaje_alerta_campo_vacio);
                        } else {
                            enviarCorreoWS(userInput.getText().toString().trim());
                            mMaterialDialog.dismiss();
                            Toast.makeText(getApplicationContext(), R.string.txt_correo_enviado, Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.txt_cancelar, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });
        mMaterialDialog.show();
    }

    public void enviarCorreoWS(String mail) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Endiando correo...");
        showpDialog();
        JSONObject dato = new JSONObject();
        try {
            dato.put("mail", mail);
            Log.d("dato", dato.toString());
            RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                    "http://appserver.mca.gov.py/expediente/recursosweb/expedientes/enviarCorreo/",
                    dato,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("response", response.toString());
                            String respuesta = null;
                            try {
                                respuesta = response.getString("status");
                                if (respuesta.equals("OK")) {
                                    hidePDialog();
                                } else {
                                    hidePDialog();
                                    mMaterialDialog.setMessage(respuesta);
                                    mMaterialDialog.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    hidePDialog();
                    mMaterialDialog.setTitle(R.string.txt_titulo_alerta_conectividad_error);
                    String mensaje = "Error code: ";
                    if (error.networkResponse == null) {
                        mensaje = "No se pudo realizar la operación intente de nuevo";
                    } else {
                        mensaje = mensaje + String.valueOf(error.networkResponse.statusCode);
                    }
                    mMaterialDialog.setMessage(mensaje);
                    mMaterialDialog.show();
                }
            });
            request.setShouldCache(false);
            request.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(request);
        } catch (JSONException e) {
            hidePDialog();
            mMaterialDialog.setTitle(R.string.txt_titulo_alerta_json_exception);
            mMaterialDialog.setMessage(e.getMessage());
            mMaterialDialog.show();
        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hidePDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_lista_movimientos, menu);
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
                Sedmovexp movimiento = new Sedmovexp();

                JSONObject jsonCodDepenMovi = jsonMovimiento.getJSONObject("codDepen");
                Semdepen codDepenMovi = new Semdepen();
                codDepenMovi.setCodDepen(jsonCodDepenMovi.getString("codDepen"));
                codDepenMovi.setDesDepen(jsonCodDepenMovi.getString("desDepen"));
                movimiento.setCodDepen(codDepenMovi);

                JSONObject jsonNroEstexpMovi = jsonMovimiento.getJSONObject("nroEstexp");
                Sebestexp nroEstexpMovi = new Sebestexp();
                nroEstexpMovi.setNroEstexp(jsonNroEstexpMovi.getInt("nroEstexp"));
                nroEstexpMovi.setDesEstexp(jsonNroEstexpMovi.getString("desEstexp"));
                movimiento.setNroEstexp(nroEstexpMovi);

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
        tvExpAnio.setText(sedmovexps.get(0).getNroExpediente().getNroCarpeta() + " / " + sedmovexps.get(0).getNroExpediente().getIndEjefiscar());
        tvNroDoc.setText(sedmovexps.get(0).getNroExpediente().getNroTitular().getNroDocide());
        tvRecurrente.setText(sedmovexps.get(0).getNroExpediente().getNroTitular().getDesPersona());
        tvDesExp.setText(sedmovexps.get(0).getNroExpediente().getDesExpediente());
        tvEstado.setText(sedmovexps.get(0).getNroExpediente().getNroEstexp().getDesEstexp());

        return sedmovexps;
    }
}
