package com.artkodec.uv_control.request.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by junior on 11/06/16.
 */
public interface InformationRequest {

    @GET("message/")
    Call<TrackMessage> listMessage();






}
