package com.artkodec.uv_control.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.utils.Util_Fonts;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junior on 25/05/16.
 */
public class AboutFragment extends Fragment {


    @BindView(R.id.tv_description_information)
    TextView tvDescriptionInformation;
    @BindView(R.id.tv_description_information2)
    TextView tvDescriptionInformation2;
    @BindView(R.id.tv_description_information3)
    TextView tvDescriptionInformation3;

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public AboutFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_about, container, false);
        ButterKnife.bind(this, root);
        tvDescriptionInformation.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvDescriptionInformation2.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvDescriptionInformation3.setTypeface(Util_Fonts.setFontLight(getContext()));
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}
