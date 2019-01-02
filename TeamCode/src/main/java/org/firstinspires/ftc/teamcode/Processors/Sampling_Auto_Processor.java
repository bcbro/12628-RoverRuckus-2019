package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.Base_Autonomous;

import java.util.List;

public class Sampling_Auto_Processor extends Base_Processor {


    public GoldPosition goldposition;
    double center=(double) (Math.sqrt((24^2)*2))/2;

    public Sampling_Auto_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    public enum GoldPosition {
        LEFT,
        CENTER,
        RIGHT
    }


    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

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
    private static final String VUFORIA_KEY = "AfobB13/////AAABmenfOD8vu0t4ke6OE69Toxdd56dgDMJq3WTTqNS3A/n5Yg8BM3quaauTXc3/5fkFr56gXsUFI378tS9ZyYKEj+ERcEb2pIA8h06lveNCSzycC0ZUl39EhlbI75A+uLm++vCFQi5hvuNWVOwqlu6sAkXkUR2ft0a13rMyTqFIU5ML6RbOZ//+/qM6tt9tQ0UNshQTzrPW0JxlEOCyZZEXtCd1VPKIrobrogmdzcBK0J3bkSFh9CtlsUtcPo/SIgRgM/xS2d7Bc5/OIz+2qgyMWJKEvUxrS6jNx9EYukQhNEOWtyq3Y3u9vxG/YP4BOuqtxL4IfjjiH/Lq5olW+08rmRC8obftJtO5XAFydRq+ers1";

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
                                encoderDrive(-6,6,2);
                                encoderDrive(-24,-24,6);
                                if ((Base_Autonomous) opMode != null && ((Base_Autonomous) opMode).getProcessors(Depo_Movement_Auto_Processor.class) != null) {
                                    ((Depo_Movement_Auto_Processor) ((Base_Autonomous) opMode)
                                            .getProcessors(Depo_Movement_Auto_Processor.class))
                                            .setGoldPosition(goldposition);
                                }
                            } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                getTelemetry().addData("Gold Mineral Position", "Right");
                                goldposition = GoldPosition.RIGHT;
                                encoderDrive(6,-6,2);
                                encoderDrive(-24,-24,6);
                                if ((Base_Autonomous) opMode != null && ((Base_Autonomous) opMode).getProcessors(Depo_Movement_Auto_Processor.class) != null) {
                                    ((Depo_Movement_Auto_Processor) ((Base_Autonomous) opMode)
                                            .getProcessors(Depo_Movement_Auto_Processor.class))
                                            .setGoldPosition(goldposition);
                                }
                            } else {
                                getTelemetry().addData("Gold Mineral Position", "Center");
                                goldposition = GoldPosition.CENTER;
                                encoderDrive(center, center, 4);
                                if ((Base_Autonomous) opMode != null && ((Base_Autonomous) opMode).getProcessors(Depo_Movement_Auto_Processor.class) != null) {
                                    ((Depo_Movement_Auto_Processor) ((Base_Autonomous) opMode)
                                            .getProcessors(Depo_Movement_Auto_Processor.class))
                                            .setGoldPosition(goldposition);
                                }
                            }
                        }
                    }

                }
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
}
