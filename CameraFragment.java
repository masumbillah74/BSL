package com.example.Fazlay_Rabbi.banglaSignLanguage;

import java.util.List;
import org.opencv.android.JavaCameraView;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.AttributeSet;


/**
 * Created by Fazlay_Rabbi on 11/14/2017.
 */


public class CameraFragment extends JavaCameraView
{

    public CameraFragment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setResolution(Size resolution)
    {
        disconnectCamera();
        mMaxHeight = resolution.height;
        mMaxWidth = resolution.width;

        connectCamera(mMaxWidth, mMaxHeight);
    }

    public List<Size> getResolutionList()
    {
        return mCamera.getParameters().getSupportedPreviewSizes();
    }

}
