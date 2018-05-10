package com.example.feliz.sitiosgo.Modulos.LoginFacebook;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.feliz.sitiosgo.Modulos.CtrlSitiosGo.MainActivity;
import com.example.feliz.sitiosgo.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.zip.Inflater;

public class LoginFacebookActivity extends AppCompatActivity{

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    //FacebookFirebase
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    //FacebookFirebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginfacebookactivity);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton)  findViewById(R.id.login_button);

        //Permisos
        loginButton.setReadPermissions(Arrays.asList("email"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //goMainScreen();
                //Firebase
                handleFacebookAccesToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                //Se mostrará mensaje si la operación se cancela
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                //Se mostrará mensaje en caso de error
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });

        //FacebookFirebase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    goMainScreen();
                }
            }
        };
        //FacebookFirebase
    }


    //FacebookFirebase
    private void handleFacebookAccesToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), R.string.firebase_error_login, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //FacebookFirebase

    private void goMainScreen() {
        Intent logIntent = new Intent(LoginFacebookActivity.this, MainActivity.class);
        logIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //FacebookFirebase
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthListener);
    }
    //FacebookFirebase
}
