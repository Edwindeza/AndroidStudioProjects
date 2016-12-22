/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.artkodec.uv_control.views.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.utils.Util_Fonts;
import com.artkodec.uv_control.views.activities.LoginActivity;
import com.artkodec.uv_control.views.activities.RegisterUserActivity;
import com.artkodec.uv_control.views.contracts.LoginContract;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Main UI for the task detail screen.
 */
public class LoginFragment extends Fragment implements LoginContract.View, Validator.ValidationListener {


    @BindView(R.id.input_email)
    TextInputLayout inputEmail;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tvText)
    TextView tvText;
    @BindView(R.id.login_facebook)
    Button loginFacebook;
    @BindView(R.id.tv_create_account)
    TextView tvCreateAccount;
    @BindView(R.id.tv_recover_password)
    TextView tvRecoverPassword;
    @NotEmpty(message = "Este campo no puede ser vacío")
    @Email(message = "Email inválido")
    @BindView(R.id.et_email)
    EditText etEmail;
    @NotEmpty(message = "Este campo no puede ser vacío")
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.input_password)
    TextInputLayout inputPassword;
    @BindView(R.id.pb_load)
    ProgressBar pbLoad;
    @BindView(R.id.tv_load)
    TextView tvLoad;
    @BindView(R.id.layout_load)
    LinearLayout layoutLoad;
    @BindView(R.id.layout_container)
    LinearLayout layoutContainer;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;


    private Validator validator;
    private LoginContract.Presenter mPresenter;
    private boolean isLoading = false;
    private CallbackManager callbackManager;
    private ProgressDialog progressDialog;



    public void setmPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String access_token_facebook = loginResult.getAccessToken().getToken();
                if (access_token_facebook != null || !access_token_facebook.equals("")) {
                    mPresenter.loginFacebook(access_token_facebook);
                    AccessToken.setCurrentAccessToken(loginResult.getAccessToken());
                }else {
                    Toast.makeText(getContext(), "Algo sucedió mal al intentar loguearse", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "El login a facebook se a cancelado, intente más tarde", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getContext(), "Error al intentar loguearse", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_login, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Ingresando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.circle_progress));

        inputEmail.setTypeface(Util_Fonts.setFontLight(getContext()));
        inputPassword.setTypeface(Util_Fonts.setFontLight(getContext()));
        etEmail.setTypeface(Util_Fonts.setFontLight(getContext()));
        etPassword.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvCreateAccount.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvRecoverPassword.setTypeface(Util_Fonts.setFontLight(getContext()));
        btnLogin.setTypeface(Util_Fonts.setFontNormal(getContext()));
        loginFacebook.setTypeface(Util_Fonts.setFontNormal(getContext()));
        tvText.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvLoad.setTypeface(Util_Fonts.setFontNormal(getContext()));

        tvCreateAccount.setText(Html.fromHtml("<p><u>" + String.format(getString(R.string.create_account)) + "</u></p>"));
        tvRecoverPassword.setText(Html.fromHtml("<p><u>" + String.format(getString(R.string.recover_password) + "</u></p>")));

        validator = new Validator(this);
        validator.setValidationListener(this);

    }


    @OnClick({R.id.btn_login, R.id.login_facebook, R.id.tv_create_account, R.id.tv_recover_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if(!isLoading){
                    tvText.setVisibility(View.GONE);
                    InputMethodManager input = (InputMethodManager) getActivity()
                            .getSystemService(Activity.INPUT_METHOD_SERVICE);
                    input.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    validator.validate();

                }



                break;
            case R.id.login_facebook:
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
                break;
            case R.id.tv_create_account:

                Intent intent = new Intent(this.getContext(), RegisterUserActivity.class);
                startActivityForResult(intent, LoginActivity.CREATE_ACCOUNT);
                break;
            case R.id.tv_recover_password:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

        if(resultCode==LoginActivity.CREATE_ACCOUNT){
            UserEntity userEntity= (UserEntity)data.getSerializableExtra(LoginActivity.ARGUMENT_USER);
            Intent i = getActivity().getIntent();
            i.putExtra(LoginActivity.ARGUMENT_USER, userEntity);
            getActivity().setResult(getActivity().RESULT_OK, i);
            getActivity().finish();
        }
    }
    @Override
    public void successLogin(UserEntity userEntity) {


        Intent i = getActivity().getIntent();
        i.putExtra(LoginActivity.ARGUMENT_USER, userEntity);
        getActivity().setResult(getActivity().RESULT_OK, i);
        getActivity().finish();
        isLoading=false;

    }



    @Override
    public void errorLogin(String msg) {
        tvText.setVisibility(View.VISIBLE);
        tvText.setText(msg);
        isLoading=false;
    }


    @Override
    public void showLoad() {
        isLoading=true;

        progressDialog.show();
        /*layoutLoad.setVisibility(View.VISIBLE);
        layoutContainer.setEnabled(false);
        layoutContainer.setVisibility(View.GONE);*/

    }

    @Override
    public void hideLoad() {
        progressDialog.dismiss();
        isLoading=false;
      /*  layoutLoad.setVisibility(View.INVISIBLE);
        layoutContainer.setEnabled(true);
        layoutContainer.setVisibility(View.VISIBLE);*/
    }


    @Override
    public void setError(String msg) {

    }

    @Override
    public void onValidationSucceeded() {
        mPresenter.loginUser(etEmail.getText().toString(), etPassword.getText().toString());
        isLoading=false;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        isLoading=false;
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





}
