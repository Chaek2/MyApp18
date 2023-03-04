package com.example.app18;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class,Account.class}, version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract AccountDao accountDao();
    public static  AppDataBase INSTANCE;
    public static AppDataBase getDbInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,"mtp.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
