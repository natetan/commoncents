package edu.washington.ischool.commoncents.commoncents.controllers;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;

import edu.washington.ischool.commoncents.commoncents.fragments.EventsListFragment;
import edu.washington.ischool.commoncents.commoncents.fragments.FriendsListFragment;
import edu.washington.ischool.commoncents.commoncents.fragments.SettingsFragment;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * Created by keegomyneego on 3/6/17.
 */
public class MainPagerController {

    static final String TAG = "MainPagerController";

    //----------------------------------------------------------------------------------------------
    // Client Interface
    //----------------------------------------------------------------------------------------------

    public static void setupViewPager(AppCompatActivity appCompatActivity, ViewPager viewPager, TabLayout tabLayout) {

        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        TabInfo[] tabInfoList = getTabInfoList();

        // Create each tab based on the info in tabInfoList
        for (TabInfo tabInfo : tabInfoList) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(tabInfo.tabName);
            tabLayout.addTab(tab);
        }

        // Customize tab layout
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Connect everything
        tabLayout.addOnTabSelectedListener(getTabSelectedListener(appCompatActivity, viewPager, tabInfoList, tabLayout));
        viewPager.setAdapter(getAdapter(fragmentManager, tabInfoList));
        viewPager.addOnPageChangeListener(getPageChangeListener(tabLayout));

        // Apply new theme
        updateTheme(appCompatActivity, tabLayout, tabInfoList[0]);
    }

    //----------------------------------------------------------------------------------------------
    // Tab Info
    //----------------------------------------------------------------------------------------------

    private static TabInfo[] getTabInfoList() {

        return new TabInfo[]{
                new TabInfo("Events", R.style.EventsTheme, EventsListFragment.class),
                new TabInfo("Friends", R.style.FriendsTheme, FriendsListFragment.class),
                new TabInfo("Settings", R.style.SettingsTheme, SettingsFragment.class)
        };
    }

    private static class TabInfo {
        String tabName;
        int theme;
        Class<? extends Fragment> tabFragment;

        private TabInfo(String tabName, int theme, Class<? extends Fragment> tabFragment) {
            this.tabName = tabName;
            this.theme = theme;
            this.tabFragment = tabFragment;
        }
    }

    //----------------------------------------------------------------------------------------------
    // Private Helpers
    //----------------------------------------------------------------------------------------------

    /**
     * Updates the theme based ont he tab info of the selected tab
     */
    private static void updateTheme(AppCompatActivity appContext, TabLayout tabLayout, TabInfo tabInfo) {

        // Apply new theme
        appContext.getTheme().applyStyle(tabInfo.theme, true);

        // Extract accent color from current theme
        TypedValue accentColorValue = new TypedValue();
        appContext.getTheme().resolveAttribute(R.attr.colorAccent, accentColorValue, true);
        int accentColor = accentColorValue.data;

        // Update tab bar
        tabLayout.setBackgroundColor(accentColor);
    }

    private static ViewPager.OnPageChangeListener getPageChangeListener(TabLayout tabLayout) {
        return new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
    }

    private static FragmentStatePagerAdapter getAdapter(FragmentManager fragmentManager, final TabInfo[] tabInfoList) {

        return new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {

                // Defensive programming
                if (position >= tabInfoList.length) {
                    return null;
                }

                // Create new instance of fragment class specified in tab info
                Fragment fragment;
                try {
                    fragment = tabInfoList[position].tabFragment.newInstance();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    return null;
                }

                return fragment;
            }

            @Override
            public int getCount() {
                return tabInfoList.length;
            }
        };
    }

    private static TabLayout.OnTabSelectedListener getTabSelectedListener(final AppCompatActivity appContext, final ViewPager viewPager, final TabInfo[] tabInfoList, final TabLayout tabLayout) {

        return new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                TabInfo tabInfo = tabInfoList[position];
                Log.i(TAG, "Selected tab #" + position);

                // Switch fragments
                viewPager.setCurrentItem(position);

                // Apply new theme
                updateTheme(appContext, tabLayout, tabInfo);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        };
    }
}
