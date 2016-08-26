package com.bobomee.android.designpatterns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bobomee.android.designpatterns.adapter.AdapterActivity;
import com.bobomee.android.designpatterns.chain.ChainActivity;
import com.bobomee.android.designpatterns.command.CommandActivity;
import com.bobomee.android.designpatterns.iterator.IteratorActivity;
import com.bobomee.android.designpatterns.memento.NoteActivity;
import com.bobomee.android.designpatterns.observer.ObservableActivity;
import com.bobomee.android.designpatterns.prototype.CloneActivity;
import com.bobomee.android.designpatterns.state.StateActivity;
import com.bobomee.android.designpatterns.stragety.TranficCal;
import com.bobomee.android.designpatterns.template.TemplateActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends BaseActivity {

  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
  private Class[] mClasses;
  private String[] mStrings;
  private MainActivity mMainActivity;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    mMainActivity = this;

    initDatas();
    initViews();
  }

  private void initViews() {

    mRecyclerView.setLayoutManager(new LinearLayoutManager(mMainActivity));
    mRecyclerView.setAdapter(new CommonAdapter<String>(mMainActivity, android.R.layout.simple_list_item_1,
        new ArrayList<String>(Arrays.asList(mStrings))) {
      @Override protected void convert(ViewHolder holder, String s, final int position) {

        TextView textView = (TextView) holder.itemView.findViewById(android.R.id.text1);
        textView.setText(s);

        holder.setOnClickListener(android.R.id.text1, new View.OnClickListener() {
          @Override public void onClick(View v) {
            startActivity(new Intent(mMainActivity,mClasses[position]));
          }
        });
      }
    });
  }

  private void initDatas() {

    mClasses = new Class[] {
        AdapterActivity.class, CloneActivity.class, TranficCal.class, StateActivity.class, ChainActivity.class,
        CommandActivity.class, NoteActivity.class, ObservableActivity.class, IteratorActivity.class,
        TemplateActivity.class
    };
    mStrings = new String[] {
        "Adapter模式使用实例", "原型模式使用实例", "策略模式使用实例", "状态模式使用实例", "责任链模式实例", "命令模式实例", "备忘录模式实例",
        "观察者模式实例", "迭代器模式使用实例", "模板方法设计模式"
    };
  }
}
