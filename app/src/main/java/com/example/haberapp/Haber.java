package com.example.haberapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Haber {

    int idhaber;
    String haberresimurl;
    String haberbaslik;
    String habericerik;
    String haberturu;
    String yayınlanmatarihi;
    int begenmesayisi;
    int begenmemesayisi;
    int goruntulenmesayisi;
    public static ArrayList<Haber> gosterilecekler;

    public Haber(){

    }

    public Haber(int idhaber, String haberresimurl, String haberbaslik, String habericerik, String haberturu, String yayınlanmatarihi, int begenmesayisi, int begenmemesayisi, int goruntulenmesayisi) {
        this.idhaber = idhaber;
        this.haberresimurl = haberresimurl;
        this.haberbaslik = haberbaslik;
        this.habericerik = habericerik;
        this.haberturu = haberturu;
        this.yayınlanmatarihi = yayınlanmatarihi;
        this.begenmesayisi = begenmesayisi;
        this.begenmemesayisi = begenmemesayisi;
        this.goruntulenmesayisi = goruntulenmesayisi;
    }

    public int getIdhaber() {
        return idhaber;
    }

    public void setIdhaber(int idhaber) {
        this.idhaber = idhaber;
    }

    public String getHaberresimurl() {
        return haberresimurl;
    }

    public void setHaberresimurl(String haberresimurl) {
        this.haberresimurl = haberresimurl;
    }

    public String getHaberbaslik() {
        return haberbaslik;
    }

    public void setHaberbaslik(String haberbaslik) {
        this.haberbaslik = haberbaslik;
    }

    public String getHabericerik() {
        return habericerik;
    }

    public void setHabericerik(String habericerik) {
        this.habericerik = habericerik;
    }

    public String getHaberturu() {
        return haberturu;
    }

    public void setHaberturu(String haberturu) {
        this.haberturu = haberturu;
    }

    public String getYayınlanmatarihi() {
        return yayınlanmatarihi;
    }

    public void setYayınlanmatarihi(String yayınlanmatarihi) {
        this.yayınlanmatarihi = yayınlanmatarihi;
    }

    public int getBegenmesayisi() {
        return begenmesayisi;
    }

    public void setBegenmesayisi(int begenmesayisi) {
        this.begenmesayisi = begenmesayisi;
    }

    public int getBegenmemesayisi() {
        return begenmemesayisi;
    }

    public void setBegenmemesayisi(int begenmemesayisi) {
        this.begenmemesayisi = begenmemesayisi;
    }

    public int getGoruntulenmesayisi() {
        return goruntulenmesayisi;
    }

    public void setGoruntulenmesayisi(int goruntulenmesayisi) {
        this.goruntulenmesayisi = goruntulenmesayisi;
    }


    public static ArrayList<Haber> createContactsList(int numContacts) {
        ArrayList<Haber> contacts = new ArrayList<Haber>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Haber(i, "", "başlık","içerik", "tür", "", 1, 1, 1));
        }

        return contacts;
    }

    public static ArrayList<Haber> createListForViewing(JSONArray array) throws JSONException {
        gosterilecekler = new ArrayList<Haber>();

        for(int i=0;i<array.length();i++){
            JSONObject data = array.getJSONObject(i);
            int id = data.getInt("idhaber");
            String resimurl= data.getString("haberresim");
            String baslik = data.getString("haberbaslik");
            String icerik = data.getString("habericerik");
            String tarih = data.getString("yayinlanmatarihi");
            String tur = data.getString("haberturu");
            int begenme = data.getInt("begenmesayisi");
            int begenmeme = data.getInt("begenmemesayisi");
            int goruntulenme = data.getInt("goruntulenmesayisi");
            gosterilecekler.add(new Haber(id, resimurl, baslik,icerik, tur, tarih, begenme, begenmeme, goruntulenme));
        }

        return gosterilecekler;
    }





}
