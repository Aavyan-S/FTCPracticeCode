package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "TestColor")
public class TestColor extends OpMode {
    private ColorSensor colorSensor;
    public String color;
    public void init() {
        colorSensor=hardwareMap.get(ColorSensor.class, "colorv3");
    }
    public void loop() {
        if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()&&colorSensor.red()>200) {
            color = "Red";
        } else if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()&&colorSensor.blue()>200) {
            color = "Blue";
        } else if (colorSensor.green() > colorSensor.red() && colorSensor.green() > colorSensor.blue()&&colorSensor.green()>200) {
            color = "Yellow";
        }
        else {
            color = "Black";
        }
        telemetry.addData("Red", colorSensor.red());
        telemetry.addData("Blue", colorSensor.blue());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Color", color);
    }
}
