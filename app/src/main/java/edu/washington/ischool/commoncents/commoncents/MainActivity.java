package edu.washington.ischool.commoncents.commoncents;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import edu.washington.ischool.commoncents.commoncents.controllers.MainPagerController;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        MainPagerController.setupViewPager(this, viewPager, tabLayout);
    }
}
