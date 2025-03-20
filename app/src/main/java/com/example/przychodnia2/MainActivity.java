package com.example.przychodnia2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ChildEventListener {
    public String wl;
    AdapterPong adapterPong;
    DatabaseReference reference;

    Notatki notatki;
    SearchView searchview;
Button buttonDodaj;
    RecyclerView recyclerViewPongs;

    EditText editImieNazwisko;
ArrayList<Pacjent> pacjents;
ArrayList<Pacjent> pongsfull;
ArrayList<Notatki> not;
Context context;


    private static final String TAG=MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDodaj=(Button)findViewById(R.id.buttonDodaj);

        buttonDodaj.setOnClickListener(this);
        pacjents =new ArrayList<>();
        pongsfull=new ArrayList<>();
        not=new ArrayList<>();
      DatabaseReference  rootreference= FirebaseDatabase.getInstance().getReference();
      DatabaseReference ownersreference=rootreference.child("Zwierzęta i właściciele");
     ValueEventListener listener = new ValueEventListener(){

    @Override
    public void onDataChange(@NonNull DataSnapshot ownersSnapshot) {
        for(DataSnapshot ownerSnapshot: ownersSnapshot.getChildren()){



            for(DataSnapshot animalSnapshot: ownerSnapshot.getChildren()){

                Pacjent pacjent = animalSnapshot.getValue(Pacjent.class);
                pacjents.add(pacjent);
                pongsfull.add(pacjent);
                for(DataSnapshot nSnapshot : animalSnapshot.getChildren()) {

                        for(DataSnapshot dSnapshot: nSnapshot.getChildren()) {

                        Notatki notatka = dSnapshot.getValue(Notatki.class);

                       not.add(notatka);
                    }
                }
            }
        }

        recyclerViewPongs=(RecyclerView) findViewById(R.id.recyclerViewLista);


        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerViewPongs.setLayoutManager(mLayoutManager);
        recyclerViewPongs.setItemAnimator((new DefaultItemAnimator()));
        recyclerViewPongs.setAdapter(adapterPong);
        recyclerViewPongs.setHasFixedSize(true);


        adapterPong.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
};
     ownersreference.addValueEventListener(listener);
        adapterPong = new AdapterPong(this, pacjents,not,pongsfull);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            StartLoginActivity();
        } else{

        }
    }

    @Override
    protected void onPause() {
        if(reference!=null){
            reference.removeEventListener(this);
        }
        super.onPause();
    }

    private void StartLoginActivity() {
        Intent intent= new Intent(MainActivity.this, LogInActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        SearchView searchview=(SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.searchView));
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterPong.getFilter().filter(newText.toString());
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.action_logout){
            FirebaseAuth.getInstance().signOut();
            StartLoginActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {




            Intent intent = new Intent(MainActivity.this, DodajZwierze.class);
            MainActivity.this.startActivity(intent);
            MainActivity.this.finish();

    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

        if(adapterPong!=null){
            Pacjent pacjent =dataSnapshot.getValue(Pacjent.class);
            adapterPong.pacjents.add(pacjent);
            adapterPong.notifyDataSetChanged();

        }
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}