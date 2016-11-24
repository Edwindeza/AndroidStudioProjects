package com.artkodec.uv_control.views.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.presenter.CommunicatePresenterNewsItem;
import com.artkodec.uv_control.presenter.FavoritesPresenter;
import com.artkodec.uv_control.presenter.WeatherPresenter;
import com.artkodec.uv_control.services.GPSTracker;
import com.artkodec.uv_control.utils.DividerItemDecoration;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.views.adapters.FavoriteAdapter;
import com.artkodec.uv_control.views.adapters.PlacesAdapter;
import com.artkodec.uv_control.views.components.RecyclerViewScrollListener;
import com.artkodec.uv_control.views.components.ScrollChildSwipeRefreshLayout;
import com.artkodec.uv_control.views.contracts.FavoriteContract;
import com.artkodec.uv_control.views.contracts.WeatherContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by junior on 19/05/16.
 */
public class FavoritesZoneFragment extends Fragment implements FavoriteContract.View {


    @BindView(R.id.news_list)
    RecyclerView newsList;
    @BindView(R.id.noNewsIcon)
    ImageView noNewsIcon;
    @BindView(R.id.noNewsMain)
    TextView noNewsMain;
    @BindView(R.id.noNews)
    LinearLayout noNews;

    private LinearLayoutManager linearLayoutManager;
    private FavoriteAdapter newsAdapter;
    private FavoriteContract.Presenter mPresenter;

    //private boolean isLoading=false;


    public static FavoritesZoneFragment newInstance() {
        FavoritesZoneFragment fragment = new FavoritesZoneFragment();
        return fragment;
    }

    public FavoritesZoneFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new FavoritesPresenter(this, getContext());

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public void setmPresenter(FavoriteContract.Presenter mPresenter) {
        this.mPresenter = checkNotNull(mPresenter);
    }


    @Override
    public void showLoad() {
        newsAdapter.showLoading(true);
        // isLoading=true;
    }

    @Override
    public void hideLoad() {
        newsAdapter.showLoading(false);
        //  isLoading=false;
    }

    @Override
    public void setError(String msg) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_favorites, container, false);
        ButterKnife.bind(this, root);
        noNews.setVisibility(View.GONE);
        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(newsList);
        newsList.addItemDecoration(new DividerItemDecoration(getContext(), null));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SessionManager sessionManager = new SessionManager(getContext());
                mPresenter.loadWeather(sessionManager.getUserToken());

            }
        });


        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsList.setLayoutManager(linearLayoutManager);
        newsAdapter = new FavoriteAdapter(getContext(), new ArrayList<FavoriteZoneEntity>());
        newsList.setAdapter(newsAdapter);
    }


    @Override
    public void setLoadingIndicator(final boolean active) {

        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showWeahter(ArrayList<FavoriteZoneEntity> weatherTrackerEntity) {
        if (this.newsList != null && newsAdapter != null) {
            newsAdapter.setItems(weatherTrackerEntity);
            newsAdapter.notifyDataSetChanged();


            if (weatherTrackerEntity.size() > 0) {
                noNews.setVisibility(View.GONE);
            } else {
                noNews.setVisibility(View.VISIBLE);
            }


            this.newsList.addOnScrollListener(new RecyclerViewScrollListener() {
                @Override
                public void onScrollUp() {

                }

                @Override
                public void onScrollDown() {

                }

                @Override
                public void onLoadMore() {

                }
            });


        } else {
            noNews.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showWeatherDetailsUi(FavoriteZoneEntity newsEntity) {
       /* Intent intent = new Intent(getContext(), DetailNewActivity.class);
        intent.putExtra(DetailNewActivity.EXTRA_NEWS, newsEntity);
        startActivity(intent);*/
    }


    @Override
    public void showLoadingWeahterError() {

        setLoadingIndicator(false);

        showMessage("Error al cargar datos");
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    private void showMessage(String message) {
        noNews.setVisibility(View.VISIBLE);

    }

}
