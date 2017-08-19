package com.joaopaulosj.movies.di.component;

import com.joaopaulosj.movies.di.PerActivity;
import com.joaopaulosj.movies.di.module.ActivityModule;
import com.joaopaulosj.movies.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
