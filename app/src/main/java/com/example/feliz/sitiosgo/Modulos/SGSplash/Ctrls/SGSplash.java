package com.example.feliz.sitiosgo.Modulos.SGSplash.Ctrls;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.example.feliz.sitiosgo.Modulos.Account.Ctrls.SGLoginFacebook;
import com.example.feliz.sitiosgo.Modulos.CtrlSitiosGo.MainActivity;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.MenuActivity;
import com.example.feliz.sitiosgo.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SGSplash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgsplash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent loginIntent = new Intent(SGSplash.this, SGLoginFacebook.class);
                //Intent loginIntent = new Intent(SGSplash.this, MenuActivity.class);
                startActivity(loginIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
