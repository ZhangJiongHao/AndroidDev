package com.bobomee.android.providerdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.bobomee.android.providerdemo.data.DataHelper;
import com.bobomee.android.providerdemo.provider.IProivderMetaData;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private DataHelper mDataHelper;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mDataHelper = new DataHelper(this);
  }

  public void onClickAddName(View view) {
    // Add a new student record

    String name = ((EditText) findViewById(R.id.editText2)).getText().toString();

    String grade = ((EditText) findViewById(R.id.editText3)).getText().toString();
    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(grade)) {

      IProivderMetaData.StudentTableMetaData.Student student =
          new IProivderMetaData.StudentTableMetaData.Student(grade, name);
      Uri uri = mDataHelper.insert(student);

      if (uri != null) {
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
      }
    } else {
      Toast.makeText(getBaseContext(), "name and grade must not be null", Toast.LENGTH_LONG).show();
    }
  }

  public void onClickRetrieveStudents(View view) {


    List<IProivderMetaData.StudentTableMetaData.Student> students = mDataHelper.queryAll();

    new AlertDialog.Builder(this).setMessage(students.toString()).show();
  }

  public void onClickDeleteStudents(View view) {
    long row = 0;
    //删除所有
    row = mDataHelper.clearAll();
    if (row != 0) {
      Toast.makeText(getBaseContext(), String.valueOf(row), Toast.LENGTH_LONG).show();
    }
  }
}