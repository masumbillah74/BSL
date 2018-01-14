package com.example.Fazlay _Rabbi.banglaSignLanguage.LearnModule;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.example.hp.banglaSignLanguage.CameraFragment;
import com.example.hp.camerawork_signlanguage.R;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import java.util.List;


/**
 * Created by Fazlay _Rabbi on 11/23/2017.
 */

public class translateFragment extends Fragment implements  CvCameraViewListener2
{
    private CameraFragment mOpenCvCameraView;;
    private Mat rgbMat = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.do_scan_background, container, false);
        mOpenCvCameraView = (CameraFragment) view.findViewById(R.id.cameraFragment);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, getContext(), mLoaderCallback);

        return view;

    }

    public void setResolution()
    {

        List<Camera.Size> mResolutionList = mOpenCvCameraView.getResolutionList();
        Camera.Size resolution = mResolutionList.get(0);
        mOpenCvCameraView.setResolution(resolution);

    }

    @Override
    public void onCameraViewStarted(int width, int height)
    {

        rgbMat = new Mat();

    }

    @Override
    public void onCameraViewStopped()
    {

    }

    @Override
    public Mat onCameraFrame(CvCameraViewFrame inputFrame)
    {

        rgbMat = inputFrame.rgba();
        return rgbMat;

    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(getContext())
    {
        @Override
        public void onManagerConnected(int status)
        {
            switch (status)
            {
                case LoaderCallbackInterface.SUCCESS:
                {

                    mOpenCvCameraView.enableView();
                    setResolution();

                }
                break;
                default:
                {

                    super.onManagerConnected(status);

                }
                break;
            }
        }
    };
}






























