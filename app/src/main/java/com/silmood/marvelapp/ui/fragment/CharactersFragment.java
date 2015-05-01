package com.silmood.marvelapp.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silmood.marvelapp.R;
import com.silmood.marvelapp.model.*;
import com.silmood.marvelapp.model.Character;
import com.silmood.marvelapp.rest.MarvelApiClient;
import com.silmood.marvelapp.rest.MarvelApiService;
import com.silmood.marvelapp.rest.model.CharactersListResponse;
import com.silmood.marvelapp.ui.adapter.CharactersListAdapter;
import com.silmood.marvelapp.ui.interfaces.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CharactersFragment extends Fragment {

    private static final String LOG_TAG = CharactersFragment.class.getCanonicalName();
    public Context CONTEXT;

    @InjectView(R.id.list_characters)
    RecyclerView mListCharacters;

    CharactersListAdapter adapter;

    public CharactersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        CONTEXT = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_characters, container, false);
        ButterKnife.inject(this, rootView);

        initListCharacters();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        MarvelApiClient.getInstance(getActivity())
                .requestCharactersList(20, 300, new Callback<CharactersListResponse>() {
                    @Override
                    public void success(CharactersListResponse charactersListResponse, Response response) {
                        adapter.updateList(charactersListResponse.getCharacters());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });

    }


    //================================================================================
    //Init Methods
    //================================================================================
    private void initListCharacters() {
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL , false);
        adapter = new CharactersListAdapter(CONTEXT);

        mListCharacters.addOnScrollListener(new EndlessRecyclerOnScrollListener(lm) {
            @Override
            public void onLoadMore(int current_page) {
                adapter.requestForMoreCharacters();
            }
        });

        mListCharacters.setLayoutManager(lm);
        mListCharacters.setAdapter(adapter);

    }

}