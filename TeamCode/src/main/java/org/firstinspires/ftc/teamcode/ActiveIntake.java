package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class ActiveIntake extends OpMode {
    CRServo left;
    public void init()
    {
        left=hardwareMap.get(CRServo.class, "leftcrservo");
    }
    public void loop()
    {
        if (gamepad1.a)
        {
            left.setPower(1.0);
        }
        telemetry.addData("leftPower", left.getPower());
    }
}
