package com.example.swapi.ResponType;

import com.apollographql.apollo.api.Error;

import java.util.List;

import apolloSchema.GetEpisodesResultsQuery;

public class Episodes {
    private List<Error>  responseErrors;
    private GetEpisodesResultsQuery.Episodes episodes;
    private String onFailureError;

    public List<Error> getResponseErrors() {
        return responseErrors;
    }

    public void setResponseErrors(List<Error> responseErrors) {
        this.responseErrors = responseErrors;
    }

    public GetEpisodesResultsQuery.Episodes getEpisodes() {
        return episodes;
    }

    public void setEpisodes(GetEpisodesResultsQuery.Episodes episodes) {
        this.episodes = episodes;
    }

    public String getOnFailureError() {
        return onFailureError;
    }

    public void setOnFailureError(String onFailureError) {
        this.onFailureError = onFailureError;
    }
}
