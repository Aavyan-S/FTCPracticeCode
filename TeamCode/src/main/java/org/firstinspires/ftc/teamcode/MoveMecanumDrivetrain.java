package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Move Mecanum Drivetrain", group="Iterative Opmode")
public class MoveMecanumDrivetrain extends OpMode {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    public void init() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
    }
    public void loop() {
        frontLeftMotor.setPower(gamepad1.right_stick_y-gamepad1.left_stick_x-gamepad1.right_stick_x);
        backLeftMotor.setPower(gamepad1.right_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
        frontRightMotor.setPower(-gamepad1.right_stick_y-gamepad1.left_stick_x-gamepad1.right_stick_x);
        backRightMotor.setPower(-gamepad1.right_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
    }
}