package com.example.cronicle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class SignUp_Page extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText username, email, phone, password;
        username = findViewById(R.id.Name);
        email = findViewById(R.id.Email);
        phone = findViewById(R.id.Mobile);
        password = findViewById(R.id.Password);

        Button registerBtn = findViewById(R.id.RegisterButton);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailstored = email.getText().toString();
                String passwordstored = password.getText().toString();
                String usernamestored = username.getText().toString();
                String phonestored = phone.getText().toString();

                HashMap<String,String> userData = new HashMap<>();
                userData.put("User Name", usernamestored);
                userData.put("Email", emailstored);
                userData.put("Password", passwordstored);
                userData.put("Phone Number", phonestored);

                if (!isValidEmail(emailstored)) {
                    Toast.makeText(SignUp_Page.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidPassword(passwordstored)) {
                    Toast.makeText(SignUp_Page.this, "Password must be at least 8 characters long, include a number, a letter, and a special character.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidPhoneNumber(phonestored)) {
                    Toast.makeText(SignUp_Page.this, "Invalid Phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(SignUp_Page.this, "All Inputs are Valid!", Toast.LENGTH_SHORT).show();
            }

            public boolean isValidEmail(String email) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                return email != null && email.matches(emailPattern);
            }

            public boolean isValidPassword(String password) {
                String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$";
                return password != null && password.matches(passwordPattern);
            }

            public boolean isValidPhoneNumber(String phone) {
                String phonePattern = "^[0-9]{10}$";
                return phone != null && phone.matches(phonePattern);
            }
        });

    }
}
