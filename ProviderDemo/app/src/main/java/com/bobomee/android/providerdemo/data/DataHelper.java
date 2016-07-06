package com.bobomee.android.providerdemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.bobomee.android.providerdemo.provider.IProivderMetaData;
import com.bobomee.android.providerdemo.provider.StudentsProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16/7/6.下午10:41.
 *
 * @author bobomee.
 *         wbwjx115@gmail.com
 */
public class DataHelper extends BaseDataHelper
    implements DataInterface<IProivderMetaData.StudentTableMetaData.Student>, IProivderMetaData {
  public DataHelper(Context context) {
    super(context);
  }

  @Override protected Uri getContentUri() {
    return StudentTableMetaData.CONTENT_URI;
  }

  @Override protected String getTableName() {
    return StudentTableMetaData.STUDENTS_TABLE_NAME;
  }

  @Override public StudentTableMetaData.Student query(String id) {
    StudentTableMetaData.Student student = new StudentTableMetaData.Student();
    Cursor cursor;
    cursor = query(null, StudentTableMetaData._ID + "= ?", new String[] {
        id
    }, null);
    if (cursor.moveToFirst()) {
      String name = cursor.getString(cursor.getColumnIndex(StudentsProvider.Student.NAME));
      String grade = cursor.getString(cursor.getColumnIndex(StudentsProvider.Student.GRADE));
      student.name = name;
      student.grade = grade;
    }
    cursor.close();
    return student;
  }

  @Override public int clearAll() {
    return delete(null, null);
  }

  @Override public ContentValues getContentValues(StudentTableMetaData.Student data) {

    ContentValues values = new ContentValues();
    values.put(StudentTableMetaData.Student.NAME, data.name);
    values.put(StudentTableMetaData.Student.GRADE, data.grade);
    return values;
  }

  @Override public Uri insert(StudentTableMetaData.Student student) {
    return insert(getContentValues(student));
  }

  @Override public List<StudentTableMetaData.Student> queryAll() {
    List<StudentTableMetaData.Student> result = new ArrayList<>();
    Cursor cursor;
    cursor = query(null, null, null, "name");

    if (cursor.moveToFirst()) {
      do {
        String name = cursor.getString(cursor.getColumnIndex(StudentsProvider.Student.NAME));
        String grade = cursor.getString(cursor.getColumnIndex(StudentsProvider.Student.GRADE));

        result.add(new StudentTableMetaData.Student(grade, name));
      } while (cursor.moveToNext());

      cursor.close();
    }
    return result;
  }

  @Override public int bulkInsert(List<StudentTableMetaData.Student> listData) {
    List<ContentValues> contentValuesList = new ArrayList<>();
    for (StudentTableMetaData.Student student : listData) {
      ContentValues contentValues = getContentValues(student);
      contentValuesList.add(contentValues);
    }
    ContentValues[] valueArray = new ContentValues[contentValuesList.size()];
    return bulkInsert(contentValuesList.toArray(valueArray));
  }
}
