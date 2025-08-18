package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp()
public class test extends OpMode {
    public int max_ticks=-1460;
    private DcMotor slider;
    public void init() {
        slider = hardwareMap.get(DcMotor.class, "slider_inconfig");
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void sliderToPos(int ticks)
    {
        slider.setTargetPosition(ticks);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slider.setPower(0.3);
    }
    public void loop(){
        telemetry.addData("current_ticks",slider.getCurrentPosition());
        sliderToPos(max_ticks);
        sliderToPos(-1000);
    }
}
