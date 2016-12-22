package com.artkodec.uv_control.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.InformationEntity;
import com.artkodec.uv_control.utils.Util_Fonts;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junior on 25/05/16.
 */
public class InformationPageFragment extends Fragment {


    @BindView(R.id.tv_description_information)
    TextView tvDescriptionInformation;


    private InformationEntity informationEntity;
    public static InformationPageFragment newInstance(InformationEntity informationEntity,String TAG) {
        InformationPageFragment fragment = new InformationPageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("info",informationEntity);
        fragment.setArguments(bundle);;
        return fragment;
    }

    public InformationPageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        informationEntity = (InformationEntity) arg.getSerializable("info");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_information, container, false);
        ButterKnife.bind(this, view);
        tvDescriptionInformation.setTypeface(Util_Fonts.setFontNormal(getContext()));
        tvDescriptionInformation.setText(informationEntity.getDescription());

        return view;
    }
}
