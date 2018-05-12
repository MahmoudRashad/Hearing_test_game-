package com.example.ahmed.hearingexpretsystem.Sql;

/**
 * Created by Ahmed on 5/10/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.ahmed.hearingexpretsystem.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

//    public DatabaseHelper context=DatabaseHelper.this;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Hearing_Database";

    // DOCTOR table name
    private static final String TABLE_DOCTOR = "doctor";


    // DOCTOR Table Columns names
    private static final String COLUMN_DOCTOR_ID = "doctor_id";
    private static final String COLUMN_DOCTOR_NAME = "doctor_name";
    private static final String COLUMN_DOCTOR_EMAIL = "doctor_email";
    private static final String COLUMN_DOCTOR_PASSWORD = "doctor_password";

    // create table DOCTOR
    private String CREATE_DOCTOR_TABLE = "CREATE TABLE " + TABLE_DOCTOR + "("
            + COLUMN_DOCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DOCTOR_NAME + " TEXT,"
            + COLUMN_DOCTOR_EMAIL + " TEXT," + COLUMN_DOCTOR_PASSWORD + " TEXT" + ")";

    // drop table sql query DOCTOR
    private String DROP_DOCTOR_TABLE = "DROP TABLE IF EXISTS " + TABLE_DOCTOR;





    // Patient table name
    private static final String TABLE_PATIENT = "patient";

    // patient Table Columns names
    private static final String COLUMN_PATIENT_ID = "patient_id";
    private static final String COLUMN_PATIENT_NAME = "patient_name";
    private static final String COLUMN_PATIENT_AGE = "patient_age";
    private static final String COLUMN_MENDER_DOCTOR = "mender_doctor";

    // create table patient
    private String CREATE_Patient_TABLE = "CREATE TABLE " + TABLE_PATIENT + "("
            + COLUMN_PATIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PATIENT_NAME + " TEXT,"
            + COLUMN_PATIENT_AGE + " INTEGER," + COLUMN_MENDER_DOCTOR + " TEXT" + ")";

    // drop table sql query
    private String DROP_PATIENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PATIENT;




    // test table name
    private static final String TABLE_TEST = "test";

    // patient Table Columns names
    private static final String COLUMN_TEST_ID = "test_id";
    private static final String COLUMN_TEST_PATIENT_ID = "patient_id";
    private static final String COLUMN_TEST_DOCTOR_ID = "doctor_id";
    private static final String COLUMN_TETS_NOISE_TYPE = "noise_type";
    private static final String COLUMN_TETS_PATIENT_DEGREE = "patient_degree";

    // create table patient
    private String CREATE_TEST_TABLE = "CREATE TABLE " + TABLE_TEST + "("
            + COLUMN_TEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TEST_PATIENT_ID + " INTEGER,"
            + COLUMN_TEST_DOCTOR_ID + " INTEGER," +COLUMN_TETS_PATIENT_DEGREE + " TEXT," + COLUMN_TETS_NOISE_TYPE + " TEXT" + ")";

    // drop table sql query
    private String DROP_TEST_TABLE = "DROP TABLE IF EXISTS " + TABLE_TEST;




    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        instance=new DatabaseHelper(context);
    }

    private static DatabaseHelper singleton;



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
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_DOCTOR_TABLE);
        db.execSQL(DROP_PATIENT_TABLE);
        db.execSQL(DROP_TEST_TABLE);
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
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_DOCTOR_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_DOCTOR_EMAIL + " = ?" + " AND " + COLUMN_DOCTOR_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

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
}
