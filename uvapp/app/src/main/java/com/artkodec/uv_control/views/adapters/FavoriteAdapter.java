package com.artkodec.uv_control.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.presenter.CommunicatePresenterNewsItem;
import com.artkodec.uv_control.utils.WS;
import com.artkodec.uv_control.views.components.LoaderAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junior on 27/05/16.
 */
public class FavoriteAdapter extends LoaderAdapter<FavoriteZoneEntity> {



    private Context context;
    public CommunicatePresenterNewsItem communicatePresenterNewsItem;
    private SimpleDateFormat format;
    ;

    public FavoriteAdapter(Context context, List<FavoriteZoneEntity> newsEntities) {
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
        return new ViewHolder(mInflater.inflate(R.layout.item_favorite
                , parent, false));
    }

    @Override
    public void bindYourViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            final FavoriteZoneEntity newsEntity = mItems.get(position);


            viewHolder.tvNameCountry.setText(newsEntity.getName());


            if ((0 <= newsEntity.getUv())
                    && (newsEntity.getUv() <= 2.9)) {
                viewHolder.tvTemp.setText("UV - "
                        + new BigDecimal((Double.parseDouble(newsEntity.getUv()+""))).
                        setScale(2, RoundingMode.DOWN)
                        + "  \r\n" +
                        "Índice Bajo");
                viewHolder.tvTemp.setTextColor(context.getResources().getColor(R.color.green));


            }
            if ((2.9 <= newsEntity.getUv())
                    && (newsEntity.getUv() <= 5.9)) {
                viewHolder.tvTemp.setText("UV - "
                        + new BigDecimal((Double.parseDouble(newsEntity.getUv()+""))).
                        setScale(2, RoundingMode.DOWN)
                        + "  \r\n" +
                        "Índice Moderado");
                viewHolder.tvTemp.setTextColor(context.getResources().getColor(R.color.yellow));


            }
            if ((6 <= newsEntity.getUv())
                    && (newsEntity.getUv() <= 7.9)) {
                viewHolder.tvTemp.setText("UV - "
                        + new BigDecimal((Double.parseDouble(newsEntity.getUv()+""))).
                        setScale(2, RoundingMode.DOWN)
                        + "  \r\n" +
                        "Índice Alto");
                viewHolder.tvTemp.setTextColor(context.getResources().getColor(R.color.orange));


            }
            if ((8 <= newsEntity.getUv())
                    && (newsEntity.getUv() <= 10.9)) {
                viewHolder.tvTemp.setText("UV - "
                        + new BigDecimal((Double.parseDouble(newsEntity.getUv()+""))).
                        setScale(2, RoundingMode.DOWN)
                        + "  \r\n" +
                        "Índice Muy Alto");
                viewHolder.tvTemp.setTextColor(context.getResources().getColor(R.color.red));


            }

            if ((10.9 < newsEntity.getUv())) {
                viewHolder.tvTemp.setText("UV - "
                        + new BigDecimal((Double.parseDouble(newsEntity.getUv()+""))).
                        setScale(2, RoundingMode.DOWN)
                        + "  \r\n" +
                        "Índice Extremo");
                viewHolder.tvTemp.setTextColor(context.getResources().getColor(R.color.violet));
            }


        }

    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_country)
        TextView tvNameCountry;
        @BindView(R.id.tv_temp)
        TextView tvTemp;
        @BindView(R.id.item_view)
        LinearLayout itemView;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);


        }

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
