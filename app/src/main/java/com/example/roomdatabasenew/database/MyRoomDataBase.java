package com.example.roomdatabasenew.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabasenew.dao.PersonDao;
import com.example.roomdatabasenew.entity.Person;


@Database(entities = {Person.class},version = 1)
public abstract class MyRoomDataBase extends RoomDatabase {

    public abstract PersonDao personDao();
    private static MyRoomDataBase INSTANCE;

    public static MyRoomDataBase getDatabase(Context context){
        if (INSTANCE==null){
            synchronized (MyRoomDataBase.class){
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                        MyRoomDataBase.class,
                        "myRoomDb"
                        )
                        .fallbackToDestructiveMigration()
                        .build();
            }

        }
        return  INSTANCE;
    }




}
