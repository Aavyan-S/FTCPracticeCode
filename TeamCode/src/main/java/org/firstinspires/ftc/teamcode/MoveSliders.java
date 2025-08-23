package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "Move Sliders")
public class MoveSliders extends LinearOpMode {
    private DcMotor leftSlider;
    private DcMotor rightSlider;
    public void initialize() {
        leftSlider = hardwareMap.get(DcMotor.class, "leftSlider");
        leftSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightSlider = hardwareMap.get(DcMotor.class, "rightSlider");
        rightSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void move(int location, DcMotor slider) {
        slider.setTargetPosition(location);
        if (slider.getMode() != DcMotor.RunMode.RUN_TO_POSITION) {
            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (slider.getPower() != 0.3) {
            slider.setPower(0.3);
        }
        if (!slider.isBusy()) {
            slider.setPower(0);
        }

    }
    public void runOpMode() {
        initialize();
        waitForStart();
        telemetry.addData("Left Slider", leftSlider.getCurrentPosition());
        telemetry.addData("Right Slider", rightSlider.getCurrentPosition());
        telemetry.addData("Left Busy", leftSlider.isBusy());
        telemetry.addData("Right Busy", rightSlider.isBusy());
        move(-1000, leftSlider);
        move(1000, rightSlider);
        sleep(1000);
        move(0, leftSlider);
        move(0, rightSlider);

    }
}
