package edu.washington.ischool.commoncents.commoncents;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by iguest on 3/3/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Log.v("TAG", "HELLO");
                SplitCostsFragment tab1 = new SplitCostsFragment();
                return tab1;
            case 1:
                Log.v("TAG", "HELLO 2");
                FriendsListFragment tab2 = new FriendsListFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
