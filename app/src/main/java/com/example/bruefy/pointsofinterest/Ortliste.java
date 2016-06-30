package com.example.bruefy.pointsofinterest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ortliste extends AppCompatActivity {

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ortliste);

        fillOrte();

        File dir = new File(path);
        dir.mkdirs();

        //ClickListener auf ListView
        ListView ortliste = (ListView) findViewById(R.id.ortliste);
        ortliste.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //Hole Inhalt des Selektierten Listenelements
                final String selected = parent.getItemAtPosition(position).toString();
                //Neues PopUp Fenster
                AlertDialog alertDialog = new AlertDialog.Builder(Ortliste.this).create();
                alertDialog.setTitle("Favoriten");
                alertDialog.setMessage("Wollen Sie diesen Eintrag den Favoriten hinzufügen?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ja", //Positiver Button
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                boolean add = true;
                                for(int i = 0; i < Bibliothek.favoriten.size(); i++){
                                    if(selected.equals(Bibliothek.favoriten.get(i))){
                                        //Schaue ob das Ausgewählte ListView Element schon in der Favoritenliste vorhanden ist
                                        add = false;
                                    }

                                }
                                if(add){//Wenn Ja
                                    Bibliothek.favoriten.add(selected);
                                    Toast.makeText(getApplicationContext(), "Zu Favoriten hinzugefügt", Toast.LENGTH_LONG).show();
                                }else{
                                    AlertDialog alertDialog = new AlertDialog.Builder(Ortliste.this).create();
                                    alertDialog.setTitle("Fehler");
                                    alertDialog.setMessage("Ort ist schon bei den Favoriten");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();

                                                }
                                            });
                                    alertDialog.show();
                                }


                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Abbrechen",//Zurück
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        //ActionBar Schatten
        getSupportActionBar().setElevation(0);


    }


    public void ButtonClick(View v){
        //Hole geklickten Button
        Button button = (Button) findViewById(v.getId());

        Intent maps = new Intent(getApplicationContext(), MapsActivity.class);
        Intent create = new Intent(getApplicationContext(), CreateActivity.class);
        Intent orte = new Intent(getApplicationContext(), Ortliste.class);
        Intent fav = new Intent(getApplicationContext(), Favoritenliste.class);



        switch(button.getText().toString()){
            case "1":
                startActivity(maps);
                overridePendingTransition(0, 0);//Activity Transition
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

    public void addOrtClick(View v){
        //Plus Button geklickt
        Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
        startActivity(intent);
    }

    public void fillOrte(){

        ArrayAdapter menuadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView ortliste = (ListView) findViewById(R.id.ortliste);


        if(Bibliothek.title.isEmpty()){
            menuadapter.add("Noch keine Orte Hinzugefügt");
        }else{
            for(int i = 0; i < Bibliothek.title.size(); i++){//Fülle ListView anhand von der List in Bibliothek
                String title = Bibliothek.title.get(i);

                menuadapter.add(title);
            }
        }

        ortliste.setAdapter(menuadapter);

    }




}
