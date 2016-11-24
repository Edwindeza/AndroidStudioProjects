package com.artkodec.uv_control.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.model.WeatherIndexUVEntity;
import com.artkodec.uv_control.model.WeatherTrackerEntity;
import com.artkodec.uv_control.request.FavoritesRequest;
import com.artkodec.uv_control.request.WeatherRequest;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimple;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimpleAux;
import com.artkodec.uv_control.request.data.TrackFavorite;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.utils.WS;
import com.artkodec.uv_control.views.contracts.FavoriteContract;
import com.artkodec.uv_control.views.contracts.MyAccountContract;

import org.json.JSONException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 23/05/16.
 */
public class FavoritesPresenter implements FavoriteContract.Presenter {

    private final FavoriteContract.View view;
    private Context context;
    private SessionManager sessionManager;

    public FavoritesPresenter(@NonNull FavoriteContract.View view, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.view = checkNotNull(view, "view cannot be null!");
        this.view.setmPresenter(this);
        this.sessionManager = new SessionManager(context);

    }

    @Override
    public void loadWeather(String token) {
        //  if(forceUpdate){
        FavoritesRequest weatherRequest =
                ServiceGeneratorSimpleAux.createService(FavoritesRequest.class);
        Call<TrackFavorite> call = weatherRequest.listZone("Token "+token);
        call.enqueue(new Callback<TrackFavorite>() {
            @Override
            public void onResponse(Call<TrackFavorite> call, Response<TrackFavorite> response) {
                if (response.isSuccessful()) {
                    if (!view.isActive()) {
                        return;
                    }
                   // view.showWeahter((ArrayList<FavoriteZoneEntity>) response.body().getResults());
                    getIndexUV((ArrayList<FavoriteZoneEntity>) response.body().getResults());
                } else {

                    if (!view.isActive()) {
                        return;
                    }
                    view.showLoadingWeahterError();
                }
            }

            @Override
            public void onFailure(Call<TrackFavorite> call, Throwable t) {

                if (!view.isActive()) {
                    return;
                }

                view.showLoadingWeahterError();
            }

        });
    }

    public void getIndexUV(final ArrayList<FavoriteZoneEntity> favoriteZoneEntities) {
        WeatherRequest weatherRequest =
                ServiceGeneratorSimple.createService(WeatherRequest.class);
        final String data="";
        for (int i = 0; i <favoriteZoneEntities.size() ; i++) {

            final String[] lat = {(new BigDecimal(favoriteZoneEntities.get(i).getLatitude()).setScale(0, RoundingMode.DOWN)).toString()};
            String lon=(new BigDecimal(favoriteZoneEntities.get(i).getLongitude()).setScale(0, RoundingMode.DOWN)).toString();
            Call<WeatherIndexUVEntity> call = weatherRequest.getUVIndex(lat[0], lon, WS.key);
            view.showLoad();
            final int temp=i;
            call.enqueue(new Callback<WeatherIndexUVEntity>() {
                @Override
                public void onResponse(Call<WeatherIndexUVEntity> call, Response<WeatherIndexUVEntity> response) {
                    if(response.isSuccessful()){

                        setUV(favoriteZoneEntities,temp,response.body().getData());
                        if(temp==favoriteZoneEntities.size()-1){
                            view.hideLoad();
                            view.showWeahter(favoriteZoneEntities);
                            return;
                        }

                    }else {
                        view.hideLoad();
                        view.setError("No se pudo obtener el índice UV");
                        return;
                    }
                }

                @Override
                public void onFailure(Call<WeatherIndexUVEntity> call, Throwable t) {
                    view.hideLoad();
                    view.setError("Ocurrió un error al tratar de conectarse, por favor intente nuevamente");
                    return;
                }


            });

        }


    }


    private void setUV(ArrayList<FavoriteZoneEntity> favoriteZoneEntities,int i, double data){
        favoriteZoneEntities.get(i).setUv(data);
    }
    @Override
    public void loadNews(boolean forceUpdate) {

    }

    @Override
    public void openWeatherDetails(@NonNull OpenWeatherEntity requestedTask) {

    }

    @Override
    public void start() {
        SessionManager sessionManager = new SessionManager(context);
        loadWeather(sessionManager.getUserToken());
    }
}
