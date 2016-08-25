package com.bobomee.android.designpatterns.stragety;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.bobomee.android.designpatterns.BaseActivity;

/**
 * Created on 16/8/16.下午11:19.
 *
 * @author bobomee.
 * @description:
 */
public class TranficCal extends BaseActivity {

  private Calculate mCalculate;

  public void setCalculate(Calculate calculate) {
    mCalculate = calculate;
  }

  public int calculate(int km) {
    return mCalculate.calculate(km);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Button button = new Button(this);
    button.setText("Click");

    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, 150);

    addContentView(button,params);

    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        TranficCal tranficCal = new TranficCal();

        tranficCal.setCalculate(new BusCal());

        Toast.makeText(TranficCal.this, "BUS -- >" + tranficCal.calculate(3), Toast.LENGTH_SHORT).show();
      }
    });
  }
}
