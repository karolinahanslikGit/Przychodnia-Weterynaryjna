package com.example.przychodnia;


import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Pong {
    public String lekarzPrzyjmujacy;
    public String gatunek;
    public String imie;
    public String dataUrodzenia;

    public String numerTelefonu;
    public String dataPrzyjecia;
    public String waga;

    public String wlasciciel;



public Pong(){}


    public Pong(String gatunek, String imie, String dataUrodzenia, String wlasciciel, String numerTelefonu, String waga){

        this.gatunek=gatunek;
        this.imie=imie;
        this.dataUrodzenia=dataUrodzenia;
        this.wlasciciel=wlasciciel;
        this.numerTelefonu=numerTelefonu;
        this.waga=waga;
        lekarzPrzyjmujacy=FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0];
        dataPrzyjecia=new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new java.util.Date());


    }
    public Pong(String gatunek){

        this.gatunek=gatunek;
        lekarzPrzyjmujacy=FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0];
        dataPrzyjecia=new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new java.util.Date());
    }


}
