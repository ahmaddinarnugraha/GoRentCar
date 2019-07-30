package com.ahmad.gorentcar;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


        FirebaseAuth mAuth;
        FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        // ini

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();




        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.kontener, new FragmentSewa()).commit();


        updateNavHeader();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sewa_mobil) {
            //panggil fragment sewa
           getSupportFragmentManager().beginTransaction().replace(R.id.kontener, new FragmentSewa()).commit();
        } else if (id == R.id.nav_favorite) {
            getSupportFragmentManager().beginTransaction().replace(R.id.kontener, new FragmentFavorit()).commit();
        } else if (id == R.id.nav_transaksi) {
            getSupportFragmentManager().beginTransaction().replace(R.id.kontener, new FragmentTransaksi()).commit();
        } else if (id == R.id.nav_panduan) {
            getSupportFragmentManager().beginTransaction().replace(R.id.kontener, new FragmentPanduan()).commit();
        } else if (id == R.id.nav_bantuan) {
            getSupportFragmentManager().beginTransaction().replace(R.id.kontener, new FragmentBantuan()).commit();
        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.kontener, new FragmentProfile()).commit();
        } else if (id == R.id.nav_keluar) {

            FirebaseAuth.getInstance().signOut();
            Intent LoginActivity = new Intent(getApplicationContext(),Login.class);
            startActivity(LoginActivity);
            finish();

        } else if (id == R.id.nav_tentang) {
            getSupportFragmentManager().beginTransaction().replace(R.id.kontener, new FragmentTentangAplikasi()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateNavHeader() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headView = navigationView.getHeaderView(0);
        TextView navUserName = headView.findViewById(R.id.nav_username);
        TextView navUserMail = headView.findViewById(R.id.nav_useremail);
        ImageView navUserPhoto = headView.findViewById(R.id.nav_userphoto);

        navUserMail.setText(currentUser.getEmail());
        navUserName.setText(currentUser.getDisplayName());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
    }


}
