package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp()
public class MoveRobot extends OpMode {
    double go_to_pos;
    double TICKS =2.5*384.5;
    public Servo clawServo;
    public Servo flipServo;
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotor sliderMotor;
    public void init() {
        clawServo = hardwareMap.get(Servo.class, "claw_servo");
        flipServo = hardwareMap.get(Servo.class, "flip_servo");
        leftFront = hardwareMap.get(DcMotor.class, "front_left");
        rightFront = hardwareMap.get(DcMotor.class,"front_right");
        leftBack = hardwareMap.get(DcMotor.class,"back_left");
        rightBack = hardwareMap.get(DcMotor.class,"back_right");
        sliderMotor = hardwareMap.get(DcMotor.class, "slider");
        sliderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void encoder(int target_val) {
        go_to_pos = target_val;
        sliderMotor.setTargetPosition((int) go_to_pos);
        sliderMotor.setPower(1.0);
        sliderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void loop() {
        leftFront.setPower(gamepad1.right_stick_y-gamepad1.left_stick_x-gamepad1.right_stick_x);
        leftBack.setPower(gamepad1.right_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
        rightFront.setPower(-gamepad1.right_stick_y-gamepad1.left_stick_x-gamepad1.right_stick_x);
        rightBack.setPower(-gamepad1.right_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
        if (gamepad1.dpad_up && (go_to_pos>-TICKS)) {
            telemetry.addData("curr_pos", sliderMotor.getCurrentPosition());
            encoder(sliderMotor.getCurrentPosition()-10);
            telemetry.addData("goto", go_to_pos);

        }

        if (gamepad1.a) {
            clawServo.setPosition(1.0);
        } else if (gamepad1.b) {
            clawServo.setPosition(0.0);
        }
        else if(gamepad1.x){
            flipServo.setPosition(0.75);
        }
        else if(gamepad1.y){
            flipServo.setPosition(0.0);
        }
    }
}
