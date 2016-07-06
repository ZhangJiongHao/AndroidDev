package com.bobomee.android.providerdemo.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 16/6/25.下午3:31.
 *
 * @author bobomee.
 *         wbwjx115@gmail.com
 */
public class DbHelper extends SQLiteOpenHelper
    implements IProivderMetaData, IProivderMetaData.StudentTableMetaData {
  public DbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_DB_TABLE);
  }

  @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME);
    onCreate(sqLiteDatabase);
  }
}
