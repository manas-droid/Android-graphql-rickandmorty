package com.example.swapi.repository;

import android.os.Handler;
import android.os.Looper;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.swapi.ResponType.RickAndMortyResponse;
import org.jetbrains.annotations.NotNull;

import apolloSchema.CharacterResultsQuery;
import apolloSchema.GetEpisodesResultsQuery;
import apolloSchema.LocationResultsQuery;

public class DataSource {
    private static final String TAG = "DataSource";
    RickAndMortyResponse<GetEpisodesResultsQuery.Episodes> episodesRickAndMortyResponse;
    RickAndMortyResponse<CharacterResultsQuery.Characters> charactersRickAndMortyResponse;
    RickAndMortyResponse<LocationResultsQuery.Locations> locationsRickAndMortyResponse;
    Handler handler;
    public DataSource(){
        handler = new Handler(Looper.getMainLooper());
    }

    public void getEpisodes(SetEpisodes results){
        episodesRickAndMortyResponse = new RickAndMortyResponse<>();
        ApolloClient apolloClient = ApolloInstance.getInstance();
        ApolloQueryCall<GetEpisodesResultsQuery.Data> episodes = apolloClient.query(GetEpisodesResultsQuery.builder().build());
        episodes.enqueue(new ApolloCall.Callback<GetEpisodesResultsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetEpisodesResultsQuery.Data> response) {
                if(response.hasErrors()){
                       episodesRickAndMortyResponse.setResponseErrors(response.getErrors());
                }
                else{
                    episodesRickAndMortyResponse.setResponse(response.getData().episodes());
                }
                handler.post(() -> results.setEpisodes(episodesRickAndMortyResponse));
            }
            @Override
            public void onFailure(@NotNull ApolloException e) {
                episodesRickAndMortyResponse.setOnFailureResponse(e.getMessage());
                handler.post(() -> results.setEpisodes(episodesRickAndMortyResponse));
            }
        });
    }

    public void getCharacters(SetCharacters sendCharacters){
        charactersRickAndMortyResponse = new RickAndMortyResponse<>();
        ApolloClient apolloClient = ApolloInstance.getInstance();
        ApolloQueryCall<CharacterResultsQuery.Data> characters = apolloClient.query(CharacterResultsQuery.builder().build());

        characters.enqueue(new ApolloCall.Callback<CharacterResultsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<CharacterResultsQuery.Data> response) {
                if(response.hasErrors()){
                    charactersRickAndMortyResponse.setResponseErrors(response.getErrors());
                }
                else{
                    charactersRickAndMortyResponse.setResponse(response.getData().characters());
                }
                handler.post(()->sendCharacters.setCharacters(charactersRickAndMortyResponse));
            }
            @Override
            public void onFailure(@NotNull ApolloException e) {
                charactersRickAndMortyResponse.setOnFailureResponse(e.getMessage());
                handler.post(()->sendCharacters.setCharacters(charactersRickAndMortyResponse));
            }
        });
    }

    public void getLocations(SetLocations locations){
        locationsRickAndMortyResponse = new RickAndMortyResponse<>();
        ApolloClient apolloClient = ApolloInstance.getInstance();
        ApolloQueryCall<LocationResultsQuery.Data> dataApolloQueryCall = apolloClient.query(LocationResultsQuery.builder().build());
        dataApolloQueryCall.enqueue(new ApolloCall.Callback<LocationResultsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<LocationResultsQuery.Data> response) {
                if(response.hasErrors()){
                    locationsRickAndMortyResponse.setResponseErrors(response.getErrors());
                }else{
                    locationsRickAndMortyResponse.setResponse(response.getData().locations());
                }

                handler.post(()->locations.setLocations(locationsRickAndMortyResponse));
            }
            @Override
            public void onFailure(@NotNull ApolloException e) {
                locationsRickAndMortyResponse.setOnFailureResponse(e.getMessage());
                handler.post(()->locations.setLocations(locationsRickAndMortyResponse));
            }
        });
    }



    public interface SetEpisodes {
     void setEpisodes(RickAndMortyResponse<GetEpisodesResultsQuery.Episodes> response);
    }

    public interface SetCharacters{
        void setCharacters(RickAndMortyResponse<CharacterResultsQuery.Characters> response);
    }

    public interface SetLocations{
        void setLocations(RickAndMortyResponse<LocationResultsQuery.Locations> response);
    }
}
