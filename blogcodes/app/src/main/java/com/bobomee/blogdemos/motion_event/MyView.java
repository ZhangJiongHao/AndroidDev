package com.bobomee.blogdemos.motion_event;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.bobomee.android.common.util.ToastUtil;

/**
 * Created on 16/8/21.下午9:20.
 *
 * @author bobomee.
 * @description:
 */
public class MyView extends View {
  public MyView(Context context) {
    this(context, null);
  }

  public MyView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init() {

    setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        ToastUtil.show(getContext(), "MyView -- onClick");
      }
    });

    setOnTouchListener(new OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        ToastUtil.show(getContext(), "MyView -- onTouch");
        return false;
      }
    });
  }

  @Override public boolean onTouchEvent(MotionEvent event) {


    MotionEvent.ACTION_DOWN

    return super.onTouchEvent(event);

  }
}
