package com.company.appintegration.roomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {EmployeeModel.class}, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {

    // below line is to create instance
    // for our database class.
    private static EmployeeDatabase instance;

    // below line is to create
    // abstract variable for dao.
    public abstract EmployeeDAO getEmployeeDAO();

    public static synchronized EmployeeDatabase getInstance(Context myContext) {

        if (instance == null) {
            instance = Room.databaseBuilder(myContext.getApplicationContext(),
                            EmployeeDatabase.class, "employee_database").fallbackToDestructiveMigration().
                    addCallback(myCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback myCallback = new Callback() {
        final EmployeeDAO myEmployeeDAO = instance.getEmployeeDAO();

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            ExecutorService myExecutor = Executors.newSingleThreadExecutor();

            myExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    myEmployeeDAO.insert(new EmployeeModel("Name", "Job", "year"));

                }
            });

        }
    };
}


