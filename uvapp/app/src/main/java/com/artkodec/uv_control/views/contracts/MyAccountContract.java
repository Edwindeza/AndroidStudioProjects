package com.artkodec.uv_control.views.contracts;

import android.support.annotation.NonNull;

import com.artkodec.uv_control.core.BasePresenter;
import com.artkodec.uv_control.core.BaseView;
import com.artkodec.uv_control.model.UserEntity;


/**
 * Created by junior on 23/05/16.
 */
public interface MyAccountContract {

    interface View extends BaseView<Presenter> {
        void ShowChangePassowrdUI();
        void showPerfilUI(UserEntity userEntity);
        void ShowSessionInformation(UserEntity userEntity);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);
        void openPerfil(@NonNull UserEntity userEntity);
        void changePassword();
    }
}
