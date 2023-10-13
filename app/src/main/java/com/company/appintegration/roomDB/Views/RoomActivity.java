package com.company.appintegration.roomDB.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.company.appintegration.R;
import com.company.appintegration.roomDB.Constant;
import com.company.appintegration.roomDB.EmployeeAdapter;
import com.company.appintegration.roomDB.EmployeeModel;
import com.company.appintegration.roomDB.EmployeeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    RecyclerView.LayoutManager myLayoutManager;
    private FloatingActionButton myFloatingButton;
    private EmployeeViewModel myEmployeeViewModel;
    private static final int ADD_Employee_REQUEST = 1;
    private static final int EDIT_Employee_REQUEST = 2;

  private EmployeeAdapter myAdapter;

    private ArrayList<EmployeeModel> myEmployeeModel;

    SearchView  mySearchView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        inItView();

    }
    private void inItView() {
        myRecyclerView = findViewById(R.id.recycler_view);
        myFloatingButton = findViewById(R.id.btn_floating);



        myFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(RoomActivity.this, NewEmployeeActivity.class);
                startActivityForResult(myIntent,ADD_Employee_REQUEST);
            }
        });

        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(myLayoutManager);

        // initializing adapter for recycler view.
         myAdapter = new EmployeeAdapter(myEmployeeModel, this);
        // setting adapter class for recycler view.
        myRecyclerView.setAdapter(myAdapter);

        myEmployeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        myEmployeeViewModel.getAllEmployeeModelLiveData().observe(this, new Observer<List<EmployeeModel>>() {
            @Override
            public void onChanged(List<EmployeeModel> employeeModels) {

                if(employeeModels != null){

                    myAdapter.submitList(employeeModels);

                }
            }
        });

        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                myEmployeeViewModel.delete(myAdapter.getEmployeeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(RoomActivity.this, "Course deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(myRecyclerView);

        // below line is use to set item click listener for our item of recycler view.
        myAdapter.setOnItemClickListener(new EmployeeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EmployeeModel model) {

                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent myIntent = new Intent(RoomActivity.this, NewEmployeeActivity.class);
                myIntent.putExtra(NewEmployeeActivity.EXTRA_ID, model.getId());
                myIntent.putExtra(NewEmployeeActivity.EXTRA_EMPLOYEE_FULL_NAME, model.getEmployeeFullNames());
                myIntent.putExtra(NewEmployeeActivity.EXTRA_JOB_DESCRIPTION, model.getJobDescription());
                myIntent.putExtra(NewEmployeeActivity.EXTRA_YEAR_OF_ENTRY, model.getYearOfEntry());

                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(myIntent, EDIT_Employee_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_Employee_REQUEST && resultCode == RESULT_OK) {

            String fullName = data.getStringExtra(NewEmployeeActivity.EXTRA_EMPLOYEE_FULL_NAME);
            String jobDescription = data.getStringExtra(NewEmployeeActivity.EXTRA_JOB_DESCRIPTION);
            String yearOfEntry = data.getStringExtra(NewEmployeeActivity.EXTRA_YEAR_OF_ENTRY);
            EmployeeModel model = new EmployeeModel(fullName, jobDescription, yearOfEntry);
            myEmployeeViewModel.insert(model);
            Toast.makeText(this, "Employee saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_Employee_REQUEST && resultCode == RESULT_OK) {

            int id = data.getIntExtra(Constant.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Employee data can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String fullName = data.getStringExtra(NewEmployeeActivity.EXTRA_EMPLOYEE_FULL_NAME);
            String jobDescription = data.getStringExtra(NewEmployeeActivity.EXTRA_JOB_DESCRIPTION);
            String yearOfEntry = data.getStringExtra(NewEmployeeActivity.EXTRA_YEAR_OF_ENTRY);
            EmployeeModel model = new EmployeeModel(fullName, jobDescription, yearOfEntry);
            model.setId(id);
            myEmployeeViewModel.update(model);
            Toast.makeText(this, "Employee data updated", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Employee Data not saved", Toast.LENGTH_SHORT).show();
        }
    }


}