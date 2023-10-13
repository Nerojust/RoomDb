package com.company.appintegration.networkServices;


import com.company.appintegration.models.GameModel;
import com.company.appintegration.models.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {


    @GET("/v3/44389778-349f-4b86-afe8-6a861cd48f46")
    Call<ResponseModel> getBillers(@Query("mocky-delay") String value);

    @GET("marvel")
    Call<List<GameModel>> getSuperHeros();
}
