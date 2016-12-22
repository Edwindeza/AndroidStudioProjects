package com.artkodec.uv_control.core;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.artkodec.uv_control.R;

/**
 * Created by junior on 08/05/16.
 */
public class BlackActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    /*public Toolbar toolbar;*/
    /*public NavigationView navigationView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

    }




    // Make sure this is the method with just `Bundle` as the signature
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

}