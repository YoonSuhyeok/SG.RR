package com.sg.sgrr;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sg.sgrr.fragment.duo_fragment;
import com.sg.sgrr.fragment.normal_fragemnt;
import com.sg.sgrr.fragment.rank_fragment;
import com.sg.sgrr.fragment.solo_fragment;
import com.sg.sgrr.fragment.squad_fragment;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_result_activity);
//
//        findViewById(R.id.Solo).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction().replace(R.id.Record, new solo_fragment()).commit();
//            }
//        });
//
//        findViewById(R.id.Duo).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction().replace(R.id.Record, new duo_fragment()).commit();
//            }
//        });
//
//        findViewById(R.id.Squad).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction().replace(R.id.Record, new squad_fragment()).commit();
//            }
//        });
//
//        findViewById(R.id.Rankgame).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction().replace(R.id.layout_summary, new rank_fragment()).commit();
//            }
//        });
//
//        findViewById(R.id.Normalgame).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction().replace(R.id.layout_summary, new normal_fragemnt()).commit();
//            }
//        });
    }
}
