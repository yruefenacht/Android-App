package com.example.bruefy.pointsofinterest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Ortliste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ortliste);

        fillOrte();

    }

    public void fillOrte(){
        MapsActivity m = new MapsActivity();
        ArrayAdapter menuadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView ortliste = (ListView) findViewById(R.id.ortliste);

        for(String listelement : m.meineOrte){
            menuadapter.add(listelement);
        }


        ortliste.setAdapter(menuadapter);
    }
}
