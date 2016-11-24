package com.artkodec.uv_control.views.contracts;

import android.support.annotation.NonNull;

import com.artkodec.uv_control.core.BasePresenter;
import com.artkodec.uv_control.core.BaseView;
import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.OpenWeatherEntity;

import java.util.ArrayList;

/**
 * Created by junior on 19/05/16.
 */
public interface FavoriteContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);
        void showWeahter(ArrayList<FavoriteZoneEntity> weatherTrackerEntity);
        void showWeatherDetailsUi(FavoriteZoneEntity openWeatherEntity);
        void showLoadingWeahterError();
        boolean isActive();

    }

    interface Presenter extends BasePresenter {
        void loadNews(boolean forceUpdate);
        void openWeatherDetails(@NonNull OpenWeatherEntity requestedTask);
        void loadWeather(String token);
    }
}
