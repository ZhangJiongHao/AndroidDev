package com.bobomee.blogdemos.view.arcmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bobomee.blogdemos.base.BaseActivity;

/**
 * @author：BoBoMEe Created at 2016/1/4.
 */
public class ArcMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArcMenuFragment arcMenuFragment = (ArcMenuFragment) Fragment.instantiate(this, ArcMenuFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, arcMenuFragment).commitAllowingStateLoss();
    }
}
