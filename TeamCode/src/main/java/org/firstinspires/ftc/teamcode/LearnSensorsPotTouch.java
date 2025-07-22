package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.Range;

@TeleOp()
public class LearnSensorsPotTouch extends OpMode {
    private DigitalChannel touchSensor;
    private AnalogInput pot; //Potentiometer

    public void init()
    {
        touchSensor=hardwareMap.get(DigitalChannel.class,"touch");
        pot=hardwareMap.get(AnalogInput.class,"pot");
    }
    public double getPotAngle() {
        return Range.scale(pot.getVoltage(),0,pot.getMaxVoltage(),0,270);
    }
    public boolean isTouchSensorPressed()
    {
        return touchSensor.getState();
    }
    public void loop()
    {
        telemetry.addData("Pot Angle",getPotAngle());
        telemetry.addData("Touch Sensor", isTouchSensorPressed());
    }
}
