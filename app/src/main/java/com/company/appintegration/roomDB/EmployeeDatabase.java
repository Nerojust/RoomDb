package com.company.appintegration.roomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.company.appintegration.roomDB.EmployeeDAO;
import com.company.appintegration.roomDB.EmployeeModel;

@Database(entities = {EmployeeModel.class}, version = 1, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {

    public abstract EmployeeDAO getEmployeeDAO();

    private static EmployeeDatabase instance;

    public static synchronized EmployeeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), EmployeeDatabase.class, "employee_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
