package com.example.brewclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.provider.BaseColumns;

public class TeaData extends SQLiteOpenHelper {
  private static final String DATABASE_NAME = "teas.db";
  private static final int DATABASE_VERSION = 1;
  
  private static final String TABLE_NAME = "teas";

  private static final String _ID = BaseColumns._ID;
  private static final String NAME = "name";
  private static final String BREW_TIME = "brew_time";
  
  public TeaData(Context context, String name, CursorFactory factory, int version) {
    super(context, DATABASE_NAME, factory, DATABASE_VERSION);
  }

  /** Overrides **/
  
  /* (non-Javadoc)
   * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
   */
  @Override
  public void onCreate(SQLiteDatabase db) {
    // CREATE TABLE teas (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, brew_time INTEGER);
    String sql = 
      "CREATE TABLE " + TABLE_NAME + " (" 
        + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + NAME + " TEXT NOT NULL, "
        + BREW_TIME + " INTEGER"
        + ");";
    
    db.execSQL(sql);
    
    // Add some default tea data! (Adjust to your preference :)
    insert(new Tea("Earl Grey", 3)); 
    insert(new Tea("Assam", 3));
    insert(new Tea("Jasmine Green", 1));
    insert(new Tea("Darjeeling", 2));
  }

  /* (non-Javadoc)
   * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // TODO Auto-generated method stub
  }

  /**
   * Insert a new tea record into the database.
   * 
   * @param name The name of the tea to add
   * @param brewTime The time (in minutes) the tea should be brewed.
   * @throws SQLException
   */
  public void insert(Tea tea) {
    SQLiteDatabase db = getWritableDatabase();
    
    ContentValues values = new ContentValues();
    values.put(NAME, tea.name);
    values.put(BREW_TIME, tea.brewTime);
    
    db.insertOrThrow(TABLE_NAME, null, values);
  }
}