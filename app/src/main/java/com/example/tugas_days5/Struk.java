package com.example.tugas_days5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Struk extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTotalBayarStruk,tvTotalHargaStruk,tvKodeBarang,tvnamaBarang,tvHargaBarang,tvJumlahBarang,tvNama,tvmember,tvdiskon,rbgold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk);
        DecimalFormat formatter = new DecimalFormat("#,###.##");

        double totalBayar = getIntent().getDoubleExtra("total_pembelian", 0);
        double totalHarga = getIntent().getDoubleExtra("total_Sebelum", 0);
        double HargaBarang = getIntent().getDoubleExtra("Harga_barang", 0);
        double JumlahBarang = getIntent().getIntExtra("jumlah_barang", 0);
        double Potongan = getIntent().getDoubleExtra("potongan",0);
        String Kodebarang = getIntent().getStringExtra("kode_barang" );
        String Namabarang = getIntent().getStringExtra("nama_barang" );
        String Nama = getIntent().getStringExtra("Nama" );
        String tipe = getIntent().getStringExtra("tipe");
        int diskon = getIntent().getIntExtra("diskon",0);

        tvTotalBayarStruk = findViewById(R.id.tvTotalBayarStruk);
        tvTotalHargaStruk = findViewById(R.id.tvTotalHargaStruk);
        tvKodeBarang = findViewById(R.id.tvKodeBarangStruk);
        tvnamaBarang = findViewById(R.id.tvNamaBarangStruk);
        tvHargaBarang = findViewById(R.id.tvHargaBarangStruk);
        tvJumlahBarang = findViewById(R.id.tvJumlahBarangStruk);
        tvNama = findViewById(R.id.tvNamaPelangganStruk);
        rbgold = findViewById(R.id.tvTipePelangganStruk);
        tvmember = findViewById(R.id.tvDiscountMemberStruk);
        tvdiskon = findViewById(R.id.tvDiscountBarangStruk);
        Button btnShare = findViewById(R.id.btnShare);

        tvTotalBayarStruk.setText(formatter.format( totalBayar));
        tvTotalHargaStruk.setText(formatter.format(totalHarga));
        tvKodeBarang.setText(""+Kodebarang);
        tvnamaBarang.setText(""+Namabarang);
        tvHargaBarang.setText(formatter.format(HargaBarang));
        tvJumlahBarang.setText(""+JumlahBarang);
        tvNama.setText(""+Nama);
        rbgold.setText(" "+tipe);
        tvmember.setText(formatter.format(Potongan));
        tvdiskon.setText(formatter.format(diskon));

        btnShare.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.btnShare){
            shareStruk();
        }

    }
    private void shareStruk() {
        String strukContent = "Kode Barang: " + tvKodeBarang.getText().toString() + "\n" +
                "Nama Barang: " + tvnamaBarang.getText().toString() + "\n" +
                "Harga Barang: " + tvHargaBarang.getText().toString() + "\n" +
                "Jumlah Barang: " + tvJumlahBarang.getText().toString() + "\n" +
                "Nama Pelanggan: " + tvNama.getText().toString() + "\n" +
                "Tipe Pelanggan: " + rbgold.getText().toString() + "\n" +
                "Discount Member: " + tvmember.getText().toString() + "\n" +
                "Discount Barang: " + tvdiskon.getText().toString() + "\n" +
                "Total Pembayaran: " + tvTotalBayarStruk.getText().toString() + "\n" +
                "Total Harga: " + tvTotalHargaStruk.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, strukContent);
        startActivity(Intent.createChooser(shareIntent, "Bagikan struk melalui"));
    }
}