package com.example.swapi.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swapi.R;

import java.util.List;

import apolloSchema.LocationResultsQuery;

public class LocationRecyclerView extends RecyclerView.Adapter<LocationRecyclerView.LocationViewHolder>{

    private List<LocationResultsQuery.Result> list;
    private static final String TAG = "LocationRecyclerView";
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public LocationRecyclerView(List<LocationResultsQuery.Result> list) {
        this.list = list;
    }



    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.name.setText(list.get(position).name());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 2);
        gridLayoutManager.setInitialPrefetchItemCount(list.get(position).residents().size());
        Log.d(TAG, "onBindViewHolder: "+list.get(position).residents().size());
        ResidentRecyclerView residentRecyclerView = new ResidentRecyclerView(list.get(position).residents());

        holder.residentRecyclerView.setLayoutManager(gridLayoutManager);
        holder.residentRecyclerView.setAdapter(residentRecyclerView);
        holder.residentRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        RecyclerView residentRecyclerView;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.locationName);
            residentRecyclerView = itemView.findViewById(R.id.residentRecyclerView);

        }
    }
}
