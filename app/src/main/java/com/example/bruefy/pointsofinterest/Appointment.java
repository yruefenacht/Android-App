package com.example.bruefy.pointsofinterest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Appointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        fillSpinner();
    }

    Calendar myCalendar = Calendar.getInstance();
    private String pressedButton;

    public void DatePicked(View v){
        //Button geklickt
        new DatePickerDialog(Appointment.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        Button btn = (Button) findViewById(v.getId());

        pressedButton = btn.getTag().toString(); //Bestimme den geklickten Button


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
            //Übernehme Datum auf DatePicker
        }

    };

    public void fillSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(int i = 0; i < Bibliothek.title.size(); i++){
            spinAdapter.add(Bibliothek.title.get(i));
        }

        spinner.setAdapter(spinAdapter);
    }


    private void updateLabel() {

        //Ändere den Text der Buttons
        String myFormat = "dd.MM.yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Button date1 = (Button) findViewById(R.id.startbutton);
        Button date2 = (Button) findViewById(R.id.endebutton);



        if(pressedButton.equals("start")){
            date1.setText(sdf.format(myCalendar.getTime()));
        }else{
            date2.setText(sdf.format(myCalendar.getTime()));
        }
    }

    public void addTerminAndOrt(View v){

        Button startb = (Button) findViewById(R.id.startbutton);
        Button endeb = (Button) findViewById(R.id.endebutton);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        String start = startb.getText().toString();
        String ende = endeb.getText().toString();
        String ort = spinner.getSelectedItem().toString();

        Bibliothek.start.add(start);
        Bibliothek.ende.add(ende);
        Bibliothek.terminort.add(ort);

        Intent intent = new Intent(getApplicationContext(), Favoritenliste.class);
        startActivity(intent);

    }


}
