package com.example.Fazlay_Rabbi.banglaSignLanguage.LearnModule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.example.hp.banglaSignLanguage.CameraFragment;
import com.example.hp.camerawork_signlanguage.R;
import org.opencv.android.OpenCVLoader;
import android.hardware.Camera;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import java.util.List;

/**
 * Created by Fazlay_Rabbi on 12/11/2017.
 */

public class doScanBackground extends Fragment implements  CvCameraViewListener2
{
    private CameraFragment mOpenCvCameraView;;

    private Mat rgbaMat = null;
    private Mat rgbMat = null;
    private Mat interMat = null;
    private Mat backMat = null;

    private Scalar mColorsRGB[] = null;
    private static final int COLOR_SPACE = Imgproc.COLOR_RGB2Lab;
    private int squareLen;
    private Point[][] samplePoints = null;
    private static final int SAMPLE_NUM = 7;
    private double[][] avgBackColor = null;

    int BACKGROUND_SCAN = 1;
    int HAND_SCAN = 2;
    int mode = BACKGROUND_SCAN;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null)
        {
            mode = (int) savedInstanceState.getSerializable("mode");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.do_scan_background, container, false);

        initiateCanera(view);
        initiateSamplePoints();
        initiateColor();

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mode",mode);
    }

    private void initiateColor()
    {
        avgBackColor = new double[SAMPLE_NUM][3];
    }

    private void initiateCanera(View view)
    {
        mOpenCvCameraView =  view.findViewById(R.id.cameraFragment);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, getContext(), mLoaderCallback);

    }

    private void initiateSamplePoints()
    {
        samplePoints = new Point[SAMPLE_NUM][2];
        for (int i = 0; i < SAMPLE_NUM; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                samplePoints[i][j] = new Point();
            }
        }
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

        if (rgbMat == null) rgbMat = new Mat();
        if (interMat == null) interMat = new Mat();
        if (backMat==null) backMat = new Mat();
        mColorsRGB = new Scalar[] { new Scalar(255, 0, 0, 255), new Scalar(0,
                                255, 0, 255), new Scalar(0, 0, 255, 255) };

    }

    @Override
    public void onCameraViewStopped()
    {

    }

    @Override
    public Mat onCameraFrame(CvCameraViewFrame inputFrame)
    {

        rgbaMat = inputFrame.rgba();

        Core.flip(rgbaMat, rgbaMat, 1);
        Imgproc.GaussianBlur(rgbaMat, rgbaMat, new Size(5,5), 5, 5);
        Imgproc.cvtColor(rgbaMat, rgbMat, Imgproc.COLOR_RGBA2RGB);
        Imgproc.cvtColor(rgbaMat, interMat, COLOR_SPACE);

        if(mode == BACKGROUND_SCAN) preSampleBack(rgbaMat);

        return rgbaMat;

    }

    void preSampleBack(Mat img)
    {
        int cols = img.cols();
        int rows = img.rows();
        squareLen = rows/20;
        Scalar color = mColorsRGB[2];

        samplePoints[0][0].x = cols/6;
        samplePoints[0][0].y = rows/3;
        samplePoints[1][0].x = cols/6;
        samplePoints[1][0].y = rows*2/3;
        samplePoints[2][0].x = cols/2;
        samplePoints[2][0].y = rows/6;
        samplePoints[3][0].x = cols/2;
        samplePoints[3][0].y = rows/2;
        samplePoints[4][0].x = cols/2;
        samplePoints[4][0].y = rows*5/6;
        samplePoints[5][0].x = cols*5/6;
        samplePoints[5][0].y = rows/3;
        samplePoints[6][0].x = cols*5/6;
        samplePoints[6][0].y = rows*2/3;

        for (int i = 0; i < SAMPLE_NUM; i++)
        {
            samplePoints[i][1].x = samplePoints[i][0].x+squareLen;
            samplePoints[i][1].y = samplePoints[i][0].y+squareLen;
        }

        for (int i = 0; i < SAMPLE_NUM; i++)
        {
            Core.rectangle(img,  samplePoints[i][0], samplePoints[i][1], color, 1);
        }

        for (int i = 0; i < SAMPLE_NUM; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                avgBackColor[i][j] = (interMat.get((int)(samplePoints[i][0].y+squareLen/2), (int)(samplePoints[i][0].x+squareLen/2)))[j];
            }
        }

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
                    //setResolution();
                    mOpenCvCameraView.setOnTouchListener(new View.OnTouchListener()
                    {
                        public boolean onTouch(View v, MotionEvent event)
                        {
                            int action = MotionEventCompat.getActionMasked(event);

                            switch (action)
                            {
                                case (MotionEvent.ACTION_DOWN):

                                    if(mode == BACKGROUND_SCAN)
                                    {
                                        rgbaMat.copyTo(backMat);
                                        mode = HAND_SCAN;
                                        gotoHandScanMode();
                                    }
                                    return false;

                                default:
                                    return true;
                            }
                        }

                    });
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

    private void gotoHandScanMode()
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        scanHandNotify fragment = new scanHandNotify();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}
