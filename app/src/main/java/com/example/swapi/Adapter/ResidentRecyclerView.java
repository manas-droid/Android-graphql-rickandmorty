package com.example.swapi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swapi.R;

import java.util.List;

import apolloSchema.LocationResultsQuery;

public class ResidentRecyclerView extends  RecyclerView.Adapter<ResidentRecyclerView.ResidentViewHolder>{
    private List<LocationResultsQuery.Resident> residentList;

    public ResidentRecyclerView(List<LocationResultsQuery.Resident> residentList) {
        this.residentList = residentList;
    }

    @NonNull
    @Override
    public ResidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_residents, parent, false);
        return new ResidentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentViewHolder holder, int position) {
        holder.name.setText(residentList.get(position).name());
        Glide.with(holder.itemView.getContext()).load(residentList.get(position).image()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.residentList.size();
    }

    public class ResidentViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        public ResidentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}

