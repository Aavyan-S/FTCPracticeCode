package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Autonomous(name = "TestDistanceSensor", group = "Test")
public class TestDistanceSensor extends LinearOpMode {
    private DistanceSensor distanceSensor;
    private ColorSensor colorSensor;
    public void initialize() {
        distanceSensor=hardwareMap.get(DistanceSensor.class,"colorV3");
        colorSensor=hardwareMap.get(ColorSensor.class,"colorV3");
    }
    public boolean hasSample() {
        return distanceSensor.getDistance(DistanceUnit.CM) < 4.1;
    }
    public String getSampleColor() {
        if (colorSensor.blue() > colorSensor.red()&&colorSensor.blue() > colorSensor.green()&&colorSensor.blue()>100) {
            return "Blue";
        }
        else if (colorSensor.green() > colorSensor.red()&&colorSensor.green() > colorSensor.blue()&&colorSensor.green()>150) {
            return "Yellow";
        }
        else if (colorSensor.red() > colorSensor.blue()&&colorSensor.red() > colorSensor.green()&&colorSensor.red()>150) {
            return "Red";
        }
        else {
            return "Gray";
        }
    }
    public void runOpMode() {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Distance", distanceSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("Color", getSampleColor());
            telemetry.addData("Has Sample", hasSample());
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Blue", colorSensor.blue());
            telemetry.addData("Green", colorSensor.green());
            telemetry.update();
        }
    }

}
