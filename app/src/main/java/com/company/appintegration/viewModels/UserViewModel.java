package com.company.appintegration.viewModels;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.company.appintegration.R;
import com.company.appintegration.apiClient.RetrofitClient;
import com.company.appintegration.models.ResponseModel;
import com.company.appintegration.utilities.Resource;
import com.company.appintegration.views.MainActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserViewModel extends ViewModel {

    public static final String TAG = "UserViewModel";
    private MutableLiveData<Resource<ResponseModel>> myResponseLivedata;

    public UserViewModel(){
        this.myResponseLivedata = new MutableLiveData<>();
    }

    public MutableLiveData<Resource<ResponseModel>> getMyResponseLivedata(){
        return myResponseLivedata;
    }

    public void executeBillers(String value){

        Call<ResponseModel> myCall = RetrofitClient.getUserService().getBillers(value);

        myResponseLivedata.postValue(Resource.loading());
        myCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {

                if(response.isSuccessful() && response != null && response.body() != null){

                   myResponseLivedata.postValue(Resource.success(response.body()));
                }

                else{
                   String errorMessage = response.message();

                   myResponseLivedata.postValue(Resource.error(errorMessage));

                    try {
                        if (response.errorBody() != null) {
                            myResponseLivedata.postValue(Resource.error(response.errorBody().string()));
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, Throwable t) {

                myResponseLivedata.postValue(Resource.error(t.getMessage()));
            }
        });

    }






}
