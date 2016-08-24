package com.bobomee.blogdemos.motion_event;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created on 16/8/21.下午9:19.
 *
 * @author bobomee.
 * @description:
 */
public class MyLayout extends LinearLayout {
  public MyLayout(Context context) {
    this(context, null);
  }

  public MyLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public MyLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    return true;
  }

  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    return super.dispatchTouchEvent(ev);
  }
}
