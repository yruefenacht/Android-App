package com.example.bruefy.pointsofinterest;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
<<<<<<< HEAD
=======
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
>>>>>>> origin/master
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.MaskedWallet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CreateActivity extends ActionBarActivity {

<<<<<<< HEAD
=======


>>>>>>> origin/master


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);



        //Entferne Activity Transition
        getSupportActionBar().setElevation(0);

    }



    public void ButtonClick(View v){
        //Button Click Event
        Button button = (Button) findViewById(v.getId());//Hole den Ausgewählten Button

        Intent maps = new Intent(getApplicationContext(), MapsActivity.class);
        Intent create = new Intent(getApplicationContext(), CreateActivity.class);
        Intent orte = new Intent(getApplicationContext(), Ortliste.class);
        Intent fav = new Intent(getApplicationContext(), Favoritenliste.class);

        //Je nach Button eine Andere Activity starten
        switch(button.getText().toString()){
            case "1":
                startActivity(maps);
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
<<<<<<< HEAD
=======

    Calendar myCalendar = Calendar.getInstance();
    private String pressedButton;

    public void DatePicked(View v){
        new DatePickerDialog(CreateActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        Button btn = (Button) findViewById(v.getId());

        pressedButton = btn.getTag().toString();
        createcounter++;

    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };



>>>>>>> origin/master


    private int createcounter = 0;
    private boolean ortfound = false;

    public void addLocation(View v) {


        //Java, Hole mir die Überschrift von folgenden Buttons:
        EditText ortinput = (EditText) findViewById(R.id.titel);
        String title = ortinput.getText().toString();

        EditText locationstring = (EditText) findViewById(R.id.ort);
        String location = locationstring.getText().toString();


        //Füge nur hinzu wenn alle Inputfelder bestimmt sind
        if(createcounter >= 1){
            //Prüfe ob der eingegebene Ort existiert
            LatLng address = findCoordinates(location);
            if(ortfound){
                //ERFOLG!!!
                //Füge Daten in die Listen der Bibliothek
                Bibliothek.Coords.add(address);
                Bibliothek.title.add(title);

                //Schreib mir die 4 Werte in ein Text-File
                //SharePreferences(title, address);

                //Wechsle auf Map
                Intent map = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(map);

                //messungen die zur Validierung deinen reseten
                createcounter = 0;
                ortfound = false;
            }else{
                //Falls der Ort nicht auf Google Maps existiert
                AlertDialog alertDialog = new AlertDialog.Builder(CreateActivity.this).create();
                alertDialog.setTitle("Ort nicht gefunden");
                alertDialog.setMessage("Der von Ihnen verlangte Ort wurde nicht gefunden");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

        }else{
            //Falls noch leere Felder existieren
            AlertDialog alertDialog = new AlertDialog.Builder(CreateActivity.this).create();
            alertDialog.setTitle("Leere Inhalte");
            alertDialog.setMessage("Füllen Sie bitte alle Felder aus");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

    }



    private static final String TAG = CreateActivity.class.getSimpleName();

    public LatLng findCoordinates(String location){

        Geocoder gc = new Geocoder(this);//Hole Koordinaten anhand von String location
        List<android.location.Address> list = null;
        LatLng newLocation = null;

        //Fange Exceptions mit try catch ab, anstatt mit throws
        try {
            //Setze Liste
            list = gc.getFromLocationName(location, 1);

        }catch(IOException e){
            Log.e(TAG, "Error");

        }catch(IllegalArgumentException e){
            Log.e(TAG, "Error");

        }catch(IllegalStateException e){
            Log.e(TAG, "Error");
        }
        if(list == null ||list.size() == 0){
            ortfound = false;
            //Wenn Exceptions eingetreten sind, bleiben die Listen null
        }
        else{
            //Wenn String location in Ordnung ist
            ortfound = true;
            android.location.Address add = list.get(0);//Fülle Address Variable


            double lat = add.getLatitude(); //Hole x Koordinate
            double lng = add.getLongitude();//Hole y Koordinate

            newLocation = new LatLng(lat, lng);//Bestimme newLocation

        }

        return newLocation;
    }

    private String pack= "com.example.bruefy.pointsofinterest";

    public void SharePreferences(String title, LatLng coordinates){

        SharedPreferences prefs = this.getSharedPreferences(pack, Context.MODE_PRIVATE);

        prefs.edit().putString(pack, title).commit();
        prefs.edit().putLong(pack, Double.doubleToLongBits(coordinates.latitude)).commit();
        prefs.edit().putLong(pack, Double.doubleToLongBits(coordinates.longitude)).commit();

    }




    //Wenn Buttons oder Textfelder geklickt wurden
    public void checkIfEmpty(View v){
        createcounter++;
    }

<<<<<<< HEAD

=======
>>>>>>> origin/master
}
