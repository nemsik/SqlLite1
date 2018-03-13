package com.example.bartek.sqllite1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG";

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        Person person = new Person("Jan", "30", "Malarz", "brak");
        myDb.addPerson(person);

        List<Person> allPeople = myDb.getAllData();

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<allPeople.size(); i++){
            stringBuilder.append(allPeople.get(i).getId()).append(" ");
            stringBuilder.append(allPeople.get(i).getName()).append(" ");
            stringBuilder.append(allPeople.get(i).getAge()).append(" ");
            stringBuilder.append(allPeople.get(i).getOccupation()).append(" ");
            stringBuilder.append(allPeople.get(i).getImage()).append(" ").append("\n");
        }
        Log.d(TAG, stringBuilder.toString());

    }
}
