package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "MoveMotor", group = "Concept")
public class MoveMotor extends OpMode {
    public DcMotor motor;
    public void init() {
        motor=hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void gotopos(int pos) {
        motor.setTargetPosition(pos);
        motor.setPower(1.0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void loop() {
        gotopos(1000);
    }
}
