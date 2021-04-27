package com.example.swapi.repository;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.swapi.ResponType.Episodes;
import com.example.swapi.ui.RickandMortyViewModel;

import org.jetbrains.annotations.NotNull;

import apolloSchema.GetEpisodesResultsQuery;

public class DataSource {
    private static final String TAG = "DataSource";
    Episodes episode;
    Handler handler;
    public DataSource(){
        handler = new Handler(Looper.getMainLooper());
    }
    public void getEpisodes(SetResults results){
        episode = new Episodes();
        ApolloClient apolloClient = ApolloInstance.getInstance();
        ApolloQueryCall<GetEpisodesResultsQuery.Data> episodes = apolloClient.query(GetEpisodesResultsQuery.builder().build());
        episodes.enqueue(new ApolloCall.Callback<GetEpisodesResultsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetEpisodesResultsQuery.Data> response) {
                if(response.hasErrors()){
                       episode.setResponseErrors(response.getErrors());
                }
                else{
                    episode.setEpisodes(response.getData().episodes());
                }

                handler.post(() -> results.setEpisodes(episode));
            }
            @Override
            public void onFailure(@NotNull ApolloException e) {
                episode.setOnFailureError(e.getMessage());
                handler.post(() -> results.setEpisodes(episode));
            }
        });

    }

    public interface SetResults{
     void setEpisodes(Episodes episodes);
    }
}
