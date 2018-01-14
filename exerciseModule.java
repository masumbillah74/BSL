package com.example.Fazlay_Rabbi.banglaSignLanguage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Fazlay_Rabbi.camerawork_signlanguage.R;

/**
 * Created by Fazlay_Rabbi on 12/6/2017.
 */

public class exerciseModule extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.learn_exercise_parent, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.practice_exercise_pager);
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));

        return view;
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
            switch(pos) {

                case 0: return learn_exercise_child1.newInstance("FirstFragment, Instance 1");
                case 1: return learn_exercise_child2.newInstance("SecondFragment, Instance 1");
                default: return learn_exercise_child1.newInstance("SecondFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}
