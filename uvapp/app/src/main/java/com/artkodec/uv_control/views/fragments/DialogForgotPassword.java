package com.artkodec.uv_control.views.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.views.contracts.CurrentWeatherContract;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 08/06/16.
 */
public class DialogForgotPassword extends AlertDialog implements Validator.ValidationListener {
    @NotEmpty(message = "Este campo no puede ser vacío")
    private EditText edEmail;
    private Button btnSendEmail;
    private CurrentWeatherContract.View viewContract;

    private Validator validator;
    public DialogForgotPassword(Context context, final CurrentWeatherContract.View viewContract) {
        super(context);
        this.viewContract = checkNotNull(viewContract, "view cannot be null!");
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.input_favorites, null);
        setView(view);
        validator=new Validator(this);
        validator.setValidationListener(this);
        edEmail=(EditText)view.findViewById(R.id.et_email);
        btnSendEmail=(Button) view.findViewById(R.id.btn_login);
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });


    }

    @Override
    public void onValidationSucceeded() {
        this.dismiss();
        viewContract.sendFavorite(edEmail.getText().toString());
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
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
