package com.company.appintegration.roomDB;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private final EmployeeRepository employeeRepository;
    private final LiveData<List<EmployeeModel>> allEmployeesLiveData;

    public EmployeeViewModel(Application application) {
        super(application);
        employeeRepository = new EmployeeRepository(application);
        allEmployeesLiveData = employeeRepository.getAllEmployees();
    }

    public LiveData<List<EmployeeModel>> getAllEmployeesLiveData() {
        return allEmployeesLiveData;
    }

    public void insert(EmployeeModel employee) {
        employeeRepository.insert(employee);
    }

    public void update(EmployeeModel employee) {
        employeeRepository.update(employee);
    }

    public void delete(EmployeeModel employee) {
        employeeRepository.delete(employee);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
