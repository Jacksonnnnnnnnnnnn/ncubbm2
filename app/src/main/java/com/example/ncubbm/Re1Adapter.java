package com.example.ncubbm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class Re1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private Context context;
    private ArrayList<Re1_list> items;
    private OnItemClickListener monitemClickListener=null;

    public static interface OnItemClickListener{
        void onItemClick(View view,int i);
    }

    public Re1Adapter(Context context, ArrayList<Re1_list> items) {
        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(context).inflate(R.layout.rel_text,null);
        RecyclerView.ViewHolder holder = new MyHolder(item);
        item.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        MyHolder hold=(MyHolder) viewHolder;
        Re1_list data = items.get(i);
            hold.t_date.setText(data.getT_date());
            hold.t_pid.setText(data.getT_pid());
            hold.t_bedNo.setText(data.getT_bedNo());
            hold.t_s1.setText(data.getT_s1());
            hold.t_s2.setText(data.getT_s2());
            hold.t_s3.setText(data.getT_s3());
            hold.t_s4.setText(data.getT_s4());
            hold.t_remark.setText(data.getT_remark());
            hold.itemView.setTag(i);

    }

    @Override
    public int getItemCount() {
        return items ==null?0:items.size();
    }

    @Override
    public void onClick(View v) {
        if (monitemClickListener != null){
            monitemClickListener.onItemClick(v,(int)v.getTag());
        }

    }

    public void setOnItemClickListener(OnItemClickListener monitemClickListener) {
        this.monitemClickListener = monitemClickListener;
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        private TextView t_date;
        private TextView t_pid;
        private TextView t_bedNo;
        private TextView t_s1;
        private TextView t_s2;
        private TextView t_s3;
        private TextView t_s4;
        private TextView t_remark;

        public MyHolder(View itemView) {
            super(itemView);
            Log.d("checkInsert","Re1Adapter");
            t_date=itemView.findViewById(R.id.t2);
            t_pid=itemView.findViewById(R.id.t3);
            t_bedNo=itemView.findViewById(R.id.t4);
            t_s1=itemView.findViewById(R.id.t5);
            t_s2=itemView.findViewById(R.id.t6);
            t_s3=itemView.findViewById(R.id.t7);
            t_s4=itemView.findViewById(R.id.t8);
            t_remark=itemView.findViewById(R.id.t9);

        }
    }
}
