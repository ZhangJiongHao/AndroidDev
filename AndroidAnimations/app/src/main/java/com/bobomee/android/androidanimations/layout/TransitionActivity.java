package com.bobomee.android.androidanimations.layout;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bobomee.android.androidanimations.BaseActivity;
import com.bobomee.android.androidanimations.R;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

/**
 * Created on 16/9/11.下午2:06.
 *
 * @author bobomee.
 * @description
 */
public class TransitionActivity extends BaseActivity {

  @BindView(R.id.transition_to) Button mTransitionTo;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transition);
    ButterKnife.bind(this);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) @OnClick(R.id.transition_to)
  public void setTransitionTo(){
    startActivity(new Intent(this,TransitionBActivity.class), makeSceneTransitionAnimation(this).toBundle());
  }
}
