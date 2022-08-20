package com.kkokate.shoppingapp.service.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDbConnection extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SHOPPING_APP_TEST";
    private static final int DATABASE_VERSION = 1;
    public static BaseDbConnection dbHelper;
    private final Context context;

    public BaseDbConnection(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    /**
     * Init the database
     * @param context
     */
    public static void initialize(Context context)
    {
        if(dbHelper == null)
        {
            dbHelper= new BaseDbConnection(context);
        }
    }

    /**
     * checks whether database get initialized or not?
     * @return
     */
    public static BaseDbConnection getInstance()throws Exception
    {
        if(dbHelper == null)
        {
            throw new Exception("Database not initialized. Please Initialize it BaseDbConnection.initialize(Context context)");
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.createTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void createTables(SQLiteDatabase db) {
        //toDo create required tables

    }

}
