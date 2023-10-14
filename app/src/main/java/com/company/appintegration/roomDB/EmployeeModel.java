package com.company.appintegration.roomDB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee_table")
public class EmployeeModel implements Parcelable {

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

    protected EmployeeModel(Parcel in) {
        id = in.readInt();
        employeeFullNames = in.readString();
        jobDescription = in.readString();
        yearOfEntry = in.readString();
    }

    public static final Creator<EmployeeModel> CREATOR = new Creator<EmployeeModel>() {
        @Override
        public EmployeeModel createFromParcel(Parcel in) {
            return new EmployeeModel(in);
        }

        @Override
        public EmployeeModel[] newArray(int size) {
            return new EmployeeModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(employeeFullNames);
        dest.writeString(jobDescription);
        dest.writeString(yearOfEntry);
    }
}
