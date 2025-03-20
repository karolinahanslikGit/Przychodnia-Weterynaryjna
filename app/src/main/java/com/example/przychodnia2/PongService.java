package com.example.przychodnia2;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PongService extends Service {


    private static final String TAG= PongService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {




        if (!intent.hasExtra("wlasciciel")) {
            return super.onStartCommand(intent, flags, startId);
        }
        final String wlasciciel = intent.getStringExtra("wlasciciel");
        final String gatunek = intent.getStringExtra("gatunek");
        final String imie = intent.getStringExtra("imie");
        final String data = intent.getStringExtra("Data Urodzenia");
        final String numerTelefonu = intent.getStringExtra("Numer Telefonu");
        final String waga=intent.getStringExtra("waga");
        final String notatka=intent.getStringExtra("notatka");
 String dataw=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
 String dataWizyty=dataw.toString();

        FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG,"onDataChange");

                if(snapshot.exists()){
                    FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).child(imie).setValue(new Pacjent(gatunek, imie, data, wlasciciel, numerTelefonu,waga));
                    FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).child(imie).child("Notatki").child(dataWizyty).setValue(new Notatki(notatka));
                }
                else{
                    FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).child(imie).setValue(new Pacjent(gatunek, imie, data, wlasciciel, numerTelefonu,waga));
                    FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).child(imie).child("Notatki").child(dataWizyty).setValue(new Notatki(notatka));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG,"onCancelled");
            }


        });
        return super.onStartCommand(intent, flags, startId);


    }


    //
//    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
