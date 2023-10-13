package com.company.appintegration.roomDB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

  private final EmployeeRepository myEmployeeRepository;

    private final LiveData<List<EmployeeModel>> allEmployeeModelLiveData;
    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        //        use the object of repository and livedata
        myEmployeeRepository = new EmployeeRepository(application);
        allEmployeeModelLiveData = myEmployeeRepository.getMyEmployeeModelLiveData();
    }

    // below method is use to insert the data to our repository.
    public void insert(EmployeeModel myEmployeeModel) {
        myEmployeeRepository.insert(myEmployeeModel);
    }

    // below line is to update data in our repository.
    public void update(EmployeeModel myEmployeeModel) {
        myEmployeeRepository.update(myEmployeeModel);
    }

    // below line is to delete the data in our repository.
    public void delete(EmployeeModel myEmployeeModel) {
        myEmployeeRepository.delete(myEmployeeModel);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllEmployee() {
        myEmployeeRepository.deleteAll();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<EmployeeModel>> getAllEmployeeModelLiveData(){
        return allEmployeeModelLiveData;
    }
}
