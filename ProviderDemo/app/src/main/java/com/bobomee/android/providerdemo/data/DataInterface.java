package com.bobomee.android.providerdemo.data;

import android.content.ContentValues;
import android.net.Uri;
import java.util.List;

/**
 * Created on 16/7/6.下午10:32.
 *
 * @author bobomee.
 *         wbwjx115@gmail.com
 */
public interface DataInterface<T> {
  /**
   * 查询某一条记录
   *
   * @param id ID
   * @return T 返回查询到得第一条记录
   */
  public T query(String id);

  /**
   * 删除所有数据
   *
   * @return count 本次操作的条数
   */
  public int clearAll();

  /**
   * 批量插入数据
   *
   * @param listData 需要插入的数据列表
   */
  public int bulkInsert(List<T> listData);

  public ContentValues getContentValues(T data);

  /**
   * 插入一条数据
   * @param t
   * @return
   */
  public Uri insert(T t);

  /**
   * 查询所有数据
   */
  public List<T> queryAll();
}
