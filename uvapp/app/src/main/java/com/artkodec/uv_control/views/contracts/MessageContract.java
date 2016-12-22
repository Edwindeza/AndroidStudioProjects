package com.artkodec.uv_control.views.contracts;

import android.support.annotation.NonNull;

import com.artkodec.uv_control.core.BasePresenter;
import com.artkodec.uv_control.core.BaseView;
import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.InformationEntity;
import com.artkodec.uv_control.model.OpenWeatherEntity;

import java.util.ArrayList;

/**
 * Created by junior on 19/05/16.
 */
public interface MessageContract {

    interface View extends BaseView<Presenter> {


        void showWeahter(ArrayList<InformationEntity> weatherTrackerEntity);


    }

    interface Presenter extends BasePresenter {


    }
}
