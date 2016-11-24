package com.artkodec.uv_control.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.InformationEntity;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.model.WeatherIndexUVEntity;
import com.artkodec.uv_control.request.FavoritesRequest;
import com.artkodec.uv_control.request.WeatherRequest;
import com.artkodec.uv_control.request.data.InformationRequest;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimple;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimpleAux;
import com.artkodec.uv_control.request.data.TrackFavorite;
import com.artkodec.uv_control.request.data.TrackMessage;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.utils.WS;
import com.artkodec.uv_control.views.contracts.FavoriteContract;
import com.artkodec.uv_control.views.contracts.MessageContract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 23/05/16.
 */
public class MessagePresenter implements MessageContract.Presenter {

    private final MessageContract.View view;
    private Context context;
    private SessionManager sessionManager;

    public MessagePresenter(@NonNull MessageContract.View view, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.view = checkNotNull(view, "view cannot be null!");
        this.view.setmPresenter(this);
        this.sessionManager = new SessionManager(context);

    }

    private void getMessage(){
        InformationRequest informationRequest =
                ServiceGeneratorSimpleAux.createService(InformationRequest.class);
        Call<TrackMessage> call = informationRequest.listMessage();

        call.enqueue(new Callback<TrackMessage>() {
            @Override
            public void onResponse(Call<TrackMessage> call, Response<TrackMessage> response) {
                if(response.isSuccessful()){
                    List<InformationEntity> informationEntities= response.body().getResults();
                    view.showWeahter((ArrayList<InformationEntity>) informationEntities);
                }else {
                    Toast.makeText(context, "No se pudo obtener los mensajes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TrackMessage> call, Throwable t) {
                Toast.makeText(context, "No se pudo obtener los mensajes", Toast.LENGTH_SHORT).show();
            }


        });

    }

    @Override
    public void start() {
        getMessage();
    }
}
