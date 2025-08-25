package org.firstinspires.ftc.teamcode;

public class UseConstants {
    public boolean getColorAndRetractState(String color) {
        switch (color) {
            case "Red":
                return true;
            case "Blue":
                return false;
            case "Green":
                return false;
            case "Yellow":
                return true;
            default:
                return false;
        }
    }
    public boolean hasSample(int dist) {
        if (dist < Constants.NO_SAMPLE_DIST) {
            System.out.println("Has Sample");
            return true;
        }
        else {
            System.out.println("No Sample");
            return false;
        }
    }
    public void extendIntake(boolean has_sample) {
        if (has_sample) {
            System.out.println("Retracting Intake to: "+Constants.RETRACT_INTAKE_SLIDER_POS);
        }
        else {
            System.out.println("Extending Intake to: "+Constants.EXTEND_INTAKE_SLIDER_POS);
        }

    }
    public void UseMethods() {
        extendIntake(hasSample(5)&&getColorAndRetractState("Red"));
    }
}
