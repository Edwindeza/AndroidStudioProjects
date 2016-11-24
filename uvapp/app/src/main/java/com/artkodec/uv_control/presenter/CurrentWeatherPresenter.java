package com.artkodec.uv_control.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.model.WeatherIndexUVEntity;
import com.artkodec.uv_control.request.WeatherRequest;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimple;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimpleAux;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.utils.WS;
import com.artkodec.uv_control.views.contracts.CurrentWeatherContract;

import java.math.BigDecimal;
import java.math.RoundingMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 23/05/16.
 */
public class CurrentWeatherPresenter implements CurrentWeatherContract.Presenter {

    private final CurrentWeatherContract.View view;
    private Context context;
    private SessionManager sessionManager;


    public CurrentWeatherPresenter(@NonNull CurrentWeatherContract.View view, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.view = checkNotNull(view, "view cannot be null!");
        this.view.setmPresenter(this);
        this.sessionManager = new SessionManager(context);

    }


    @Override
    public void start(double latitude, double longitude) {
        getWeahter(latitude,longitude);
    }





    public void getWeahter(double latitude, double longitude) {
        WeatherRequest weatherRequest =
                ServiceGeneratorSimple.createService(WeatherRequest.class);
        Call<OpenWeatherEntity> call = weatherRequest.getWeather(latitude, longitude, WS.key);
        view.showLoad();
        call.enqueue(new Callback<OpenWeatherEntity>() {
            @Override
            public void onResponse(Call<OpenWeatherEntity> call, Response<OpenWeatherEntity> response) {
                if(response.isSuccessful()){

                    getIndexUV(response.body());
                }else {
                    view.hideLoad();
                    view.setError("No se pudo obtener el clima");
                }
            }

            @Override
            public void onFailure(Call<OpenWeatherEntity> call, Throwable t) {
                view.hideLoad();
                view.setError("Ocurrió un error al tratar de conectarse, por favor intente nuevamente");
            }


        });
    }

    @Override
    public void registeFavorite(String token,String name, double latitude, double longitude) {
        WeatherRequest weatherRequest =
                ServiceGeneratorSimpleAux.createService(WeatherRequest.class);
        Call<FavoriteZoneEntity> call = weatherRequest.registerFavorite("Token "+token,
                name,String.valueOf(latitude),
                String.valueOf(longitude));
        view.showLoad();
        call.enqueue(new Callback<FavoriteZoneEntity>() {
            @Override
            public void onResponse(Call<FavoriteZoneEntity> call, Response<FavoriteZoneEntity> response) {
                    if(response.isSuccessful()){
                        view.hideLoad();
                        Toast.makeText(context, "Registrado en favoritos", Toast.LENGTH_SHORT).show();
                    }else {
                    view.hideLoad();
                    Toast.makeText(context, "Problema al registrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FavoriteZoneEntity> call, Throwable t) {
                view.hideLoad();
                view.setError("Ocurrió un error al tratar de conectarse, por favor intente nuevamente");
            }


        });
    }
    public void getIndexUV(final OpenWeatherEntity openWeatherEntity) {
        WeatherRequest weatherRequest =
                ServiceGeneratorSimple.createService(WeatherRequest.class);
        String lat=(new BigDecimal(openWeatherEntity.getCoord().getLat()).setScale(0, RoundingMode.DOWN)).toString();
        String lon=(new BigDecimal(openWeatherEntity.getCoord().getLon()).setScale(0, RoundingMode.DOWN)).toString();
        Call<WeatherIndexUVEntity> call = weatherRequest.getUVIndex(lat, lon, WS.key);
        view.showLoad();
        call.enqueue(new Callback<WeatherIndexUVEntity>() {
            @Override
            public void onResponse(Call<WeatherIndexUVEntity> call, Response<WeatherIndexUVEntity> response) {
                if(response.isSuccessful()){
                    view.hideLoad();
                    openWeatherEntity.setWeatherIndexUVEntity(response.body());
                    view.showWeather(openWeatherEntity);
                }else {
                    view.hideLoad();
                    view.showWeather(openWeatherEntity);
                    view.setError("No se pudo obtener el índice UV");
                }
            }

            @Override
            public void onFailure(Call<WeatherIndexUVEntity> call, Throwable t) {
                view.hideLoad();
                view.setError("Ocurrió un error al tratar de conectarse, por favor intente nuevamente");
            }


        });
    }


    @Override
    public void getWeather(@NonNull UserEntity userEntity, @NonNull String token) {

    }

    @Override
    public void likeWeather(@NonNull String token, double latitude, double longitude) {

    }

    @Override
    public void start() {

    }
}
