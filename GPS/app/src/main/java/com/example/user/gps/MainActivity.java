package com.example.user.gps;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.annotation.MediumTest;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText HR;
    private EditText MM;
    private EditText SS;
    private Button Show_Time;

    long x , y, z;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HR = (EditText)findViewById(R.id.hr);
        MM = (EditText)findViewById(R.id.mm);
        SS = (EditText)findViewById(R.id.ss);
        Show_Time = (Button)findViewById(R.id.Show_Time);
        Show_Time.setOnClickListener(this);
    }
    public void start(View view){
        int h,m,s,total;
        h = Integer.parseInt(HR.getText().toString());
        m = Integer.parseInt(MM.getText().toString());
        s = Integer.parseInt(SS.getText().toString());
        total = h*60*60*1000 + m*60*1000 + s*1000;
        HR.setCursorVisible(false);
        MM.setCursorVisible(false);
        SS.setCursorVisible(false);
        HR.setInputType(InputType.TYPE_NULL);
        MM.setInputType(InputType.TYPE_NULL);
        SS.setInputType(InputType.TYPE_NULL);
        new CountDownTimer(total, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                x = millisUntilFinished / 60 /60 / 1000;
                y = (millisUntilFinished -( x * 1000*60*60))/60/1000;
                z = (millisUntilFinished - (x * 1000 * 60 * 60) - (y * 60 * 1000))/1000;
            }

            @Override
            public void onFinish() {
                HR.setCursorVisible(true);
                MM.setCursorVisible(true);
                SS.setCursorVisible(true);
                Toast toast = Toast.makeText(MainActivity.this, "時間到！！！", Toast.LENGTH_LONG);
                toast.show();

            }
        }.start();
    }
    @Override
    public void onClick(View v) {
        Toast toast = Toast.makeText(MainActivity.this, x+"時"+y+"分"+z+"秒", Toast.LENGTH_SHORT);
        toast.show();
    }
}

