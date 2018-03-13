package com.example.bartek.sqllite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartek on 13.03.2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "people.db";
    private static final int DATABASE_VERSION = 6;
    public static final String TABLE_NAME = "People";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PERSON_NAME = "name";
    public static final String COLUMN_PERSON_AGE = "age";
    public static final String COLUMN_PERSON_OCCUPATION = "occupation";
    public static final String COLUMN_PERSON_IMAGE = "image";
    public static final String COLUMN_PERSON_LATITUDE= "latitude";
    public static final String COLUMN_PERSON_LONGITUDE= "longitude";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PERSON_NAME + " TEXT NOT NULL, " +
                COLUMN_PERSON_AGE + " NUMBER NOT NULL, " +
                COLUMN_PERSON_OCCUPATION + " TEXT NOT NULL, " +
                COLUMN_PERSON_IMAGE + " BLOB NOT NULL, " +
                COLUMN_PERSON_LATITUDE + "NUMBER, " +
                COLUMN_PERSON_LONGITUDE + "NUMBER);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSON_NAME, person.getName());
        values.put(COLUMN_PERSON_AGE, person.getAge());
        values.put(COLUMN_PERSON_OCCUPATION, person.getOccupation());
        values.put(COLUMN_PERSON_IMAGE, person.getImage());

        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Person> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        List<Person> personList = new LinkedList<>();
        Person person;
        while (cursor.moveToNext()) {
            person = new Person();
            person.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
            person.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_NAME)));
            person.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_AGE)));
            person.setOccupation(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_OCCUPATION)));
            person.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGE)));
            //person.setLatitude(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_LATITUDE)));
            //person.setLatitude(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_LONGITUDE)));
            personList.add(person);
        }

        return personList;
    }

    public Person getPerson(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id=" + id;
        Cursor cursor = db.rawQuery(query, null);

        Person receivedPerson = new Person();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedPerson.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_NAME)));
            receivedPerson.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_AGE)));
            receivedPerson.setOccupation(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_OCCUPATION)));
            receivedPerson.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGE)));
            //receivedPerson.setLatitude(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_LATITUDE)));
            //receivedPerson.setLongitude(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_LONGITUDE)));
        }

        return receivedPerson;
    }

    public void updatePerson(long id, Person updatedperson) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  " + TABLE_NAME + " SET name ='" + updatedperson.getName()
                + "', age ='" + updatedperson.getAge() + "', occupation ='" + updatedperson.getOccupation()
                + "', image ='" + updatedperson.getImage() + "'  WHERE _id='" + id + "'");

    }
}
