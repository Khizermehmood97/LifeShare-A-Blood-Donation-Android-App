package com.osama.lifeshare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.location.LocationListener;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


import com.google.firebase.auth.FirebaseAuth;


public class MapsActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        ProfileInfo.OnFragmentInteractionListener,
        ProfileEdit.OnFragmentInteractionListener,
        SearchDonor.OnFragmentInteractionListener,
        AllDonor.OnFragmentInteractionListener{


    private DrawerLayout drawer;
    private FirebaseAuth mAuth;
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    private  double latitude,longitude;
    private int ProximityRadius = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        drawer = findViewById(R.id.drawer_layout);
        mAuth = FirebaseAuth.getInstance();
        NavigationView navigationView = findViewById(R.id.nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_profile:
                Intent intent4 = new Intent(this, ProfileActivity.class);
                String value = "Profile Fragment";
                intent4.putExtra("key",value);
                startActivity(intent4);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.nav_finddonor:
                Intent intent1 = new Intent(this, ProfileActivity.class);
                String value1 = "Find Donor Fragment";
                intent1.putExtra("key",value1);
                startActivity(intent1);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FinddonorFragment()).commit();
                break;
            case R.id.nav_hospital:
                Intent intent2 = new Intent(this,MapsActivity.class);
                startActivity(intent2);

                break;
//            case R.id.nav_requests:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RequestsFragment()).commit();
//                break;
            case R.id.nav_forum:
                Intent intent3 = new Intent(this, ProfileActivity.class);
                String value3 = "Forum Fragment";
                intent3.putExtra("key",value3);
                startActivity(intent3);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ForumFragment()).commit();
                break;
            case R.id.nav_share :
                Toast.makeText(this, "Share this App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_feedback :
                Intent intent5 = new Intent(this,ProfileActivity.class);
                String value5 = "Feedback Fragment";
                intent5.putExtra("key",value5);
                startActivity(intent5);

                //Toast.makeText(this, "Give Feedback", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_aboutus :
                Intent intent6 = new Intent(this,ProfileActivity.class);
                String value6 = "About_Us Fragment";
                intent6.putExtra("key",value6);
                startActivity(intent6);

                //Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;

        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onFragmentInteraction(Uri uri) {

    }

    private String getURL(double latitude,double longitude,String nearbyPlace)
    {
        StringBuilder googleURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleURL.append("location="+latitude+"," +longitude);
        googleURL.append("&radius="+ProximityRadius);
        googleURL.append("&type="+nearbyPlace);
        googleURL.append("&sensor=true");
        googleURL.append("&key="+"AIzaSyAjECp4WqNIV4javeDm_2LxmqzNpGE0ZRA");
        //AIzaSyAjECp4WqNIV4javeDm_2LxmqzNpGE0ZRA


        Log.d("GoogleMapsActivity","url = "+ googleURL.toString());
        return googleURL.toString();


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }


    public  void Search(View v)
    {
        Object transferData[] = new Object[2];
        GetNearbyPlaces getNearbyPlaces = new GetNearbyPlaces();
        EditText addressField = (EditText) findViewById(R.id.location_search);
        String address = addressField.getText().toString();

        List<Address> addressList = null;
        MarkerOptions userMarkerOptions = new MarkerOptions();

        if (!TextUtils.isEmpty(address))
        {
            Geocoder geocoder = new Geocoder(this);

            try {
                addressList = geocoder.getFromLocationName(address,6);

                if (addressList !=null)
                {
                    for (int i=0;i<addressList.size();i++)
                    {
                        Address userAddress = addressList.get(i);
                        LatLng latLng = new LatLng(userAddress.getLatitude(),userAddress.getLongitude());

                        userMarkerOptions.position(latLng);
                        userMarkerOptions.title(address);
                        userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

                        mMap.addMarker(userMarkerOptions);
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                    }
                }
                else
                {
                    Toast.makeText(this, "Location Not Found . . .", Toast.LENGTH_SHORT).show();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


        }
        else
        {
            Toast.makeText(this, "Please Write Any Location Name", Toast.LENGTH_SHORT).show();
        }

    }
    public void SearchHospital(View v)
    {
        String hospital = "hospital";
        Object transferData[] = new Object[2];
        GetNearbyPlaces getNearbyPlaces = new GetNearbyPlaces();



        mMap.clear();

        String url = getURL(latitude,longitude,hospital);
        transferData[0] = mMap;
        transferData[1] = url;

        getNearbyPlaces.execute(transferData);
        Toast.makeText(this, "Searching Nearby Hospitals", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Showing Nearby Hospitals", Toast.LENGTH_SHORT).show();
    }

    public boolean checkUserLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case Request_User_Location_Code:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if (googleApiClient == null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this, "Permission Denied . .. .", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    protected synchronized void buildGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location)
    {

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        lastLocation  = location;

        if(currentUserLocationMarker != null)
        {
            currentUserLocationMarker.remove();
        }
        Log.d("lat = ",""+latitude);
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions =new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentUserLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));


        if(googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED )
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);

        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
