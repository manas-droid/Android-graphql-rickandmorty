package com.example.swapi.ui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swapi.Adapter.CharacterRecyclerView;
import com.example.swapi.R;
import com.example.swapi.ResponType.RickAndMortyResponse;
import com.example.swapi.ui.ViewModels.CharacterFragmentViewModel;

import apolloSchema.CharacterResultsQuery;

public class CharactersFragment extends Fragment {
    private CharacterFragmentViewModel characterFragmentViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private static final String TAG = "CharactersFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        characterFragmentViewModel = new ViewModelProvider(getActivity(), ViewModelProvider
                .AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(CharacterFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters, container , false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        characterFragmentViewModel.getCharactersMutableLiveData().observe(getActivity(), charactersRickAndMortyResponse -> {
            if(charactersRickAndMortyResponse!=null){
                progressBar.setVisibility(View.GONE);
                if(charactersRickAndMortyResponse.getResponse()!=null){
                    CharacterRecyclerView characterRecyclerView = new CharacterRecyclerView(charactersRickAndMortyResponse.getResponse().results());
                    recyclerView.setAdapter(characterRecyclerView);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }
            }
            else{
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }




}
