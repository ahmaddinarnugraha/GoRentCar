package com.ahmad.gorentcar;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapterMobil extends RecyclerView.Adapter<RecyclerAdapterMobil.viewHolder> {
    private Context mContext;
    private List<Mobil> dataMobil;


    public RecyclerAdapterMobil(Context mContext, List<Mobil> dataMobil) {
        this.mContext = mContext;
        this.dataMobil = dataMobil;
    }

    @NonNull
    @Override
    public RecyclerAdapterMobil.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.itemview_mobil,viewGroup,false);
        viewHolder mviewHolder = new viewHolder(v);
        return new RecyclerAdapterMobil.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapterMobil.viewHolder viewHolder, final int i) {

        final String nama = dataMobil.get(i).getNama_mobil();
        final String warna = dataMobil.get(i).getWarna_mobil();
        final String transmisi = dataMobil.get(i).getTransmisi_mbl();
        final String penumpang = dataMobil.get(i).getJml_penumpang();
        final String cc = dataMobil.get(i).getCC_mobil();
        final String harga = dataMobil.get(i).getHarga_mbl();
        Glide.with(mContext).load(dataMobil.get(i).getGambar_mobil()).into(viewHolder.img_mobil);
        viewHolder.txt_nama_mobil.setText(dataMobil.get(i).getNama_mobil());

        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(mContext,DetailSewaActivity.class);
                mContext.startActivity(inten);
            }
        });


        viewHolder.setnama_mobil(nama);

    }

    @Override
    public int getItemCount() {
        return dataMobil.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView img_mobil;
        TextView txt_harga_mobil;
        TextView txt_nama_mobil;
        TextView txt_thn_mobil;
        TextView txt_jml_penumpang;
        TextView txt_transmisi;
        TextView txt_warna;
        CardView cardView;
        Button btn;
        public viewHolder (@NonNull View itemView ){
            super(itemView);
             img_mobil = (ImageView) itemView.findViewById(R.id.img_mobil);
             txt_harga_mobil = (TextView) itemView.findViewById(R.id.txt_harga_mobil);
             txt_nama_mobil = (TextView) itemView.findViewById(R.id.txt_nama_mobil);
             txt_thn_mobil = (TextView) itemView.findViewById(R.id.txt_thn_mobil);
             txt_jml_penumpang = (TextView) itemView.findViewById(R.id.txt_jml_penumpang);
             txt_transmisi = (TextView) itemView.findViewById(R.id.txt_transmisi);
             txt_warna = (TextView) itemView.findViewById(R.id.txt_warna);
             cardView = (CardView) itemView.findViewById(R.id.cardView_mobil);
             btn = (Button) itemView.findViewById(R.id.btn_sewa);



            }

            public void setnama_mobil(String nama_mobil){
                txt_nama_mobil = itemView.findViewById(R.id.txt_nama_mobil);
                txt_nama_mobil.setText(nama_mobil);
            }

        }

}

