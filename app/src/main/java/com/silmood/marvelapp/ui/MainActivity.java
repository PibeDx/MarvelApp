package com.silmood.marvelapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.silmood.marvelapp.R;
import com.silmood.marvelapp.ui.fragment.CharactersFragment;
import com.silmood.marvelapp.ui.fragment.ComicsFragment;
import com.silmood.marvelapp.ui.fragment.NavigationDrawerFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private int mCurrentSelectedPositionpPresent = -1;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayoutND;

    @InjectView(R.id.navigation_drawer)
    View mFragmentContainerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        Fresco.initialize(this);

        setSupportActionBar(toolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(mFragmentContainerView, drawerLayoutND, toolbar);
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {


        if (mCurrentSelectedPositionpPresent != position) {
            mCurrentSelectedPositionpPresent = position;

            setNewTitle(mCurrentSelectedPositionpPresent);
            Fragment fragment = null;

            switch (mCurrentSelectedPositionpPresent) {
                case 0:
                    fragment = new CharactersFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, fragment)
                            .commit();
                    break;
                case 1:
                    fragment = new ComicsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, fragment)
                            .commit();
                    break;
                case 2:
//                    fragment = new CreatorsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();


        }

    }

    public void setNewTitle(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section1);
                break;
            case 1:
                mTitle = getString(R.string.title_section2);
                break;
            case 2:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar(CharSequence title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar(mTitle);
            return true;
        } else {
            restoreActionBar(getString(R.string.app_name));
        }
        return true;
    }

}
