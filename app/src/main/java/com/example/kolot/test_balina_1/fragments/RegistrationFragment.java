package com.example.kolot.test_balina_1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.networking.api.Api_Registration;
import com.example.kolot.test_balina_1.networking.dto.UserDTO;
import com.example.kolot.test_balina_1.networking.dto.registrationDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kolot on 15.01.2018.
 */

public class RegistrationFragment extends android.support.v4.app.Fragment {

    private static final String BASE_URL = "http://junior.balinasoft.com/api/account/";

    private EditText login, pass, pass_rep;
    private Button button;
    private String password, password_rep, log;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_fragment, null);

        login = (EditText) view.findViewById(R.id.reg_login);
        pass = (EditText) view.findViewById(R.id.reg_password);
        pass_rep = (EditText) view.findViewById(R.id.reg_password_repeat);
        button = (Button) view.findViewById(R.id.registration_button);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password = pass.getText().toString();
                password_rep = pass_rep.getText().toString();
                log = login.getText().toString();

                if (!password.isEmpty() && !password_rep.isEmpty() && !log.isEmpty()) {

                    if (password.equals(password_rep)) {
                        Toast.makeText(getContext(), "Registration goes", Toast.LENGTH_SHORT).show();
                        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create());
                        Retrofit retrofit = builder.build();

                        Api_Registration api_registration = retrofit.create(Api_Registration.class);

                        api_registration.signup(new UserDTO(log, password)).enqueue(new Callback<registrationDTO>() {
                            @Override
                            public void onResponse(Call<registrationDTO> call, Response<registrationDTO> response) {
                                log = response.body().getData().getLogin();
                                password = String.valueOf(response.body().getData().getUserId());
                                if (!log.isEmpty() && !password.isEmpty())
                                    Toast.makeText(getContext(), "Registration has went well.", Toast.LENGTH_LONG).show();
                                Log.d("Response",response.body().getData().getLogin());
                                Log.d("Response",response.body().getData().getToken());
                                Log.d("Response", String.valueOf(response.body().getData().getUserId()));

                            }

                            @Override
                            public void onFailure(Call<registrationDTO> call, Throwable t) {
                                Log.d("Response", t.getMessage());
                            }
                        });
                    }
                    else
                        Toast.makeText(getContext(), "Check your password", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getContext(), "Check your password", Toast.LENGTH_LONG).show();
            }
        });
    }
}