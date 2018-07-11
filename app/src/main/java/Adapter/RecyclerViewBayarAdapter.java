package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jkt48.vbast.laundry_online.R;

import java.util.List;

import Model.getDataBayarAdapter;

/**
 * Created by vbast on 04/07/18.
 */

public class RecyclerViewBayarAdapter extends RecyclerView.Adapter<RecyclerViewBayarAdapter.ViewHolder>{
    Context context;
    List<getDataBayarAdapter> getDataBayarAdapters;

    public RecyclerViewBayarAdapter (List<getDataBayarAdapter> getDataBayarAdapters,Context context){
        this.getDataBayarAdapters = getDataBayarAdapters;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(context).inflate(R.layout.item_bayar,parent,false);
        return new ViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final getDataBayarAdapter getDataBayarAdapter = getDataBayarAdapters.get(position);
        holder.kd_member.setText(getDataBayarAdapter.getKd_member());
        holder.kd_transaksi.setText(getDataBayarAdapter.getKd_transaksi());
        holder.tgl_transaksi.setText(getDataBayarAdapter.getTgl_transaksi());
        holder.alamat.setText(getDataBayarAdapter.getAlamat());
        holder.berat.setText(getDataBayarAdapter.getBerat());
        holder.total.setText(getDataBayarAdapter.getTotal());
    }

    @Override
    public int getItemCount() {
        return getDataBayarAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView kd_member,kd_transaksi,alamat,tgl_transaksi,berat,total;
        public ViewHolder(View itemView) {
            super(itemView);
            kd_member = itemView.findViewById(R.id.tv_kode_member_bayar);
            kd_transaksi = itemView.findViewById(R.id.tv_kode_transaksi_bayar);
            alamat = itemView.findViewById(R.id.tv_alamat_bayar);
            tgl_transaksi = itemView.findViewById(R.id.tv_tgl_transaksi_bayar);
            berat = itemView.findViewById(R.id.tv_berat_bayar);
            total = itemView.findViewById(R.id.tv_total);
        }
    }
}
