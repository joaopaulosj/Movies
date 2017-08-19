package com.joaopaulosj.movies.di.component;

import android.app.Application;
import android.content.Context;

import com.joaopaulosj.movies.BaseApplication;
import com.joaopaulosj.movies.di.module.ApplicationModule;
import com.joaopaulosj.movies.ui.main.MainActivity;
import com.joaopaulosj.movies.ui.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jpsja_000 on 18/08/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
    void inject(MainViewModel mainViewModel);

}
