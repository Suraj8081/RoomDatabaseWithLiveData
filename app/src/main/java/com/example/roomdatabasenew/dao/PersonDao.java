package com.example.roomdatabasenew.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.roomdatabasenew.entity.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Person person);

    @Update
    int updatePerson(Person person);

    @Query("select * from person")
    LiveData<List<Person>>  getAllPerson();

    @Query("select * from person ORDER BY first_name asc")
    List<Person>  getAllPersonByASC();

    @Query("select * from person ORDER BY first_name desc")
    List<Person>  getAllPersonByDSC();

    @Query("delete from person")
    int deleteAllPerson();

    @Delete
    int deletePerson(Person person);


}
