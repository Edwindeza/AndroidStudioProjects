package com.artkodec.uv_control.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.presenter.CommunicatePresenterNewsItem;
import com.artkodec.uv_control.utils.WS;
import com.artkodec.uv_control.views.components.LoaderAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junior on 27/05/16.
 */
public class PlacesAdapter extends LoaderAdapter<OpenWeatherEntity> implements NewsItemListener {



    private Context context;
    public CommunicatePresenterNewsItem communicatePresenterNewsItem;
    private SimpleDateFormat format;
    ;

    public PlacesAdapter(Context context, List<OpenWeatherEntity> newsEntities, CommunicatePresenterNewsItem communicatePresenterNewsItem) {
        super(context);
        this.context = context;
        this.communicatePresenterNewsItem = communicatePresenterNewsItem;

        setItems(newsEntities);
    }


    @Override
    public long getYourItemId(int position) {
        return position;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent) {
        return new ViewHolder(mInflater.inflate(R.layout.item_weather, parent, false), this);
    }

    @Override
    public void bindYourViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            final OpenWeatherEntity newsEntity = mItems.get(position);


            viewHolder.tvNameCountry.setText(newsEntity.getName());
            viewHolder.tvTemp.setText("Temperatura: "
                    +new BigDecimal((Double.parseDouble(newsEntity.getMain().getTemp()))).
                    setScale(0, RoundingMode.DOWN)
                    + " C° \r\n " +
                    "Viento: "+new BigDecimal((Double.parseDouble(newsEntity.getWind().getSpeed()) * (3.6)))
                    .setScale(2, RoundingMode.DOWN) + " Km/H \r\n "+
                    "Nubosidad: "+new BigDecimal((Double.parseDouble(newsEntity.getClouds()
                    .getAll()))).setScale(0, RoundingMode.DOWN) + " %\r\n "+
                    "Humedad: "+new BigDecimal((Double.parseDouble(newsEntity.getMain()
                    .getHumidity()))).setScale(0, RoundingMode.DOWN) + " %");
            Glide.with(context).load(WS.url_icon + newsEntity.getWeather().get(0).getIcon() + ".png")
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(viewHolder.ivWeather);

        }

    }


    @Override
    public void onNewsClick(int position) {
        communicatePresenterNewsItem.clickItem(getmItems().get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name_country)
        TextView tvNameCountry;
        @BindView(R.id.tv_temp)
        TextView tvTemp;
        @BindView(R.id.item_view)
        LinearLayout itemView;
        @BindView(R.id.iv_weather)
        ImageView ivWeather;

        NewsItemListener newsItemListener;

        public ViewHolder(View view, NewsItemListener newsItemListener) {
            super(view);
            this.newsItemListener = newsItemListener;
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            newsItemListener.onNewsClick(getAdapterPosition());
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
