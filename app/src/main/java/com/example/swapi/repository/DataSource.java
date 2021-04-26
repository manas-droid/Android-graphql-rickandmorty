package com.example.swapi.repository;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.swapi.ResponType.Episodes;

import org.jetbrains.annotations.NotNull;

import apolloSchema.GetEpisodesResultsQuery;

public class DataSource {
    private Episodes result;
    public Episodes getEpisodes(){
        result = new Episodes();
        ApolloClient apolloClient = ApolloInstance.getInstance();
        ApolloQueryCall<GetEpisodesResultsQuery.Data> episodes = apolloClient.query(GetEpisodesResultsQuery.builder().build());
        episodes.enqueue(get);
        return result;
    }


    private  ApolloCall.Callback<GetEpisodesResultsQuery.Data> get = new ApolloCall.Callback<GetEpisodesResultsQuery.Data>() {
        @Override
        public void onResponse(@NotNull Response<GetEpisodesResultsQuery.Data> response) {
            if(response.hasErrors()){
                result.setResponseErrors(response.getErrors());
                return;
            }
            else
                result.setEpisodes(response.getData().episodes());
        }
        @Override
        public void onFailure(@NotNull ApolloException e) {
            result.setOnFailureError(e.getMessage());
        }
    };



}
