package com.example.ncubbm;

import android.content.Context;
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
            hold.t_id.setText(data.getT_id());
            hold.t_name.setText(data.getT_name());
            hold.t_m1.setText(data.getT_m1());
            hold.t_m2.setText(data.getT_m2());
            hold.t_m3.setText(data.getT_m3());
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
        private TextView t_id;
        private TextView t_name;
        private TextView t_m1;
        private TextView t_m2;
        private TextView t_m3;

        public MyHolder(View itemView) {
            super(itemView);
            t_id=itemView.findViewById(R.id.t1);
            t_name=itemView.findViewById(R.id.t2);
            t_m1=itemView.findViewById(R.id.t3);
            t_m2=itemView.findViewById(R.id.t4);
            t_m3=itemView.findViewById(R.id.t5);

        }
    }
}
