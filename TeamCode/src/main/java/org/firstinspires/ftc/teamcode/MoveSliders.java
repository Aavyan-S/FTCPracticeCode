package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MoveSliders {
    private DcMotor leftSlider;
    private DcMotor rightSlider;
    private ElapsedTime et = new ElapsedTime();
    public void initialize(HardwareMap hwMap) {
        leftSlider = hwMap.get(DcMotor.class, "leftSlider");
        leftSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightSlider = hwMap.get(DcMotor.class, "rightSlider");
        rightSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
//    public void move(int location, DcMotor slider) {
//        slider.setTargetPosition(location);
//        if (slider.getMode() != DcMotor.RunMode.RUN_TO_POSITION) {
//            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        }
//        if (slider.getPower() != 0.3) {
//            slider.setPower(0.3);
//        }
//        if (!slider.isBusy()) {
//            slider.setPower(0);
//        }
//    }

    public void moveInternal(boolean extend, Telemetry telemetry)
    {
        if (extend) {
            leftSlider.setTargetPosition(-1000);
            leftSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftSlider.setPower(0.3);

            rightSlider.setTargetPosition(1000);
            rightSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightSlider.setPower(0.3);
            while ((leftSlider.isBusy() || rightSlider.isBusy())) {
//                telemetry.addData("TargetLeft", -1000);
//                telemetry.addData("TargetRight", 1000);
//                telemetry.addData("CurrentLeft", leftSlider.getCurrentPosition());
//                telemetry.addData("CurrentRight", rightSlider.getCurrentPosition());
                telemetry.update();
            }
            leftSlider.setPower(0);
            rightSlider.setPower(0);
        }
        else {
            leftSlider.setTargetPosition(50);
            leftSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftSlider.setPower(0.3);

            rightSlider.setTargetPosition(-50);
            rightSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightSlider.setPower(0.3);
            et.reset();
            while ((leftSlider.isBusy() || rightSlider.isBusy()) && et.seconds()<=3) {
//                telemetry.addData("TargetLeft", 50);
//                telemetry.addData("TargetRight", -50);
//                telemetry.addData("CurrentLeft", leftSlider.getCurrentPosition());
//                telemetry.addData("CurrentRight", rightSlider.getCurrentPosition());
                telemetry.update();
            }
//            telemetry.addLine("Out of While Loop");
            telemetry.update();
            leftSlider.setPower(0);
            rightSlider.setPower(0);
        }
    }
//    public void move(int location, DcMotor slider) {
//        slider.setTargetPosition(location);
//        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        slider.setPower(0.3);
//
//        while (slider.isBusy() && opModeIsActive()) {
//            telemetry.addData("Target", location);
//            telemetry.addData("Current", slider.getCurrentPosition());
//            telemetry.update();
//        }
//
//        slider.setPower(0);
//    }
//    public void runOpMode() {
//        initialize(hardwareMap);
//        waitForStart();
////        move(-1000, leftSlider);
////        move(1000, rightSlider);
//        moveInternal(true);
//        telemetry.addLine("Before sleep");
//        sleep(1000);
//        telemetry.addLine("After sleep");
//        sleep(1000);
////        move(1000, leftSlider);
////        move(-1000, rightSlider);
//        moveInternal(false);
//        sleep(1000);
//    }
}
