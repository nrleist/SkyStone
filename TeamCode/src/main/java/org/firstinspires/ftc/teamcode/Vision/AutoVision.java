package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

public class AutoVision {

    public AutoVision(Hardware hardware, LinearOpMode mode ){
        this.bot = hardware;
        this.opMode = mode;
    }

    private Hardware bot;
    private LinearOpMode opMode;

    OpenCvCamera webcam;
    RingNumberDeterminationPipeline pipeline;

    public void start(int pointX, int pointY, int width, int height, int oneThreshold, int fourThreshold) {
        int cameraMonitorViewId = bot.webcamView;
        webcam = OpenCvCameraFactory.getInstance().createWebcam(bot.webcamOne, cameraMonitorViewId);
        pipeline = new RingNumberDeterminationPipeline();
        pipeline.startUp(pointX, pointY, width, height, oneThreshold, fourThreshold);
        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(432,240, OpenCvCameraRotation.UPRIGHT);
            }
        });
    }

    public int getNumberOfRings() {
        pipeline.getAnalysis();
        return pipeline.ringNumber;
    }

    public int analiseFrame(){
        return pipeline.getAnalysis();
    }

    public static class RingNumberDeterminationPipeline extends OpenCvPipeline {

        /*
         * Some color constants
         */
        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        /*
         * The core values which define the location and size of the sample regions
         */
    //    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(181,98);

       // static final int REGION_WIDTH = 35;
       // static final int REGION_HEIGHT = 25;

      //  final int FOUR_RING_THRESHOLD = 150;
      //  final int ONE_RING_THRESHOLD = 135;


        /*
         * Working variables
         */
        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1;

        public Point anchorPoint;
        public int regionWidth;
        public int regionHeight;
        public int fourRingThreshold;
        public int oneRingThreshold;

        public Point pointA;
        public Point pointB;
       // public Point REGION1_TOPLEFT_ANCHOR_POINT;


        // Volatile since accessed by OpMode thread w/o synchronization
        private int ringNumber;

        public void startUp(int pointX, int pointY, int width, int height, int oneThreshold, int fourThreshold) {
            anchorPoint = new Point(pointX, pointY);
            regionWidth = width;
            regionHeight = height;

            oneRingThreshold = oneThreshold;
            fourRingThreshold = fourThreshold;

            pointA = new Point(anchorPoint.x, anchorPoint.y);
            pointB = new Point(anchorPoint.x + regionWidth, anchorPoint.y + regionHeight);
        }
        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToCb(Mat input) {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        @Override
        public void init(Mat firstFrame) {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(pointA, pointB));
        }

        @Override
        public Mat processFrame(Mat input)
        {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(input, // Buffer to draw on
                    pointA, // First point which defines the rectangle
                    pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            // Record our analysis
            if(avg1 > fourRingThreshold) {
                ringNumber = 4;
            } else if (avg1 > oneRingThreshold){
                ringNumber = 1;
            } else{
                ringNumber = 0;
            }

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    pointA, // First point which defines the rectangle
                    pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis()
        {
            return avg1;
        }
    }
}


