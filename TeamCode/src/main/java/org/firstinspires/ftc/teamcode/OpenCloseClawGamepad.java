package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp()
public class OpenCloseClawGamepad extends OpMode {
    private Servo servo, servo2;
    public void init()
    {
        servo=hardwareMap.get(Servo.class, "servoHOME");
        servo2=hardwareMap.get(Servo.class, "servoHOME2");
    }
    public void loop()
    {
        if (gamepad1.a) servo.setPosition(0.9); else servo.setPosition(0.0);
        if (gamepad1.b) servo2.setPosition(0.2); else servo2.setPosition(0.0);
    }
}
