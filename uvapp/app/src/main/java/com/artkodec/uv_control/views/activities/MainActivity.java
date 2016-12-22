package com.artkodec.uv_control.views.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.core.BaseDrawerActivity;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.utils.CircleTransform;
import com.artkodec.uv_control.views.fragments.CurrentWeatherFragment;
import com.artkodec.uv_control.views.fragments.NewsFragment;
import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junior on 19/05/16.
 */
public class MainActivity extends BaseDrawerActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.activity_frame);
        // inflate the custom activity layout
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.layout_tab, null,false);

        // add the custom layout of this activity to frame layout.
        frameLayout.addView(activityView);
        viewPager = (ViewPager) activityView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) activityView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



        if(sessionManager.isLogin()){
            try {
                tv_username.setText(sessionManager.getUserEntity().getFirst_name()+" "+sessionManager.getUserEntity().getLast_name());


                if(AccessToken.getCurrentAccessToken()!=null){

                    if(isOnline()){
                        Glide.with(this).load("https://graph.facebook.com/" + Profile.getCurrentProfile().getId()
                                + "/picture?type=normal").bitmapTransform(new CircleTransform(this)).into(profile_image);

                        GraphRequest request = GraphRequest.newMeRequest(
                                AccessToken.getCurrentAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                          //  response.getJSONObject();
                                        try {
                                            JSONObject json_cover = object.getJSONObject("cover");
                                            String source = (String) json_cover.get("source");
                                            Glide.with(MainActivity.this).load(source).into(front_cover);

                                        } catch (JSONException e) {
                                            e.printStackTrace();}
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "cover");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }
                }else{
                    profile_image.setBackgroundResource(R.drawable.ic_account_circle);
                    front_cover.setBackgroundResource(R.drawable.header_blue);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            tv_username.setText("Iniciar Sesión | Registrate ");
            profile_image.setImageDrawable(null);
            front_cover.setImageDrawable(null);
        }


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CurrentWeatherFragment.newInstance(),"UBICACIÓN ACTUAL");
        adapter.addFragment(NewsFragment.newInstance(), "UBICACIONES CERCANAS");
       /* adapter.addFragment(WorkShopsFragment.newInstance(), getResources().getString(R.string.workshop));*/

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (LoginActivity.LOGIN_NORMAL == requestCode && Activity.RESULT_OK == resultCode) {


            UserEntity userEntity= (UserEntity)data.getSerializableExtra(LoginActivity.ARGUMENT_USER);
            tv_username.setText(userEntity.getFirst_name()+" "+userEntity.getLast_name());
            if(AccessToken.getCurrentAccessToken()!=null){

                if(isOnline()){
                    Glide.with(this).load("https://graph.facebook.com/" + Profile.getCurrentProfile().getId()
                            + "/picture?type=normal").bitmapTransform(new CircleTransform(this)).into(profile_image);

                    GraphRequest request = GraphRequest.newMeRequest(
                            AccessToken.getCurrentAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(
                                        JSONObject object,
                                        GraphResponse response) {
                                    //  response.getJSONObject();
                                    try {
                                        JSONObject json_cover = object.getJSONObject("cover");
                                        String source = (String) json_cover.get("source");
                                        Glide.with(MainActivity.this).load(source).into(front_cover);

                                    } catch (JSONException e) {
                                        e.printStackTrace();}
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "cover");
                    request.setParameters(parameters);
                    request.executeAsync();
                }



            }else{

                profile_image.setBackgroundResource(R.drawable.ic_account_circle);
                front_cover.setBackgroundResource(R.drawable.header_blue);
            }

        }

        if(resultCode==MyAccountActivity.CLOSE_SESSION){
            tv_username.setText("Iniciar Sesión | Registrate ");
            profile_image.setImageDrawable(null);
            front_cover.setImageDrawable(null);
        }
    }


    public boolean isOnline() {
        NetworkInfo localNetworkInfo = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
    }
}
