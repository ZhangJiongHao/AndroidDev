package com.bobomee.android.designpatterns.observer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created on 16/8/20.下午11:45.
 *
 * @author bobomee.
 * @description:
 */
public class ObservableActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Button button = new Button(this);
    button.setText("Click");

    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, 150);

    addContentView(button, params);


    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ISubject subject = new ConcreteSubject();

        IObserver observer1 = new ConcreteObserver1();
        IObserver observer2 = new ConcreteObserver2();

        //regist observer
        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        //update observer
        subject.notify("notify all");

      }
    });
  }
}
