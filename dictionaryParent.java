package com.example.Fazlay_Rabbi.banglaSignLanguage;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Fazlay_Rabbi.camerawork_signlanguage.R;

/**
 * Created by Fazlay_Rabbi on 11/23/2017.
 */

public class dictionaryParent extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        
        View v = inflater.inflate(R.layout.dictionary_fragment, container, false);
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        return v;

    }

    private class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos)
        {

            switch(pos)
            {

                case 0:
                {

                    return dictionaryChildFragment.newInstance("FirstFragment, Instance 1");

                }
        }

        @Override
        public int getCount()
        {

            return 0;

        }
    }
}
