package py.gov.mca.serviasuncion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;
import py.gov.mca.serviasuncion.ListaExpedientesActivity;
import py.gov.mca.serviasuncion.ListaMovimientosActivity;
import py.gov.mca.serviasuncion.R;
import py.gov.mca.serviasuncion.adapters.ListaExpedientesAdapter;
import py.gov.mca.serviasuncion.entidades.Semexpediente;
import py.gov.mca.serviasuncion.interfaces.RecyclerViewOnClickListenerHack;


public class ListaExpedientesFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<Semexpediente> mList;

    private MaterialDialog mMaterialDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_expedientes, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
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
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ListaExpedientesAdapter adapter = new ListaExpedientesAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClickListener(final View view, final int position) {
      /*  Toast.makeText(getActivity(), "onClickListener()" + position + mList.get(position).getNumero(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ListaMovimientosActivity.class);
        startActivity(intent);*/
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
}