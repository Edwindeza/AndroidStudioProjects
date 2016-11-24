package com.artkodec.uv_control.views.activities;

import android.os.Bundle;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.presenter.MyPerfilPresenter;
import com.artkodec.uv_control.utils.ActivityUtils;
import com.artkodec.uv_control.utils.EspressoIdlingResource;
import com.artkodec.uv_control.views.fragments.MyPerfilFragment;
import com.google.common.annotations.VisibleForTesting;


/**
 * Created by junior on 01/06/16.
 */
public class MyPerfilActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "USER_PARAMETER";
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Get the requested task id

        imageButton = (ImageButton)toolbar.findViewById(R.id.ib_update_user) ;


        UserEntity userEntity = (UserEntity) getIntent().getSerializableExtra(EXTRA_USER);

        MyPerfilFragment fragment = (MyPerfilFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = MyPerfilFragment.newInstance(userEntity);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.contentFrame);

            final MyPerfilFragment finalFragment = fragment;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalFragment.updateUser();
                }
            });
        }


        new MyPerfilPresenter(fragment,this);


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
