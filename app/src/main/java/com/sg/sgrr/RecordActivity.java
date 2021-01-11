package com.sg.sgrr;

import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.sg.sgrr.fragment.record_all_fragment;
import com.sg.sgrr.fragment.record_duo_fragment;
import com.sg.sgrr.fragment.record_solo_fragment;
import com.sg.sgrr.fragment.record_squad_fragment;
import com.sg.sgrr.fragment.total_all_fragment;
import com.sg.sgrr.fragment.total_duo_fragment;
import com.sg.sgrr.fragment.total_solo_fragment;
import com.sg.sgrr.fragment.total_squad_fragment;

public class RecordActivity extends AppCompatActivity {

    /*토스트 테스트

    Toast.makeText(getApplicationContext(), "total - 전체", Toast.LENGTH_SHORT).show();

    토스트 테스트*/

    LayoutInflater inflater;
    LinearLayout linearLayout;

    @Override
    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_result_activity);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Total 부분 버튼 동작
        // total - 전체
        findViewById(R.id.total_btn_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // total - summary 변경
                getFragmentManager().beginTransaction().replace(R.id.total_summary, new total_all_fragment()).commit();
            }
        });

        // total - 솔로
        findViewById(R.id.total_btn_solo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // total - summary 변경
                getFragmentManager().beginTransaction().replace(R.id.total_summary, new total_solo_fragment()).commit();
            }
        });

        // total - 듀오
        findViewById(R.id.total_btn_duo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // total - summary 변경
                getFragmentManager().beginTransaction().replace(R.id.total_summary, new total_duo_fragment()).commit();
            }
        });

        // total - 스쿼드
        findViewById(R.id.total_btn_squad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // total - summary 변경
                getFragmentManager().beginTransaction().replace(R.id.total_summary, new total_squad_fragment()).commit();
            }
        });

        // Record 부분 버튼 동작
        // record - 전체
        findViewById(R.id.record_btn_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // record - layout_record_slide 변경
                getFragmentManager().beginTransaction().replace(R.id.layout_record, new record_all_fragment()).commit();
            }
        });

        // record - 솔로
        findViewById(R.id.record_btn_solo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // record - layout_record_slide 변경
                getFragmentManager().beginTransaction().replace(R.id.layout_record, new record_solo_fragment()).commit();
            }
        });

        // record - 듀오
        findViewById(R.id.record_btn_duo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // record - layout_record_slide 변경
                getFragmentManager().beginTransaction().replace(R.id.layout_record, new record_duo_fragment()).commit();
            }
        });

        // record - 스쿼드
        findViewById(R.id.record_btn_squad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // record - layout_record_slide 변경
                getFragmentManager().beginTransaction().replace(R.id.layout_record, new record_squad_fragment()).commit();
            }
        });
    }
}
