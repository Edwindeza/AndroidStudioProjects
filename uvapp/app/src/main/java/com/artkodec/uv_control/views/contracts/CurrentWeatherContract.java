package com.artkodec.uv_control.views.contracts;

import android.support.annotation.NonNull;

import com.artkodec.uv_control.core.BasePresenter;
import com.artkodec.uv_control.core.BaseView;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.model.UserEntity;


/**
 * Created by junior on 01/06/16.
 */
public interface CurrentWeatherContract {

    interface View extends BaseView<Presenter> {

        void showWeather(OpenWeatherEntity openWeatherEntity);
        void start(double latitude, double longitude);
        void iLike();
        boolean isActive();
        void sendFavorite(String s);
    }

    interface Presenter extends BasePresenter {

        void start(double latitude, double longitude);
        void registeFavorite(String token,String name, double latitude, double longitude);
        void getWeather(@NonNull UserEntity userEntity, @NonNull String token);
        void likeWeather(@NonNull String token,double latitude, double longitude);

    }
}
