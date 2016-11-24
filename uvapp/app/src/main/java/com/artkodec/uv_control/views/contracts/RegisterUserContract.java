package com.artkodec.uv_control.views.contracts;

import android.support.annotation.NonNull;

import com.artkodec.uv_control.core.BasePresenter;
import com.artkodec.uv_control.core.BaseView;
import com.artkodec.uv_control.model.UserEntity;


/**
 * Created by junior on 23/05/16.
 */
public interface RegisterUserContract {

    interface View extends BaseView<Presenter> {
        /*void ShowChangePassowrdUI();
        void showPerfilUI(UserEntity userEntity);
        void ShowSessionInformation(UserEntity userEntity);*/
        public void ShowRegisterSuccesful(UserEntity userEntity);
        public void ShowErrorRegister(String msg);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void RegisterUser(@NonNull UserEntity userEntity);
    }
}
