package com.example.bruefy.pointsofinterest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        File dir = new File(path);
        dir.mkdirs();


        File file = new File(path + "/daten.txt");
        if(file.exists()){
            String[] data = Load(file);
            Toast.makeText(getApplicationContext(), data[0], Toast.LENGTH_LONG).show();
        }

        boolean network = isNetworkAvailable();
        checkNetwork(network);


        getSupportActionBar().setElevation(0);

    }

    public static String[] Load(File file){
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
        }catch(IOException e){
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl = 0;
        try{
            while ((test = br.readLine()) != null){
                anzahl++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            fis.getChannel().position(0);
        }catch(IOException e){
            e.printStackTrace();
        }

        String[] array = new String[anzahl];

        String line;
        int i = 0;

        try{
            while ((line = br.readLine()) != null){
                array[i] = line;
                i++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return array;
    }

    public void checkNetwork(boolean net){
        if(! net){
            AlertDialog alertDialog = new AlertDialog.Builder(MapsActivity.this).create();
            alertDialog.setTitle("Internet");
            alertDialog.setMessage("Diese App benötigt eine Internet Verbindung, bitte überprüfe dein Netzwerkstatus");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }


    public void ButtonClick(View v){
        Button button = (Button) findViewById(v.getId());

        Intent maps = new Intent(getApplicationContext(), MapsActivity.class);
        Intent create = new Intent(getApplicationContext(), CreateActivity.class);
        Intent orte = new Intent(getApplicationContext(), Ortliste.class);
        Intent fav = new Intent(getApplicationContext(), Favoritenliste.class);



        switch(button.getText().toString()){
            case "1":
                startActivity(maps);
                overridePendingTransition(0, 0);
                break;
            case "2":
                startActivity(create);
                overridePendingTransition(0, 0);
                break;
            case "3":
                startActivity(orte);
                overridePendingTransition(0, 0);
                break;
            default:
                startActivity(fav);
                overridePendingTransition(0, 0);
                break;
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        //getMenuInflater().inflate(R.menu.menu_second, menu);  //<-You should remove this
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
             ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_FINE_LOCATION);
            }
        }*/




        /*
        Bibliothek.Coords.add(new LatLng(-200, 100));
        Bibliothek.title.add("Test");
        */
        mMap.clear();

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        boolean markerset = false;

        if(! Bibliothek.Coords.isEmpty()){
            for(int i = 0; i < Bibliothek.Coords.size(); i++){

                LatLng currenLocation = Bibliothek.Coords.get(i);
                String title = Bibliothek.title.get(i);
                String von = Bibliothek.start.get(i);
                String ende = Bibliothek.ende.get(i);
                String termin = von+" - "+ende;

                for(int y = 0; y < Bibliothek.favoriten.size(); y++){
                    String line = Bibliothek.favoriten.get(y);
                    String split[] = line.split("\r\n");
                    String favtitle = split[1];


                    if(favtitle.equals(title)){


                        mMap.addMarker(new MarkerOptions().position(currenLocation).title(title).snippet(termin).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                        markerset = true;
                    }
                }
                if(! markerset){
                    mMap.addMarker(new MarkerOptions().position(currenLocation).title(title).snippet(termin).icon(BitmapDescriptorFactory.defaultMarker()));
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLng(currenLocation));
                markerset = false;
            }
        }


    }


}
