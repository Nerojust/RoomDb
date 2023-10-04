package com.company.appintegration.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.company.appintegration.ApiClient.RetrofitClient;
import com.company.appintegration.Models.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserViewModel extends ViewModel {

    public static final String TAG = "UserViewModel";
    private MutableLiveData<ResponseModel> myResponseLivedata;

    public UserViewModel(){
        this.myResponseLivedata = new MutableLiveData<>();
    }

    public MutableLiveData<ResponseModel> getMyResponseLivedata(){
        return myResponseLivedata;
    }

    public void getUserValue(String value){

        Call<ResponseModel> myCall = RetrofitClient.getUserService().getUser(value);
        myCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if(response.isSuccessful()){

                    myResponseLivedata.postValue(response.body());
                }

                else{
                    myResponseLivedata.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                myResponseLivedata.postValue(null);
            }
        });

    }




}
