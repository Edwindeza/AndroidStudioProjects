package com.artkodec.uv_control.views.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.utils.SessionManager;

import org.json.JSONException;

/**
 * Created by junior on 06/06/16.
 */
public class TipsUVDialog extends AlertDialog {

    private OpenWeatherEntity openWeatherEntity;
    private SessionManager sessionManager;
    public TipsUVDialog(Context context, OpenWeatherEntity openWeatherEntity) throws JSONException {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_layout_tips, null);
        setView(view);
        TextView   tv_uv =(TextView)view.findViewById(R.id.tv_uv);
        TextView   tv_time =(TextView)view.findViewById(R.id.tv_time);
        TextView   tv_suggestion =(TextView)view.findViewById(R.id.tv_suggestion);
        TextView   optional =(TextView)view.findViewById(R.id.optional);
        TextView   name =(TextView)view.findViewById(R.id.name_account);
        sessionManager=new SessionManager(context);
        this.openWeatherEntity=openWeatherEntity;

        if(sessionManager.isLogin()){
            optional.setVisibility(View.GONE);
            name.setVisibility(View.VISIBLE);
            try {
                name.setText("Hola "+ sessionManager.getUserEntity().getFirst_name()+" "
                +sessionManager.getUserEntity().getLast_name());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (openWeatherEntity.getWeatherIndexUVEntity() != null) {
                if ((0 <= openWeatherEntity.getWeatherIndexUVEntity().getData())
                        && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 2.9)) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Bajo");
                    if(sessionManager.getUserEntity().getSkin()==3){
                        tv_time.setText("Más de 60 minutos");
                    }
                    if(sessionManager.getUserEntity().getSkin()==2){
                        tv_time.setText("1 hora máximo");
                    }
                    if(sessionManager.getUserEntity().getSkin()==1){
                        tv_time.setText("50 minutos");
                    }
                    tv_suggestion.setText("-Gafas de sol el días brillantes\r\n " +
                            "-Necesita protección mínima");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.green));
                }
                if ((2.9 < openWeatherEntity.getWeatherIndexUVEntity().getData())
                        && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 5.9)) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Moderado");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.yellow));
                    if(sessionManager.getUserEntity().getSkin()==3){
                        tv_time.setText("45 minutos");
                    }
                    if(sessionManager.getUserEntity().getSkin()==2){
                        tv_time.setText("40 minutos");
                    }
                    if(sessionManager.getUserEntity().getSkin()==1){
                        tv_time.setText("35 minutos");
                    }
                    tv_suggestion.setText("-Usar Gorro o sombrero\r\n" +
                            "-Crema con filtro\r\n"+
                            "-Gafas de sol\r\n"+
                            "-Áreas sombreadas en horas centrales");
                }
                if ((6 < openWeatherEntity.getWeatherIndexUVEntity().getData())
                        && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 7.9)) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Alto");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.orange));
                    if(sessionManager.getUserEntity().getSkin()==3){
                        tv_time.setText("30 minutos");
                    }
                    if(sessionManager.getUserEntity().getSkin()==2){
                        tv_time.setText("25 minutos");
                    }
                    if(sessionManager.getUserEntity().getSkin()==1){
                        tv_time.setText("20 minutos");
                    }
                    tv_suggestion.setText("-Usar Gorro o sombrero\r\n" +
                            "-Crema con filtro\r\n"+
                            "-Gafas de sol\r\n"+
                            "-Áreas sombreadas en horas centrales\r\n"+
                            "-Cuidado con los bébes y niños");
                }
                if ((8 < openWeatherEntity.getWeatherIndexUVEntity().getData())
                        && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 10.9)) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Muy Alto");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.red));
                    if(sessionManager.getUserEntity().getSkin()==3){
                        tv_time.setText("25 minutos");
                    }
                    if(sessionManager.getUserEntity().getSkin()==2){
                        tv_time.setText("20 minutos");
                    }
                    if(sessionManager.getUserEntity().getSkin()==1){
                        tv_time.setText("15 minutos");
                    }
                    tv_suggestion.setText("-Usar Gorro o sombrero\r\n" +
                            "-Crema con filtro\r\n"+
                            "-Gafas de sol\r\n"+
                            "-Áreas sombreadas en horas centrales\r\n"+
                            "-Cuidado con los bébes y niños\r\n" +
                            "-Evitar el sol de 12h a 16h");
                }
                if ((10.9 < openWeatherEntity.getWeatherIndexUVEntity().getData())) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Extremo");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.orange));
                    if(sessionManager.getUserEntity().getSkin()==3){
                        tv_time.setText("10 minutos");
                    }
                    if(sessionManager.getUserEntity().getSkin()==2){
                        tv_time.setText("5 minutos");
                    }
                    if(sessionManager.getUserEntity().getSkin()==1){
                        tv_time.setText("No exponerse por ninguna razón");
                    }
                    tv_suggestion.setText("-Evite completamente la exposición al sol");
                }

            }


        }else{
            optional.setVisibility(View.VISIBLE);
            name.setVisibility(View.GONE);
            if (openWeatherEntity.getWeatherIndexUVEntity() != null) {
                if ((0 <= openWeatherEntity.getWeatherIndexUVEntity().getData())
                        && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 2.9)) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Bajo");
                    tv_time.setText("Más de 60 minutos");
                    tv_suggestion.setText("-Gafas de sol el días brillantes\r\n " +
                            "-Necesita protección mínima");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.green));
                }
                if ((2.9 < openWeatherEntity.getWeatherIndexUVEntity().getData())
                        && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 5.9)) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Moderado");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.yellow));
                    tv_time.setText("45 minutos");
                    tv_suggestion.setText("-Usar Gorro o sombrero\r\n" +
                            "-Crema con filtro\r\n"+
                            "-Gafas de sol\r\n"+
                            "-Áreas sombreadas en horas centrales");
                }
                if ((6 < openWeatherEntity.getWeatherIndexUVEntity().getData())
                        && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 7.9)) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Alto");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.orange));
                    tv_time.setText("30 minutos");
                    tv_suggestion.setText("-Usar Gorro o sombrero\r\n" +
                            "-Crema con filtro\r\n"+
                            "-Gafas de sol\r\n"+
                            "-Áreas sombreadas en horas centrales\r\n"+
                            "-Cuidado con los bébes y niños");
                }
                if ((8 < openWeatherEntity.getWeatherIndexUVEntity().getData())
                        && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 10.9)) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Muy Alto");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.red));
                    tv_time.setText("25 minutos");
                    tv_suggestion.setText("-Usar Gorro o sombrero\r\n" +
                            "-Crema con filtro\r\n"+
                            "-Gafas de sol\r\n"+
                            "-Áreas sombreadas en horas centrales\r\n"+
                            "-Cuidado con los bébes y niños\r\n" +
                            "-Evitar el sol de 12h a 16h");
                }
                if ((10.9 < openWeatherEntity.getWeatherIndexUVEntity().getData())) {
                    tv_uv.setText(openWeatherEntity.getWeatherIndexUVEntity().getData()+" - Riesgo Extremo");
                    tv_uv.setTextColor(getContext().getResources().getColor(R.color.orange));
                    tv_time.setText("10 minutos");
                    tv_suggestion.setText("-Evite completamente la exposición al sol");
                }

            }


        }





    }


}
