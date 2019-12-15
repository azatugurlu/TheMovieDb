package com.example.themoviedb.ui;

import androidx.paging.DataSource;

import com.example.themoviedb.model.Movie;
import com.example.themoviedb.repository.Repository;

public class MovieDataSourceFactory extends DataSource.Factory<Long, Movie> {
    private Repository repository;
    private String filter;

    MovieDataSourceFactory(Repository repository, String filter){
        this.repository = repository;
        this.filter = filter;
    }

    @Override
    public DataSource<Long, Movie> create() {
        return new MovieDataSource(repository, filter);
    }
}
