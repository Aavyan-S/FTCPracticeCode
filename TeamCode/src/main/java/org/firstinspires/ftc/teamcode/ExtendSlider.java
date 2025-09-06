package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ExtendSlider{
    public DcMotor slider;
    private ElapsedTime et = new ElapsedTime();
    //public int stages;
    //public final int TICKS_PER_STAGE = 680;
    public void initialize(HardwareMap hardwareMap) {
        slider = hardwareMap.get(DcMotor.class, "sliderMotor");
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //stages = 3;
    }
    public void move_slider(int target_pos, Telemetry telemetry) {
        et.reset();
        slider.setTargetPosition(target_pos);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slider.setPower(0.3);
        while (slider.isBusy() && et.seconds()<3) {
//            telemetry.addData("Current", slider.getCurrentPosition());
            telemetry.update();
        }
        slider.setPower(0);
    }
    public void keep_pos()
    {
        slider.setTargetPosition(slider.getCurrentPosition());
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slider.setPower(0.2);
    }
//    public void runOpMode() {
//        initialize();
//        waitForStart();
//        move_slider(-2320);
//        sleep(1000);
//        move_slider(2320);
//    }
}
