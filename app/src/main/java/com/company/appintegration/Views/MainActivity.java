package com.company.appintegration.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.company.appintegration.Adapters.UserAdapter;
import com.company.appintegration.Models.ResponseModel;
import com.company.appintegration.Models.Result;
import com.company.appintegration.R;
import com.company.appintegration.ViewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserViewModel myUserViewModel;
    UserAdapter myAdapter;

    RecyclerView.LayoutManager myLayoutManager;

    RecyclerView myRecyclerView;

    private List<Result> myResponse = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inItView();
        inItModel();

    }




    private void inItView() {

        myRecyclerView = findViewById(R.id.recycler_view);

        myUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

    }

    private void inItModel() {

     myUserViewModel.getMyResponseLivedata().observe(this, new Observer<ResponseModel>() {
         @Override
         public void onChanged(ResponseModel responseModel) {

             if(responseModel != null){

                 List<Result> myResult = responseModel.getResult();
                 myResponse.addAll(myResult);
                 myAdapter.notifyDataSetChanged();

                 String value = responseModel.getResult().toString();

                myAdapter.setResults(responseModel.getResult());
                myUserViewModel.getUserValue(value);

             }
         }
     });
        recyclerView();

    }

    private void recyclerView() {


        if(myAdapter == null){

            myAdapter = new UserAdapter(this, myResponse);
            myRecyclerView.setHasFixedSize(true);
            myLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            myRecyclerView.setLayoutManager(myLayoutManager);
            myRecyclerView.setAdapter(myAdapter);
        }

        else{
            myAdapter.notifyDataSetChanged();
        }




    }

}