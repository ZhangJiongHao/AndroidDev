package com.bobomee.android.androidanimations.layout;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bobomee.android.androidanimations.BaseActivity;
import com.bobomee.android.androidanimations.R;

/**
 * Created on 16/9/11.下午2:06.
 *
 * @author bobomee.
 * @description
 */
public class TransitionBActivity extends BaseActivity {

  @BindView(R.id.bt_click) Button mBtClick;

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transition_b);
    ButterKnife.bind(this);

    getWindow().setEnterTransition(new Explode().setDuration(2000));
    getWindow().setExitTransition(new Explode().setDuration(2000));
  }

  @OnClick(R.id.bt_click) public void setBtClick() {
    finish();
  }
}
