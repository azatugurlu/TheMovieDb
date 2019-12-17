package com.example.themoviedb.di;

import com.example.themoviedb.ui.movielist.MovieListFragment;
import javax.inject.Singleton;
import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {
    void inject(MovieListFragment movieListFragment);
}
