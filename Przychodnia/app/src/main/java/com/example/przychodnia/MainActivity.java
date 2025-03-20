package com.example.przychodnia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ChildEventListener {

    AdapterPong adapterPong;
    DatabaseReference reference;
    Button buttonDodaj;
    RecyclerView recyclerViewPongs;




    private static final String TAG=MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPongs=(RecyclerView) findViewById(R.id.recyclerViewLista);
         adapterPong = new AdapterPong();

        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerViewPongs.setLayoutManager(mLayoutManager);
        recyclerViewPongs.setItemAnimator((new DefaultItemAnimator()));
        recyclerViewPongs.setAdapter(adapterPong);

        buttonDodaj=(Button) findViewById(R.id.buttonDodaj);

       buttonDodaj.setOnClickListener(this);

    }
    @Override
    protected void onResume(){
        super.onResume();
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            StartLoginActivity();
        } else{
            reference= FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele");
            reference.addChildEventListener(this);
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
        Intent intent= new Intent(MainActivity.this,LogInActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
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
        Log.d(TAG,"OnClick");
        Intent intent= new Intent(MainActivity.this,DodajZwierze.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
        Log.d(TAG,"onChildAdded");
        if(adapterPong!=null){
            Pong pong=dataSnapshot.getValue(Pong.class);
            adapterPong.pongs.add(pong);
            adapterPong.notifyDataSetChanged();

        }
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        Log.d(TAG,"onChildChanges");
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
        Log.d(TAG,"onChildRemoved");
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        Log.d(TAG,"onChildMoved");
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Log.d(TAG,"onCancelled");
    }
}