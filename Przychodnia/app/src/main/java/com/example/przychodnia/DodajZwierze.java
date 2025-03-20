package com.example.przychodnia;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DodajZwierze extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG=DodajZwierze.class.getSimpleName();
    Button buttonAdd;
    EditText editTextGatunek;
    EditText editTextImie;
    EditText editTextData;
    EditText editTextWlasciciel;
    EditText editTextPhone;
    EditText editTextWaga;

    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodaj_view);
        editTextGatunek=(EditText) findViewById(R.id.editTextGatunek);
        editTextImie=(EditText) findViewById(R.id.editTextImie);
        editTextData=(EditText) findViewById(R.id.editTextData);
        editTextWlasciciel=(EditText) findViewById(R.id.editTextWlasciciel);
        editTextPhone=(EditText)findViewById(R.id.editTextPhone) ;
        editTextWaga=(EditText) findViewById(R.id.editTextWaga);
        buttonAdd=(Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        String gatunek=editTextGatunek.getText().toString();
        String imie=editTextImie.getText().toString();
        String data=editTextData.getText().toString();
        String wlasciciel=editTextWlasciciel.getText().toString();
        String numerTelefonu=editTextPhone.getText().toString();
        String waga=editTextWaga.getText().toString();


            Intent servivceIntent= new Intent (DodajZwierze.this, PongService.class);
         servivceIntent.putExtra("wlasciciel", wlasciciel);
            servivceIntent.putExtra("gatunek",gatunek);
            servivceIntent.putExtra("imie",imie);
            servivceIntent.putExtra("Data Urodzenia",data);
            servivceIntent.putExtra("Numer Telefonu",numerTelefonu);
            servivceIntent.putExtra("waga",waga);
            startService(servivceIntent);

        Log.d(TAG,"OnClick");

        Intent intent= new Intent(DodajZwierze.this,MainActivity.class);
        DodajZwierze.this.startActivity(intent);
        DodajZwierze.this.finish();
    }


}
