package com.artkodec.uv_control.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.views.contracts.MyPerfilContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 01/06/16.
 */
public class MyPerfilPresenter implements MyPerfilContract.Presenter {

    private final MyPerfilContract.View view;
    private Context context;
    private SessionManager sessionManager;

    public MyPerfilPresenter(@NonNull MyPerfilContract.View view, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.view = checkNotNull(view, "view cannot be null!");
        this.view.setmPresenter(this);
        this.sessionManager = new SessionManager(context);

    }
    @Override
    public void udpateUser(@NonNull UserEntity userEntity, @NonNull String token) {

    }

    @Override
    public void start() {
        view.showUser();
    }
}
