package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class ActiveIntake extends OpMode {
    CRServo left;
    CRServo right;
    public void init()
    {
        left=hardwareMap.get(CRServo.class, "leftcrservo");
        right=hardwareMap.get(CRServo.class, "rightcrservo");
    }
    public void loop()
    {
        if (gamepad1.a)
        {
            left.setPower(1.0);
            right.setPower(-1.0);
        }
        left.setPower(0.0);
        right.setPower(0.0);
    }
}
