package com.bobomee.android.designpatterns.state;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.bobomee.android.designpatterns.BaseActivity;

/**
 * Created on 16/8/17.下午10:30.
 *
 * @author bobomee.
 * @description:
 */
public class StateActivity extends BaseActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Button button = new Button(this);
    button.setText("Click");

    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, 150);

    addContentView(button, params);

    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        UserContext.getUserContext().doAction(StateActivity.this);
        UserState userState = UserContext.getUserContext().getUserState();
        boolean equals = userState.getClass().getName().equals(LoginedState.class.getName());
        UserContext.getUserContext().setUserState(equals ? new LogoutState() : new LoginedState());
      }
    });
  }
}
