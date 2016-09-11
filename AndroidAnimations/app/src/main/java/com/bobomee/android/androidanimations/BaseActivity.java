package com.bobomee.android.androidanimations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created on 16/9/7.下午11:04.
 *
 * @author bobomee.
 * @description:
 */
public class BaseActivity
extends AppCompatActivity{
  protected BaseActivity mActivity;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivity = this;
  }
}
