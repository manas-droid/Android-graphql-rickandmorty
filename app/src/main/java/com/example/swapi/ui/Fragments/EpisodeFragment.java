package com.example.swapi.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swapi.Adapter.EpisodeRecyclerView;
import com.example.swapi.R;
import com.example.swapi.ui.ViewModels.EpisodeFragmentViewModel;

public class EpisodeFragment extends Fragment {
    private EpisodeFragmentViewModel viewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(EpisodeFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_episodes, container , false);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel.getEpisodes().observe(getActivity(), rickAndMortyResponse -> {
            if(rickAndMortyResponse!=null){
                progressBar.setVisibility(View.GONE);
                if(rickAndMortyResponse.getResponse()!=null) {
                    EpisodeRecyclerView episodeRecyclerView = new EpisodeRecyclerView(rickAndMortyResponse.getResponse().results());
                    recyclerView.setAdapter(episodeRecyclerView);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
}
