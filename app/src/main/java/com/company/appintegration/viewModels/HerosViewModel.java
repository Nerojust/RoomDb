package com.company.appintegration.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.company.appintegration.apiClient.RetrofitClientHero;
import com.company.appintegration.models.GameModel;
import com.company.appintegration.utilities.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HerosViewModel extends ViewModel {

    private MutableLiveData<Resource<List<GameModel>>> myGameModel;
    public HerosViewModel(){
        this.myGameModel = new MutableLiveData<>();
    }

    public MutableLiveData<Resource<List<GameModel>>> getGameLivedata(){

        return myGameModel;
    }

    public void executeHeroResponse(){
        Call<List<GameModel>> myCall = RetrofitClientHero.getWebService().getSuperHeros();
        myGameModel.postValue(Resource.loading());
        myCall.enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<GameModel>> call, @NonNull Response<List<GameModel>> response) {

                if(response.isSuccessful() &&  response != null && response.body() !=null) {

                    myGameModel.postValue(Resource.success(response.body()));
                }
                else{
                    myGameModel.postValue(Resource.error(response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GameModel>> call, Throwable t) {
                myGameModel.postValue(Resource.error(t.getMessage()));
            }
        });
    }
}
