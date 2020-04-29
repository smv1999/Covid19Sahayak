package com.programmersgateway.sm1999.covid19sahayak.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.programmersgateway.sm1999.covid19sahayak.R;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.dashboard.Statewise;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.MyViewHolder> {

    List<Statewise> statewiseList;
    Context context;
    OnItemClickListener onItemClickListener;
    String type;

    public StateAdapter(List<Statewise> statewiseList, Context context, OnItemClickListener onItemClickListener, String type) {
        this.statewiseList = statewiseList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_item, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(itemView, onItemClickListener);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Statewise statewise = statewiseList.get(position);

        String typeResponse = null;


        if (type.equals("Confirmed")){
            typeResponse = statewise.getConfirmed();
        } else if (type.equals("Active")){
            typeResponse = statewise.getActive();
        } else if (type.equals("Recovered")){
            typeResponse = statewise.getRecovered();
        } else if (type.equals("Death")){
            typeResponse = statewise.getDeaths();
        } else {
            typeResponse = "NA";
        }

        holder.tvState.setText(statewise.getState());
        holder.tvCount.setText("(" + typeResponse + ")");


    }

    @Override
    public int getItemCount() {
        return statewiseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvState,tvCount;
        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            tvState = itemView.findViewById(R.id.tvState);
            tvCount = itemView.findViewById(R.id.tvCount);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(statewiseList.get(getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Statewise statewise);
    }

}
