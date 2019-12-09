package com.example.themoviedb.repository;

import com.example.themoviedb.api.ApiCallInterface;

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }
}
