package com.artkodec.uv_control.views.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.views.contracts.MyPerfilContract;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by junior on 01/06/16.
 */
public class MyPerfilFragment extends Fragment implements MyPerfilContract.View, Validator.ValidationListener {
    public static final String EXTRA_USER = "USER_PARAMETER";

    @BindView(R.id.tv_personal_information)
    TextView tvPersonalInformation;
    @BindView(R.id.tv_error)
    TextView tvError;
    @Length(max = 50, message = "Cantidad de dígitos no permitida", sequence = 1)
    @NotEmpty(message = "Este campo no puede ser vacío",sequence = 2)
    @BindView(R.id.ed_first_name)
    EditText edFirstName;
    @BindView(R.id.input_first_name)
    TextInputLayout inputFirstName;
    @Length(max = 50, message = "Cantidad de dígitos no permitida", sequence = 3)
    @NotEmpty(message = "Este campo no puede ser vacío",sequence = 4)
    @BindView(R.id.ed_last_name)
    EditText edLastName;
    @BindView(R.id.input_last_name)
    TextInputLayout inputLastName;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;
    private UserEntity userEntity;
    private MyPerfilContract.Presenter mPresenter;
    private Validator validator;

    public static MyPerfilFragment newInstance(UserEntity userEntity) {

        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_USER, userEntity);
        MyPerfilFragment fragment = new MyPerfilFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    public MyPerfilFragment() {

    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle arg = getArguments();
        userEntity = (UserEntity) arg.getSerializable(EXTRA_USER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_my_perfil, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
        setHasOptionsMenu(true);
    }


    @Override
    public void updateUser() {
        validator.validate();
    }

    @Override
    public void showUser() {
        edFirstName.setText(userEntity.getFirst_name());
        edLastName.setText(userEntity.getLast_name());
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    public void setmPresenter(MyPerfilContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
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

    @Override
    public void onValidationSucceeded() {
        userEntity.setLast_name(edLastName.getText().toString());
        userEntity.setFirst_name(edFirstName.getText().toString());
        SessionManager sessionManager = new SessionManager(getContext());
        mPresenter.udpateUser(userEntity,sessionManager.getUserToken());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                //tvText.setText(message);
            }
        }
    }
}
