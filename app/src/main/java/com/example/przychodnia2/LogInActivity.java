package com.example.przychodnia2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG=LogInActivity.class.getSimpleName();
    EditText editTextPassword;
    EditText editTextLogin;
    Button buttonLogin;
    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        editTextLogin= (EditText) findViewById((R.id.editTextLogin));
        buttonLogin=(Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener( this);
        firebaseAuth=FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View view) {
        String login=editTextLogin.getText().toString();
        String password=editTextPassword.getText().toString();
        if(editTextLogin.getText().toString().trim().length()>0 && editTextPassword.getText().toString().trim().length()>0) {
            firebaseAuth.signInWithEmailAndPassword(editTextLogin.getText().toString(),editTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.d(TAG,"onComplete");
                        UserLoggedIn(login);

                    }else {
                        Toast.makeText(LogInActivity.this,"Error"+task.getException(),Toast.LENGTH_SHORT).show();

                        if(task.getException().getMessage().contains("There is no user record corresponding to this identifier. The user may have been deleted."));{
                            firebaseAuth.createUserWithEmailAndPassword(login,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG,"OnComplete");
                                    if (task.isSuccessful()) {
                                        UserLoggedIn(login);
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    private void UserLoggedIn(String email) {
        String name=email.split("@")[0];


        FirebaseDatabase.getInstance().getReference("Lekarze:").child("Lek: "+name).setValue(new Person(name)).addOnCompleteListener(new OnCompleteListener<Void>() {


            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG,"OnComplete");
            }
        });
        Intent intent= new Intent(LogInActivity.this,MainActivity.class);
        LogInActivity.this.startActivity(intent);
        LogInActivity.this.finish();
    }

}