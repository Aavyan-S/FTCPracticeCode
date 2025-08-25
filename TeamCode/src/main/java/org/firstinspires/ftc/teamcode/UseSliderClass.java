package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "UseSliderClass", group = "Linear Opmode")
public class UseSliderClass extends LinearOpMode {
    MoveSliders moveSliders = new MoveSliders();

    public void initialize() {
        moveSliders.initialize(hardwareMap);
    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        moveSliders.moveInternal(true, telemetry);
        sleep(1000);
        moveSliders.moveInternal(false, telemetry);
        sleep(1000);
    }
}
