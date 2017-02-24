package com.laboratory.lapada.retrofit_http_conect;

import com.laboratory.lapada.retrofit_http_conect.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UdacityService {

    public static final String BASE_URL = "https://www.udacity.com/public-api/v0/";

    @GET("courses")
    Call<UdacityCatalog> listCatalog();
}
