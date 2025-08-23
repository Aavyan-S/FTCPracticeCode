package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "Move Sliders")
public class MoveSliders extends OpMode {
    private DcMotor leftSlider;
    private DcMotor rightSlider;
    public void init() {
        leftSlider = hardwareMap.get(DcMotor.class, "leftSlider");
        rightSlider = hardwareMap.get(DcMotor.class, "rightSlider");
        leftSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void move(int location, DcMotor slider) {
        slider.setTargetPosition(location);
        if (slider.getMode() != DcMotor.RunMode.RUN_TO_POSITION) {
            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (slider.getPower() != 1) {
            slider.setPower(0.3);
        }
    }
    public void loop() {
        move(-1380, leftSlider);
        move(1380, rightSlider);
    }
}
