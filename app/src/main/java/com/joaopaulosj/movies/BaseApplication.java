package com.joaopaulosj.movies;

import android.app.Application;
import android.content.Context;

import com.joaopaulosj.movies.di.component.ApplicationComponent;
import com.joaopaulosj.movies.di.component.DaggerApplicationComponent;
import com.joaopaulosj.movies.di.module.ApplicationModule;

/**
 * Created by jpsja_000 on 18/08/2017.
 */

public class BaseApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
