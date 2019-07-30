package com.ahmad.gorentcar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.Calendar;
import java.util.Locale;

public class DetailSewaActivity extends AppCompatActivity {

    private TextView harga_mobil, nama_mobil, tahun_mobil, jml_penumpang, warna_mobil, transmisi_mobil;
    private ImageView gbr_mobil;
    private TextView mDisplayDate;
    private TextView mDisplayDateTwo;

    Button btn;

    //private String mDay, mMonth, mYear;
    //TextView mTv;
    //Button mBtn;

    //Calendar c;
    //DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sewa);

        mDisplayDate = (TextView) findViewById(R.id.tgl_pinjam);
        mDisplayDateTwo = (TextView) findViewById(R.id.tgl_kembali);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(DetailSewaActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                        mDisplayDate.setText("Tanggal Pinjam : "+dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        mDisplayDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(DetailSewaActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                        mDisplayDateTwo.setText("Tanggal Kembali : "+dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        harga_mobil = (TextView) findViewById(R.id.txt_harga_mobil);
        nama_mobil = (TextView) findViewById(R.id.txt_nama_mobil);
        tahun_mobil = (TextView) findViewById(R.id.txt_thn_mobil);
        jml_penumpang = (TextView) findViewById(R.id.txt_jml_penumpang);
        warna_mobil = (TextView) findViewById(R.id.txt_warna);
        transmisi_mobil = (TextView) findViewById(R.id.txt_transmisi);
        gbr_mobil = (ImageView) findViewById(R.id.img_mobil);

        //Intent intent = getIntent();
        //String mobil_harga = intent.getExtras().getString("txt_harga_mobil");
        //String mobil_nama = intent.getExtras().getString("txt_nama_mobil");
        //String mobil_tahun = intent.getExtras().getString("txt_tahun_mobil");
        //String jumlah_penumpang = intent.getExtras().getString("txt_jml_penumpang");
        //String mobil_warna = intent.getExtras().getString("txt_warna");
        //String transmisimobil = intent.getExtras().getString("txt_transmisi");
        //int gambar_mobil = intent.getExtras().getInt("img_mobil");

        //harga_mobil.setText(mobil_harga);
        //nama_mobil.setText(mobil_nama);
        //tahun_mobil.setText(mobil_tahun);
        //jml_penumpang.setText(jumlah_penumpang);
        //warna_mobil.setText(mobil_warna);
        //transmisi_mobil.setText(transmisimobil);
        //gbr_mobil.setImageResource(gambar_mobil);

        btn = findViewById(R.id.btn_sewa);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(DetailSewaActivity.this,TransaksiDua.class);
                 startActivity(inten);
            }
        });

    }
    public void sewa(View view) {
        Intent intent = new Intent(DetailSewaActivity.this, TransaksiDua.class);
        startActivity(intent);
    }
}
