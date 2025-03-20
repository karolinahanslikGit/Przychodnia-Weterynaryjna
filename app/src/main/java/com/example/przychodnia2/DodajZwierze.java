package com.example.przychodnia2;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DodajZwierze extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG=DodajZwierze.class.getSimpleName();
    Button buttonAdd;
    EditText editTextGatunek;
    EditText editTextImie;
    EditText editTextData;
    EditText editTextWlasciciel;
    EditText editTextPhone;
    EditText editTextWaga;
    EditText editTextNotatka;
    Button buttonWroc;
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
        buttonWroc=(Button) findViewById(R.id.buttonWroc);
        buttonWroc.setOnClickListener(this);
        editTextNotatka=(EditText) findViewById(R.id.editTextNotatka);
        buttonAdd.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view==buttonAdd) {
            String gatunek = editTextGatunek.getText().toString();
            String imie = editTextImie.getText().toString();
            String data = editTextData.getText().toString();
            String wlasciciel = editTextWlasciciel.getText().toString();
            String numerTelefonu = editTextPhone.getText().toString();
            String waga = editTextWaga.getText().toString();
            String notatka = editTextNotatka.getText().toString();

            if (imie.equals(""))
            {
                Toast.makeText(DodajZwierze.this,"Podaj Imię Pacjenta",Toast.LENGTH_SHORT).show();
            }
            else if(wlasciciel.equals("")){
                Toast.makeText(DodajZwierze.this,"Podaj Imię wlaściciela",Toast.LENGTH_SHORT).show();
            }
            else if(notatka.equals("")){
                Toast.makeText(DodajZwierze.this,"Podaj powód wizyty",Toast.LENGTH_SHORT).show();
            }
            else{
                Intent servivceIntent = new Intent(DodajZwierze.this, PongService.class);
                servivceIntent.putExtra("wlasciciel", wlasciciel);
                servivceIntent.putExtra("gatunek", gatunek);
                servivceIntent.putExtra("imie", imie);
                servivceIntent.putExtra("Data Urodzenia", data);
                servivceIntent.putExtra("Numer Telefonu", numerTelefonu);
                servivceIntent.putExtra("waga", waga);
                servivceIntent.putExtra("notatka", notatka);
                startService(servivceIntent);

                Log.d(TAG, "OnClick");

                Intent intent = new Intent(DodajZwierze.this, MainActivity.class);
                DodajZwierze.this.startActivity(intent);
                DodajZwierze.this.finish();
            }

        }
        else {
            Intent intent = new Intent(DodajZwierze.this, MainActivity.class);
            DodajZwierze.this.startActivity(intent);
            DodajZwierze.this.finish();
        }
    }


}
