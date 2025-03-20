package com.example.przychodnia;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;


import android.util.Log;

import android.widget.EditText;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Service;

import android.os.IBinder;


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


        FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG,"onDataChange");

                if(snapshot.exists()){
                FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).push().setValue(new Pong(gatunek, imie, data, wlasciciel, numerTelefonu,waga));
            }
                else{
                    FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).push().setValue(new Pong(gatunek, imie, data, wlasciciel, numerTelefonu,waga));
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
