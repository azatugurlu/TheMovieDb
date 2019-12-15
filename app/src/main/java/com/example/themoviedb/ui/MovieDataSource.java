package com.example.themoviedb.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.themoviedb.model.GenericResponse;
import com.example.themoviedb.model.Movie;
import com.example.themoviedb.repository.Repository;

import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private Repository repository;
    private String filter;

    MovieDataSource(Repository repository, String filter) {
        this.repository = repository;
        this.filter = filter;

    }

    public void setFilter(String filter){
        this.filter = filter;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Movie> callback) {
        MutableLiveData<List<Movie>> liveData = new MutableLiveData<List<Movie>>();
        repository.executeGetMovieList(filter, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GenericResponse<Movie>>() {
                    @Override
                    public void accept(GenericResponse<Movie> result) throws Exception {
                        liveData.setValue(result.getResults());
                        callback.onResult(liveData.getValue(), new Long((result.getPage()-1)), new Long(result.getPage() + 1));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error occured");
                    }
                });

                /*
                .subscribe(result -> {
                    liveData.setValue(result.getResults());
                    callback.onResult(liveData.getValue(), new Long((result.getPage()-1)), new Long(result.getPage() + 1));
                }, error -> {
                    System.out.println("Error occured");
                });
                */
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {
        MutableLiveData<List<Movie>> liveData = new MutableLiveData<List<Movie>>();
        repository.executeGetMovieList(filter, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    liveData.setValue(result.getResults());
                    callback.onResult(liveData.getValue(), new Long((result.getPage()+1)));
                }, error -> {

                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {
        MutableLiveData<List<Movie>> liveData = new MutableLiveData<List<Movie>>();
        repository.executeGetMovieList(filter, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    liveData.setValue(result.getResults());
                    callback.onResult(liveData.getValue(), new Long((result.getPage()-1)));
                }, error -> {

                });
    }
}
