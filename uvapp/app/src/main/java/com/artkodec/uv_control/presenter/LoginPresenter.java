package com.artkodec.uv_control.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artkodec.uv_control.model.AccessTokenEntity;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.request.LoginRequest;
import com.artkodec.uv_control.request.UserRequest;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimple;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimpleAux;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.views.contracts.LoginContract;
import com.facebook.AccessToken;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 22/05/16.
 */
public class LoginPresenter implements LoginContract.Presenter {


    private final LoginContract.View mLoginView;
    private Context context;
    private SessionManager sessionManager;

    public LoginPresenter(@NonNull LoginContract.View mLoginView, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.mLoginView = checkNotNull(mLoginView, "newsView cannot be null!");
        this.mLoginView.setmPresenter(this);
        sessionManager = new SessionManager(context);

    }
    @Override
    public void loginUser(String username, String password) {
        LoginRequest loginService =
                ServiceGeneratorSimpleAux.createService(LoginRequest.class);
        Call<AccessTokenEntity> call = loginService.basicLogin(username, password);
        mLoginView.showLoad();
        call.enqueue(new Callback<AccessTokenEntity>() {
            @Override
            public void onResponse(Call<AccessTokenEntity> call, Response<AccessTokenEntity> response) {
                if(response.isSuccessful()){
                    getPerfil(response.body());
                }else {
                    mLoginView.hideLoad();
                    mLoginView.errorLogin("Email o contraseña incorrectos");
                }
            }

            @Override
            public void onFailure(Call<AccessTokenEntity> call, Throwable t) {
                mLoginView.hideLoad();
                mLoginView.errorLogin("Ocurrió un error al tratar de ingresar, por favor intente nuevamente");
            }


        });
    }

    @Override
    public void loginFacebook(String token) {
        LoginRequest loginService =
                ServiceGeneratorSimpleAux.createService(LoginRequest.class);

        Call<AccessTokenEntity> call = loginService.loginFacebook(token);
        mLoginView.showLoad();
        call.enqueue(new Callback<AccessTokenEntity>() {
            @Override
            public void onResponse(Call<AccessTokenEntity> call, Response<AccessTokenEntity> response) {
                if(response.isSuccessful()){
                    getPerfil(response.body());
                }else {
                    mLoginView.hideLoad();
                    AccessToken.setCurrentAccessToken(null);
                    mLoginView.errorLogin("Login fallido, puede que el correo vinculado a su " +
                            "facebook ya este registrado ");

                }
            }

            @Override
            public void onFailure(Call<AccessTokenEntity> call, Throwable t) {
                mLoginView.hideLoad();
                AccessToken.setCurrentAccessToken(null);
                mLoginView.errorLogin("Ocurrió un error al entrar, por favor intente nuevamente");
            }
        });
    }

    @Override
    public void getPerfil(final AccessTokenEntity tokenEntity) {
        UserRequest userRequest =
                ServiceGeneratorSimpleAux.createService(UserRequest.class);
        Call<UserEntity> call = userRequest.getUser("Token " + tokenEntity.getAccessToken());
        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if(response.isSuccessful()){
                    openSession(tokenEntity.getAccessToken(), response.body());
                }else {
                    mLoginView.hideLoad();
                    mLoginView.errorLogin("Ocurrió un error al cargar su perfil");
                }
             }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                mLoginView.hideLoad();
                mLoginView.errorLogin("Fallo al traer datos, comunicarse con su administrador");
            }


        });
    }

    @Override
    public void openSession(String token, UserEntity userEntity) {
        sessionManager.openSession(token,userEntity);
        mLoginView.hideLoad();
        mLoginView.successLogin(userEntity);
    }

    @Override
    public void start() {

    }
}
