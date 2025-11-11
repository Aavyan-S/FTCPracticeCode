import org.firstinspires.ftc.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.hardware.Servo;

@TeleOp(name = "Color Sorting", group = "Decode")
public class ColorSorting extends OpMode {
    private ColorSensor colorSensor;
    private Servo sorter;
    private String lastDetectedColor = "Other";
    private double spinStartTime = 0;
    private boolean isSpinning = false;
    private static final double SPIN_DURATION = 1.0; // Duration in seconds for one spin
    
    // Array to store colors for each of the three compartments
    private String[] compartmentColors = new String[3];
    private int currentCompartment = 0; // Track which compartment we're currently at (0, 1, or 2)
    private String pendingColor = null; // Color to store after spin completes
    
    public void init() {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        colorSensor.enableLed(true);
        sorter = hardwareMap.get(Servo.class, "sorter");
        sorter.setPosition(0.5); // Initialize to center/stop position
        
        // Initialize all compartments to "Empty"
        for (int i = 0; i < compartmentColors.length; i++) {
            compartmentColors[i] = "Empty";
        }
        
        telemetry.addData("Status", "Initialized");
    }
    
    public void loop() {
        // Read RGB values from the color sensor
        int red = colorSensor.red();
        int green = colorSensor.green();
        int blue = colorSensor.blue();
        
        // Determine the detected color
        String detectedColor = detectColor(red, green, blue);
        
        // Check if color just changed to Green or Purple (one-time trigger)
        boolean colorJustDetected = !detectedColor.equals(lastDetectedColor) && 
                                    (detectedColor.equals("Green") || detectedColor.equals("Purple"));
        
        // Start spinning when a color is first detected
        if (colorJustDetected && !isSpinning) {
            spinStartTime = getRuntime();
            isSpinning = true;
            pendingColor = detectedColor; // Store the color to save after spin completes
            // For continuous rotation servo: 1.0 = full speed one way, 0.5 = stop
            // For regular servo: 0.0 to 1.0 = different positions
            // Adjust these values based on your servo type and desired rotation direction
            sorter.setPosition(1.0); // Start spinning
        }
        
        // Stop spinning after the duration has elapsed
        if (isSpinning && (getRuntime() - spinStartTime >= SPIN_DURATION)) {
            isSpinning = false;
            sorter.setPosition(0.5); // Stop (0.5 for continuous rotation, or 0.0 for regular servo)
            
            // Store the detected color in the current compartment
            if (pendingColor != null) {
                compartmentColors[currentCompartment] = pendingColor;
                pendingColor = null;
                
                // Move to the next compartment (cycle through 0, 1, 2)
                currentCompartment = (currentCompartment + 1) % 3;
            }
        }
        
        // Update last detected color
        lastDetectedColor = detectedColor;
        
        // Display RGB values and detected color
        telemetry.addData("Red", red);
        telemetry.addData("Green", green);
        telemetry.addData("Blue", blue);
        telemetry.addData("Detected Color", detectedColor);
        telemetry.addData("Servo Position", sorter.getPosition());
        telemetry.addData("Is Spinning", isSpinning);
        telemetry.addLine();
        telemetry.addData("Current Compartment", currentCompartment);
        telemetry.addData("Compartment 0", compartmentColors[0]);
        telemetry.addData("Compartment 1", compartmentColors[1]);
        telemetry.addData("Compartment 2", compartmentColors[2]);
        telemetry.update();
    }
    
    /**
     * Determines if the detected color is green, purple, or other
     * @param red Red component value (0-255)
     * @param green Green component value (0-255)
     * @param blue Blue component value (0-255)
     * @return "Green", "Purple", or "Other"
     */
    private String detectColor(int red, int green, int blue) {
        // Calculate total brightness to avoid false positives on very dark readings
        int totalBrightness = red + green + blue;
        
        // If the sensor reading is too dark, return "Other"
        if (totalBrightness < 50) {
            return "Other";
        }
        
        // Normalize values to percentages for comparison
        double redPercent = (double) red / totalBrightness;
        double greenPercent = (double) green / totalBrightness;
        double bluePercent = (double) blue / totalBrightness;
        
        // Green detection: Green should be dominant (typically >40% of total)
        // and significantly higher than both red and blue
        if (greenPercent > 0.4 && green > red && green > blue && green > (red + blue) * 0.6) {
            return "Green";
        }
        
        // Purple detection: Purple is a mix of red and blue
        // Both red and blue should be relatively high, and green should be lower
        // Typically red and blue together make up most of the color
        if ((redPercent + bluePercent) > 0.6 && 
            greenPercent < 0.3 && 
            Math.abs(red - blue) < (red + blue) * 0.4) {
            return "Purple";
        }
        
        // If neither green nor purple is detected, return "Other"
        return "Other";
    }
}