package com.company.appintegration.apiClient;



import com.company.appintegration.networkServices.WebService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String CUSTOMER_DEMAND_BASE_URL = "https://run.mocky.io/";

    private static Retrofit myRetrofit;

   private static Retrofit getRetrofit(){




       HttpLoggingInterceptor myHttpInterceptor = new HttpLoggingInterceptor();
       myHttpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

       OkHttpClient myOkHttpClient = new OkHttpClient.Builder().addInterceptor(myHttpInterceptor).build();

       if(myRetrofit == null) {
           myRetrofit = new Retrofit.Builder().baseUrl(CUSTOMER_DEMAND_BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create()).client(myOkHttpClient).build();


       }
       return myRetrofit;
   }


   public static WebService getUserService(){
       WebService myUserService = getRetrofit().create(WebService.class);

       return myUserService;
   }
}
