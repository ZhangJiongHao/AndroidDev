package com.bobomee.android.providerdemo.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created on 16/6/25.上午11:47.
 *
 * @author bobomee.
 *         wbwjx115@gmail.com
 */
public interface IProivderMetaData {
  // 定义外部访问的Authority
  String AUTHORITY = "com.example.provider.College";
  // 数据库名称
  String DATABASE_NAME = "College";
  // 数据库版本号
  int DATABASE_VERSION = 1;

  interface StudentTableMetaData extends BaseColumns {
    // 表名
    String STUDENTS_TABLE_NAME = "students";
    // 外部程序访问本表的uri地址
    String URL = "content://" + AUTHORITY + "/students";
    Uri CONTENT_URI = Uri.parse(URL);

    class Student {
      // 数据库字段
      public static String NAME = "name";
      public static String GRADE = "grade";

      public String name;
      public String grade;

      public Student() {
        super();
      }

      public Student(String grade, String name) {
        this.grade = grade;
        this.name = name;
      }

      @Override public String toString() {
        return "Student{" +
            "grade='" + grade + '\'' +
            ", name='" + name + '\'' +
            '}';
      }
    }

    // 创建数据库
    String CREATE_DB_TABLE = " CREATE TABLE " + STUDENTS_TABLE_NAME +
        " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        " name TEXT NOT NULL, " +
        " grade TEXT NOT NULL);";
    //默认排序
    String SORT_ORDER = "_id desc";
    //得到students表中的所有记录
    String CONTENT_LIST = "vnd.android.cursor.dir/vnd.example.students";
    //得到一个表信息
    String CONTENT_ITEM = "vnd.android.cursor.item/vnd.example.students";
  }
}
