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

import com.company.appintegration.adapters.UserAdapter;
import com.company.appintegration.models.ResponseModel;
import com.company.appintegration.models.Result;
import com.company.appintegration.R;
import com.company.appintegration.utilities.Resource;
import com.company.appintegration.viewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private  UserViewModel myUserViewModel;
  private  UserAdapter myAdapter;

    RecyclerView.LayoutManager myLayoutManager;

    RecyclerView myRecyclerView;

    private ProgressBar myProgressBar;

    List<Result> myBillerList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inItView();
        inItSubscriber();

    }
    private void inItView() {

        myProgressBar = findViewById(R.id.progress_bar);
        myRecyclerView = findViewById(R.id.recycler_view);

        myRecyclerView();
        myProgressBar.setVisibility(View.VISIBLE);

        myUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        myUserViewModel.executeBillers("2s");

    }

    private void myRecyclerView() {
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(myLayoutManager);
    }

    private void inItSubscriber() {

        myUserViewModel.getMyResponseLivedata().observe(this, new Observer<Resource<ResponseModel>>() {
            @Override
            public void onChanged(Resource<ResponseModel> responseModelResource) {

                switch(responseModelResource.status){
                    case SUCCESS:
                        myProgressBar.setVisibility(View.GONE);
//                        List<Result> myBillerList = null;
                        if(responseModelResource.data != null){

                            myBillerList = responseModelResource.data.getResult();
                        }
                        myAdapter = new UserAdapter(MainActivity.this, myBillerList);
                        myRecyclerView.setAdapter(myAdapter);
                        myAdapter.notifyDataSetChanged();
                        break;

                    case LOADING:
                        myProgressBar.setVisibility(View.VISIBLE);
                        break;

                    case ERROR:
                        myProgressBar.setVisibility(View.GONE);
                        sendErrorDialog(responseModelResource.message);
                }
            }
        });
    }

    private void sendErrorDialog(String message) {


        AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
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