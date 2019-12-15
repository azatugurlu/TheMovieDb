package com.example.themoviedb.api;

import com.example.themoviedb.model.GenericResponse;
import com.example.themoviedb.model.Movie;
import com.example.themoviedb.utils.Urls;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCallInterface {

    @GET(Urls.FetchMovieList)
    Observable<GenericResponse<Movie>> fetchMovieList(@Query("api_key") String apiKey,
                                                     @Query("language") String language,
                                                     @Query("query") String query,
                                                     @Query("page") String page,
                                                     @Query("include_adult") String include_adult);
}
