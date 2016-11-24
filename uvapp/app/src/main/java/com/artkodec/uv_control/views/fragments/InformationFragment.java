package com.artkodec.uv_control.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.InformationEntity;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.presenter.CurrentWeatherPresenter;
import com.artkodec.uv_control.presenter.MessagePresenter;
import com.artkodec.uv_control.request.WeatherRequest;
import com.artkodec.uv_control.request.data.InformationRequest;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimple;
import com.artkodec.uv_control.request.data.ServiceGeneratorSimpleAux;
import com.artkodec.uv_control.request.data.TrackFavorite;
import com.artkodec.uv_control.request.data.TrackMessage;
import com.artkodec.uv_control.utils.WS;
import com.artkodec.uv_control.views.contracts.MessageContract;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.viewpagerindicator.CirclePageIndicator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 25/05/16.
 */
public class InformationFragment extends Fragment implements MessageContract.View{


    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;

    InformationAdapter informationAdapter;
    ArrayList<InformationEntity> informationEntities;
    MessageContract.Presenter presenter;

    public static InformationFragment newInstance() {
        InformationFragment fragment = new InformationFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public InformationFragment() {

    }



    @Override
    public void onResume() {
        super.onResume();
        presenter.start();

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MessagePresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_information_pager, container, false);
        ButterKnife.bind(this, root);
        return root;
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






    }

    @Override
    public void showWeahter(ArrayList<InformationEntity> informationEntities) {
        List<InformationEntity> proposalsEntities = new ArrayList<>();
        if(informationEntities!=null){
            Collections.sort(informationEntities);


            for (int i = 0; i < informationEntities.size(); i++) {
                if(i<=4){
                    proposalsEntities.add(informationEntities.get(i));
                }else{
                    return;
                }

            }

            informationAdapter = new InformationAdapter(getActivity().getSupportFragmentManager(), proposalsEntities);
            pager.setAdapter(informationAdapter);
            final float density = getResources().getDisplayMetrics().density;
            indicator.setViewPager(pager);
            indicator.setRadius(6 * density);
        }
    }

    @Override
    public void setmPresenter(MessageContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void showLoad() {

    }

    @Override
    public void hideLoad() {

    }

    @Override
    public void setError(String msg) {

    }

    public static class InformationAdapter extends FragmentPagerAdapter {
        List<InformationEntity> informationEntities;


        public InformationAdapter(FragmentManager fragmentManager, List<InformationEntity> informationEntities) {
            super(fragmentManager);
            this.informationEntities = informationEntities;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return informationEntities.size();
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return InformationPageFragment.newInstance(informationEntities.get(position),"Page #"+position);
            //return InformationPageFragment.newInstance(informationEntities.get(position), "Page # 1");
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

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
                    returnMessage((ArrayList<InformationEntity>) informationEntities);
                }else {
                    Toast.makeText(getContext(), "No se pudo obtener los mensajes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TrackMessage> call, Throwable t) {
                Toast.makeText(getContext(), "No se pudo obtener los mensajes", Toast.LENGTH_SHORT).show();
            }


        });

    }

    private void returnMessage(ArrayList<InformationEntity> informationEntities){
    this.informationEntities= new ArrayList<>();

        this.informationEntities=informationEntities;
    }
}
