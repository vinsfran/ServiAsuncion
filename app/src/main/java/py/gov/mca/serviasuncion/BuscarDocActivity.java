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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;
import py.gov.mca.serviasuncion.entidades.Sebtipdocide;

public class BuscarDocActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ProgressDialog pDialog;
    private MaterialDialog mMaterialDialog;

    public ImageButton imageButtonBuscarNroDoc;
    private TextView textViewBuscarNroDoc;
    private View.OnClickListener listenerBuscarNroDoc;

    private EditText etNroDoc;
    private Spinner spTipDoc;

    private String expeJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_buscar_doc);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        asignaciones();
        asignacionBuscarNroDocListener();

    }

    private void asignaciones() {
        etNroDoc = (EditText) findViewById(R.id.et_nro_doc);
        spTipDoc = (Spinner) findViewById(R.id.sp_tip_doc);
        imageButtonBuscarNroDoc = (ImageButton) findViewById(R.id.image_button_buscar_nro_doc);
        textViewBuscarNroDoc = (TextView) findViewById(R.id.text_view_buscar_nro_doc);
        agregarItemsSpTipDoc();
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

   /* public void asignacionListenerSpinnerTipoDucumento() {
        //Metodo necesario si se necesita realizar alguna accion cuando se selecciona un item de la lista

        spinnerTipoDocumentoIntegrante.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {




                // CI = 2, RUC = 20, PAS = 6, CRP = 4, VAR = 5
                String sTipoDocumento = parent.getItemAtPosition(position).toString();
                if (sTipoDocumento.equals("CI")) {
                    tipoDocumento = 2;
                } else if (sTipoDocumento.equals("RUC")) {
                    tipoDocumento = 20;
                } else if (sTipoDocumento.equals("PAS")) {
                    tipoDocumento = 6;
                } else if (sTipoDocumento.equals("CRP")) {
                    tipoDocumento = 4;
                } else if (sTipoDocumento.equals("VAR")) {
                    tipoDocumento = 5;
                } else {
                    tipoDocumento = 0;
                }
                Toast.makeText(parent.getContext(),
                        "OnItemSelectedListener : " + parent.getItemAtPosition(position) + " " + tipoDocumento,
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }*/

    public void agregarItemsSpTipDoc() {
        Sebtipdocide tipDocCi = new Sebtipdocide();
        tipDocCi.setIndTipdocide("CIPN");
        tipDocCi.setDesTipdocide("CEDULA DE IDENTIDAD POLICIAL");
        Sebtipdocide tipDocRuc = new Sebtipdocide();
        tipDocRuc.setIndTipdocide("RUC");
        tipDocRuc.setDesTipdocide("RUC");
        List<Sebtipdocide> tipoDocumentos = new ArrayList<>();
        tipoDocumentos.add(tipDocCi);
        tipoDocumentos.add(tipDocRuc);
        ArrayAdapter<Sebtipdocide> dataAdapter = new ArrayAdapter<>(this, R.layout.item_spinners, tipoDocumentos);
        dataAdapter.setDropDownViewResource(R.layout.items_spinners);
        spTipDoc.setAdapter(dataAdapter);
       /* for (int i = 0; i < dataAdapter.getCount(); i++) {
            if (dataAdapter.getItem(i).getTipDocCodigo().equals(tipoDoc)) {
                spinnerTipoDocumentoIntegrante.setSelection(dataAdapter.getPosition(dataAdapter.getItem(i)));
            }
        }*/

    }

    private void asignacionBuscarNroDocListener() {
        listenerBuscarNroDoc = new View.OnClickListener() {
            public void onClick(View v) {
                buscarNroDocClicado(v);
            }
        };
        imageButtonBuscarNroDoc.setOnClickListener(listenerBuscarNroDoc);
        textViewBuscarNroDoc.setOnClickListener(listenerBuscarNroDoc);
    }

    protected void buscarNroDocClicado(View view) {
        mMaterialDialog = new MaterialDialog(view.getContext());
        mMaterialDialog.setTitle(R.string.txt_atencion);
        mMaterialDialog.setPositiveButton(R.string.txt_aceptar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
            }
        });
        if (etNroDoc.getText().toString().trim().equals("")) {
            mMaterialDialog.setMessage(R.string.txt_mensaje_alerta_campo_vacio);
            mMaterialDialog.show();
        } else {
            consultar();
        }

    }

    public void consultar() {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Realizando consulta...");
        showpDialog();
        JSONObject dato = new JSONObject();
        try {
            Sebtipdocide tipoDocumento = new Sebtipdocide();
            tipoDocumento = (Sebtipdocide) spTipDoc.getSelectedItem();


            dato.put("nroDocide", etNroDoc.getText().toString().trim());
            dato.put("indTipdocide", tipoDocumento.getIndTipdocide());
            Log.d("dato", dato.toString());
            RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
            final JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,
                    "http://appserver.mca.gov.py/expediente/recursosweb/expedientes/listarExpedientesPorNroDocideIndTipdocide/",
                    dato,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d("response", response.toString());
                            expeJson = response.toString();
                            if(!expeJson.equals("[]")){
                                Intent intent = new Intent(BuscarDocActivity.this, ListaExpedientesActivity.class);
                                intent.putExtra("expeJson", expeJson);
                                startActivity(intent);
                            } else {
                                mMaterialDialog.setMessage(R.string.txt_no_tiene_expedientes);
                                mMaterialDialog.show();
                            }
                            hidePDialog();
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
