package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp()
public class LearnSensorsColorDistance extends OpMode {
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;
    public void init()
    {
        colorSensor=hardwareMap.get(ColorSensor.class, "color_distance");
        distanceSensor=hardwareMap.get(DistanceSensor.class,"color_distance");
    }
    public int getAmountRed()
    {
        return colorSensor.red();
    }
    public double getDistance(DistanceUnit du)
    {
        return distanceSensor.getDistance(du);
    }
    public void loop()
    {
        telemetry.addData("CS Red", getAmountRed());
        telemetry.addData("DS Dist", getDistance(DistanceUnit.CM));
    }
}
