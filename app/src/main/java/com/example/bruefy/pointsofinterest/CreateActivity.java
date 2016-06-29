package com.example.bruefy.pointsofinterest;

<<<<<<< HEAD
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
=======
import android.content.Intent;
>>>>>>> origin/master
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
=======
>>>>>>> origin/master

public class CreateActivity extends AppCompatActivity {

    Button button;
    int day_x,month_x,year_x;
    static final int DILOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

<<<<<<< HEAD
    public void showDialogOnButtonClick(View v){
        button = (Button)findViewById(R.id.datebutton);



        /*button.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onCreate(View v) {
                    showDialog(DILOG_ID);
                }
            }

        );*/
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DILOG_ID)
            return new DatePickerDialog(this, dpickerListener.get(), day_x,month_x,year_x);
        return null;
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

=======


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
>>>>>>> origin/master
}
