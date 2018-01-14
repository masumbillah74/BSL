package com.example.Fazlay_Rabbi.banglaSignLanguage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.Fazlay_Rabbi.banglaSignLanguage.LearnModule.scanBackgroundNotify;
import com.example.Fazlay_Rabbi.camerawork_signlanguage.R;

/**
 * Created by Fazlay_Rabbi on 11/23/2017.
 */

public class homepageFragment extends Fragment
{
    Button translateBtn,learnBtn,dictionaryBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.homepage_fragment, container, false);

        translateBtn = (Button) v.findViewById(R.id.translateBtn);
        learnBtn = (Button) v.findViewById(R.id.learnBtn);
        dictionaryBtn = (Button) v.findViewById(R.id.dictBtn);

        translateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                translate();
            }
        });
        
        learnBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                learn();
            }
        });
        
        dictionaryBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dictionary();
            }
        });

        return v;

    }


    public void translate()
    {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        signToBangla fragment = new signToBangla();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void learn()
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        learnFragment fragment = new learnFragment();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public  void dictionary()
    {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        dictionaryFragment fragment = new dictionaryFragment();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
