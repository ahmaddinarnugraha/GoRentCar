package com.ahmad.gorentcar;

import android.content.Intent;
import android.view.View;

public class Mobil {

    private String CC_mobil;
    private String gambar_mobil;
    private String harga_mbl;
    private String jml_penumpang;
    private String nama_mobil;
    private String tahun_mobil;
    private String transmisi_mbl;
    private String warna_mobil;

    public Mobil(String CC_mobil, String gambar_mobil, String harga_mbl, String jml_penumpang, String nama_mobil, String tahun_mobil, String transmisi_mbl, String warna_mobil) {
        this.CC_mobil = CC_mobil;
        this.gambar_mobil = gambar_mobil;
        this.harga_mbl = harga_mbl;
        this.jml_penumpang = jml_penumpang;
        this.nama_mobil = nama_mobil;
        this.tahun_mobil = tahun_mobil;
        this.transmisi_mbl = transmisi_mbl;
        this.warna_mobil = warna_mobil;
    }

    public Mobil() {
    }

    public String getCC_mobil() {
        return CC_mobil;
    }

    public void setCC_mobil(String CC_mobil) {
        this.CC_mobil = CC_mobil;
    }

    public String getGambar_mobil() {
        return gambar_mobil;
    }

    public void setGambar_mobil(String gambar_mobil) {
        this.gambar_mobil = gambar_mobil;
    }

    public String getHarga_mbl() {
        return harga_mbl;
    }

    public void setHarga_mbl(String harga_mbl) {
        this.harga_mbl = harga_mbl;
    }

    public String getJml_penumpang() {
        return jml_penumpang;
    }

    public void setJml_penumpang(String jml_penumpang) {
        this.jml_penumpang = jml_penumpang;
    }

    public String getNama_mobil() {
        return nama_mobil;
    }

    public void setNama_mobil(String nama_mobil) {
        this.nama_mobil = nama_mobil;
    }

    public String getTahun_mobil() {
        return tahun_mobil;
    }

    public void setTahun_mobil(String tahun_mobil) {
        this.tahun_mobil = tahun_mobil;
    }

    public String getTransmisi_mbl() {
        return transmisi_mbl;
    }

    public void setTransmisi_mbl(String transmisi_mbl) {
        this.transmisi_mbl = transmisi_mbl;
    }

    public String getWarna_mobil() {
        return warna_mobil;
    }

    public void setWarna_mobil(String warna_mobil) {
        this.warna_mobil = warna_mobil;
    }

    @Override
    public String toString() {
        return  " "+ CC_mobil + "\n" +
                " "+ gambar_mobil + "\n" +
                " "+ harga_mbl + "\n" +
                " "+ jml_penumpang + "\n" +
                " "+ nama_mobil + "\n" +
                " "+ tahun_mobil + "\n" +
                " "+ transmisi_mbl + "\n" +
                " "+ warna_mobil;

    }
}
