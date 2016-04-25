package py.gov.mca.serviasuncion.red;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

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

import me.drakeet.materialdialog.MaterialDialog;
import py.gov.mca.serviasuncion.ListaMovimientosActivity;
import py.gov.mca.serviasuncion.MyApplication;
import py.gov.mca.serviasuncion.R;

/**
 * Created by vinsfran on 25/04/16.
 */
public class SisExpedientesWsClient {

    private ProgressDialog pDialog;
    private MaterialDialog mMaterialDialog;
    private String moviJson;


    private void consultarMovimientos(Integer nroCarpeta, Integer indEjefiscar) {
        JSONObject dato = new JSONObject();
        try {
            dato.put("nroCarpeta", indEjefiscar);
            dato.put("indEjefiscar", indEjefiscar);
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
                            if(!moviJson.equals("[]")){
                               /* Intent intent = new Intent(BuscarExpActivity.this, ListaMovimientosActivity.class);
                                intent.putExtra("moviJson", moviJson);
                                startActivity(intent);*/
                                hidePDialog();
                            } else {
                                hidePDialog();
                                mMaterialDialog.setMessage(R.string.txt_no_existe_expediente);
                                mMaterialDialog.show();
                            }
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
