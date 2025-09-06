package org.firstinspires.ftc.teamcode.resets;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
@Autonomous(name="Reset Servo", group = "Resets")
public class ResetServo extends OpMode {
    private Servo servo;
    public static final int SERVOHOMEPORT = 6;
    public void init() {
        servo=hardwareMap.get(Servo.class, "servoHOME");
    }
    public void loop() {
        servo.setPosition(0.0);
    }
}
