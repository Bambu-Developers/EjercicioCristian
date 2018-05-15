package com.example.feliz.sitiosgo.Modulos.Menu.Ctrls;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.feliz.sitiosgo.Modulos.Account.Ctrls.SGLoginFacebook;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGContacto.ContactoFragment;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGMapa.MapaFragment;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGPerfil.Ctrls.PerfilFragment;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGSitios.SitiosFragment;
import com.example.feliz.sitiosgo.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int INTERVALO = 2000; //2 segundos para salir
    private long tiempoPrimerClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Método para que se inicie el LoginActivity
    private void goLoginScreen() {
        Intent intent = new Intent(this, SGLoginFacebook.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //Método para cerrar sesión
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    //@Override
    //public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

        @Override
        public void onBackPressed(){
            if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()){
                super.onBackPressed();
                return;
            }else {
                Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
            }
            tiempoPrimerClick = System.currentTimeMillis();

            //if(getFragmentManager().)
        }
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        FragmentManager fragmentManager = getSupportFragmentManager();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            //Aqui poner el fragment de contacto
            fragmentManager.beginTransaction().replace(R.id.contenedor, new ContactoFragment()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager = getSupportFragmentManager();
        int id = item.getItemId();

        //El addToBackStack es para regresar entre los fragmentos.

        if (id == R.id.nav_perfil) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new PerfilFragment()).addToBackStack(null).commit();
        } else if (id == R.id.nav_sitios) {
            fragmentManager.beginTransaction().replace( R.id.contenedor, new SitiosFragment()).addToBackStack(null).commit();
        } else if (id == R.id.nav_mapa) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new MapaFragment()).addToBackStack(null).commit();
        } else if (id == R.id.nav_contacto) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new ContactoFragment()).addToBackStack(null).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
