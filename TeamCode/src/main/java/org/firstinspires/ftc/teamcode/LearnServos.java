package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp()
public class LearnServos extends OpMode {
    public CRServo crServo;
    public void init()
    {
        crServo=hardwareMap.get(CRServo.class,"servo");
    }
    public void loop()
    {
        if (gamepad1.a)
        {
            crServo.setPower(1.0);
        }
        crServo.setPower(0.0);
    }
}
