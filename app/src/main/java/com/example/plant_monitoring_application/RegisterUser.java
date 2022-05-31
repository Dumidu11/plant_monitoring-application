package com.example.plant_monitoring_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView banner;
    private EditText Name, Email2, Password2;
    private Button register;
    private ProgressBar progressBar;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        Name = (EditText) findViewById(R.id.Name);
        Email2 = (EditText) findViewById(R.id.email2);
        Password2 = (EditText) findViewById(R.id.password2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.register:
                registerUser();
                break;
        }

    }
    private void registerUser(){
        String Emailsave =Email2.getText().toString().trim();
        String Passwordsave =Password2.getText().toString().trim();
        String Namesave =Name.getText().toString().trim();

        if(Namesave.isEmpty()){
            Name.setError("Name is required!");
            Name.requestFocus();
            return;
        }
        if(Emailsave.isEmpty()){
            Email2.setError("Email is required");
            Email2.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Emailsave).matches()){
            Email2.setError("Please provide valid email!");
            Email2.requestFocus();
            return;
        }
        if (Passwordsave.isEmpty()){
            Password2.setError("Password is reqired!");
            Password2.requestFocus();
            return;
        }
        if(Passwordsave.length()<5){
            Password2.setError("Minimum password should be 6 characters!");
            Password2.requestFocus();
            return;
        }

         progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Emailsave,Passwordsave)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(Namesave, Emailsave);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(RegisterUser.this, MainActivity.class ));
                                        Toast.makeText(RegisterUser.this, "User has been registered sucessfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegisterUser.this, "Failed !", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                });
    }
}