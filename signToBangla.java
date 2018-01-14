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
 * Created by Fazlay Rabbi on 12/19/2017.
 */

public class signToBangla extends Fragment
{
    Button documentWriteButton, signAlapbutton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view= inflater.inflate(R.layout.selected_sign_to_bangla, container, false);

        documentWriteButton = (Button) view.findViewById(R.id.documentwrite);
        signAlapbutton = (Button) view.findViewById(R.id.signalap);

        documentWriteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                documentWriteButtonPressed();
            }
        });

        signAlapbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signAlapButtonPressed();
            }
        });

        return view;
    }

    public void documentWriteButtonPressed()
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        scanBackgroundNotify fragment = new scanBackgroundNotify();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void signAlapButtonPressed()
    {
        //need to implement
    }

}
