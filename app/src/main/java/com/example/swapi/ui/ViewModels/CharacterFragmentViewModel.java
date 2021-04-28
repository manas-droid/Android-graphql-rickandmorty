package com.example.swapi.ui.ViewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.swapi.ResponType.RickAndMortyResponse;
import com.example.swapi.repository.DataSource;

import apolloSchema.CharacterResultsQuery;

public class CharacterFragmentViewModel extends ViewModel implements DataSource.SetCharacters {
    private MutableLiveData<RickAndMortyResponse<CharacterResultsQuery.Characters>> charactersMutableLiveData;
    DataSource dataSource;
    private static final String TAG = "CharacterFragment";
    public CharacterFragmentViewModel(){
        dataSource = new DataSource();
        charactersMutableLiveData = new MutableLiveData<>();
        charactersMutableLiveData.setValue(null);
        dataSource.getCharacters(this);
    }
    public MutableLiveData<RickAndMortyResponse<CharacterResultsQuery.Characters>> getCharactersMutableLiveData() {
        return charactersMutableLiveData;
    }

    @Override
    public void setCharacters(RickAndMortyResponse<CharacterResultsQuery.Characters> response) {
        this.charactersMutableLiveData.setValue(response);
    }

}
