package com.example.Fazlay_Rabbi.banglaSignLanguage.LearnModule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Fazlay_Rabbi.camerawork_signlanguage.R;

/**
 * Created by Fazlay_Rabbi on 12/11/2017.
 */

public class scanBackgroundNotify extends Fragment
{

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.scan_background_notify, container, false);

        textView = (TextView) view.findViewById(R.id.scanback);

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
