package com.example.kolot.test_balina_1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.activities.Main2Activity;
import com.example.kolot.test_balina_1.networking.api.Api;
import com.example.kolot.test_balina_1.networking.dto.Sign.UserDTO;
import com.example.kolot.test_balina_1.networking.dto.Sign.registrationDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kolot on 15.01.2018.
 */

public class LoginFragment extends android.support.v4.app.Fragment {

    private static final String BASE_URL = "http://junior.balinasoft.com/api/account/";

    private EditText login, pass;
    private Button button;
    private String password, log, token = "";
    private int id = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        login = (EditText) view.findViewById(R.id.login_login);
        pass = (EditText) view.findViewById(R.id.login_password);
        button = (Button) view.findViewById(R.id.login_button);

        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                password = pass.getText().toString();
                log = login.getText().toString();

                if (!password.isEmpty() && !log.isEmpty()) {

                    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create());
                    Retrofit retrofit = builder.build();

                    Api api_registration = retrofit.create(Api.class);

                    api_registration.signin(new UserDTO(log, password)).enqueue(new Callback<registrationDTO>() {
                        @Override
                        public void onResponse(Call<registrationDTO> call, Response<registrationDTO> response) {

                            if (response.isSuccessful()) {
                                token = response.body().getData().getToken();
                                id = response.body().getData().getUserId();
                            }
                            Intent intent = new Intent(getContext(), Main2Activity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("token", token);
                            bundle.putInt("photo_Id", id);
                            intent.putExtras(bundle);
                            startActivity(intent);


                        }

                        @Override
                        public void onFailure(Call<registrationDTO> call, Throwable t) {
                            Snackbar.make(view, t.getMessage(), Snackbar.LENGTH_LONG).show();
                            Log.d("ResponseLogin", t.getMessage());
                        }
                    });
                } else
                    Toast.makeText(getContext(), "Check your password", Toast.LENGTH_LONG).show();
            }
        });
    }
}
