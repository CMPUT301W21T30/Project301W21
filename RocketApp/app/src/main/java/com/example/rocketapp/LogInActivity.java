package com.example.rocketapp;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.concurrent.atomic.AtomicBoolean;

import static android.content.ContentValues.TAG;

public class LogInActivity extends AppCompatActivity {

    public Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // set up login Button
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> {
            EditText usernameEditText = findViewById(R.id.usernameEditText);
            String username = usernameEditText.getText().toString();
            loginOrCreateUser(username);
        });


    }





    private void loginOrCreateUser(String username) {
        DataManager.login(username, user -> {
            Log.d("Login Succesfully", "Login Successfully");
            Intent ExperimentsListActivityIntent = new Intent(LogInActivity.this, ExperimentsListActivity.class);
            startActivity(ExperimentsListActivityIntent);
        }, loginError -> {

            Log.d("Login Error", "User Doesn't exist");
            DataManager.createUser(username, user -> {

                Log.d("Created New user", "Created New User.");
                DataManager.login(username, user1 -> {

                    Log.d("Login Succesfully", "Login Successfully");
                    Intent ExperimentsListActivityIntent = new Intent(LogInActivity.this, ExperimentsListActivity.class);
                    startActivity(ExperimentsListActivityIntent);
                }, loginErrorAfterCreateUser -> {
                    Log.e("Can't Login", "User cannot be created and Login.");
                });
            }, createUserError -> {
                Log.e("User cannot be created", "User Can Not be created.");
            });
        });
    }


    private void exampleUsageForDataManager() {
        // Should use the callback lambdas to do work following these methods, since these are asynchronous commands their effects
        // won't exist until after they synchronize with firebase (they will be synchronized at the point when lambdas are called).

        // Create a new user and login
//        DataManager.createUser("Marty", user -> {
//
//        }, error -> {
//            Log.e(TAG, error.toString());
//        });

        DataManager.login("Morty", user -> {
            // Use this to load the users subscriptions
//            DataManager.pullSubscriptions(experiments -> {
//                Log.d(TAG, "Subscribed experiments:");
//
//            });

//             Publish a new experiment
//            DataManager.publishExperiment(
//                    new IntCountExperiment("Marty's second experiment", "An experiment started by Marty", "Canada", 10, true),
//                    experiments -> {
//                Log.d(TAG, "Subscribed experiments:");
//
//            });
//
            // Pull all experiments from firebase
//            DataManager.pullAllExperiments(experiments -> {
//                Log.d(TAG, "All experiments:");
//                for (Experiment e : experiments) {
//                    DataManager.subscribe(e, () -> {
//                        for (Experiment ex : DataManager.getExperimentArrayList()) {
//                            Log.d(TAG, ex.toString());
//                        }
//                    });
//                }
//            });

//            // Pull all experiments owned by this user from firebase
//            DataManager.pullOwnedExperiments(experiments -> {
//                for (Experiment experiment : experiments) {
////                    DataManager.push(new Question("Here is a question"), experiment, question -> {});
//                    DataManager.push(new BinomialTrial("Here is a question"), experiment, trial -> {});
//                }
//            });
//
//            // Pull all subscriptions by this user from firebase
//            DataManager.pullSubscriptions(experiments -> {
//                for (Experiment experiment : experiments)
//                    DataManager.push(new Question("Here is a question"), experiment, trial -> {});
//            });

        }, e -> {
            Log.e(TAG, "User not found.");
        });
    }



}

