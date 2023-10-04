package com.company.appintegration.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.company.appintegration.ApiClient.RetrofitClient;
import com.company.appintegration.ApiClient.RetrofitClientHero;
import com.company.appintegration.Models.GameModel;
import com.company.appintegration.Models.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HerosViewModel extends ViewModel {

    private MutableLiveData<List<GameModel>>myGameModel;

//    public HerosViewModel(){
//        this.myGameModel = new MutableLiveData<>();
//    }

    public LiveData<List<GameModel>> getGameLivedata(){

        if(myGameModel == null){
            myGameModel = new MutableLiveData<>();

            loadHeros();
        }
        return myGameModel;
    }

    private void loadHeros(){
        Call<List<GameModel>> myCall = RetrofitClientHero.getUserService().getSuperHeros();
        myCall.enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(Call<List<GameModel>> call, Response<List<GameModel>> response) {

                if(response.isSuccessful()) {
                    myGameModel.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GameModel>> call, Throwable t) {


            }
        });
    }
}
