package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous()
public class TestSlider extends LinearOpMode {
    public DcMotor slider;
    public void initialize()
    {
        slider=hardwareMap.get(DcMotor.class, "sliderMotor");
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void move_slider(int target_pos) {
        slider.setTargetPosition(target_pos);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION); //Set the RunMode
        //Extend slider to target_pos
        slider.setPower(0.3); //Low Power
//        if (slider.getCurrentPosition() >= target_pos - 2 && slider.getCurrentPosition() <= target_pos + 2) {
//            telemetry.addData("Power", slider.getPower());
//            slider.setPower(1.0);
//        }
    }

    public void runOpMode()
    {
        initialize();
        waitForStart();
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addData("Start Ticks",slider.getCurrentPosition());
        telemetry.update();
        sleep(5000);
        move_slider(-1460);
        telemetry.addData("Extend Ticks",slider.getCurrentPosition());
        telemetry.update();
        sleep(5000);
        slider.setDirection(DcMotorSimple.Direction.REVERSE);
        move_slider(-1000);
        telemetry.addData("Retract Ticks",slider.getCurrentPosition());
        telemetry.update();
        sleep(5000);
    }
}
