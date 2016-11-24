package com.artkodec.uv_control.request;

import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.model.WeatherIndexUVEntity;
import com.artkodec.uv_control.model.WeatherTrackerEntity;
import com.artkodec.uv_control.request.data.TrackFavorite;

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
public interface FavoritesRequest {

    @GET("zones/")
    Call<TrackFavorite> listZone(@Header("Authorization") String token);






}
