package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Intake", group = "Test")
public class Intake extends LinearOpMode {
    // Declare OpMode members
    private Servo intakeflipservo;
    private DcMotor leftRoller;
    private DcMotor rightRoller;
    private ColorSensor colorSensor;
    private String color;

    // Method to check color
    public void checkColor() {
        if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green() && colorSensor.blue()>200) {
            color = "Blue";
        }
        else if (colorSensor.green() > colorSensor.red() && colorSensor.green() > colorSensor.blue()&&colorSensor.green()>200) {
            color = "Yellow";
        }
        else {
            color = "Black";
        }
    }
    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the hardware variables
        leftRoller = hardwareMap.get(DcMotor.class, "leftRoller");
        rightRoller = hardwareMap.get(DcMotor.class, "rightRoller");
        intakeflipservo = hardwareMap.get(Servo.class, "servo");
        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");

        // Wait for the game to start
        intakeflipservo.setPosition(0);
        waitForStart();
        // main loop
        while (opModeIsActive()) {
            leftRoller.setPower(-1);
            rightRoller.setPower(1);
            intakeflipservo.setPosition(0);
            checkColor();
            sleep(1000);
            if (!color.equals("Black")) {
                leftRoller.setPower(0);
                rightRoller.setPower(0);
                intakeflipservo.setPosition(0);
                sleep(1000);
                checkColor();
                if (color.equals("Yellow")) {
                    intakeflipservo.setPosition(0.4);
                    sleep(1000);
                    leftRoller.setPower(-1);
                    rightRoller.setPower(1);
                    sleep(2000);
                    leftRoller.setPower(0);
                    rightRoller.setPower(0);
                    intakeflipservo.setPosition(0);
                }
                else if (color.equals("Blue")) {
                    break;
                }
            }
        }
    }
}