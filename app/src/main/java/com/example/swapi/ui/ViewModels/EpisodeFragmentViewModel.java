package com.example.swapi.ui.ViewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.swapi.ResponType.RickAndMortyResponse;
import com.example.swapi.repository.DataSource;

import apolloSchema.GetEpisodesResultsQuery;


public class EpisodeFragmentViewModel extends ViewModel implements DataSource.SetEpisodes {
    private MutableLiveData<RickAndMortyResponse<GetEpisodesResultsQuery.Episodes>> episodesLiveData;

    private static final String TAG = "RickandMortyViewModel";
    DataSource dataSource;
    public EpisodeFragmentViewModel() {
        dataSource = new DataSource();
        episodesLiveData = new MutableLiveData<>();
        episodesLiveData.setValue(null);
        dataSource.getEpisodes(this);
    }
    public MutableLiveData<RickAndMortyResponse<GetEpisodesResultsQuery.Episodes>> getEpisodes(){
        return this.episodesLiveData;
    }

    @Override
    public void setEpisodes(RickAndMortyResponse<GetEpisodesResultsQuery.Episodes> episodes) {
        this.episodesLiveData.setValue(episodes);
        Log.d(TAG, "setEpisodes: "+this.episodesLiveData.getValue().getResponse());
    }

}
