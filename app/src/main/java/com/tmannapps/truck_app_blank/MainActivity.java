package com.tmannapps.truck_app_blank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tmannapps.truck_app_blank.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    FragmentContainerView fragmentContainer;
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button sigupButton;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper db;
        usernameEditText = findViewById(R.id.editTextUsernameLF);
        passwordEditText = findViewById(R.id.editTextPasswordLF);
        loginButton = findViewById(R.id.buttonLogInLF);
        sigupButton = findViewById(R.id.buttonSignUpLF);
       // fragmentContainer = findViewById(R.id.fragmentContainerView);
        db = new DatabaseHelper(getActivity().getParent());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                boolean result = db.fetchUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if (result) {
                    Toast.makeText(getActivity().getParent(), "Successfully logged in", Toast.LENGTH_SHORT).show();
                    fragment = new homeFragment();
                } else {
                    Toast.makeText(getActivity().getParent(), "The username and password do not match. Please try again or create an account", Toast.LENGTH_SHORT).show();
                    fragment = new loginFragment();
                }


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment).addToBackStack(null).commit();

            }

        });


    }
}