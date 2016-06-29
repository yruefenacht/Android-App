package com.example.bruefy.pointsofinterest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
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
    static final int DILOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

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

}
