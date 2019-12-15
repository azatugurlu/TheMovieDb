package com.example.themoviedb.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.themoviedb.R;
import com.example.themoviedb.TheMovieDbApplication;
import com.example.themoviedb.model.Movie;
import com.example.themoviedb.utils.ViewModelFactory;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    public MovieListFragment() {
        // Required empty public constructor
    }

    @Inject
    ViewModelFactory viewModelFactory;

    private MovieViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TheMovieDbApplication) getActivity().getApplication()).getAppComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        viewModel.getMovies().observe(this, this::consumeResponse);
        viewModel.setSearchText("test");
    }

    private void consumeResponse(PagedList<Movie> pagedList){

    }
}
