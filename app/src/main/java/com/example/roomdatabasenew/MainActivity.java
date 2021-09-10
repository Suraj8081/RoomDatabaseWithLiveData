package com.example.roomdatabasenew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.roomdatabasenew.adapter.PersonAdapter;
import com.example.roomdatabasenew.entity.Person;
import com.example.roomdatabasenew.repository.PersonRepository;
import com.example.roomdatabasenew.viewModel.PersonViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText fr_name, lt_name;
    private TextView tv_notFound;
    private Button button_save, button_deleteAll;
    private RecyclerView rv_person;
    private PersonViewModel personViewModel;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIds();

        ////////////
        //repository = new PersonRepository(getApplication());

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);

        ///////////
        rv_person.setLayoutManager(new LinearLayoutManager(this));
        rv_person.setHasFixedSize(true);
        PersonAdapter adapter = new PersonAdapter(MainActivity.this);
        rv_person.setAdapter(adapter);
        /////////////////////////////////////////////


        personViewModel.getAllPerson().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                if (people != null && people.size() > 0) {
                    tv_notFound.setVisibility(View.GONE);
                    rv_person.setVisibility(View.VISIBLE);
                    adapter.setData(people);
                } else {
                    tv_notFound.setVisibility(View.VISIBLE);
                    rv_person.setVisibility(View.GONE);
                }
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = fr_name.getEditableText().toString();
                String lstNme = lt_name.getEditableText().toString();
                Person person = new Person();
                person.setFirstname(fName);
                person.setLastName(lstNme);
                personViewModel.insertPerson(person);
            }
        });

        button_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personViewModel.deleteAllPerson();
            }
        });


    }

    private void findIds() {
        fr_name = findViewById(R.id.fr_name);
        lt_name = findViewById(R.id.lt_name);
        button_save = findViewById(R.id.button_save);
        rv_person = findViewById(R.id.rv_person);
        tv_notFound = findViewById(R.id.tv_notFound);
        button_deleteAll = findViewById(R.id.button_deleteAll);
    }

    public void deletePerson(Person person) {
        personViewModel.deletePerson(person);
    }

    public void updatePerson(Person person) {
        personViewModel.updatePerson(person);
    }


}