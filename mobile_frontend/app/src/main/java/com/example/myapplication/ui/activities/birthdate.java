package com.example.myapplication.ui.activities;

<<<<<<< Updated upstream
import android.app.AlertDialog;
=======
>>>>>>> Stashed changes
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
<<<<<<< Updated upstream
import android.widget.EditText;
=======
>>>>>>> Stashed changes
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.Calendar;

public class birthdate extends AppCompatActivity {
DatePickerDialog datePickerDialog;
EditText date_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream
        setContentView(R.layout.activity_birthdate);
        initDatePicker();
        date_edt = (EditText) findViewById(R.id.date_edt);
        date_edt.setText(getTodayDate());

=======
>>>>>>> Stashed changes
//        EdgeToEdge.enable(this);

        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenGender();
            }
<<<<<<< Updated upstream
=======
        });

        TextView accExist = (TextView) findViewById(R.id.accountExist);
        accExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenLogin();
            }
        });

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
>>>>>>> Stashed changes
        });

//        TextView accExist = (TextView) findViewById(R.id.accountExist);
//        accExist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                doOpenLogin();
//            }
//        });

//        ImageView back = (ImageView) findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });



    }

    private String getTodayDate() {
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
return makeDateString(day,month,year);
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        return dayOfMonth+" tháng "+month+", "+year;
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=makeDateString(dayOfMonth,month,year);
                date_edt.setText(date);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int style= AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog=new DatePickerDialog(this,style, dateSetListener,year,month,day);
    }

    private void doOpenLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    private void doOpenGender() {
        Intent intent = new Intent(this, gender.class);
        startActivity(intent);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private void doOpenLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    private void doOpenGender() {
        Intent intent = new Intent(this, gender.class);
        startActivity(intent);
    }
}