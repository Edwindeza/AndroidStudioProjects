package com.artkodec.uv_control.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.model.WeatherTrackerEntity;
import com.artkodec.uv_control.request.WeatherRequest;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimple;
import com.artkodec.uv_control.utils.WS;
import com.artkodec.uv_control.views.contracts.WeatherContract;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 19/05/16.
 */
public class WeatherPresenter implements WeatherContract.Presenter,CommunicatePresenterNewsItem {

    private final WeatherContract.View mNewsView;
    private Context context;
    private boolean mFirstLoad = true;
    private String next;


    public WeatherPresenter(@NonNull WeatherContract.View mNewsView, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.mNewsView = checkNotNull(mNewsView, "newsView cannot be null!");
    }

    @Override
    public void start(double latitude, double longitude,boolean forceUpdate) {
        loadWeather(latitude,longitude,forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    @Override
    public void loadNews(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.

    }

    @Override
    public void openWeatherDetails(@NonNull OpenWeatherEntity requestedTask) {
        checkNotNull(requestedTask, "requestedTask cannot be null!");
        mNewsView.showWeatherDetailsUi(requestedTask);
    }




    @Override
    public void start() {
    }

    private void loadWeather(double latitude, double longitude,boolean forceUpdate, final boolean showLoadingUI) {

      //  if(forceUpdate){
        String box= ((int)longitude -3 )+ ","+((int)latitude -3 )+ ","+((int)longitude +3 )+ ","+((int)latitude +3 )+",10";
            if (showLoadingUI ) {
                mNewsView.setLoadingIndicator(true);
            }

        WeatherRequest weatherRequest =
                ServiceGeneratorSimple.createService(WeatherRequest.class);

            Call<WeatherTrackerEntity> call = weatherRequest.getWeather(WS.key,box
                    );

            call.enqueue(new Callback<WeatherTrackerEntity>() {
                @Override
                public void onResponse(Call<WeatherTrackerEntity> call, Response<WeatherTrackerEntity> response) {
                    if (response.isSuccessful()) {


                        if (!mNewsView.isActive()) {
                            return;
                        }
                        if (showLoadingUI) {
                            mNewsView.setLoadingIndicator(false);
                        }

                        mNewsView.showWeahter(response.body().getList());
                    } else {

                        if (!mNewsView.isActive()) {
                            return;
                        }
                        if (showLoadingUI) {
                            mNewsView.setLoadingIndicator(false);
                        }
                        mNewsView.showLoadingWeahterError();

                    }
                }

                @Override
                public void onFailure(Call<WeatherTrackerEntity> call, Throwable t) {

                    if (!mNewsView.isActive()) {
                        return;
                    }
                    if (showLoadingUI) {
                        mNewsView.setLoadingIndicator(false);
                    }
                    mNewsView.showLoadingWeahterError();
                }

            });




    }


    public String getNext() {
        return next;
    }

    @Override
    public void clickItem(OpenWeatherEntity newsEntity) {
        mNewsView.showWeatherDetailsUi(newsEntity);
    }
}