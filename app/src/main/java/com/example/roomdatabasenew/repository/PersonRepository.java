package com.example.roomdatabasenew.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomdatabasenew.dao.PersonDao;
import com.example.roomdatabasenew.database.MyRoomDataBase;
import com.example.roomdatabasenew.entity.Person;

import java.util.List;

public class PersonRepository {

    private PersonDao personDao;
    private LiveData<List<Person>> allPerson;


    public PersonRepository(Application application) {
        MyRoomDataBase db = MyRoomDataBase.getDatabase(application);
        personDao = db.personDao();
        allPerson = personDao.getAllPerson();
    }

    public LiveData<List<Person>> getAllPerson() {
        return allPerson;
    }

    public List<Person> getAllPersonASC() {
        return personDao.getAllPersonByASC();
    }

    public List<Person> getAllPersonDESC() {
        return personDao.getAllPersonByDSC();
    }

    public void updatePerson(Person person) {
        new UpdatePerson(personDao).execute(person);
    }

    public void insertPerson(Person person) {
        new InsertPerson(personDao).execute(person);
    }

    public void deleteAllPerson() {
        new DeleteAllPerson(personDao).execute();
    }

    public void deletePerson(Person person) {
        new DeletePerson(personDao).execute(person);
    }


    public static class InsertPerson extends AsyncTask<Person, Void, Void> {

        private PersonDao personDao;

        public InsertPerson(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.insert(people[0]);
            return null;
        }
    }

    public static class UpdatePerson extends AsyncTask<Person, Void, Void> {

        private PersonDao personDao;

        public UpdatePerson(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.updatePerson(people[0]);
            return null;
        }
    }


    public static class DeleteAllPerson extends AsyncTask<Void, Void, Void> {

        private PersonDao personDao;

        public DeleteAllPerson(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personDao.deleteAllPerson();
            return null;
        }
    }

    public static class DeletePerson extends AsyncTask<Person, Void, Void> {

        private PersonDao personDao;

        public DeletePerson(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.deletePerson(people[0]);
            return null;
        }
    }


}
