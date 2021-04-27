package com.example.swapi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swapi.R;

import java.util.List;

import apolloSchema.GetEpisodesResultsQuery;


public class EpisodeRecyclerView  extends  RecyclerView.Adapter<EpisodeRecyclerView.EpisodeViewHolder>{
    List<GetEpisodesResultsQuery.Result> results;

    public EpisodeRecyclerView(List<GetEpisodesResultsQuery.Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_episode, parent , false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.episode.setText(this.results.get(position).episode());
        holder.name.setText(this.results.get(position).name());
    }

    @Override
    public int getItemCount() {
        return this.results.size();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder{
        TextView name , episode;
        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            episode = itemView.findViewById(R.id.episode);
        }
    }

}
