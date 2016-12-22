package com.artkodec.uv_control.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.IdlingResource;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.utils.EspressoIdlingResource;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.views.activities.AboutActivity;
import com.artkodec.uv_control.views.activities.FavoritesZonesActivity;
import com.artkodec.uv_control.views.activities.InformationActivity;
import com.artkodec.uv_control.views.activities.LoginActivity;
import com.artkodec.uv_control.views.activities.MyAccountActivity;


/**
 * Created by junior on 08/05/16.
 */
public class BaseDrawerActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    public NavigationView mNavigationView;
    public Toolbar toolbar;
    public ActionBarDrawerToggle mDrawerToggle;
    public SessionManager sessionManager;
    public TextView tv_username;
    public ImageView profile_image;
    public ImageView front_cover;
    public  Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager=new SessionManager(getBaseContext());
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view) ;

        setupDrawerContent(mNavigationView);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                toolbar,
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        );

        //mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        View header=mNavigationView.getHeaderView(0);
        tv_username=(TextView)header.findViewById(R.id.tv_username);
        profile_image=(ImageView) header.findViewById(R.id.profile_image);
        front_cover=(ImageView)header.findViewById(R.id.front_cover);
        tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sessionManager.isLogin()){
                    Intent intent = new Intent(getBaseContext(), MyAccountActivity.class);
                    startActivityForResult(intent, MyAccountActivity.UPDATE_USER);
                }else{
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivityForResult(intent, LoginActivity.LOGIN_NORMAL);
                }
            }
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        menuItem.setChecked(false);
                        menuItem.setCheckable(false);


                        switch (menuItem.getItemId()) {
                            case R.id.menu_1:
                               Intent intent_information = new Intent(getBaseContext(), InformationActivity.class);
                                intent_information.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent_information);
                                break;
                            case R.id.menu_2:
                                if(sessionManager.isLogin()){
                                    Intent intent_about_me = new Intent(getBaseContext(), FavoritesZonesActivity.class);
                                    intent_about_me.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent_about_me);
                                }else{
                                    Toast.makeText(BaseDrawerActivity.this, "Debe iniciar sesión para poder ver " +
                                            "sus zonas favoritas", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            case R.id.menu_3:
                                Intent intent_donations = new Intent(getBaseContext(), AboutActivity.class);
                                intent_donations.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent_donations);
                                // Do nothing, we're already on that screen
                                break;
                            case R.id.menu_4:
                                if(sessionManager.isLogin()){
                                    Intent intent = new Intent(getBaseContext(), MyAccountActivity.class);
                                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivityForResult(intent, MyAccountActivity.UPDATE_USER);
                                }else{
                                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                                    startActivityForResult(intent, LoginActivity.LOGIN_NORMAL);
                                }


                                break;

                            default:

                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(false);
                        // mDrawerLayout.closeDrawers();
                        return true;
                    }

                });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
