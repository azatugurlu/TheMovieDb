package com.example.themoviedb;

import android.app.Application;
import android.content.Context;

import com.example.themoviedb.di.AppComponent;
import com.example.themoviedb.di.DaggerAppComponent;
import com.example.themoviedb.di.UtilsModule;

public class TheMovieDbApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
