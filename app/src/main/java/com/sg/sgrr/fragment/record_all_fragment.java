package com.sg.sgrr.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.sg.sgrr.R;

public class record_all_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_record_all, container, false);

        LinearLayout layout = rootView.findViewById(R.id.frag_record_all);
        for(int i = 0; i < 6; i++) {
            //View newView = inflater.inflate(R.layout.record_result_fragment, layout, false);
            layout.addView(inflater.inflate(R.layout.record_result_fragment, layout, false));
        }

        return rootView;
    }
}