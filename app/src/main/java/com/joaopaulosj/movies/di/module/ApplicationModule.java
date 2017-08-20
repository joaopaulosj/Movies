package com.joaopaulosj.movies.di.module;

import android.app.Application;

import com.joaopaulosj.movies.data.NetConstants;
import com.joaopaulosj.movies.data.remote.ApiKeyInterceptor;
import com.joaopaulosj.movies.data.remote.MoviesService;
import com.joaopaulosj.movies.data.repository.MovieRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    @Singleton
    MoviesService provideMoviesService(){

        HttpLoggingInterceptor logIntercpetor = new HttpLoggingInterceptor();
        logIntercpetor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .addInterceptor(logIntercpetor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(NetConstants.API_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesService.class);
    }

    @Provides
    @Singleton
    MovieRepositoryImpl provideMovieRepository(MoviesService moviesService){
        return new MovieRepositoryImpl(moviesService);
    }

}
