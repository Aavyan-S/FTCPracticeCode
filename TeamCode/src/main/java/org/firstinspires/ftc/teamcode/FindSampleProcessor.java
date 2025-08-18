package org.firstinspires.ftc.teamcode;

import android.graphics.Canvas;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

import org.firstinspires.ftc.vision.VisionProcessor;

public class FindSampleProcessor implements VisionProcessor {
    private final List<MatOfPoint> contours = new ArrayList<>();
    private final Mat gray = new Mat();
    private final Mat hsv = new Mat();
    private Mat submat = new Mat();
    private final Mat binary = new Mat();
    public void init(int width, int height, CameraCalibration calibration) {

    }
    public Object processFrame(Mat frame, long captureTimeNanos) {
        contours.clear();

        // Convert to grayscale
        Imgproc.cvtColor(frame, gray, Imgproc.COLOR_RGB2GRAY); // SDK uses RGB format
        Imgproc.cvtColor(frame, hsv, Imgproc.COLOR_RGB2HSV);

        // Threshold
        Imgproc.threshold(gray, binary, 100, 255, Imgproc.THRESH_BINARY);

        // Find contours
        Mat hierarchy = new Mat();
        Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Draw contours on the frame
        Imgproc.drawContours(frame, contours, -1, new Scalar(0, 255, 0), 2);

        return contours.size(); // You can return any object here

    }
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {

    }

    public List<RotatedRect> getContourCoords() {

        List<RotatedRect> contourBounds = new ArrayList<>();
        for (MatOfPoint contour : contours) {
            Point[] points = contour.toArray();
            MatOfPoint2f contour2f = new MatOfPoint2f(points);
            RotatedRect temp= Imgproc.minAreaRect(contour2f);
            contourBounds.add(temp);
        }
        return contourBounds;
    }
    public double[] getContoursHue(List<RotatedRect> contours) {
        double[] contourHue = new double[contours.size()];
        for(int i = 0; i < contours.size(); i++) {
            contourHue[i] = getAvgHue(hsv, contours.get(i));
        }
        return contourHue;
    }
    protected double getAvgHue(Mat input, RotatedRect rect) {
        submat=input.submat(rect.boundingRect());
        Scalar color=Core.mean(submat);
        return color.val[0];
    }

}
