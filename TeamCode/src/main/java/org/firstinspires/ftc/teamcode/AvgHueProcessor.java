package org.firstinspires.ftc.teamcode;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class AvgHueProcessor implements VisionProcessor {
    public Rect outer_rect =new Rect(160,42,40,40);
    Mat submat=new Mat();
    Mat hsvmat=new Mat();
    public void init(int width, int height, CameraCalibration calibration) {
    }
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.cvtColor(frame, hsvmat, Imgproc.COLOR_RGB2HSV);
        return null;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(scaleCanvasDensity*4);
        paint.setStyle(Paint.Style.STROKE);
        android.graphics.Rect rect=makeGraphicsRect(outer_rect,scaleBmpPxToCanvasPx);
        canvas.drawRect(rect, paint);
    }

    protected double getAvgHue(Mat input, Rect rect) {
        submat=input.submat(rect);
        Scalar color=Core.mean(submat);
        return color.val[0];
    }
    private android.graphics.Rect makeGraphicsRect(Rect rect, float scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round((rect.width) * scaleBmpPxToCanvasPx);
        int bottom = top + Math.round((rect.height) * scaleBmpPxToCanvasPx);
        return new android.graphics.Rect(left, top, right, bottom);
    }
    public double returnAvgHue(){
        return getAvgHue(hsvmat, outer_rect);
    }

}