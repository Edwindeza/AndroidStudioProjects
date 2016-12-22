package com.artkodec.uv_control.views.activities;

import android.os.Bundle;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.presenter.FavoritesPresenter;
import com.artkodec.uv_control.presenter.MessagePresenter;
import com.artkodec.uv_control.utils.ActivityUtils;
import com.artkodec.uv_control.utils.EspressoIdlingResource;
import com.artkodec.uv_control.views.fragments.InformationFragment;
import com.google.common.annotations.VisibleForTesting;


/**
 * Created by junior on 25/05/16.
 */
public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        // Get the requested task id

        InformationFragment fragment = (InformationFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = InformationFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.contentFrame);
        }

        // Create the presenter
        new MessagePresenter(fragment,this);

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
