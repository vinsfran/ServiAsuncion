package py.gov.mca.serviasuncion.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.ParseException;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;
import py.gov.mca.serviasuncion.ListaExpedientesActivity;
import py.gov.mca.serviasuncion.ListaMovimientosActivity;
import py.gov.mca.serviasuncion.MyApplication;
import py.gov.mca.serviasuncion.R;
import py.gov.mca.serviasuncion.adapters.ListaExpedientesAdapter;
import py.gov.mca.serviasuncion.entidades.Semexpediente;
import py.gov.mca.serviasuncion.interfaces.RecyclerViewOnClickListenerHack;


public class ListaExpedientesFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private ProgressDialog pDialog;
    private MaterialDialog mMaterialDialog;

    private RecyclerView mRecyclerView;
    private List<Semexpediente> mList;

    private String moviJson;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_expedientes, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list_expedientes);
        mRecyclerView.setHasFixedSize(true);
      /*  mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                BancasAdapter adapter = (BancasAdapter) mRecyclerView.getAdapter();
                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Banca> listAux = ((BancasActivity) getActivity()).getSetBancasList(10);
                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }

            }
        });*/
        //  mRecyclerView.addOnItemTouchListener(new ReclyclerViewTouchListener(getActivity(), mRecyclerView, this));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        try {
            mList = ((ListaExpedientesActivity) getActivity()).getSetExpedientesList();
            //if(mList.size() == 1){
            //    consultarMovimientos(mList.get(0).getNroCarpeta(), mList.get(0).getIndEjefiscar());
            //}
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListaExpedientesAdapter adapter = new ListaExpedientesAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClickListener(final View view, final int position) {
       // Toast.makeText(getActivity(), "onClickListener()" + position + mList.get(position).getDesExpediente(), Toast.LENGTH_SHORT).show();

        consultarMovimientos(mList.get(position).getNroCarpeta(), mList.get(position).getIndEjefiscar());



        //Intent intent = new Intent(getActivity(), ListaMovimientosActivity.class);
        //startActivity(intent);
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        //  Toast.makeText(getActivity(), "onLongPressClickListener()" + position + mList.get(position).getNumero(), Toast.LENGTH_SHORT).show();

    }

   /* private static class ReclyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public ReclyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh) {
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());
                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv, rv.getChildAdapterPosition(cv));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());
                    // Toast.makeText(cv.getContext(), "Eliminar banca333 ", Toast.LENGTH_SHORT).show();
                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onClickListener(cv, rv.getChildAdapterPosition(cv));
                    }

                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && mRecyclerViewOnClickListenerHack != null && mGestureDetector.onTouchEvent(e)) {
                mRecyclerViewOnClickListenerHack.onClickListener(child, rv.getChildAdapterPosition(child));
            }
            return false;


          //  mGestureDetector.onTouchEvent(e);
         //   return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }*/

    private void consultarMovimientos(Integer nroCarpeta, Integer indEjefiscar) {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Realizando consulta...");
        showpDialog();
        JSONObject dato = new JSONObject();
        try {
            dato.put("nroCarpeta", nroCarpeta);
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
                            if (!moviJson.equals("[]")) {
                                Intent intent = new Intent(getActivity(), ListaMovimientosActivity.class);
                                intent.putExtra("moviJson", moviJson);
                                startActivity(intent);
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
}