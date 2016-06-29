package com.example.bruefy.pointsofinterest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private final static String Map = "Map";
    private final static String Create = "Eriegnis erstellen";
    private final static String ortliste = "Orte und Ereignisse";
    private final static String favorite = "Favoriten";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillmenu();
        ListView lv = (ListView) findViewById(R.id.menu);
        lv.setOnItemClickListener(menuClickListener);
    }

    public void fillmenu(){
        ListView lv = (ListView) findViewById(R.id.menu);
        ArrayAdapter menuadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        menuadapter.add(Map);
        menuadapter.add(ortliste);
        menuadapter.add(Create);
        menuadapter.add(favorite);
        lv.setAdapter(menuadapter);
    }

    AdapterView.OnItemClickListener menuClickListener = new AdapterView.OnItemClickListener(){
      public void onItemClick(AdapterView parent, View v, int position, long id){
          Intent maps = new Intent(getApplicationContext(), MapsActivity.class);
          Intent create = new Intent(getApplicationContext(), CreateActivity.class);
          Intent orte = new Intent(getApplicationContext(), Ortliste.class);
          Intent fav = new Intent(getApplicationContext(), MainActivity.class);
          Intent favorite = new Intent(getApplicationContext(), MainActivity.class);

          String selected = parent.getItemAtPosition(position).toString();

          switch(selected){
              case Map:
                  startActivity(maps);
                  break;
              case Create:
                  startActivity(create);
                  break;
              case ortliste:
                  startActivity(orte);
                  break;
              default:
                  startActivity(maps);
                  break;
          }
      }
    };




}
