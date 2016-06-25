package com.bobomee.android.providerdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onClickAddName(View view) {
    // Add a new student record
    ContentValues values = new ContentValues();

    String name = ((EditText) findViewById(R.id.editText2)).getText().toString();

    String grade = ((EditText) findViewById(R.id.editText3)).getText().toString();
    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(grade)) {
      values.put(StudentsProvider.NAME, name);
      values.put(StudentsProvider.GRADE, grade);
      Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);

      if (uri != null) {
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
      }
    } else {
      Toast.makeText(getBaseContext(), "name and grade must not be null", Toast.LENGTH_LONG).show();
    }
  }

  public void onClickRetrieveStudents(View view) {

    Cursor c = managedQuery(StudentsProvider.CONTENT_URI, null, null, null, "name");

    StringBuilder builder = new StringBuilder();
    if (c.moveToFirst()) {
      do {
        builder.append(c.getString(c.getColumnIndex(StudentsProvider._ID)))
            .append(". name =  ")
            .append(c.getString(c.getColumnIndex(StudentsProvider.NAME)))
            .append(", grade = ")
            .append(c.getString(c.getColumnIndex(StudentsProvider.GRADE)))
            .append("\n");
      } while (c.moveToNext());
    }

    new AlertDialog.Builder(this).setMessage(builder.toString()).show();
  }

  public void onClickDeleteStudents(View view) {
    long row = 0;
    //删除所有
    row = getContentResolver().delete(IProivderMetaData.StudentTableMetaData.CONTENT_URI, null, null);
    if (row != 0) {
      Toast.makeText(getBaseContext(), String.valueOf(row), Toast.LENGTH_LONG).show();
    }
  }
}