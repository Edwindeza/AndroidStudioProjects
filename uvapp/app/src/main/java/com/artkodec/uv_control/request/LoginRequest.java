package com.artkodec.uv_control.request;


import com.artkodec.uv_control.model.AccessTokenEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by junior on 22/05/16.
 */
public interface LoginRequest {

    @FormUrlEncoded
    @POST("login/")
    Call<AccessTokenEntity> basicLogin(@Field("email") String username,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("login/mobile/facebook/")
    Call<AccessTokenEntity> loginFacebook(@Field("access_token") String tokenFace);
}
