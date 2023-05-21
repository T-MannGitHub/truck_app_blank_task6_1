package com.tmannapps.truck_app_blank;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.tmannapps.truck_app_blank.data.DatabaseHelper;
import com.tmannapps.truck_app_blank.databinding.ActivitySignUpBinding;
import com.tmannapps.truck_app_blank.model.User;

import java.io.File;
//camera setup and permissions from youtube tutorial- www.youtube.com/watch?v=9XSlbZN1yFg
public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper db;
    ActivitySignUpBinding signUpBinding;
    ActivityResultLauncher<Uri> takePictureLauncher;
    Uri imageUri;
    private static final int CAMERA_PERMISSION_CODE = 1;
    BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            Bundle bundle = intent.getExtras();
            String value =  bundle.getString("return_value");
            Toast.makeText(SignUpActivity.this, "value "  + value +" ToastText in onReceive from Broadcast receiver", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcastReceiver, new IntentFilter("com.tmannapps.truck_app_blank.MyIntentService"));
    }

    private Uri createUri(){
        File imageFile = new File(getApplicationContext().getFilesDir(), "camera_photo.jpg");
        return FileProvider.getUriForFile(
                getApplicationContext(),
                "com.tmannapps.truck_app_blank.fileProvider",
                imageFile
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signUpBinding.getRoot());

        db = new DatabaseHelper(this);

        if (SignUpActivity.this.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            //do the camera part here
            imageUri = createUri();
            registerPictureLauncher();
            signUpBinding.floatingActionButton.setOnClickListener(view -> {
                checkCameraPermissionAndOpenCamera();
                Intent intent = new Intent(SignUpActivity.this, MyIntentService.class);
                intent.putExtra("value", "value from Sign Up Page");
                startService(intent);
            });
        } else {
            //have user upload from file - TODO
            Toast.makeText(SignUpActivity.this, ("Looks like you don't have a camera"), Toast.LENGTH_SHORT).show();
        }


        signUpBinding.saveButton.setOnClickListener(v -> {

            String username = signUpBinding.sUsernameEditText.getText().toString();
            String password = signUpBinding.sPasswordEditText.getText().toString();
            String fullname = signUpBinding.fullNameEditText.getText().toString();
            int phone = Integer.parseInt(signUpBinding.phoneEditText.getText().toString());
            String confPassword = signUpBinding.sConfirmPasswordEditText.getText().toString();

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
        });


    }
    private void registerPictureLauncher() {
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        try {
                            if (result) {
                                signUpBinding.imageView2.setImageURI(null);
                                signUpBinding.imageView2.setImageURI(imageUri);
                            }
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                }
        );
    }
    private void checkCameraPermissionAndOpenCamera() {
        if (ActivityCompat.checkSelfPermission(SignUpActivity.this,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SignUpActivity.this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            takePictureLauncher.launch(imageUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePictureLauncher.launch(imageUri);

            } else {
                Toast.makeText(SignUpActivity.this, "Camera permission denied, please allow permissions", Toast.LENGTH_SHORT).show();

            }
        }
    }
}