package com.silmood.marvelapp.rest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silmood.marvelapp.rest.model.CharactersListResponse;
import com.silmood.marvelapp.rest.model.ComicsListResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Singleton for the API requests in retrofit
 */
public class MarvelApiClient {

    private MarvelApiService apiService;
    private Context context;

    private static MarvelApiClient API_CLIENT;

    public static MarvelApiClient getInstance(Context context) {
        //Singleton Pattern
        if(API_CLIENT == null)
            API_CLIENT = new MarvelApiClient(context);

        return API_CLIENT;
    }

    private MarvelApiClient(Context context) {
        this.context = context;

        //Build the response parser
        Gson gsonConf = new GsonBuilder()
                .registerTypeAdapter(CharactersListResponse.class , new CharactersListResponseDeserializer())
                .registerTypeAdapter(ComicsListResponse.class, new ComicsListResponseDeserializer())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        //Retrofit adapter to make requests
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(Constants.MAIN_URL)
                .setConverter(new GsonConverter(gsonConf))
                .build();

        apiService = restAdapter.create(MarvelApiService.class);
    }

    /**
     * This method will return the retrofit interface to call any method available
     * */
    private MarvelApiService getApiService() {
        return apiService;
    }

    /**
     * @param limit How many heroes you want to request
     * @param offset Skip the specified number of heroes in the result set.
     * @param callback the interface to communicate the response
     * */
    public void requestCharactersList(int limit, int offset, Callback<CharactersListResponse> callback){
        Long ts = UtilMethods.generateTimeStamp();
        String hash = UtilMethods.generateHash(ts);

        getApiService().requestCharactersList(limit,
                offset,
                Constants.API_PUBLIC_KEY,
                ts,
                hash,
                callback);
    }


    public void requestComicsList (int limit, int offset, Callback<ComicsListResponse> callback){
        Long ts = UtilMethods.generateTimeStamp();
        String hash = UtilMethods.generateHash(ts);

        getApiService().requestComicsList(limit,
                offset,
                Constants.API_PUBLIC_KEY,
                ts,
                hash,
                callback);
    }



}