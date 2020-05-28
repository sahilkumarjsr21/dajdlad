package com.example.textspeech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText signUpEmail, passwordSignUp, reEnterPassword, nameEditText;
    private MaterialButton loginButton, signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpButton = findViewById(R.id.signUpBtn);
        loginButton = findViewById(R.id.loginBtn);
        signUpEmail = findViewById(R.id.signupEmailAddress);
        reEnterPassword = findViewById(R.id.signupRenterPassword);
        passwordSignUp = findViewById(R.id.signupPassword);
        nameEditText = findViewById(R.id.signupName);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Objects.requireNonNull(nameEditText.getText()).toString();
                String email = Objects.requireNonNull(signUpEmail.getText()).toString();
                String password = Objects.requireNonNull(passwordSignUp.getText()).toString();
                String reEnter = Objects.requireNonNull(reEnterPassword.getText()).toString();

                if (name.isEmpty()) {
                    return;
                } else if (email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    return;
                }else if(password.isEmpty() || reEnter.isEmpty() && password.equals(reEnter)){
                    return;
                }

                User user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);

                UserDatabase userDatabase =  new UserDatabase(SignUpActivity.this);
                if(userDatabase.insertUser(user)){
                    Intent i =  new Intent (SignUpActivity.this, HomeActivity.class);
                    startActivity(i);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

}
