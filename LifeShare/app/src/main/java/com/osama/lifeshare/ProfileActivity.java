package com.osama.lifeshare;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class ProfileActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        ProfileInfo.OnFragmentInteractionListener,
        ProfileEdit.OnFragmentInteractionListener,
        SearchDonor.OnFragmentInteractionListener,
        AllDonor.OnFragmentInteractionListener,
        About_Us.OnFragmentInteractionListener,
        Feed_Back.OnFragmentInteractionListener,
        ForumFragment.OnFragmentInteractionListener,
        search_all_donor.OnFragmentInteractionListener{

    private DrawerLayout drawer;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        mAuth = FirebaseAuth.getInstance();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            String key = intent.getStringExtra("key");
            if(key == null)
            {
                key = "Profile Fragment";
            }
            switch (key)
            {
                case "Profile Fragment":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                    break;
                case "Find Donor Fragment":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new search_all_donor()).commit();
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FinddonorFragment()).commit();
                    break;
                case "Forum Fragment":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ForumFragment()).commit();
                    break;
                 case "Feedback Fragment":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Feed_Back()).commit();
                    break;
                case "About_Us Fragment":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new About_Us()).commit();
                    break;
            }
            navigationView.setCheckedItem(R.id.nav_profile);
        }
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.nav_finddonor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new search_all_donor()).commit();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FinddonorFragment()).commit();
                break;
            case R.id.nav_hospital:
                Intent intent = new Intent(this,MapsActivity.class);
                startActivity(intent);

                break;
//            case R.id.nav_requests:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RequestsFragment()).commit();
//                break;
            case R.id.nav_forum:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ForumFragment()).commit();
                break;
            case R.id.nav_share :
                Toast.makeText(this, "Share this App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_feedback :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Feed_Back()).commit();
                Toast.makeText(this, "Give Feedback", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_aboutus :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new About_Us()).commit();
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
