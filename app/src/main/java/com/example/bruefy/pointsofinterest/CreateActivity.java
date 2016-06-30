package com.example.bruefy.pointsofinterest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    Button button;
    int day_x,month_x,year_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }


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
}
