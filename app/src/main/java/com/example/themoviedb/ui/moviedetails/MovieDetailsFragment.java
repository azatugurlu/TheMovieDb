package com.example.themoviedb.ui.moviedetails;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themoviedb.R;
import com.example.themoviedb.model.Movie;
import com.example.themoviedb.utils.Urls;
import com.squareup.picasso.Picasso;


public class MovieDetailsFragment extends Fragment {

    private Movie movie;
    private TextView title;
    private ImageView imageView;


    public MovieDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = (Movie) getArguments().getSerializable("movieItem");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.textViewTitle);
        imageView = view.findViewById(R.id.imageView);
        updateUI();
    }

    private void updateUI(){
        if (movie != null){
            title.setText(movie.getTitle());
            Picasso.get().load(Urls.BASE_IMAGE_URL + movie.getBackdrop_path()).into(imageView);

        }
    }
}
