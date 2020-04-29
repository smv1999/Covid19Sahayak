package com.programmersgateway.sm1999.covid19sahayak.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.programmersgateway.sm1999.covid19sahayak.R;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.statewise.DistrictDatum;

import java.util.List;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.MyViewHolder> {

    List<DistrictDatum> districtDataList;
    Context context;
    OnItemClickListener onItemClickListener;

    public DistrictAdapter(List<DistrictDatum> districtDataList, Context context, OnItemClickListener onItemClickListener) {
        this.districtDataList = districtDataList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
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
        DistrictDatum districtDatum = districtDataList.get(position);

        String typeResponse = null;


        typeResponse = districtDatum.getConfirmed() + "";

        holder.tvState.setText(districtDatum.getDistrict());
        holder.tvCount.setText("(" + typeResponse + ")");


    }

    @Override
    public int getItemCount() {
        return districtDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvState, tvCount;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            tvState = itemView.findViewById(R.id.tvState);
            tvCount = itemView.findViewById(R.id.tvCount);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(districtDataList.get(getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DistrictDatum districtDatum);
    }

}
