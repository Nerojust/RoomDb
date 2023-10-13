package com.company.appintegration.roomDB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmployeeRepository {

    private final EmployeeDAO myDao;

    private final LiveData<List<EmployeeModel>> myEmployeeModelLiveData;

    ExecutorService myExecutor = Executors.newSingleThreadExecutor();

    public EmployeeRepository(Application application) {
        //  call the note database class and create an instance withe the application as context
        EmployeeDatabase myEmployeeDatabase = EmployeeDatabase.getInstance(application);
        myDao = myEmployeeDatabase.getEmployeeDAO();
        //        call the model instance to the Model class method for livedata
        myEmployeeModelLiveData = myDao.getAllEmployees();
    }

    public void insert(EmployeeModel myEmployeeModel) {

        myExecutor.execute(new Runnable() {
            @Override
            public void run() {

                myDao.insert(myEmployeeModel);
            }
        });
    }

    public void update(EmployeeModel myEmployeeModel) {
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                myDao.update(myEmployeeModel);
            }
        });
    }

    public void delete(EmployeeModel myEmployeeModel) {
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                myDao.delete(myEmployeeModel);
            }
        });
    }

    public void deleteAll() {
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                myDao.deleteAll();
            }
        });
    }

    public LiveData<List<EmployeeModel>> getMyEmployeeModelLiveData(){
        return myEmployeeModelLiveData;
    }

}




//    public void insertEmployee(EmployeeModel myEmployeeModel) {
//        new InsertEmployeeAsyncTask(dao).execute(myEmployeeModel);
//    }
//
//    // creating a method to update data in database.
//    public void updateEmployee(EmployeeModel myEmployeeModel) {
//        new UpdateEmployeeAsyncTask(dao).execute(myEmployeeModel);
//    }
//
//    // creating a method to delete the data in our database.
//    public void deleteEmployee(EmployeeModel myEmployeeModel) {
//        new DeleteEmployeeAsyncTask(dao).execute(myEmployeeModel);
//    }
//
//    // below is the method to delete all the courses.
//    public void deleteAllCourses() {
//        new DeleteAllEmployeeAsyncTask(dao).execute();
//    }
//
//    // below method is to read all the courses.
//    public LiveData<List<EmployeeModel>> getAllEmployee() {
//        return myEmployeeModelLiveData;
//    }
//
//private static class InsertEmployeeAsyncTask extends AsyncTask<EmployeeModel, Void, Void> {
//    private EmployeeDAO myEmployeeDao;
//
//    public InsertEmployeeAsyncTask(EmployeeDAO myEmployeeDAO) {
//        this.myEmployeeDao = myEmployeeDAO;
//    }
//    @Override
//    protected Void doInBackground(EmployeeModel... employeeModels) {
//        myEmployeeDao.insert(employeeModels[0]);
//        return null;
//    }
//}
//
//private static class UpdateEmployeeAsyncTask extends AsyncTask<EmployeeModel, Void, Void> {
//    private EmployeeDAO myEmployeeDao;
//
//    public UpdateEmployeeAsyncTask(EmployeeDAO myEmployeeDAO) {
//        this.myEmployeeDao = myEmployeeDAO;
//    }
//
//    @Override
//    protected Void doInBackground(EmployeeModel... employeeModels) {
//        myEmployeeDao.update(employeeModels[0]);
//        return null;
//    }
//}
//
//private static class DeleteEmployeeAsyncTask extends AsyncTask<EmployeeModel, Void, Void> {
//    private EmployeeDAO myEmployeeDao;
//
//    public DeleteEmployeeAsyncTask(EmployeeDAO myEmployeeDAO) {
//        this.myEmployeeDao = myEmployeeDAO;
//    }
//
//    @Override
//    protected Void doInBackground(EmployeeModel... employeeModels) {
//        myEmployeeDao.delete(employeeModels[0]);
//        return null;
//    }
//}
//
//private static class DeleteAllEmployeeAsyncTask extends AsyncTask<Void, Void, Void> {
//    private EmployeeDAO myEmployeeDao;
//
//    public DeleteAllEmployeeAsyncTask(EmployeeDAO myEmployeeDAO) {
//        this.myEmployeeDao = myEmployeeDAO;
//    }
//    @Override
//    protected Void doInBackground(Void... voids) {
//        myEmployeeDao.deleteAll();
//        return null;
//    }
//}
//}
