package com.artkodec.uv_control.views.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.utils.Util_Fonts;
import com.artkodec.uv_control.views.activities.LoginActivity;
import com.artkodec.uv_control.views.contracts.RegisterUserContract;
import com.facebook.CallbackManager;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 25/05/16.
 */
public class RegisterUserFragment extends Fragment implements RegisterUserContract.View, Validator.ValidationListener {


    @BindView(R.id.layout_load)
    LinearLayout layoutLoad;
    @BindView(R.id.tvText)
    TextView tvText;

    @NotEmpty(message = "Este campo no puede ser vacío")
    @Length(max = 250, message = "Cantidad de dígitos no permitida", sequence = 1)
    @Email(message = "Email inválido")
    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.input_email)
    TextInputLayout inputEmail;

    @Length(max = 50, message = "Cantidad de dígitos no permitida", sequence = 1)
    @NotEmpty(message = "Este campo no puede ser vacío", sequence = 2)
    @BindView(R.id.et_first_name)
    EditText etFirstName;

    @BindView(R.id.input_first_name)
    TextInputLayout inputFirstName;

    @Length(max = 50, message = "Cantidad de dígitos no permitida", sequence = 3)
    @NotEmpty(message = "Este campo no puede ser vacío", sequence = 4)
    @BindView(R.id.et_last_name)
    EditText etLastName;

    @BindView(R.id.input_last_name)
    TextInputLayout input_last_name;

    @NotEmpty(message = "Este campo no puede ser vacío", sequence = 5)
    @Length(min = 6, max = 30, message = "La contraseña debe ser de al menos 6 dígitos", sequence = 6)
    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.input_password)
    TextInputLayout input_password;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.layout_container)
    LinearLayout layoutContainer;
    @NotEmpty(message = "Este campo no puede ser vacío", sequence = 7)
    @BindView(R.id.et_age)
    EditText etAge;

    @BindView(R.id.input_age)
    TextInputLayout inputAge;
    @BindView(R.id.skin_white)
    TextView skinWhite;
    @BindView(R.id.skin_medium)
    TextView skinMedium;
    @BindView(R.id.skin_black)
    TextView skinBlack;
    @BindView(R.id.checkbox_white)
    CheckBox checkboxWhite;
    @BindView(R.id.checkbox_medium)
    CheckBox checkboxMedium;
    @BindView(R.id.checkbox_black)
    CheckBox checkboxBlack;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;
    private Validator validator;
    private RegisterUserContract.Presenter mPresenter;
    private boolean isLoading = false;
    private CallbackManager callbackManager;
    private int colorSkin = 0;

    public static RegisterUserFragment newInstance() {
        RegisterUserFragment fragment = new RegisterUserFragment();
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_register_user, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputEmail.setTypeface(Util_Fonts.setFontLight(getContext()));
        inputFirstName.setTypeface(Util_Fonts.setFontLight(getContext()));
        input_last_name.setTypeface(Util_Fonts.setFontLight(getContext()));
        input_password.setTypeface(Util_Fonts.setFontLight(getContext()));
        etEmail.setTypeface(Util_Fonts.setFontLight(getContext()));
        etFirstName.setTypeface(Util_Fonts.setFontLight(getContext()));
        etPassword.setTypeface(Util_Fonts.setFontLight(getContext()));
        etLastName.setTypeface(Util_Fonts.setFontLight(getContext()));
        inputAge.setTypeface(Util_Fonts.setFontLight(getContext()));
        etAge.setTypeface(Util_Fonts.setFontLight(getContext()));
        skinBlack.setTypeface(Util_Fonts.setFontLight(getContext()));
        skinMedium.setTypeface(Util_Fonts.setFontLight(getContext()));
        skinWhite.setTypeface(Util_Fonts.setFontLight(getContext()));
        btnRegister.setTypeface(Util_Fonts.setFontNormal(getContext()));

        validator = new Validator(this);
        validator.setValidationListener(this);


    }

    @Override
    public void onValidationSucceeded() {
        UserEntity userEntity = new UserEntity(
                etFirstName.getText().toString(),
                etLastName.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                Integer.valueOf(etAge.getText().toString()),
                colorSkin);
        mPresenter.RegisterUser(userEntity);
        isLoading = false;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        isLoading = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                tvText.setText(message);
            }
        }
    }

    @Override
    public void ShowRegisterSuccesful(UserEntity userEntity) {
        Intent i = getActivity().getIntent();
        i.putExtra(LoginActivity.ARGUMENT_USER, userEntity);
        getActivity().setResult(LoginActivity.CREATE_ACCOUNT, i);
        getActivity().finish();
        isLoading = false;
    }


    @Override
    public void ShowErrorRegister(String msg) {
        tvText.setVisibility(View.VISIBLE);
        tvText.setText(msg);
        isLoading = false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    public void setmPresenter(RegisterUserContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showLoad() {
        isLoading = true;
        layoutLoad.setVisibility(View.VISIBLE);
        layoutContainer.setEnabled(false);
        layoutContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoad() {
        layoutLoad.setVisibility(View.INVISIBLE);
        layoutContainer.setEnabled(true);
        layoutContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void setError(String msg) {
        tvText.setVisibility(View.VISIBLE);
        tvText.setText(msg);
        isLoading = false;

    }

    @OnClick(R.id.btn_register)
    public void onClick() {

        if (!isLoading) {

            if (checkboxBlack.isChecked() || checkboxMedium.isChecked() || checkboxWhite.isChecked()) {
                tvText.setVisibility(View.GONE);
                InputMethodManager input = (InputMethodManager) getActivity()
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                validator.validate();
            } else {
                Toast.makeText(getContext(), "Debe seleccionar su color de piel", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @OnClick({R.id.checkbox_white, R.id.checkbox_medium, R.id.checkbox_black})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkbox_white:
                if(!checkboxWhite.isChecked()){
                    checkboxBlack.setChecked(false);
                    checkboxMedium.setChecked(false);
                    checkboxWhite.setChecked(false);
                }else{
                    checkboxBlack.setChecked(false);
                    checkboxMedium.setChecked(false);
                    checkboxWhite.setChecked(true);
                    colorSkin=1;
                }
                break;
            case R.id.checkbox_medium:
                if(!checkboxMedium.isChecked()){
                    checkboxBlack.setChecked(false);
                    checkboxMedium.setChecked(false);
                    checkboxWhite.setChecked(false);
                }else{
                    checkboxBlack.setChecked(false);
                    checkboxMedium.setChecked(true);
                    checkboxWhite.setChecked(false);
                    colorSkin=2;
                }
                break;
            case R.id.checkbox_black:
                if(!checkboxBlack.isChecked()){
                    checkboxBlack.setChecked(false);
                    checkboxMedium.setChecked(false);
                    checkboxWhite.setChecked(false);
                }else{
                    checkboxBlack.setChecked(true);
                    checkboxMedium.setChecked(false);
                    checkboxWhite.setChecked(false);
                    colorSkin=3;
                }
                break;
        }
    }
}
