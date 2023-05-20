package com.tmannapps.truck_app_blank;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tmannapps.truck_app_blank.data.DatabaseHelper;

public class Login extends AppCompatActivity {
    DatabaseHelper db;
    EditText usernameEditText = findViewById(R.id.editTextUsernameLF);
    EditText passwordEditText = findViewById(R.id.editTextPasswordLF);
    Button loginButton = findViewById(R.id.buttonLogInLF);
    Button sigupButton = findViewById(R.id.buttonSignUpLF);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                boolean result = db.fetchUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if (result) {
                    Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                    fragment = new homeFragment();
                } else {
                    Toast.makeText(Login.this, "The username and password do not match. Please try again or create an account", Toast.LENGTH_SHORT).show();
                    fragment = new loginFragment();
                }


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment).addToBackStack(null).commit();

            }

        });
    }
}