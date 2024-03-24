package com.example.tugas_days5;

import java.util.ArrayList;

class inputbarang {
    private String kodeBarang;
    private String namaBarang;
    private Double hargabarang;

    public inputbarang(String kodeBarang, String namaBarang, double hargabarang){
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargabarang = hargabarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public Double getHargabarang() {
        return hargabarang;
    }

}
public class barang {
    public static void main(String[] args) {
        ArrayList<inputbarang> barang = new ArrayList<>();
        barang.add(new inputbarang("AV4", "Asus Vivobook 14", 9150999));
        barang.add(new inputbarang("AA5", "Acer Aspire 5", 9999999));
        barang.add(new inputbarang("LV3", "Lenovo V14 Gen 3", 6666666));
    }
}
