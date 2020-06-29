package com.example.bloodbankapplicationf_2017065116.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbankapplicationf_2017065116.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailEt, passwordEt;
    Button loginButton;
    TextView signUpText;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        signUpText = findViewById(R.id.sign_up_text);
        mFirebaseAuth = FirebaseAuth.getInstance();

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();
                mFirebaseAuth = FirebaseAuth.getInstance();

                if (email.isEmpty()){
                    emailEt.setError("Please enter your email");
                    emailEt.requestFocus();

                }else if (password.isEmpty()){
                    passwordEt.setError("Please enter your password");
                    passwordEt.requestFocus();

                }else if (!email.isEmpty() && !password.isEmpty()){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "SignUp Failed", Toast.LENGTH_LONG).show();
                            }else {
                                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                                Toast.makeText(LoginActivity.this, "SignUp Successful", Toast.LENGTH_LONG).show();

                            }
                        }
                    });


                }




            }
        });


         loginButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String Email = emailEt.getText().toString();
                 String Password = passwordEt.getText().toString();
                 mFirebaseAuth = FirebaseAuth.getInstance();

                 if (Email.isEmpty()) {
                     emailEt.setError("Please enter your email");
                     emailEt.requestFocus();

                 } else if (Password.isEmpty()) {
                     passwordEt.setError("Please enter your password");
                     passwordEt.requestFocus();

                 }else if (!Email.isEmpty() && !Password.isEmpty()){
                     mFirebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         String number = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("number", String.valueOf(12345));

                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (!task.isSuccessful()) {
                                 Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                             } else {
                                 startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                 PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString( "number", number).apply();
                                 LoginActivity.this.finish();
                                 Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                 }
             }
         });

    }

}
