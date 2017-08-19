package com.joaopaulosj.movies.di.module;

import android.app.Application;
import android.content.Context;

import com.joaopaulosj.movies.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jpsja_000 on 18/08/2017.
 */

@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

}
