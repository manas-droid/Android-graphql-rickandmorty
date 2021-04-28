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

import apolloSchema.CharacterResultsQuery;


public class CharacterRecyclerView extends RecyclerView.Adapter<CharacterRecyclerView.CharacterViewHolder>{
    List<CharacterResultsQuery.Result> characterResult;

    private static final String TAG = "CharacterRecyclerView";
    public CharacterRecyclerView(List<CharacterResultsQuery.Result> characterResult){
        this.characterResult = characterResult;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_characters, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {

        holder.name.setText(this.characterResult.get(position).name());
        Glide.with(holder.itemView.getContext())
                .load(this.characterResult.get(position).image()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return characterResult.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView imageView;
        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
