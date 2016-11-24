package com.artkodec.uv_control.views.contracts;


import com.artkodec.uv_control.core.BasePresenter;
import com.artkodec.uv_control.core.BaseView;
import com.artkodec.uv_control.model.AccessTokenEntity;
import com.artkodec.uv_control.model.UserEntity;

/**
 * Created by junior on 22/05/16.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void successLogin(UserEntity userEntity);
        void errorLogin(String msg);
    }

    interface Presenter extends BasePresenter {
        void loginUser(String username, String password);
        void loginFacebook(String token);
        void getPerfil(AccessTokenEntity token);
        void openSession(String token, UserEntity userEntity);
    }
}
