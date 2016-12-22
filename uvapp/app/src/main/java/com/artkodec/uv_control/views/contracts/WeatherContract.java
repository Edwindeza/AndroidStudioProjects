package com.artkodec.uv_control.views.contracts;

import android.support.annotation.NonNull;


import com.artkodec.uv_control.core.BasePresenter;
import com.artkodec.uv_control.core.BaseView;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.model.WeatherTrackerEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junior on 19/05/16.
 */
public interface WeatherContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);
        void showWeahter(ArrayList<OpenWeatherEntity> weatherTrackerEntity);
        void showWeatherDetailsUi(OpenWeatherEntity openWeatherEntity);
        void showLoadingWeahterError();
        void start(double latitude, double longitude);
        boolean isActive();

    }

    interface Presenter extends BasePresenter {
        void start(double latitude, double longitude,boolean force);
        void loadNews(boolean forceUpdate);
        void openWeatherDetails(@NonNull OpenWeatherEntity requestedTask);

    }
}
