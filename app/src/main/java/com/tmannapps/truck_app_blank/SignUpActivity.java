package com.tmannapps.truck_app_blank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tmannapps.truck_app_blank.data.DatabaseHelper;
import com.tmannapps.truck_app_blank.model.User;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView textView;

    BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            Bundle bundle = intent.getExtras();
            String value =  bundle.getString("return_value");
            textView = findViewById(R.id.textView);
            textView.setText(value);
            Toast.makeText(SignUpActivity.this, "value "  + value +" ToastText in onReceive from Broadcast receiver", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onResume() {

        super.onResume();
        registerReceiver(myBroadcastReceiver, new IntentFilter("com.tmannapps.truck_app_blank.MyIntentService"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText sUsernameEditText = findViewById(R.id.sUsernameEditText);
        EditText sPasswordEditText = findViewById(R.id.sPasswrodEditText);
        EditText fullNameEditText = findViewById(R.id.fullNameEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditTextNumber);
        EditText sConfirmPasswordEditText = findViewById(R.id.sConfirmPasswordEditText);
        Button saveButton = findViewById(R.id.saveButton);
        FloatingActionButton takePictureButton = findViewById(R.id.floatingActionButton);

        db = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = sUsernameEditText.getText().toString();
                String password = sPasswordEditText.getText().toString();
                String fullname = fullNameEditText.getText().toString();
                int phone = Integer.parseInt(phoneEditText.getText().toString());
                String confPassword = sConfirmPasswordEditText.getText().toString();

                if (password.equals(confPassword))
                {
                    long result = db.insertUser(new User(username, password, fullname, phone));
                    if (result > 0)
                    {
                        Toast.makeText(SignUpActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(SignUpActivity.this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MyIntentService.class);
                intent.putExtra("value", "value from Sign Up Page");
                startService(intent);
            }
        });
    }

}