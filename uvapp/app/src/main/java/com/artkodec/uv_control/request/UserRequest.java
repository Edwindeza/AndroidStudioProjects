package com.artkodec.uv_control.request;




import com.artkodec.uv_control.model.AccessTokenEntity;
import com.artkodec.uv_control.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by junior on 24/04/16.
 */
public interface UserRequest {
    @GET("user/retrieve/")
    Call<UserEntity> getUser(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("register/")
    Call<AccessTokenEntity> registerUser(@Field("email") String email, @Field("password") String password, @Field("first_name") String first_name,
                                         @Field("last_name") String last_name,@Field("age") int age,
                                         @Field("skin") int skin);

/*

    @POST("register/")
    Call<AccessTokenEntity> registerUser(@Body UserEntity userEntity);



    @FormUrlEncoded
    @PUT("user/update/{id}/")
    Call<UserEntity> editUser(@Header("Authorization") String token, @Field("first_name") String first_name, @Field("last_name") String last_name,
                              @Field("gender") String gender, @Field("celphone") String cellphone,
                              @Field("birthdate") String birthdate, @Path("id") String id);*/

}
