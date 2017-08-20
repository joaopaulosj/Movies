package com.joaopaulosj.movies.data.remote;

import com.joaopaulosj.movies.data.NetConstants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jpsja_000 on 20/08/2017.
 */

public class ApiKeyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        HttpUrl originalUrl = originalRequest.url();

        HttpUrl url = originalUrl.newBuilder()
                .addQueryParameter("api_key", NetConstants.API_KEY)
                .build();

        Request request = originalRequest.newBuilder()
                .url(url)
                .build();

        return chain.proceed(request);
    }
}
