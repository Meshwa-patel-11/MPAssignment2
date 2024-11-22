package com.example.mpassignment2.network;

import com.example.mpassignment2.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class to create a Retrofit client
 * Centralized class to create a Retrofit client and get the ApiService object
 */
public class ApiClient {

    // Only one instance of Retrofit is created
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Get the ApiService object
     *
     * @return ApiService object
     */
    public static ApiService getApiService() {
        return getClient().create(ApiService.class);
    }
}
