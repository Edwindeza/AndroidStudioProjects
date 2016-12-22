package com.artkodec.uv_control.presenter;

import android.content.Context;
import android.support.annotation.NonNull;


import com.artkodec.uv_control.model.AccessTokenEntity;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.request.UserRequest;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimple;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimpleAux;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.views.contracts.RegisterUserContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 25/05/16.
 */
public class RegisterUserPresenter implements RegisterUserContract.Presenter {

    private RegisterUserContract.View view;
    private Context context;
    private SessionManager sessionManager;

    public RegisterUserPresenter(@NonNull RegisterUserContract.View view, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.view = checkNotNull(view, "view cannot be null!");
        this.view.setmPresenter(this);
        this.sessionManager = new SessionManager(this.context);
    }




    @Override
    public void start() {

    }

    @Override
    public void RegisterUser(@NonNull UserEntity userEntity) {
        UserRequest userRequest =
                ServiceGeneratorSimpleAux.createService(UserRequest.class);
        Call<AccessTokenEntity> call = userRequest.registerUser(userEntity.getEmail(),
                userEntity.getPassword(),userEntity.getFirst_name(),userEntity.getLast_name(),
                userEntity.getAge(),userEntity.getSkin());
        view.showLoad();
        call.enqueue(new Callback<AccessTokenEntity>() {
            @Override
            public void onResponse(Call<AccessTokenEntity> call, Response<AccessTokenEntity> response) {
                if(response.isSuccessful()){
                    getPerfil(response.body());
                }else {
                    view.hideLoad();
                    view.ShowErrorRegister("Falló el registro, por favor inténtelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<AccessTokenEntity> call, Throwable t) {
                view.hideLoad();
                view.setError("Ocurrió un error al tratar de ingresar, por favor intente nuevamente");
            }


        });
    }


    private void getPerfil(final AccessTokenEntity tokenEntity) {
        UserRequest userRequest =
                ServiceGeneratorSimpleAux.createService(UserRequest.class);
        Call<UserEntity> call = userRequest.getUser("Token " + tokenEntity.getAccessToken());
        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if(response.isSuccessful()){
                    openSession(tokenEntity.getAccessToken(), response.body());
                }else {
                    view.hideLoad();
                    view.ShowErrorRegister("Ocurrió un error al cargar su perfil");
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                view.hideLoad();
                view.setError("Fallo al traer datos, comunicarse con su administrador");
            }


        });
    }


    private void openSession(String token, UserEntity userEntity) {
        sessionManager.openSession(token,userEntity);
        view.hideLoad();
        view.ShowRegisterSuccesful(userEntity);
    }
}
