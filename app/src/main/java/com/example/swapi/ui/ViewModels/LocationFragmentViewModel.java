package com.example.swapi.ui.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.swapi.ResponType.RickAndMortyResponse;
import com.example.swapi.repository.DataSource;

import apolloSchema.LocationResultsQuery;

public class LocationFragmentViewModel extends ViewModel implements DataSource.SetLocations {
    private DataSource dataSource;
    private MutableLiveData<RickAndMortyResponse<LocationResultsQuery.Locations>> locationsMutableLiveData;

    public LocationFragmentViewModel(){
        dataSource = new DataSource();
        locationsMutableLiveData = new MutableLiveData<>();
        locationsMutableLiveData.setValue(null);
        dataSource.getLocations(this);
    }

    public MutableLiveData<RickAndMortyResponse<LocationResultsQuery.Locations>> getLocationsMutableLiveData() {
        return locationsMutableLiveData;
    }

    @Override
    public void setLocations(RickAndMortyResponse<LocationResultsQuery.Locations> response) {
            locationsMutableLiveData.setValue(response);
    }
}
