package com.example.swapi.repository;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class ApolloInstance {
    public static final String BASE_URL = "https://rickandmortyapi.com/graphql";
    private static ApolloClient apolloClient = null;

    public static ApolloClient getInstance(){
        if(apolloClient == null){

            apolloClient =  ApolloClient
                    .builder()
                    .okHttpClient(new OkHttpClient.Builder().build())
                    .serverUrl(BASE_URL)
                    .build();
        }
        return apolloClient;
    }
}
