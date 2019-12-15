package com.example.themoviedb.repository;

import com.example.themoviedb.BuildConfig;
import com.example.themoviedb.api.ApiCallInterface;
import com.example.themoviedb.model.GenericResponse;
import com.example.themoviedb.model.Movie;

import io.reactivex.Observable;


public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    public Observable<GenericResponse<Movie>> executeGetMovieList(String query, int page) {
        //keep this optionals for future use
        String language = "en-US";
        boolean includeAdult = true;
        return apiCallInterface.fetchMovieList(BuildConfig.API_KEY, language, query, String.valueOf(page), String.valueOf(includeAdult));
    }
}
