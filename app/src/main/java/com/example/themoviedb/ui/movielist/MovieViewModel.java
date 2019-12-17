package com.example.themoviedb.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.themoviedb.model.Movie;
import com.example.themoviedb.repository.Repository;

public class MovieViewModel extends ViewModel {

    private Repository repository;
    private String searchText;
    private PagedList.Config config;
    private LiveData<PagedList<Movie>> allMovies;

    public MovieViewModel(Repository repository){
        this.repository = repository;
        this.searchText = "test";
        this.init();
    }

    public LiveData<PagedList<Movie>> getMovies(){
        return allMovies;
    }

    public void setSearchText(String str) {
        this.allMovies = initializedPagedListBuilder(str).build();
        try {
            this.allMovies.getValue().getDataSource().invalidate();
        } catch (NullPointerException ex){
            System.out.println("test");
        }
    }

    private void init() {
        this.config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();

        this.allMovies = initializedPagedListBuilder(searchText).build();
    }

    private LivePagedListBuilder<Long, Movie> initializedPagedListBuilder(String filter){
        return new LivePagedListBuilder<Long, Movie>(new MovieDataSourceFactory(repository, filter), config);
    }
}
