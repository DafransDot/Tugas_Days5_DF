package com.example.tugas_days5;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.translation.Translator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNamaPelanggan, etKodeBarang, etJumlahBarang;
    TextView tvpelanggan;

    private Button btnPesan, btnhasil1;
    private RadioButton rbGold, rbSilver, rbBiasa;
    private ArrayList<inputbarang> barang;
    private inputbarang pilihbarang;
    private double total = 0;
    private double potongan = 0;
    private double viewpotongan;
    private String tipepelanggan;
    private int Diskon;
    private double totalSetelahDiskon;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNamaPelanggan = findViewById(R.id.etNamaPelanggan);
        etKodeBarang = findViewById(R.id.etKodeBarang);
        etJumlahBarang = findViewById(R.id.etJumlahBarang);
        btnPesan = findViewById(R.id.btnPesan);
        rbGold = findViewById(R.id.rbGold);
        rbSilver = findViewById(R.id.rbSilver);
        rbBiasa = findViewById(R.id.rbBiasa);
        tvpelanggan = findViewById(R.id.tvpelanggan);


        btnPesan.setOnClickListener(this);


        barang = new ArrayList<>();
        barang.add(new inputbarang("AV4", "Asus Vivobook 14", 9150999));
        barang.add(new inputbarang("AA5", "Acer Aspire 5", 9999999));
        barang.add(new inputbarang("LV3", "Lenovo V14 Gen 3", 6666666));


    }


    public void ProsesPesanan() {
        String inputText = etKodeBarang.getText().toString().trim();
        try {
            String selectedId = inputText;
            pilihbarang = null;
            for (inputbarang brg : barang) {
                if (brg.getKodeBarang().equals(selectedId)) {
                    pilihbarang = brg;
                    break;
                }
            }
            if (pilihbarang != null) {
                total += pilihbarang.getHargabarang();
                Total();
            } else {
                Toast.makeText(this, "Kode barang tidak ditemukan.", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Masukkan ID pesanan yang valid.", Toast.LENGTH_SHORT).show();
        }
    }

    private void Total() {
        int jumlahBarang = Integer.parseInt(etJumlahBarang.getText().toString().trim());
        double totalSebelumdiskon = total * jumlahBarang;
        if (rbGold.isChecked()) {
            potongan = total * 0.1;
            tipepelanggan = "Gold";
        } else if (rbSilver.isChecked()) {
            potongan = total * 0.05;
            tipepelanggan = "silver";
        } else if (rbBiasa.isChecked()) {
            potongan = total * 0.02;
            tipepelanggan = "biasa";
        }

        if (totalSebelumdiskon >= 10000000) {
            Diskon = 100000;
            totalSetelahDiskon = totalSebelumdiskon - potongan - Diskon;
        } else {
            totalSetelahDiskon = totalSebelumdiskon - potongan;
        }

        viewpotongan = potongan;


        Intent intent = new Intent(MainActivity.this, Struk.class);
        intent.putExtra("total_pembelian", totalSetelahDiskon);
        intent.putExtra("total_Sebelum", totalSebelumdiskon);
        intent.putExtra("Harga_barang", pilihbarang.getHargabarang());
        intent.putExtra("kode_barang", pilihbarang.getKodeBarang());
        intent.putExtra("nama_barang", pilihbarang.getNamaBarang());
        intent.putExtra("jumlah_barang", jumlahBarang);
        intent.putExtra("Nama", etNamaPelanggan.getText().toString().trim());
        intent.putExtra("tipe", tipepelanggan);
        intent.putExtra("potongan", viewpotongan);
        intent.putExtra("diskon", Diskon);

        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPesan) {
            ProsesPesanan();

        }

    }

}