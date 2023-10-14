package com.company.appintegration.roomDB.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.company.appintegration.R;
import com.company.appintegration.roomDB.EmployeeModel;

public class NewEmployeeActivity extends AppCompatActivity {

    // creating a constant string variable for our
    // course name, description and duration.
    public static final String EXTRA_ID = "userId";
    public static final String EXTRA_EMPLOYEE_FULL_NAME = "userFullName";
    public static final String EXTRA_JOB_DESCRIPTION = "userJobDescription";
    public static final String EXTRA_YEAR_OF_ENTRY = "userYearOfEntry";
    public static final String EXTRA_NEW_EMPLOYEE = "EXTRA_NEW_EMPLOYEE";
    int id;
    private EditText fullNameEditText, jobDescriptionEditText, yearOfEntryEditText;
    private Button saveEmployeeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);
        inItView();
        inItListener();
    }

    private void inItView() {
        fullNameEditText = findViewById(R.id.et_full_name);
        jobDescriptionEditText = findViewById(R.id.et_job_Description);
        yearOfEntryEditText = findViewById(R.id.et_year_of_entry);
        saveEmployeeData = findViewById(R.id.btn_save_employee);

        // below line is to get intent as we
        // are getting data via an intent.
        Intent myIntent = getIntent();
        if (myIntent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            fullNameEditText.setText(myIntent.getStringExtra(EXTRA_EMPLOYEE_FULL_NAME));
            jobDescriptionEditText.setText(myIntent.getStringExtra(EXTRA_JOB_DESCRIPTION));
            yearOfEntryEditText.setText(myIntent.getStringExtra(EXTRA_YEAR_OF_ENTRY));
        }
    }

    private void inItListener() {

        saveEmployeeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInput();
            }
        });

    }

    private void saveUserInput() {

        String fullName = fullNameEditText.getText().toString();
        String jobDescription = jobDescriptionEditText.getText().toString();
        String yearOfEntry = yearOfEntryEditText.getText().toString();

        if (fullName.isEmpty() && jobDescription.isEmpty() && yearOfEntry.isEmpty()) {
            fullNameEditText.setError("Field can't be empty");
            jobDescriptionEditText.setError("Field can't be empty");
            yearOfEntryEditText.setError("Field can't be empty");
            return;
        }

        // Create an EmployeeModel with this data
//        EmployeeModel newEmployee = new EmployeeModel(fullName, jobDescription, yearOfEntry);
//
//        // Pass this data back to the RoomActivity
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra(EXTRA_NEW_EMPLOYEE, newEmployee);
//        setResult(RESULT_OK, resultIntent);
//        finish();

        // inside this method we are passing
        // all the data via an intent.
        Intent myIntent = new Intent();
        // in below line we are passing all our course detail.
        myIntent.putExtra(EXTRA_EMPLOYEE_FULL_NAME, fullName);
        myIntent.putExtra(EXTRA_JOB_DESCRIPTION, jobDescription);
        myIntent.putExtra(EXTRA_YEAR_OF_ENTRY, yearOfEntry);
        id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            myIntent.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, myIntent);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Employees data has been saved to the  Database. ", Toast.LENGTH_LONG).show();
        finish();
    }

}