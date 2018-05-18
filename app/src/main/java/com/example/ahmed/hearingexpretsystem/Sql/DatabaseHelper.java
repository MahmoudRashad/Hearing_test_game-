package com.example.ahmed.hearingexpretsystem.Sql;

/**
 * Created by Ahmed on 5/10/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.ahmed.hearingexpretsystem.Utils.KeyVal;
import com.example.ahmed.hearingexpretsystem.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

//    public DatabaseHelper context=DatabaseHelper.this;

    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "Hearing_Database";

    // DOCTOR table name
    public static final String TABLE_DOCTOR = "doctor";


    // DOCTOR Table Columns names
    public static final String COLUMN_DOCTOR_ID = "doctor_id";
    public static final String COLUMN_DOCTOR_NAME = "doctor_name";
    public static final String COLUMN_DOCTOR_EMAIL = "doctor_email";
    public static final String COLUMN_DOCTOR_PASSWORD = "doctor_password";

    // create table DOCTOR
    public String CREATE_DOCTOR_TABLE = "CREATE TABLE " + TABLE_DOCTOR + "("
            + COLUMN_DOCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DOCTOR_NAME + " TEXT,"
            + COLUMN_DOCTOR_EMAIL + " TEXT," + COLUMN_DOCTOR_PASSWORD + " TEXT" + ")";

    // drop table sql query DOCTOR
    public String DROP_DOCTOR_TABLE = "DROP TABLE IF EXISTS " + TABLE_DOCTOR;





    // Patient table Patient
    public static final String TABLE_PATIENT = "patient";

    // patient Table Columns names
    public static final String COLUMN_PATIENT_ID = "patient_id";
    public static final String COLUMN_PATIENT_NAME = "patient_name";
    public static final String COLUMN_PATIENT_AGE = "patient_age";
    public static final String COLUMN_MENDER_DOCTOR = "mender_doctor";

    // create table patient
    public String CREATE_Patient_TABLE = "CREATE TABLE " + TABLE_PATIENT + "("
            + COLUMN_PATIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PATIENT_NAME + " TEXT,"
            + COLUMN_PATIENT_AGE + " INTEGER," + COLUMN_MENDER_DOCTOR + " TEXT" + ")";

    // drop table sql query
    public String DROP_PATIENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PATIENT;




    // test table name
    public static final String TABLE_TEST = "test";

    // patient Table Columns names
    public static final String COLUMN_TEST_ID = "test_id";
    public static final String COLUMN_TEST_PATIENT_ID = "patient_id";
    public static final String COLUMN_TEST_DOCTOR_ID = "doctor_id";
    public static final String COLUMN_TETS_NOISE_TYPE = "noise_type";
    public static final String COLUMN_TETS_PATIENT_DEGREE = "patient_degree";

    // create table patient
    public String CREATE_TEST_TABLE = "CREATE TABLE " + TABLE_TEST + "("
            + COLUMN_TEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TEST_PATIENT_ID + " INTEGER,"
            + COLUMN_TEST_DOCTOR_ID + " INTEGER," +COLUMN_TETS_PATIENT_DEGREE + " TEXT," + COLUMN_TETS_NOISE_TYPE + " TEXT" + ")";

    // drop table sql query
    public String DROP_TEST_TABLE = "DROP TABLE IF EXISTS " + TABLE_TEST;




    // test table Signals
    public static final String TABLE_SIGNALS = "signals";

    // patient Table Columns names
    public static final String COLUMN_SIGNALS_ID = "signals_id";
    public static final String COLUMN_SIGNALS_TEXT = "signals_text";
    public static final String COLUMN_SIGNALS_TEST_ID = "signals_test_id";
    public static final String COLUMN_SIGNALS_IMAGE_ID = "signals_test_id";


    // create table patient
    public String CREATE_SIGNALS_TABLE = "CREATE TABLE " + TABLE_SIGNALS + "("
            + COLUMN_SIGNALS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SIGNALS_TEXT + " TEXT," + COLUMN_SIGNALS_TEST_ID + " INTEGER" +COLUMN_SIGNALS_IMAGE_ID + " INTEGER" + ")";

    // drop table sql query
    public String DROP_SIGNALS_TABLE = "DROP TABLE IF EXISTS " + TABLE_SIGNALS;





    // test table imaged
    public static final String TABLE_IMAGES = "images";

    // patient Table Columns names
    public static final String COLUMN_IMAGES_ID = "images_id";
    public static final String COLUMN_IMAGES_TEXT = "images_text";


    // create table patient
    public String CREATE_IMAGES_TABLE = "CREATE TABLE " + TABLE_IMAGES + "("
            + COLUMN_IMAGES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_IMAGES_TEXT + " TEXT" + ")";

    // drop table sql query
    public String DROP_IMAGES_TABLE = "DROP TABLE IF EXISTS " + TABLE_IMAGES;




    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        instance=new DatabaseHelper(context);
    }

    public static DatabaseHelper singleton;



    public static DatabaseHelper init(Context context) {

            singleton= new DatabaseHelper(context);
            return  singleton;

    }

    public static DatabaseHelper getInstance()   {

        return singleton;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DOCTOR_TABLE);
        db.execSQL(CREATE_Patient_TABLE);
        db.execSQL(CREATE_TEST_TABLE);
        db.execSQL(CREATE_SIGNALS_TABLE);
        db.execSQL(CREATE_IMAGES_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_DOCTOR_TABLE);
        db.execSQL(DROP_PATIENT_TABLE);
        db.execSQL(DROP_TEST_TABLE);
        db.execSQL(DROP_SIGNALS_TABLE);
        db.execSQL(DROP_IMAGES_TABLE);
        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTOR_NAME, user.getName());
        values.put(COLUMN_DOCTOR_EMAIL, user.getEmail());
        values.put(COLUMN_DOCTOR_PASSWORD, user.getPassword());

        // Inserting Row
        long v =db.insert(TABLE_DOCTOR, null, values);
        db.close();
    }

    /**
     * This method is to fetch all DOCTOR and return the list of DOCTOR records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_DOCTOR_ID,
                COLUMN_DOCTOR_EMAIL,
                COLUMN_DOCTOR_NAME,
                COLUMN_DOCTOR_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_DOCTOR_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_DOCTOR, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTOR_NAME, user.getName());
        values.put(COLUMN_DOCTOR_EMAIL, user.getEmail());
        values.put(COLUMN_DOCTOR_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_DOCTOR, values, COLUMN_DOCTOR_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_DOCTOR, COLUMN_DOCTOR_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_DOCTOR_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_DOCTOR_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_DOCTOR, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param name
     * @param password
     * @return true/false
     */
    public boolean checkUser(String name, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_DOCTOR_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_DOCTOR_NAME+ " = ?" + " AND " + COLUMN_DOCTOR_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {name, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_DOCTOR, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    public List<Object> select(String queryString) {

            SQLiteDatabase database = this.getReadableDatabase();
            List<Object> list = new ArrayList<Object>();
            Cursor cursor = database.rawQuery(queryString, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        ContentValues content = new ContentValues();
                        String[] columnNames = cursor.getColumnNames();
                        for (String columnName : columnNames) {
                            content.put(columnName, cursor.getString(cursor
                                    .getColumnIndex(columnName)));
                        }
                        list.add(content);
                    } while (cursor.moveToNext());
                }
            }try {
                cursor.close();

            }catch (Exception e){
                Log.d("select_exception", e.getMessage());
                e.printStackTrace();
            }
            try {
                database.close();

            }catch (Exception e)
            {
                Log.d( "close  database ",e.getMessage());
                e.printStackTrace();
            }
            return list;


    }


}
