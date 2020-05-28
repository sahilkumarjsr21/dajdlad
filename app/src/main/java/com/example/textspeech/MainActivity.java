package com.example.textspeech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText loginEmail, passwordLogin;
    private MaterialButton loginButton, signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEmail = findViewById(R.id.loginEmailAddress);
        passwordLogin = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUp);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String password =passwordLogin.getText().toString().trim();
                if(email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    return ;
                }
                if(password.isEmpty() && !(password.length() > 6)) {
                    return;
                }
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                UserDatabase userDatabase = new UserDatabase(MainActivity.this);
                Cursor cursor = userDatabase.readUser(user);

                Intent intent = new Intent (MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
