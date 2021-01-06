package com.sg.sgrr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_result_activity);

        Button solo = (Button) findViewById(R.id.Solo);
        Button duo = (Button) findViewById(R.id.Duo);
        Button squad = (Button) findViewById(R.id.Squad);

        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "솔로", Toast.LENGTH_SHORT).show();
                //getFragmentManager().beginTransaction().replace(R.id.Record, new solo_fragment()).commit();
            }
        });

        duo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "듀오", Toast.LENGTH_SHORT).show();
                //getFragmentManager().beginTransaction().replace(R.id.Record, new duo_fragment()).commit();
            }
        });

        squad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "스쿼드", Toast.LENGTH_SHORT).show();
                //getFragmentManager().beginTransaction().replace(R.id.Record, new squad_fragment()).commit();
            }
        });
    }
}
