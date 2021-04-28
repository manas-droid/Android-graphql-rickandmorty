package com.example.swapi.ResponType;

import com.apollographql.apollo.api.Error;

import java.util.List;

public class RickAndMortyResponse<Response>{
    private List<Error> responseErrors;
    private Response response;
    private String onFailureResponse;

    public List<Error> getResponseErrors() {
        return responseErrors;
    }

    public void setResponseErrors(List<Error> responseErrors) {
        this.responseErrors = responseErrors;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getOnFailureResponse() {
        return onFailureResponse;
    }

    public void setOnFailureResponse(String onFailureResponse) {
        this.onFailureResponse = onFailureResponse;
    }
}
