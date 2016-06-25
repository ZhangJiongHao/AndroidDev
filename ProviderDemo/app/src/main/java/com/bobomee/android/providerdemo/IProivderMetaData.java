package com.bobomee.android.providerdemo;

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

    // 数据库字段
    String NAME = "name";
    String GRADE = "grade";

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
