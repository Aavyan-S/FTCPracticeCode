package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "CRServo Test", group = "Test")
public class ServoTest extends OpMode {
    // Declare the CRServo object
    private CRServo crservo;

    @Override
    public void init() {
        // Initialize the CRServo
        crservo = hardwareMap.get(CRServo.class, "crservo");
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        // Set the CRServo to run at full speed (1.0)
        crservo.setPower(1.0);
        
        // Show the current power setting on the driver station
        telemetry.addData("CRServo Power", "%.2f", 1.0);
    }
    
    @Override
    public void stop() {
        // Stop the CRServo when the op mode ends
        crservo.setPower(0);
    }
}
