package com.osama.lifeshare;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Khizer Mehmood on 12/2/2018.
 */

public class DonorPagerAdapter  extends FragmentPagerAdapter {

    int numTabs;

    public DonorPagerAdapter(FragmentManager fm, int NoTabs) {
        super(fm);
        this.numTabs = NoTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0 :
                return new SearchDonor();
            case 1 :
                return new AllDonor();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
