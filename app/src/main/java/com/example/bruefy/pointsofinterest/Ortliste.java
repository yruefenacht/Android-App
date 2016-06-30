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


        ListView ortliste = (ListView) findViewById(R.id.ortliste);
        ortliste.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                final String selected = parent.getItemAtPosition(position).toString();
                AlertDialog alertDialog = new AlertDialog.Builder(Ortliste.this).create();
                alertDialog.setTitle("Favoriten");
                alertDialog.setMessage("Wollen Sie diesen Eintrag den Favoriten hinzufügen?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ja",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                boolean add = true;
                                for(int i = 0; i < Bibliothek.favoriten.size(); i++){
                                    if(selected.equals(Bibliothek.favoriten.get(i))){
                                        add = false;
                                    }

                                }
                                if(add){
                                    Bibliothek.favoriten.add(selected);
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

                                Toast.makeText(getApplicationContext(), "Zu Favoriten hinzugefügt", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Abbrechen",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

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

    public void fillOrte(){

        ArrayAdapter menuadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView ortliste = (ListView) findViewById(R.id.ortliste);


        if(Bibliothek.title.isEmpty()){
            menuadapter.add("Noch keine Orte Hinzugefügt");
        }else{
            for(int i = 0; i < Bibliothek.title.size(); i++){
                String text = Bibliothek.title.get(i);
                String inputold = "\r\n"+text +"\r\n"+Bibliothek.start.get(i)+" - "+Bibliothek.ende.get(i)+"\r\n";
                String input = inputold.replaceAll("[ \t]+(\r\n?|\n)", "$1");

                menuadapter.add(input);
            }
        }



        ortliste.setAdapter(menuadapter);

    }




}
