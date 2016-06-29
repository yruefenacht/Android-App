package com.example.bruefy.pointsofinterest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Ortliste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ortliste);

        fillOrte();

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
                break;
            case "2":
                startActivity(create);
                break;
            case "3":
                startActivity(orte);
                break;
            default:
                startActivity(fav);
                break;
        }


    }

    public void fillOrte(){
        MapsActivity m = new MapsActivity();
        ArrayAdapter menuadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView ortliste = (ListView) findViewById(R.id.ortliste);


        if(Bibliothek.Orte.isEmpty()){
            menuadapter.add("Noch keine Ort Hinzugefügt");
        }else{
            for(int i = 0; i <= Bibliothek.Orte.size(); i++){
                menuadapter.add(Bibliothek.Orte.get(i));
            }
        }



        ortliste.setAdapter(menuadapter);
    }
}
