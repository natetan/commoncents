package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import edu.washington.ischool.commoncents.commoncents.Fragments.EventsListFragment;
import edu.washington.ischool.commoncents.commoncents.Fragments.FriendsListFragment;
import edu.washington.ischool.commoncents.commoncents.Fragments.SettingsFragment;


/**
 * Created by iguest on 3/3/17.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    public static final String TAG = "MainPagerAdapter";
    int numOfTabs;

    public MainPagerAdapter(FragmentManager fragmentManager, int numOfTabs) {
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Log.v(TAG, "switching to tab #" + position);

        switch (position) {
            case 0: {
                EventsListFragment tab = new EventsListFragment();
                return tab;
            }
            case 1: {
                FriendsListFragment tab = new FriendsListFragment();
                return tab;
            }
            case 2: {
                // TODO make SettingsActivity a fragment and return it here
                SettingsFragment tab = new SettingsFragment();
                return tab;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
