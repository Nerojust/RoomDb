package com.company.appintegration.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.company.appintegration.R;
import com.company.appintegration.adapters.HeroAdapter;
import com.company.appintegration.models.GameModel;
import com.company.appintegration.utilities.Resource;
import com.company.appintegration.viewModels.HerosViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView myRecyclerView;
  private  HeroAdapter myHeroAdapter;

  private ProgressBar myProgressBar;

  private HerosViewModel myHeroViewModel;

    RecyclerView.LayoutManager myLayoutManager;

   List<GameModel> myGameModel = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        inItView();
        inItSubscriber();

    }
    private void inItView() {
        myProgressBar = findViewById(R.id.progress_bar);
        myRecyclerView = findViewById(R.id.recycler_view);



        myRecyclerView();

        myHeroViewModel = ViewModelProviders.of(this).get(HerosViewModel.class);
        myHeroViewModel.executeHeroResponse();
    }

    private void myRecyclerView() {
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(myLayoutManager);
    }

    private void inItSubscriber() {
        myHeroViewModel.getGameLivedata().observe(this, new Observer<Resource<List<GameModel>>>() {
            @Override
            public void onChanged(Resource<List<GameModel>> listResource) {
                   switch(listResource.status){

                       case SUCCESS:
                           myProgressBar.setVisibility(View.GONE);
                           if(listResource != null){
                              myGameModel = listResource.data;
                           }
                           myHeroAdapter = new HeroAdapter(MainActivity2.this, myGameModel);
                           myRecyclerView.setAdapter(myHeroAdapter);
                           break;

                       case ERROR:
                        myProgressBar.setVisibility(View.GONE);
                        sendErrorDialog(listResource.message);
                        break;

                       case LOADING:
                           myProgressBar.setVisibility(View.VISIBLE);
                   }
            }
        });
        }
    private void sendErrorDialog(String message) {


        AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity2.this);
        View myView = getLayoutInflater().inflate(R.layout.error_dialog, null);
        myBuilder.setView(myView);
        myBuilder.setCancelable(false);

        TextView ok = myView.findViewById(R.id.tv_ok);
        TextView msgView = myView.findViewById(R.id.tv_error_message);
        msgView.setText(message);

        AlertDialog myDialog = myBuilder.create();
        myDialog.show();


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.dismiss();

            }
        });

    }


}