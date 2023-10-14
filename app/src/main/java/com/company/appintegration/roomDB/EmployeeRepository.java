package com.company.appintegration.roomDB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmployeeRepository {

    private final EmployeeDAO employeeDAO;
    private final LiveData<List<EmployeeModel>> allEmployees;
    private final ExecutorService executorService;

    public EmployeeRepository(Application application) {
        // Initialize the Room database and DAO.
        EmployeeDatabase employeeDatabase = EmployeeDatabase.getInstance(application);
        employeeDAO = employeeDatabase.getEmployeeDAO();

        // Initialize LiveData for all employees.
        allEmployees = employeeDAO.getAllEmployees();

        // Initialize an ExecutorService for database operations.
        executorService = Executors.newSingleThreadExecutor();
    }

    // Expose LiveData to observe all employees.
    public LiveData<List<EmployeeModel>> getAllEmployees() {
        return allEmployees;
    }

    // Perform database operations on a background thread.

    public void insert(EmployeeModel employee) {
        executorService.execute(() -> employeeDAO.insert(employee));
    }

    public void update(EmployeeModel employee) {
        executorService.execute(() -> employeeDAO.update(employee));
    }

    public void delete(EmployeeModel employee) {
        executorService.execute(() -> employeeDAO.delete(employee));
    }

    public void deleteAll() {
        executorService.execute(() -> employeeDAO.deleteAll());
    }
}
