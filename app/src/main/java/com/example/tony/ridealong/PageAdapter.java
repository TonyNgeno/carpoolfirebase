package com.example.tony.ridealong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tony.ridealong.Fragment.TabPast;
import com.example.tony.ridealong.Fragment.TabUpcoming;

public class PageAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PageAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                TabPast tabPast = new TabPast();
                return tabPast;

            case 1:
                TabUpcoming tabUpcoming = new TabUpcoming();
                default:
                    return null;

        }

    }

    @Override
    public int getCount() {
        return 0;
    }
}
