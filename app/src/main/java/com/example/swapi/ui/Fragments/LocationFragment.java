package com.example.swapi.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swapi.Adapter.LocationRecyclerView;
import com.example.swapi.R;
import com.example.swapi.ResponType.RickAndMortyResponse;
import com.example.swapi.ui.ViewModels.LocationFragmentViewModel;

import apolloSchema.LocationResultsQuery;

public class LocationFragment extends Fragment {
    private LocationFragmentViewModel locationFragmentViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationFragmentViewModel = new ViewModelProvider(getActivity() ,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(LocationFragmentViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container , false);
        recyclerView = view.findViewById(R.id.locationRecyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        locationFragmentViewModel.getLocationsMutableLiveData().observe(getActivity(), new Observer<RickAndMortyResponse<LocationResultsQuery.Locations>>() {
            @Override
            public void onChanged(RickAndMortyResponse<LocationResultsQuery.Locations> locationsRickAndMortyResponse) {
                if(locationsRickAndMortyResponse!=null){
                    progressBar.setVisibility(View.GONE);
                    LocationRecyclerView locationRecyclerView = new LocationRecyclerView(locationsRickAndMortyResponse.getResponse().results());
                    recyclerView.setAdapter(locationRecyclerView);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }

}
