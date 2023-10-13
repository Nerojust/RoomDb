package com.company.appintegration.roomDB;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface EmployeeDAO {

    @Insert
    void insert(EmployeeModel myEmployeeModel);

    @Update
    void update(EmployeeModel myEmployeeModel);


    @Delete
    void delete(EmployeeModel myEmployeeModel);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM employee_table")
    void deleteAll();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM employee_table ORDER BY employeeFullNames ASC")
    LiveData<List<EmployeeModel>> getAllEmployees();
}
