package com.example.przychodnia2;


import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Pacjent {
    public String lekarzPrzyjmujacy;
    public String gatunek;
    public String imie;
    public String dataUrodzenia;

    public String numerTelefonu;
    public String dataPrzyjecia;
    public String waga;

    public String wlasciciel;
    public String notatka;
    public String dataWpisu;



    public Pacjent(){}

   public String getLekarzPrzyjmujacy() {
        return lekarzPrzyjmujacy;
    }

    public void setLekarzPrzyjmujacy(String lekarzPrzyjmujacy) {
        this.lekarzPrzyjmujacy = lekarzPrzyjmujacy;
   }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public String getDataPrzyjecia() {
        return dataPrzyjecia;
    }

    public void setDataPrzyjecia(String dataPrzyjecia) {
        this.dataPrzyjecia = dataPrzyjecia;
    }

    public String getWaga() {
        return waga;
    }

    public void setWaga(String waga) {
        this.waga = waga;
    }

    public String getWlasciciel() {
        return wlasciciel;
    }

    public void setWlasciciel(String wlasciciel) {
        this.wlasciciel = wlasciciel;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka= notatka;
    }

    public Pacjent(String gatunek, String imie, String dataUrodzenia, String wlasciciel, String numerTelefonu, String waga){

        this.gatunek=gatunek;
        this.imie=imie;
        this.dataUrodzenia=dataUrodzenia;
        this.wlasciciel=wlasciciel;
        this.numerTelefonu=numerTelefonu;
        this.waga=waga;

        lekarzPrzyjmujacy=FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0];
        dataPrzyjecia=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());


    }






}
