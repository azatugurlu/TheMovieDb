package com.example.themoviedb.utils.listeners;

import android.view.View;

import javax.inject.Inject;

public interface RecyclerViewClickListener {
    public void recyclerViewListClicked(View view, int position);
}
