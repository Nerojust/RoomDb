package com.company.appintegration.roomDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee_table")
public class EmployeeModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String employeeFullNames;
    private String jobDescription;
    private String yearOfEntry;

    public EmployeeModel(String employeeFullNames, String jobDescription, String yearOfEntry) {
        this.id = id;
        this.employeeFullNames = employeeFullNames;
        this.jobDescription = jobDescription;
        this.yearOfEntry = yearOfEntry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeFullNames() {
        return employeeFullNames;
    }

    public void setEmployeeFullNames(String employeeFullNames) {
        this.employeeFullNames = employeeFullNames;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getYearOfEntry() {
        return yearOfEntry;
    }

    public void setYearOfEntry(String yearOfEntry) {
        this.yearOfEntry = yearOfEntry;
    }
}
