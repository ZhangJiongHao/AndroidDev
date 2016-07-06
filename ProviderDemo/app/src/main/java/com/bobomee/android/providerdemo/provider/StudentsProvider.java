package com.bobomee.android.providerdemo.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;

public class StudentsProvider extends ContentProvider
    implements IProivderMetaData, IProivderMetaData.StudentTableMetaData {

  private DbHelper dbHelper;
  private SQLiteDatabase db;

  static final int STUDENTS = 1;
  static final int STUDENT_ID = 2;

  static final UriMatcher uriMatcher;

  static {
    uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    uriMatcher.addURI(AUTHORITY, "students", STUDENTS);
    uriMatcher.addURI(AUTHORITY, "students/#", STUDENT_ID);
  }

  public StudentsProvider() {
  }

  @Override public int delete(Uri uri, String selection, String[] selectionArgs) {
    int count = 0;

    switch (uriMatcher.match(uri)) {
      case STUDENTS:
        count = db.delete(STUDENTS_TABLE_NAME, selection, selectionArgs);
        break;

      case STUDENT_ID:
        String id = uri.getPathSegments().get(1);
        count = db.delete(STUDENTS_TABLE_NAME, _ID + " = " + id +
            (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
        break;

      default:
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    getContext().getContentResolver().notifyChange(uri, null);
    return count;
  }

  @Override public String getType(Uri uri) {
    switch (uriMatcher.match(uri)) {
      /**
       * Get all student records
       */
      case STUDENTS:
        return CONTENT_LIST;

      /**
       * Get a particular student
       */
      case STUDENT_ID:
        return CONTENT_ITEM;

      default:
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }
  }

  @Override public Uri insert(Uri uri, ContentValues values) {
    switch (uriMatcher.match(uri)) {
      case STUDENTS: {
        /**
         * Add a new student record
         */
        long rowID = db.insert(STUDENTS_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */

        if (rowID > 0) {
          Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
          getContext().getContentResolver().notifyChange(_uri, null);
          return _uri;
        }
      }
      default:
        //不能识别uri
        throw new IllegalArgumentException("This is a unKnow Uri" + uri.toString());
    }
  }

  public DbHelper getDbHelper() {
    return dbHelper;
  }

  @Override public boolean onCreate() {
    dbHelper = new DbHelper(getContext());
    /**
     * Create a write able database which will trigger its
     * creation if it doesn't already exist.
     */
    db = dbHelper.getWritableDatabase();
    return (dbHelper == null) ? false : true;
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
    qb.setTables(STUDENTS_TABLE_NAME);

    switch (uriMatcher.match(uri)) {
      case STUDENTS:
        qb.setProjectionMap(new HashMap<String, String>());
        break;

      case STUDENT_ID:
        qb.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
        break;

      default:
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    if (sortOrder == null || sortOrder == "") {
      /**
       * By default sort on student names
       */
      sortOrder = SORT_ORDER;
    }
    Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);

    /**
     * register to watch a content URI for changes
     */
    c.setNotificationUri(getContext().getContentResolver(), uri);
    return c;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    int count = 0;

    switch (uriMatcher.match(uri)) {
      case STUDENTS:
        count = db.update(STUDENTS_TABLE_NAME, values, selection, selectionArgs);
        break;

      case STUDENT_ID:
        count = db.update(STUDENTS_TABLE_NAME, values, _ID + " = " + uri.getPathSegments().get(1) +
            (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
        break;

      default:
        throw new IllegalArgumentException("Unknown URI " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return count;
  }
}
