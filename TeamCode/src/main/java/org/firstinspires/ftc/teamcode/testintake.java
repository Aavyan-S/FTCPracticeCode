package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;


@TeleOp(name = "Test Intake", group = "Test")
public class testintake extends LinearOpMode {
    /*
     * Create the hardware variables
     */
    private DcMotor leftSlider;
    private DcMotor rightSlider;
    private CRServo leftRoller;
    private CRServo rightRoller;
    private TouchSensor touchSensor;
    private ColorSensor colorSensor;

    /*
     * Initialize the hardware variables
     */
    public void initialize() {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        touchSensor = hardwareMap.get(TouchSensor.class, "touchSensor");
        leftRoller = hardwareMap.get(CRServo.class, "leftRoller");
        rightRoller = hardwareMap.get(CRServo.class, "rightRoller");
    }
    /*
     * Get the sample color
     */
    public void move(int location, DcMotor slider) {
        slider.setTargetPosition(location);
        if (slider.getMode() != DcMotor.RunMode.RUN_TO_POSITION) {
            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (slider.getPower() != 0.3) {
            slider.setPower(0.3);
        }
    }
    public void stopMotor(DcMotor slider) {
        if (slider.getPower() != 0) {
            slider.setPower(0);
        }
    }
    public String getSampleColor(boolean isBlueTeam) {
        if (isBlueTeam) {
            if (colorSensor.blue() > colorSensor.red()&&colorSensor.blue() > colorSensor.green()&&colorSensor.blue()>200) {
                return "Retract";
            }
            else if (colorSensor.green() > colorSensor.red()&&colorSensor.green() > colorSensor.blue()&&colorSensor.green()>200) {
                return "Retract";
            }
            else {
                return "Throw out";
            }
        }
        else if (!isBlueTeam) { // If it's not blue team
            if (colorSensor.red() > colorSensor.blue()&&colorSensor.red() > colorSensor.green()&&colorSensor.red()>200) {
                return "Retract";
            }
            else if (colorSensor.green() > colorSensor.red()&&colorSensor.green() > colorSensor.blue()&&colorSensor.green()>200) {
                return "Retract";
            }
            else {
                return "Throw out";
            }
        }
        else {
            return "Error";
        }
    }

    /*
     * Run the opmode
     */
    @Override
    public void runOpMode() {
        initialize();
        waitForStart();
        move(-1380, leftSlider);
        move(1380, rightSlider);
        while(leftSlider.isBusy()){
            telemetry.addData("Left Slider", leftSlider.getCurrentPosition());
            telemetry.addData("Right Slider", rightSlider.getCurrentPosition());
            telemetry.addData("Left Busy", leftSlider.isBusy());
            telemetry.addData("Right Busy", rightSlider.isBusy());
            telemetry.update();
        }
        stopMotor(leftSlider);
        stopMotor(rightSlider);
        leftRoller.setPower(-1);
        rightRoller.setPower(1);
        sleep(500); // Give it some time to start moving
        while (opModeIsActive()) {
            telemetry.addData("Touch Sensor", touchSensor.isPressed());
            telemetry.update();
            if (touchSensor.isPressed()) {
                telemetry.addLine("Touch Sensor Pressed");
                leftRoller.setPower(0);
                rightRoller.setPower(0);
                sleep(1000);
                boolean isBlue = false;
                if (getSampleColor(isBlue).equals("Retract")) {
                    telemetry.addLine("Retracting");
                    move(0, leftSlider);
                    move(0, rightSlider);
                } else if (getSampleColor(isBlue).equals("Throw out")) {
                    telemetry.addLine("Throwing Out");
                    rightRoller.setPower(-1);
                    leftRoller.setPower(1);
                    sleep(2000);
                    rightRoller.setPower(1);
                    leftRoller.setPower(-1);
                }
             }
        }
    }
}