package com.example.swapi.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.swapi.ResponType.Episodes;
import com.example.swapi.repository.DataSource;


public class RickandMortyViewModel extends ViewModel implements DataSource.SetResults {
    private MutableLiveData<Episodes> episodesLiveData;

    private static final String TAG = "RickandMortyViewModel";
    DataSource dataSource;
    public RickandMortyViewModel() {
        dataSource = new DataSource();
        episodesLiveData = new MutableLiveData<>();
        episodesLiveData.setValue(null);
        dataSource.getEpisodes(this);
    }
    public MutableLiveData<Episodes> getEpisodes(){
        return this.episodesLiveData;
    }

    @Override
    public void setEpisodes(Episodes episodes) {
        this.episodesLiveData.setValue(episodes);
        Log.d(TAG, "setEpisodes: "+this.episodesLiveData.getValue().getEpisodes());
    }

}
