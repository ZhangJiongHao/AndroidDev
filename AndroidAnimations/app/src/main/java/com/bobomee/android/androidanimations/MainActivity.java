package com.bobomee.android.androidanimations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bobomee.android.androidanimations.drawable.DrawableAnimationActivity;
import com.bobomee.android.androidanimations.layout.GridLayoutAnimationActivity;
import com.bobomee.android.androidanimations.layout.LayoutAnimationActivity;
import com.bobomee.android.androidanimations.layout.LayoutChangesActivity;
import com.bobomee.android.androidanimations.contenttransition.TransitionActivity;
import com.bobomee.android.androidanimations.property.AnimatorActivity;
import com.bobomee.android.androidanimations.view.InterpolatorActivity;
import com.bobomee.android.androidanimations.view.ViewAnimationActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

  List<String> mStrings = new ArrayList<>(Arrays.asList(new String[] {
      "LayoutAnimation", "GridLayoutAnimation", "LayoutChangeAnimation", "ViewAnimation","Interpolator"
      ,"DrawableAnimation","PropertyAnimation","ActivityTransition"
  }));
  List<Class> mClasses = new ArrayList<>(Arrays.asList(new Class[] {
      LayoutAnimationActivity.class, GridLayoutAnimationActivity.class, LayoutChangesActivity.class,
      ViewAnimationActivity.class, InterpolatorActivity.class, DrawableAnimationActivity.class,
      AnimatorActivity.class, TransitionActivity.class
  }));

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initView();
  }

  private void initView() {

    mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    mRecyclerView.addItemDecoration(new RecyclerDecoration(mActivity, OrientationHelper.VERTICAL));

    mRecyclerView.setAdapter(
        new CommonAdapter<String>(mActivity, android.R.layout.simple_list_item_1, mStrings) {
          @Override protected void convert(ViewHolder holder, String _s, final int position) {

            holder.setText(android.R.id.text1, _s);
            holder.setOnClickListener(android.R.id.text1, new View.OnClickListener() {
              @Override public void onClick(View _view) {
                startActivity(new Intent(mActivity, mClasses.get(position)));
              }
            });
          }
        });
  }
}
