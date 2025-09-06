package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp()
public class FTCOdometry extends LinearOpMode {
    GoBildaPinpointDriver pinpointDriver;
    public void runOpMode() {
        pinpointDriver = hardwareMap.get(GoBildaPinpointDriver.class, "pinpointDriver");
        pinpointDriver.setOffsets(66.0,42.0, DistanceUnit.MM);
        pinpointDriver.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        pinpointDriver.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);//Forward: X+, Left: Y+
        pinpointDriver.resetPosAndIMU();
        pinpointDriver.setPosition(new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0));
        waitForStart();
        while (opModeIsActive()) {
            pinpointDriver.update();
            Pose2D pose2D = pinpointDriver.getPosition();
            telemetry.addData("X (CM)", pose2D.getX(DistanceUnit.CM));
            telemetry.addData("Y (CM)", pose2D.getY(DistanceUnit.CM));
            telemetry.addData("A (Deg)", pose2D.getHeading(AngleUnit.DEGREES));
            telemetry.update();
        }
    }
}
