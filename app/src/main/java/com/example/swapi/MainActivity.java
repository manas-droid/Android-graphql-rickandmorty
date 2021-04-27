package com.example.swapi;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.swapi.Adapter.EpisodeRecyclerView;
import com.example.swapi.ResponType.Episodes;
import com.example.swapi.ui.RickandMortyViewModel;

public class MainActivity extends AppCompatActivity   {
    public Observer<Episodes> episodesObserver;
    private  RickandMortyViewModel viewModel;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(RickandMortyViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        episodesObserver = episodes -> {
            if(episodes!=null && episodes.getEpisodes()!=null && episodes.getEpisodes().results().size()>0){
                EpisodeRecyclerView episodeRecyclerView = new EpisodeRecyclerView(episodes.getEpisodes().results());
                recyclerView.setAdapter(episodeRecyclerView);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }
        };
        viewModel.getEpisodes().observeForever(episodesObserver);
        Log.d(TAG, "onCreate: "+viewModel.getEpisodes().hasActiveObservers());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.getEpisodes().removeObserver(episodesObserver);
        Log.d(TAG, "onDestroy: "+viewModel.getEpisodes().hasActiveObservers());
    }
}