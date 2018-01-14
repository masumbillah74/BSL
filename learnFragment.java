package com.example.Fazlay_Rabbi.banglaSignLanguage;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.Fazlay_Rabbi.camerawork_signlanguage.R;

/**
 * Created by Fazlay_Rabbi on 11/23/2017.
 */

public class learnFragment extends Fragment
{
    Button exerciseButton;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view= inflater.inflate(R.layout.learn_fragment_layout, container, false);
        exerciseButton = (Button) view.findViewById(R.id.exerciseBtn);

        exerciseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                exerciseBtnPressed();
            }
        });

        return view;
    }


    public void exerciseBtnPressed()
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        exerciseModule fragment = new exerciseModule();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
