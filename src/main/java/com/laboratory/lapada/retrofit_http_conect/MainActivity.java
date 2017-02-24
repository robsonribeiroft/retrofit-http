package com.laboratory.lapada.retrofit_http_conect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.laboratory.lapada.retrofit_http_conect.models.Course;
import com.laboratory.lapada.retrofit_http_conect.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(UdacityService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        UdacityService udacityService = retrofit.create(UdacityService.class);

        Call<UdacityCatalog> resquestCatalog = udacityService.listCatalog();

        resquestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if (!response.isSuccessful()){
                    Log.i("ErrorServidor", "Problema no servidor" + response.code());
                } else {
                    //Sucesso na requisição
                    UdacityCatalog catalog = response.body();

                    for (Course course : catalog.courses){
                        Log.i("Sucess", String.format("%s: %s", course.title, course.subtitle));
                        Log.i("Sucess", "------------");
                    }
                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {

                Log.e("ErrorLocal", "Aparelho sem acesso a internet - " + t.getMessage());
            }
        });
    }
}
