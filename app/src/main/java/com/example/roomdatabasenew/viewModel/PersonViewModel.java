package com.example.roomdatabasenew.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roomdatabasenew.entity.Person;
import com.example.roomdatabasenew.repository.PersonRepository;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository repository;
    private LiveData<List<Person>> allPerson;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        repository = new PersonRepository(application);
        allPerson = repository.getAllPerson();
    }

    public LiveData<List<Person>> getAllPerson() {
        return allPerson;
    }


    public void insertPerson(Person person) {
        repository.insertPerson(person);
    }

    public void deletePerson(Person person) {
        repository.deletePerson(person);
    }

    public void deleteAllPerson() {
        repository.deleteAllPerson();
    }

    public void updatePerson(Person person) {
        repository.updatePerson(person);
    }


}
