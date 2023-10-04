package com.company.appintegration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.company.appintegration.Adapters.HeroAdapter;
import com.company.appintegration.Models.GameModel;
import com.company.appintegration.ViewModels.HerosViewModel;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView myRecyclerView;
    HeroAdapter myHeroAdapter;

    RecyclerView.LayoutManager myLayoutManager;

   List<GameModel> myGameModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myRecyclerView = findViewById(R.id.recycler_view);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myHeroAdapter = new HeroAdapter(this, myGameModel);
        myRecyclerView.setAdapter(myHeroAdapter);

        HerosViewModel myHeroViewModel = ViewModelProviders.of(this).get(HerosViewModel.class);
        myHeroViewModel.getGameLivedata().observe(this, new Observer<List<GameModel>>() {
            @Override
            public void onChanged(List<GameModel> gameModels) {

                if(gameModels != null) {
                     myGameModel = gameModels;
                    myHeroAdapter.setHeros(gameModels);
//                    myHeroAdapter = new HeroAdapter(MainActivity2.this, myGameModel);
//                    myRecyclerView.setAdapter(myHeroAdapter);
                }
            }
        });






    }
}