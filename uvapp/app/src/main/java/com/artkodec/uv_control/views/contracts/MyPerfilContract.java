package com.artkodec.uv_control.views.contracts;

import android.support.annotation.NonNull;

import com.artkodec.uv_control.core.BasePresenter;
import com.artkodec.uv_control.core.BaseView;
import com.artkodec.uv_control.model.UserEntity;


/**
 * Created by junior on 01/06/16.
 */
public interface MyPerfilContract  {

    interface View extends BaseView<Presenter> {

        void updateUser();
        void showUser();
        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void udpateUser(@NonNull UserEntity userEntity, @NonNull String token);


    }
}
