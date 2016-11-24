package com.artkodec.uv_control.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.views.contracts.MyAccountContract;

import org.json.JSONException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 23/05/16.
 */
public class MyAccountPresenter implements MyAccountContract.Presenter {

    private final MyAccountContract.View view;
    private Context context;
    private SessionManager sessionManager;

    public MyAccountPresenter(@NonNull MyAccountContract.View view, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.view = checkNotNull(view, "view cannot be null!");
        this.view.setmPresenter(this);
        this.sessionManager = new SessionManager(context);

    }


    @Override
    public void start() {
        try {
            view.ShowSessionInformation(sessionManager.getUserEntity());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void openPerfil(@NonNull UserEntity userEntity) {

    }

    @Override
    public void changePassword() {

    }


}
