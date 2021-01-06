package com.sg.sgrr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// 리사이클러뷰 어댑터
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 뷰 홀더
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View view){
            super(view);
        }
    }

    private ArrayList<BS> dataList = null;

    public MyAdapter(ArrayList<BS> data){
        dataList = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.record_result_activity, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // holder에 view 데이터 바인딩
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
