package com.example.bruefy.pointsofinterest;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
>>>>>>> origin/master
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
<<<<<<< HEAD
=======
>>>>>>> origin/master
>>>>>>> origin/master
>>>>>>> origin/master
>>>>>>> origin/master
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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
=======
<<<<<<< HEAD
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
<<<<<<< HEAD
=======
=======
>>>>>>> origin/master
>>>>>>> origin/master
>>>>>>> origin/master


    Button button;
    int day_x,month_x,year_x;

>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        File dir = new File(path);
        dir.mkdirs();

        //Entferne Activity Transition
        getSupportActionBar().setElevation(0);



    }


<<<<<<< HEAD
=======
    public final ThreadLocal<DatePickerDialog.OnDateSetListener> dpickerListener
            = new ThreadLocal<DatePickerDialog.OnDateSetListener>() {
        @Override
        protected DatePickerDialog.OnDateSetListener initialValue() {
            return new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int dayOfMonth, int monthOfYear, int year) {
                    year_x = year;
                    month_x = monthOfYear;
                    day_x = dayOfMonth;
                    Toast.makeText(CreateActivity.this, day_x + "/" + month_x + "/" + year_x, Toast.LENGTH_LONG).show();
                }
            };
        }
    };

>>>>>>> origin/master

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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
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




    private void updateLabel() {

        String myFormat = "dd.MM.yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Button date1 = (Button) findViewById(R.id.datebutton);
        Button date2 = (Button) findViewById(R.id.datebutton2);



        if(pressedButton.equals("start")){
            date1.setText(sdf.format(myCalendar.getTime()));
        }else{
            date2.setText(sdf.format(myCalendar.getTime()));
        }
    }

    private int createcounter = 0;
    private boolean ortfound = false;

    public void addLocation(View v) {


        //Java, Hole mir die Überschrift von folgenden Buttons:
        EditText ortinput = (EditText) findViewById(R.id.titel);
        String title = ortinput.getText().toString();

        EditText locationstring = (EditText) findViewById(R.id.ort);
        String location = locationstring.getText().toString();

        Button startb = (Button) findViewById(R.id.datebutton);
        String start = startb.getText().toString();

        Button endeb = (Button) findViewById(R.id.datebutton2);
        String ende = endeb.getText().toString();

        if(createcounter >= 3){

            LatLng address = findCoordinates(location);
            if(ortfound){
                //ERFOLG!!!
                Bibliothek.Coords.add(address);
                Bibliothek.title.add(title);
                Bibliothek.start.add(start);
                Bibliothek.ende.add(ende);

                //Schreib mir die 4 Werte in ein Text-File
                WriteInTextFile(title, start, ende, address);


                Intent map = new Intent(getApplicationContext(), MapsActivity.class);

                startActivity(map);
                createcounter = 0;
                ortfound = false;
            }else{
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



        Geocoder gc = new Geocoder(this);
        List<android.location.Address> list = null;
        LatLng newLocation = null;

        try {
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

        }
        else{
            ortfound = true;
            android.location.Address add = list.get(0);
            String locality = add.getLocality();

            double lat = add.getLatitude();
            double lng = add.getLongitude();

            newLocation = new LatLng(lat, lng);

        }

        return newLocation;
    }

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

    public void WriteInTextFile(String title, String start, String ende, LatLng coordinates){

        File file = new File(path + "/daten.txt");
        if(! file.exists()){
            try {
                file.createNewFile();
            }catch(IOException e){
                Log.e(TAG, "Error");
            }
        }
        String saveText = title;

        //Save (file, saveText);

    }

    public void Save(File file, String data){
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            try{
                /*for(int i = 0; i<data.length; i++){
                    fos.write(data[i].getBytes());
                    if(i < data.length - 1){
                        fos.write("\n".getBytes());
                    }
                }*/
                fos.write(data.getBytes());

            }catch(IOException e){
                e.printStackTrace();
            }
        }finally {
            try{
                fos.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }



    public void checkIfEmpty(View v){
        createcounter++;
    }





<<<<<<< HEAD
=======
=======
>>>>>>> origin/master
>>>>>>> origin/master
>>>>>>> origin/master
>>>>>>> origin/master
}
