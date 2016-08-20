package com.bobomee.android.designpatterns.command;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created on 16/8/19.下午10:42.
 *
 * @author bobomee.
 * @description:
 */
public class CommandActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Button button = new Button(this);
    button.setText("Click");

    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, 150);

    addContentView(button, params);

    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //创建接收者对象那个
        Receiver receiver = new Receiver();
        //根据接收者对象构建命令对象
        Command command = new ConcreteCommand(receiver);
        //根据命令对象那个构建请求者对象
        Invoker invoker = new Invoker(command);
        //执行请求方法
        invoker.action();
      }
    });
  }
}
