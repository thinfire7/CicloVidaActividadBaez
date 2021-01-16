package com.cronometrobaez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    private boolean running;
    private String time="";
    int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        seconds = 0;
        final TextView roundview = (TextView) findViewById(R.id.roundview);
        roundview.setText("");
        contador=0;
    }

    public void onClickRound(View view){
        if(contador<10){
            contador++;
            final TextView roundview = (TextView) findViewById(R.id.roundview);
            String textoActual = roundview.getText().toString();
            roundview.setText(textoActual + "\n" + "vuelta: " + contador + " tiempo: " +time);
        }
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.timeview);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }

}