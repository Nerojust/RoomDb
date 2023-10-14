package com.company.appintegration.roomDB.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.appintegration.R;
import com.company.appintegration.roomDB.Constant;
import com.company.appintegration.roomDB.EmployeeAdapter;
import com.company.appintegration.roomDB.EmployeeModel;
import com.company.appintegration.roomDB.EmployeeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {
    private static final int ADD_Employee_REQUEST = 1;
    private static final int EDIT_Employee_REQUEST = 2;
    RecyclerView.LayoutManager myLayoutManager;
    EmployeeViewModel myEmployeeViewModel;
    private EmployeeAdapter myAdapter;
    private ArrayList<EmployeeModel> myEmployeeModel;
    private List<EmployeeModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        // Initialize your ViewModel here.
        myEmployeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        inItView();
    }

    private void inItView() {
        RecyclerView myRecyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton myFloatingButton = findViewById(R.id.btn_floating);

        myFloatingButton.setOnClickListener(view -> {
            Intent myIntent = new Intent(RoomActivity.this, NewEmployeeActivity.class);
            startActivityForResult(myIntent, ADD_Employee_REQUEST);
        });

        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(myLayoutManager);


        // initializing adapter for recycler view.

        // setting adapter class for recycler view.


        myEmployeeViewModel.getAllEmployeesLiveData().observe(this, employeeModels -> {
            if (employeeModels != null) {
                myAdapter = new EmployeeAdapter(employeeModels, this);
                myRecyclerView.setAdapter(myAdapter);
//                myAdapter.submitList(employeeModels);
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
//        myAdapter.setOnItemClickListener(model -> {
//
//            // after clicking on item of recycler view
//            // we are opening a new activity and passing
//            // a data to our activity.
//            Intent myIntent = new Intent(RoomActivity.this, NewEmployeeActivity.class);
//            myIntent.putExtra(NewEmployeeActivity.EXTRA_ID, model.getId());
//            myIntent.putExtra(NewEmployeeActivity.EXTRA_EMPLOYEE_FULL_NAME, model.getEmployeeFullNames());
//            myIntent.putExtra(NewEmployeeActivity.EXTRA_JOB_DESCRIPTION, model.getJobDescription());
//            myIntent.putExtra(NewEmployeeActivity.EXTRA_YEAR_OF_ENTRY, model.getYearOfEntry());
//
//            // below line is to start a new activity and
//            // adding a edit course constant.
//            startActivityForResult(myIntent, EDIT_Employee_REQUEST);
//
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
// In onActivityResult()
//        if (requestCode == ADD_Employee_REQUEST && resultCode == RESULT_OK) {
//            if (data != null && data.hasExtra(NewEmployeeActivity.EXTRA_NEW_EMPLOYEE)) {
//                EmployeeModel newEmployee = data.getParcelableExtra(NewEmployeeActivity.EXTRA_NEW_EMPLOYEE);
//
//                // Insert the new employee into the database using your ViewModel
//                myEmployeeViewModel.insert(newEmployee);
//                Toast.makeText(this, "Employee saved", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
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
        } else {
            Toast.makeText(this, "Employee Data not saved", Toast.LENGTH_SHORT).show();
        }
    }


}