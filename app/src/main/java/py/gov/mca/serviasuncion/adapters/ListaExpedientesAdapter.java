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
import py.gov.mca.serviasuncion.entidades.Semexpediente;
import py.gov.mca.serviasuncion.interfaces.RecyclerViewOnClickListenerHack;

public class ListaExpedientesAdapter extends RecyclerView.Adapter<ListaExpedientesAdapter.MyViewHolder> {
    // Formato para SQL date time
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private List<Semexpediente> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private DecimalFormat decimalFormat;

    public ListaExpedientesAdapter(Context context, List<Semexpediente> l) {
        this.mList = l;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_lista_expedientes, viewGroup, false);
        return (new MyViewHolder(v));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {
        myViewHolder.tvNroExpAnio.setText(mList.get(position).getNroCarpeta() + " / " + mList.get(position).getIndEjefiscar());
        myViewHolder.tvDescripcion.setText(mList.get(position).getDesExpediente());
        //myViewHolder.tvUbicacionActual.setText(mList.get(position).getCodDepen().getDesDepen());
       // myViewHolder.tvFechaMovimiento.setText(dateFormat.format(mList.get(position).getFecUltmov()));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;

    }

    public void addListItem(Semexpediente semexpediente, int position) {
        mList.add(semexpediente);
        notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void renovarLista(List<Semexpediente> nuevaLista) {
        mList = null;
        mList = nuevaLista;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvNroExpAnio;
        public TextView tvDescripcion;
        //public TextView tvUbicacionActual;
        //public TextView tvFechaMovimiento;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNroExpAnio = (TextView) itemView.findViewById(R.id.tv_nro_exp_anio);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tv_descripcion);
            //tvUbicacionActual = (TextView) itemView.findViewById(R.id.tv_ubicacion_actual);
            //tvFechaMovimiento = (TextView) itemView.findViewById(R.id.tv_fecha_movimiento);

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
