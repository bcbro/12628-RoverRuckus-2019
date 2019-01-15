package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.Base_Autonomous;

import java.util.List;

public class Sampling_Auto_Processor extends Base_Processor {


    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final int WAIT_TIME= 2000;
    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY = "AQhhuVL/////AAABmS4u0+EtRUKkhIcbaZtQ+FR6aJt5Prxx6qPt8bcrot5G4A+DLe72s9kCh6+96KCEHitv/xbbicVwP3mKh1S1+8POubfCYm6JV07zFWU8+3X1xVFfdKnnXjhPjk4myuQAPAFE9QunebEtwLvKZ3vtjMGaukeoKSsNgs9V2u11alBC7uJo7A6k4/vJ9uYpAwL98c/f/fa578GrYY5zvPlXVWPMvI/qb4GZRByHKjlQ+A6vvhxcNmS+zSAAm6reFbRqTAG97J/ZqNgurBm6HSJF6Hl/AebqhBgqQZiWGGYUXc554yRkfU5xtYLawvgH3zGAGAuHcx4eF/AVccLcamerRdMscFLcZXQ/9AmDjZyfHeQ6";
    public GoldPosition goldposition;
    double center = (double) (Math.sqrt((24 ^ 2) * 2)) / 2;
    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;
    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    public Sampling_Auto_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {

        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            getTelemetry().addData("Sorry!", "This device is not compatible with TFOD");
        }

        /** Wait for the game to begin */
        getTelemetry().addData(">", "Press Play to start tracking");
        getTelemetry().update();
    }

    @Override
    public void process() {
        if (tfod != null) {
            tfod.activate();
        }
        long startTimeMillis  = System.currentTimeMillis();
        while (true) {
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    getTelemetry().addData("# Object Detected", updatedRecognitions.size());
                    if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                goldMineralX = (int) recognition.getLeft();
                                int gold_mineral_position = (int) Math.sqrt(recognition.getLeft() * recognition.getLeft() + recognition.getRight() * recognition.getRight());
                            } else if (silverMineral1X == -1) {
                                silverMineral1X = (int) recognition.getLeft();
                            } else {
                                silverMineral2X = (int) recognition.getLeft();
                            }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                            if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                getTelemetry().addData("Gold Mineral Position", "Left");
                                goldposition = GoldPosition.LEFT;
                            }
                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            getTelemetry().addData("Gold Mineral Position", "Right");
                            goldposition = GoldPosition.RIGHT;
                        } else {
                            getTelemetry().addData("Gold Mineral Position", "Center");
                            goldposition = GoldPosition.CENTER;
                        }
                        getTelemetry().update();
                        sleep(3000);
                        if ( opMode != null  && opMode instanceof Base_Autonomous  && ((Base_Autonomous) opMode).getProcessors(Sampling_to_Depo_Auto_Processor.class) != null) {
                            ((Sampling_to_Depo_Auto_Processor) ((Base_Autonomous) opMode)
                                    .getProcessors(Sampling_to_Depo_Auto_Processor.class))
                                    .setGoldPosition(goldposition);
                        }
                    }

                }
            }
            if (System.currentTimeMillis() - startTimeMillis > WAIT_TIME){
                break;
            }
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public enum GoldPosition {
        LEFT,
        CENTER,
        RIGHT
    }
}
