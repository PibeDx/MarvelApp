package com.silmood.marvelapp.rest;

import com.silmood.marvelapp.rest.model.CharactersListResponse;
import com.silmood.marvelapp.rest.model.ComicsListResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MarvelApiService {

    @GET(Constants.CHARACTERS_URL)
    public void requestCharactersList(@Query(Constants.LIMIT_PARAM) int limit,
                                      @Query(Constants.OFFSET_PARAM) int offset,
                                      @Query(Constants.API_KEY_PARAM) String apiKey,
                                      @Query(Constants.TS_PARAM) long ts,
                                      @Query(Constants.HASH_PARAM) String hash,
                                      Callback<CharactersListResponse> callback);


    @GET(Constants.COMICS_URL)
    public void requestComicsList(@Query(Constants.LIMIT_PARAM) int limit,
                                  @Query(Constants.OFFSET_PARAM) int offset,
                                  @Query(Constants.API_KEY_PARAM) String apiKey,
                                  @Query(Constants.TS_PARAM) long ts,
                                  @Query(Constants.HASH_PARAM) String hash,
                                  Callback<ComicsListResponse> callback);


}