package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp()
public class LearnServos extends OpMode {
    public CRServo crServo;
    public Servo servo;
    public void init()
    {
        servo=hardwareMap.get(Servo.class,"servo");
        crServo=hardwareMap.get(CRServo.class,"crservo");
    }
    public void loop()
    {
        if (gamepad1.a)
        {
            crServo.setPower(1.0);
        }
        crServo.setPower(0.0);
        if (gamepad1.b)
        {
            servo.setPosition(0.5);
        }
        servo.setPosition(0.0);
    }
}
