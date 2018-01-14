package com.example.Fazlay _Rabbi.banglaSignLanguage.LearnModule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Fazlay _Rabbi.camerawork_signlanguage.R;

/**
 * Created by Fazlay _Rabbi on 12/14/2017.
 */

public class scanHandNotify extends Fragment
{
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.scan_hand_notify  , container, false);
        textView = (TextView) view.findViewById(R.id.scanHand);

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                doScanBackground fragment = new doScanBackground();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
