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
    private MoveSliders moveSliders = new MoveSliders();
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
        leftSlider = hardwareMap.get(DcMotor.class, "leftSlider");
        rightSlider = hardwareMap.get(DcMotor.class, "rightSlider");
        leftSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        touchSensor = hardwareMap.get(TouchSensor.class, "touchSensor");
        leftRoller = hardwareMap.get(CRServo.class, "leftRoller");
        rightRoller = hardwareMap.get(CRServo.class, "rightRoller");
    }
    /*
     * Get the sample color
     */
//    public void move(int location) {
//        leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftSlider.setTargetPosition(location);
//        if (leftSlider.getMode() != DcMotor.RunMode.RUN_TO_POSITION) {
//            leftSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        }
//        if (leftSlider.getPower() != 0.3) {
//            leftSlider.setPower(0.3);
//        }
//        if (!leftSlider.isBusy()) {
//            leftSlider.setPower(0);
//        }
//        rightSlider.setTargetPosition(location);
//        if (rightSlider.getMode() != DcMotor.RunMode.RUN_TO_POSITION) {
//            rightSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        }
//        if (rightSlider.getPower() != 0.3) {
//            rightSlider.setPower(0.3);
//        }
//        if (!rightSlider.isBusy()) {
//            rightSlider.setPower(0);
//        }
//    }
//    public void stopMotor(DcMotor slider) {
//        if (slider.getPower() != 0) {
//            slider.setPower(0);
//        }
//    }
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
        else { // If it's not blue team
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
    }

    /*
     * Run the opmode
     */
    @Override
    public void runOpMode() {
        initialize();
        waitForStart();
        leftRoller.setPower(-1);
        rightRoller.setPower(1);
        moveSliders.move(-1000, leftSlider);
        moveSliders.move(1000, rightSlider);
        while(leftSlider.isBusy()||rightSlider.isBusy()){
            telemetry.addData("Left Slider", leftSlider.getCurrentPosition());
            telemetry.addData("Right Slider", rightSlider.getCurrentPosition());
            telemetry.addData("Left Busy", leftSlider.isBusy());
            telemetry.addData("Right Busy", rightSlider.isBusy());
            telemetry.update();
        }
//        stopMotor(leftSlider);
//        stopMotor(rightSlider);
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
                    moveSliders.move(0, leftSlider);
                    moveSliders.move(0, rightSlider);
                } else {
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