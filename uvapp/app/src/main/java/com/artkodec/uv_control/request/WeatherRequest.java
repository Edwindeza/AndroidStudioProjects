package com.artkodec.uv_control.request;

import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.model.WeatherIndexUVEntity;
import com.artkodec.uv_control.model.WeatherTrackerEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by junior on 11/06/16.
 */
public interface WeatherRequest {

    @GET("data/2.5/box/city/")
    Call<WeatherTrackerEntity> getWeather(@Query("appid")String appid, @Query("bbox") String bbox);

    @GET("data/2.5/weather/")
    Call<OpenWeatherEntity> getWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apiKey);

    @GET("v3/uvi/{lat},{long}/current.json ")
    Call<WeatherIndexUVEntity> getUVIndex(@Path("lat") String lat, @Path("long") String lon, @Query("appid") String apiKey);

    @FormUrlEncoded
    @POST("zones/")
    Call<FavoriteZoneEntity> registerFavorite(@Header("Authorization") String lat, @Field("name") String name,
                                        @Field("latitude") String latitude, @Field("longitude") String longitude);






}
