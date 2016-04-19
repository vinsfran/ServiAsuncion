package py.gov.mca.serviasuncion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

public class BuscarExpActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ProgressDialog pDialog;
    private MaterialDialog mMaterialDialog;
    public ImageButton imageButtonBuscarNroExp;
    private TextView textViewBuscarNroExp;
    private View.OnClickListener listenerBuscarNroExp;

    private EditText etNroExp;
    private EditText etAnio;

    private String moviJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_buscar_exp);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageButtonBuscarNroExp = (ImageButton) findViewById(R.id.image_button_buscar_nro_exp);
        textViewBuscarNroExp = (TextView) findViewById(R.id.text_view_buscar_nro_exp);

        asignaciones();
        asignacionBuscarNroExpListener();
    }

    private void asignaciones() {
        etNroExp = (EditText) findViewById(R.id.et_nro_exp);
        etAnio = (EditText) findViewById(R.id.et_anio);
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

    private void asignacionBuscarNroExpListener() {
        listenerBuscarNroExp = new View.OnClickListener() {
            public void onClick(View v) {
                buscarNroExpClicado(v);
            }
        };
        imageButtonBuscarNroExp.setOnClickListener(listenerBuscarNroExp);
        textViewBuscarNroExp.setOnClickListener(listenerBuscarNroExp);
    }

    protected void buscarNroExpClicado(View view) {
        mMaterialDialog = new MaterialDialog(view.getContext());
        mMaterialDialog.setTitle(R.string.txt_atencion);
        mMaterialDialog.setPositiveButton(R.string.txt_aceptar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
            }
        });
        consultar();



    }

    public void consultar() {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Realizando consulta...");
        showpDialog();
        JSONObject dato = new JSONObject();
        try {
            dato.put("nroCarpeta", Integer.parseInt(etNroExp.getText().toString().trim()));
            dato.put("indEjefiscar", Integer.parseInt(etAnio.getText().toString().trim()));
            Log.d("dato", dato.toString());
            RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
            final JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,
                    "http://appserver.mca.gov.py/expediente/recursosweb/expedientes/listarMovimientosPorNroCarpetaEjerFiscar/",
                    dato,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d("response", response.toString());
                            moviJson = response.toString();
                            Intent intent = new Intent(BuscarExpActivity.this, ListaMovimientosActivity.class);
                            intent.putExtra("moviJson", moviJson);
                            startActivity(intent);
                            hidePDialog();
                            /*if (!response.isNull("Respuesta")) {
                                try {
                                    String respuesta = response.getString("Respuesta");
                                    String fechaBt = response.getString("FechaBT");
                                    Integer minIntegrantes = response.getInt("MinIntegrantes");
                                    Integer maxIntegrantes = response.getInt("MaxIntegrantes");
                                    Integer contadorBanca = response.getInt("ContadorBanca");
                                    if (respuesta.equals("OK")) {
                                        if (!fechaBt.equals("0000-00-00")) {
                                            hidePDialog();
                                            ingresarSistema(fechaBt, minIntegrantes, maxIntegrantes, contadorBanca);
                                        } else {
                                            hidePDialog();
                                            mMaterialDialog.setMessage("No se pudo obtener fecha de BT");
                                            mMaterialDialog.show();
                                        }
                                    } else {
                                        hidePDialog();
                                        mMaterialDialog.setMessage(respuesta);
                                        mMaterialDialog.show();
                                    }
                                } catch (JSONException e) {
                                    hidePDialog();
                                    mMaterialDialog.setTitle(R.string.txt_titulo_alerta_json_exception);
                                    mMaterialDialog.setMessage(e.getMessage());
                                    mMaterialDialog.show();
                                }
                            } else {
                                hidePDialog();
                                mMaterialDialog.setMessage("No hay respuesta del servidor");
                                mMaterialDialog.show();
                            }*/
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    hidePDialog();
                    mMaterialDialog.setTitle(R.string.txt_titulo_alerta_conectividad_error);
                    String mensaje = "Error code: ";
                    if(error.networkResponse == null){
                        mensaje = "No se pudo realizar la operación intente de nuevo";
                    }else{
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

}
