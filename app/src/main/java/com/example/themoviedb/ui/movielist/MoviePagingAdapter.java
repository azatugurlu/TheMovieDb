package com.example.themoviedb.ui.movielist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.R;
import com.example.themoviedb.model.Movie;
import com.example.themoviedb.utils.Urls;
import com.example.themoviedb.utils.listeners.RecyclerViewClickListener;
import com.squareup.picasso.Picasso;

public class MoviePagingAdapter extends PagedListAdapter<Movie, MoviePagingAdapter.MoviePagingViewHolder> {
    private RecyclerViewClickListener itemListener;
    private Context context;

    public MoviePagingAdapter(RecyclerViewClickListener itemListener, Context context){
        super(Movie.DIFF_CALLBACK);
        this.itemListener = itemListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviePagingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MoviePagingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePagingViewHolder holder, int position) {
        holder.title.setText(getItem(position).getTitle());
        holder.overview.setText(getItem(position).getOverview());
        holder.avarageRating.setText(getItem(position).getTitle());
        holder.releaseDate.setText(getItem(position).getRelease_date());

        Picasso.get().load(Urls.BASE_IMAGE_URL + getItem(position).getPoster_path()).into(holder.image);
    }

    class MoviePagingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public TextView overview;
        public TextView avarageRating;
        public TextView releaseDate;
        public ImageView image;

        public MoviePagingViewHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            overview = (TextView) itemView.findViewById(R.id.overview);
            avarageRating = (TextView) itemView.findViewById(R.id.avarageRating);
            releaseDate = (TextView) itemView.findViewById(R.id.releaseDate);
            image = (ImageView) itemView.findViewById(R.id.movieImage);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }
}
