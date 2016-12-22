package com.artkodec.uv_control.views.activities;


import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.presenter.RegisterUserPresenter;
import com.artkodec.uv_control.utils.ActivityUtils;
import com.artkodec.uv_control.utils.EspressoIdlingResource;
import com.artkodec.uv_control.views.fragments.RegisterUserFragment;


/**
 * Created by junior on 25/05/16.
 */
public class RegisterUserActivity extends AppCompatActivity {
    public static final int CREATE_ACCOUNT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


        // Get the requested task id

        RegisterUserFragment fragment = (RegisterUserFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = RegisterUserFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.contentFrame);
        }

        // Create the presenter
        new RegisterUserPresenter(fragment,this);
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
