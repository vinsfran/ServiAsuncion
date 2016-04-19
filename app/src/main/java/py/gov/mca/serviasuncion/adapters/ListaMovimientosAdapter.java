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
import py.gov.mca.serviasuncion.entidades.Sedmovexp;
import py.gov.mca.serviasuncion.interfaces.RecyclerViewOnClickListenerHack;

public class ListaMovimientosAdapter extends RecyclerView.Adapter<ListaMovimientosAdapter.MyViewHolder> {
    // Formato para SQL date time
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private List<Sedmovexp> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private DecimalFormat decimalFormat;

    public ListaMovimientosAdapter(Context context, List<Sedmovexp> l) {
        this.mList = l;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_lista_movimientos, viewGroup, false);
        return (new MyViewHolder(v));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {
        myViewHolder.tvDependencia.setText(mList.get(position).getCodDepen().getDesDepen());
        myViewHolder.tvMovimiento.setText(mList.get(position).getNroTipmov().getDesTipmov());
        myViewHolder.tvFecha.setText(dateFormat.format(mList.get(position).getFecMovexp()));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;

    }

    public void addListItem(Sedmovexp sedmovexp, int position) {
        mList.add(sedmovexp);
        notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void renovarLista(List<Sedmovexp> nuevaLista){
        mList = null;
        mList = nuevaLista;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView tvDependencia;
        public TextView tvMovimiento;
        public TextView tvFecha;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDependencia = (TextView) itemView.findViewById(R.id.tv_dependencia);
            tvMovimiento = (TextView) itemView.findViewById(R.id.tv_movimiento);
            tvFecha = (TextView) itemView.findViewById(R.id.tv_fecha);
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
