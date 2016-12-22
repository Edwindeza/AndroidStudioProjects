package com.artkodec.uv_control.views.activities;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.presenter.FavoritesPresenter;
import com.artkodec.uv_control.presenter.MyAccountPresenter;
import com.artkodec.uv_control.utils.ActivityUtils;
import com.artkodec.uv_control.utils.EspressoIdlingResource;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.views.fragments.FavoritesZoneFragment;
import com.artkodec.uv_control.views.fragments.MyAccountFragment;


/**
 * Created by junior on 19/05/16.
 */
public class FavoritesZonesActivity extends AppCompatActivity {
    public SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorites);
        sessionManager= new SessionManager(getBaseContext());
        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


        // Get the requested task id

        FavoritesZoneFragment fragment = (FavoritesZoneFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = FavoritesZoneFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.contentFrame);
        }

        // Create the presenter
        new FavoritesPresenter(fragment,this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }



}
