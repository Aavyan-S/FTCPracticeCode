package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp(name = "Test Intake", group = "Test")
public class testintake extends OpMode {
    private CRServo leftRoller;
    private CRServo rightRoller;

    @Override
    public void init() {
        leftRoller = hardwareMap.get(CRServo.class, "leftRoller");
        rightRoller = hardwareMap.get(CRServo.class, "rightRoller");
    }

    @Override
    public void loop() {
        leftRoller.setPower(-1);
        rightRoller.setPower(1);
    }
}