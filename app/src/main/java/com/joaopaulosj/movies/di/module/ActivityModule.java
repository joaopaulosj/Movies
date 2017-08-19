package com.joaopaulosj.movies.di.module;

import android.app.Activity;
import android.content.Context;

import com.joaopaulosj.movies.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }

//    @Provides
//    @ActivityContext
//    LoginProvider providesLoginProvider(){
//        return new LoginProvider(mActivity);
//    }

}