package com.artkodec.uv_control.views.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.utils.CircleTransform;
import com.artkodec.uv_control.utils.LayoutRipple;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.utils.Util_Fonts;
import com.artkodec.uv_control.views.activities.MyAccountActivity;
import com.artkodec.uv_control.views.activities.MyPerfilActivity;
import com.artkodec.uv_control.views.contracts.MyAccountContract;
import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;


import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 23/05/16.
 */
public class MyAccountFragment extends Fragment implements MyAccountContract.View{


    @BindView(R.id.tv_name_user)
    TextView tvNameUser;
    @BindView(R.id.background_perfil)
    LinearLayout backgroundPerfil;
    @BindView(R.id.tv_perfil)
    TextView tvPerfil;
    @BindView(R.id.tv_change_password)
    TextView tvChangePassword;
    @BindView(R.id.tv_close_session)
    TextView tvCloseSession;
    @BindView(R.id.item_perfil_access)
    LayoutRipple itemPerfilAccess;
    @BindView(R.id.item_change_password)
    LayoutRipple itemChangePassword;
    @BindView(R.id.item_close_session)
    LayoutRipple itemCloseSession;
    @BindView(R.id.front_cover)
    ImageView frontCover;
    @BindView(R.id.profile_image)
    ImageView profileImage;


    private MyAccountContract.Presenter mPresenter;
    private UserEntity userEntity;


    public void setmPresenter(MyAccountContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    public static MyAccountFragment newInstance() {
        MyAccountFragment fragment = new MyAccountFragment();
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_my_account, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvChangePassword.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvNameUser.setTypeface(Util_Fonts.setFontNormal(getContext()));
        tvCloseSession.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvPerfil.setTypeface(Util_Fonts.setFontLight(getContext()));


    }


    @Override
    public void showLoad() {

    }

    @Override
    public void hideLoad() {

    }

    @Override
    public void setError(String msg) {

    }

    @Override
    public void ShowChangePassowrdUI() {

    }

    @Override
    public void showPerfilUI(UserEntity userEntity) {

    }

    @Override
    public void ShowSessionInformation(UserEntity userEntity) {
        if (userEntity != null) {
            this.userEntity=userEntity;
            tvNameUser.setText(userEntity.getFirst_name() + " " + userEntity.getLast_name());

            if (AccessToken.getCurrentAccessToken() != null) {

            if(isOnline()){
                Glide.with(this).load("https://graph.facebook.com/" + Profile.getCurrentProfile().getId()
                        + "/picture?type=normal").bitmapTransform(new CircleTransform(getContext())).into(profileImage);

                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                //  response.getJSONObject();
                                try {
                                    if(isAdded()){
                                        JSONObject json_cover = object.getJSONObject("cover");
                                        String source = (String) json_cover.get("source");
                                        Glide.with(getContext()).load(source).into(frontCover);
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "cover");
                request.setParameters(parameters);
                request.executeAsync();


            }


            } else {
                profileImage.setBackgroundResource(R.drawable.ic_account_circle);
                frontCover.setBackgroundResource(R.color.colorPrimary);
            }
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @OnClick({R.id.item_perfil_access, R.id.item_change_password, R.id.item_close_session})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_perfil_access:
                Intent intent = new Intent(getContext(), MyPerfilActivity.class);
                intent.putExtra(MyPerfilActivity.EXTRA_USER, userEntity);
                startActivity(intent);
                break;
            case R.id.item_change_password:
                /*Intent intent_pass = new Intent(getContext(), EditPasswordActivity.class);
                startActivity(intent_pass);
                break;*/
            case R.id.item_close_session:
                SessionManager sessionManager = new SessionManager(getContext());
                sessionManager.closeSession();
                AccessToken.setCurrentAccessToken(null);
                getActivity().setResult(MyAccountActivity.CLOSE_SESSION);
                getActivity().finish();
                break;
        }
    }


    public boolean isOnline() {
        NetworkInfo localNetworkInfo = ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
    }
}
