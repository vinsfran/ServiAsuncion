package py.gov.mca.serviasuncion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import py.gov.mca.serviasuncion.R;
import py.gov.mca.serviasuncion.entidades.Sempersona;
import py.gov.mca.serviasuncion.interfaces.RecyclerViewOnClickListenerHack;

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.MyViewHolder> {
    // Formato para SQL date time
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private List<Sempersona> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private DecimalFormat decimalFormat;

    public ListaPersonasAdapter(Context context, List<Sempersona> l) {
        this.mList = l;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_lista_personas, viewGroup, false);
        return (new MyViewHolder(v));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {
      //  myViewHolder.tvNombrePersona.setText(mList.get(position).getNombre());
      //  myViewHolder.tvCodigo.setText(String.valueOf(mList.get(position).getCodigo()));

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;

    }

    public void addListItem(Sempersona sempersona, int position) {
        mList.add(sempersona);
        notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void renovarLista(List<Sempersona> nuevaLista){
        mList = null;
        mList = nuevaLista;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView tvNombrePersona;
        public TextView tvCodigo;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNombrePersona = (TextView) itemView.findViewById(R.id.tv_nombre_persona);
            tvCodigo = (TextView) itemView.findViewById(R.id.tv_codigo);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onLongPressClickListener(v, getAdapterPosition());
            }
            return true;
        }
    }
}
