package com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGPerfil.Ctrls;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.feliz.sitiosgo.Modulos.Account.Ctrls.SGLoginFacebook;
import com.example.feliz.sitiosgo.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class PerfilFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

}
