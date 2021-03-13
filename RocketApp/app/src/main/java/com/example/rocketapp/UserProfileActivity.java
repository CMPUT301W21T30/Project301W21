package com.example.rocketapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.security.auth.callback.Callback;

public class UserProfileActivity extends AppCompatActivity {
    public ImageButton saveProfileData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        TextView userName = findViewById(R.id.userNameOnProfile);
        userName.setText('@'+DataManager.getUser().getName());

        EditText userEmail = findViewById(R.id.userEmail);
        EditText userPhoneNumber = findViewById(R.id.userPhoneNumber);

        userEmail.setText(DataManager.getUser().getEmail());
        userPhoneNumber.setText(DataManager.getUser().getPhone_number());

        saveProfileData = findViewById(R.id.saveUserProfileData);
        saveProfileData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                String phone = userPhoneNumber.getText().toString();

                DataManager.getUser().setPhone_number(phone);
                DataManager.getUser().setEmail(email);
                DataManager.updateUser(user -> {
                    Toast.makeText(UserProfileActivity.this, "User Profile Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }, e -> {});
            }
        });




    }
}
