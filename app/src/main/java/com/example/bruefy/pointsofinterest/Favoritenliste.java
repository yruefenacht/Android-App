package com.example.bruefy.pointsofinterest;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;

public class Favoritenliste extends AppCompatActivity {

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriten);

        fillListe();

        File dir = new File(path);
        dir.mkdirs();

        getSupportActionBar().setElevation(0);



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

    public void fillListe(){


        ArrayAdapter menuadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView faves = (ListView) findViewById(R.id.favoriten);


        if(Bibliothek.terminort.isEmpty()){
            menuadapter.add("Keine Termine vorhanden");
        }else{
            for(int i = 0; i < Bibliothek.terminort.size(); i++){ //Lese Favoriten Liste aus der Bibliothek aus
                menuadapter.add(Bibliothek.terminort.get(i)+"  "+Bibliothek.start.get(i)+" - "+Bibliothek.ende.get(i));
                
            }
        }



        faves.setAdapter(menuadapter);
    }

    public void addTermin(View v){
        Intent intent = new Intent(getApplicationContext(), Appointment.class);
        startActivity(intent);
    }
}
