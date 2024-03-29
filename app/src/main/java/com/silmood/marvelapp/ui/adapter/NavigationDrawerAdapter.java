package com.silmood.marvelapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silmood.marvelapp.R;
import com.silmood.marvelapp.model.ItemOptionNavDra;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Adapter para el Navigation Drawer
 */
public class NavigationDrawerAdapter extends ArrayAdapter<ItemOptionNavDra> {

    private List<ItemOptionNavDra> itemOptionNavDras;
    private LayoutInflater layoutInflater;

    @InjectView(R.id.imageViewIconContent)
    ImageView imageViewIcon;

    @InjectView(R.id.textViewContentTitle)
    TextView textView;


    public NavigationDrawerAdapter(Context context, List<ItemOptionNavDra> itemOptionNavDras) {
        super(context, R.layout.item_navigation_drawer, itemOptionNavDras);
        this.layoutInflater = LayoutInflater.from(context);
        this.itemOptionNavDras = itemOptionNavDras;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.item_navigation_drawer, parent, false);

        ButterKnife.inject(this, view);

        imageViewIcon.setImageResource(itemOptionNavDras.get(position).getIdIcon());
        textView.setText(itemOptionNavDras.get(position).getIdText());

        return view;
    }
}
