package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.AvgHueProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp()
public class AvgHueOpMode extends OpMode {
    private AvgHueProcessor processor;
    private double avgHue;
    private VisionPortal visionPortal;
    public void init() {
        processor = new AvgHueProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), processor);
    }
    public void init_loop() {
        avgHue = processor.returnAvgHue();
    }
    public void start() {
        visionPortal.stopStreaming();
    }
    public void loop() {
        telemetry.addData("Average Hue", avgHue);
    }
}
