package com.Bronwyn_Dinardo.cas_smash_game.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.Bronwyn_Dinardo.cas_smash_game.R;

@SuppressLint("CustomSplashScreen")
    public class SplashActivity extends AppCompatActivity {

        Thread timer;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            timer = new Thread(){
                @Override
                public void run() {
                    try {
                        synchronized (this){
                            wait(2500);
                        }
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    } finally {
                        if (!isConnected()) {
                            Intent intent = new Intent(SplashActivity.this, NetErrorActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(SplashActivity.this, GameActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            };

            timer.start();

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        private boolean isConnected() {
            ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext().getSystemService(CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetwork()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
        }

    }