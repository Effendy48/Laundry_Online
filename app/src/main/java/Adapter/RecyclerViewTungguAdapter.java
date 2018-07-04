package Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.jkt48.vbast.laundry_online.R;

import java.util.List;

import Model.getDataTungguAdapter;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by vbast on 23/05/18.
 */

public class RecyclerViewTungguAdapter
        extends RecyclerView.Adapter<RecyclerViewTungguAdapter.ViewHolder> {
    Context context;
    List<getDataTungguAdapter> getDataAdapterTunggu;
    Bitmap bitmap;

     int QRcodeWidth = 500;

     public RecyclerViewTungguAdapter(List<getDataTungguAdapter> getDataAdapterTunggu, Context context){
         this.context = context;
         this.getDataAdapterTunggu = getDataAdapterTunggu;
     }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tunggu,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final getDataTungguAdapter getDataTungguAdapter =
                getDataAdapterTunggu.get(position);
        holder.tv_kd_pesan.setText(getDataTungguAdapter.getKd_transaksi());
        holder.tv_nm_pesan.setText(getDataTungguAdapter.getNama_member());
        holder.tv_alamat_pesan.setText(getDataTungguAdapter.getAlamat());
        holder.tv_tgl_transaksi.setText(getDataTungguAdapter.getTgl_transaksi());
        holder.tv_status_pesan.setText(getDataTungguAdapter.getStatus());
        holder.tv_berat_pesan.setText(getDataTungguAdapter.getBerat()+" Kg");
        holder.tv_total_pesan.setText("Rp. "+ getDataTungguAdapter.getTotal());
        try {
            bitmap = TextToImageEncode(getDataTungguAdapter.getKd_transaksi());
            holder.img_kd_pesanan.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return getDataAdapterTunggu.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_kd_pesan,tv_nm_pesan,tv_alamat_pesan,tv_status_pesan,
                tv_berat_pesan,tv_total_pesan,tv_tgl_transaksi;
        ImageView img_kd_pesanan;
         public ViewHolder(View itemView){
             super(itemView);
             tv_kd_pesan = itemView.findViewById(R.id.tv_kode_pesan);
             tv_nm_pesan = itemView.findViewById(R.id.tv_nama_pesan);
             tv_alamat_pesan = itemView.findViewById(R.id.tv_alamat_pesan);
             tv_tgl_transaksi = itemView.findViewById(R.id.tv_tgl_pesan);
             tv_status_pesan = itemView.findViewById(R.id.tv_status_pesan);
             tv_berat_pesan = itemView.findViewById(R.id.tv_berat_pesan);
             tv_total_pesan = itemView.findViewById(R.id.tv_total_bayar_pesan);
             img_kd_pesanan = itemView.findViewById(R.id.img_kd_pesanan);
         }
     }

    Bitmap TextToImageEncode(String Value)throws WriterException {

        BitMatrix bitMatrix;
        try{
            bitMatrix = new MultiFormatWriter().encode(
                    Value, BarcodeFormat.DATA_MATRIX.QR_CODE, QRcodeWidth, QRcodeWidth,null

            );

        }catch (IllegalArgumentException IllegalArgumentException){
            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeigth = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeigth];
        for(int y = 0; y < bitMatrixHeigth; y++){
            int offset = y * bitMatrixWidth;

            for(int x = 0; x < bitMatrixHeigth; x++){
                pixels[offset + x] = bitMatrix.get(x,y) ? context.getResources().getColor(R.color.QRColorBlack):context.getResources().getColor(R.color.QRColorWhite);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeigth, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels,0,500,0,0, bitMatrixWidth,bitMatrixHeigth);
        return bitmap;
    }
}

