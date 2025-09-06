package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "Test Intake", group = "Test")
public class testintake extends LinearOpMode {
    /*
     * Create the hardware variables
     */
    private MoveSliders moveSliders = new MoveSliders();
    private ExtendSlider exSlider = new ExtendSlider();
//    private DcMotor leftSlider;
//    private DcMotor rightSlider;
    private CRServo leftRoller;
    private CRServo rightRoller;
    private Servo clawServo;
    private Servo flipServo;
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;
    private boolean isIntaking=false;
    private boolean hasSample=false;
    private int repeatCounter = 0;

    /*
     * Initialize the hardware variables
     */
    public void initialize() {
//        leftSlider = hardwareMap.get(DcMotor.class, "leftSlider");
//        rightSlider = hardwareMap.get(DcMotor.class, "rightSlider");
//        leftSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        colorSensor = hardwareMap.get(ColorSensor.class, "colorV3");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "colorV3");
        leftRoller = hardwareMap.get(CRServo.class, "leftRoller");
        rightRoller = hardwareMap.get(CRServo.class, "rightRoller");
        clawServo=hardwareMap.get(Servo.class, "clawServo");
        clawServo.setPosition(0.2);
        flipServo=hardwareMap.get(Servo.class, "flipServo");
        flipServo.setPosition(0.0);
        moveSliders.initialize(hardwareMap);
        exSlider.initialize(hardwareMap);
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
    public boolean containsSample(){
        if (distanceSensor.getDistance(DistanceUnit.CM) < 4.1) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean getSampleColor(boolean isBlueTeam) {
        if (isBlueTeam) {
            if (colorSensor.blue() > colorSensor.red()&&colorSensor.blue() > colorSensor.green()&&colorSensor.blue()>100) {
                return true;
            }
            else if (colorSensor.green() > colorSensor.red()&&colorSensor.green() > colorSensor.blue()&&colorSensor.green()>150) {
                return true;
            }
            else {
                return false;
            }
        }
        else { // If it's not blue team
            if (colorSensor.red() > colorSensor.blue()&&colorSensor.red() > colorSensor.green()&&colorSensor.red()>150) {
                return true;
            }
            else if (colorSensor.green() > colorSensor.red()&&colorSensor.green() > colorSensor.blue()&&colorSensor.green()>150) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    public void clawOpenClose(boolean open) {
        if (open) clawServo.setPosition(0.2); else clawServo.setPosition(0.0);
    }
    public void flipUpDown(boolean up) {
        if (up) flipServo.setPosition(1.0); else flipServo.setPosition(0.0);
    }
    public void intake() {
        moveSliders.moveInternal(true, telemetry);
//        telemetry.addLine("Sliders Extended");
        telemetry.update();
        leftRoller.setPower(-1);
        rightRoller.setPower(1);
//        telemetry.addLine("Roller Power Set");
        telemetry.update();
        isIntaking=true;
        if (containsSample())
        {
            if(getSampleColor(false))
            {
                leftRoller.setPower(-1);
                rightRoller.setPower(1);
                sleep(1000);
                leftRoller.setPower(0);
                rightRoller.setPower(0);
//                telemetry.addLine("Retracting");
                moveSliders.moveInternal(false, telemetry);
                sleep(1000);
                hasSample=true;
            }
            else {
//                telemetry.addLine("Throwing Out");
                rightRoller.setPower(-1);
                leftRoller.setPower(1);
                sleep(2000);
                rightRoller.setPower(1);
                leftRoller.setPower(-1);
                hasSample=false;
            }
        }
        isIntaking=false;
    }

    public void outtake()
    {
        clawOpenClose(false);
        sleep(1000);
        exSlider.move_slider(-2000, telemetry);
        exSlider.keep_pos();
        flipUpDown(true);
        sleep(1500);
        clawOpenClose(true);
        sleep(200);
        flipUpDown(false);
        sleep(1000);
        exSlider.move_slider(2000, telemetry);
    }

    /*
     * Run the opmode
     */
//    @Override
//    public void runOpMode()
//    {
//        initialize();
//        waitForStart();
//        sleep(5000);
//        while (opModeIsActive())
//        {
//            if(gamepad1.a) {
//                clawOpenClose(true);
//            }
//            if (gamepad1.b)
//            {
//                exSlider.move_slider(-2320, telemetry);
//            }
//            if (gamepad1.x)
//            {
//                exSlider.move_slider(2320, telemetry);
//            }
//            if (gamepad1.y)
//            {
//                clawOpenClose(false);
//            }
//            if (gamepad1.dpad_up)
//            {
//                flipUpDown(true);
//            }
//            if (gamepad1.dpad_down)
//            {
//                flipUpDown(false);
//            }
//        }

//        exSlider.move_slider(-2320, telemetry);
//        flipUpDown(true);
//        clawOpenClose(false);
//        flipUpDown(false);
//        exSlider.move_slider(2320, telemetry);
//    }
//}
    public void runOpMode() {
        initialize();
        waitForStart();
        //sleep(10000);
//        stopMotor(leftSlider);
//        stopMotor(rightSlider);
        while (opModeIsActive()) {
            repeatCounter++;
            telemetry.addData("RepeatCounter", repeatCounter);
            telemetry.update();
            intake();
            telemetry.addLine("still intaking");
            telemetry.update();
            while (isIntaking)
            {
                telemetry.addLine("Intaking");
                telemetry.update();
            }
            telemetry.addLine("done intaking");
            telemetry.update();
//            sleep(2000);
//            telemetry.addData("Contains Sample", containsSample());
//            telemetry.addData("Distance", distanceSensor.getDistance(DistanceUnit.CM));
//            telemetry.addData("Status", getSampleColor(false));
//            telemetry.addData("Color Red", colorSensor.red());
//            telemetry.addData("Color Blue", colorSensor.blue());
//            telemetry.addData("Color Green", colorSensor.green());
//            telemetry.update();
            telemetry.addData("Has Sample before outtake", hasSample);
            telemetry.update();
            if (hasSample) {
                outtake();
                hasSample=false;
            }
        }
        telemetry.addLine("Outside of main loop");
        telemetry.update();
    }
}
