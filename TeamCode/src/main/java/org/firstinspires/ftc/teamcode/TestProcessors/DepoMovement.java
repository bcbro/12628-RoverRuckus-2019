package org.firstinspires.ftc.teamcode.TestProcessors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware_Pushbot;
import org.firstinspires.ftc.teamcode.Processors.Base_Processor;
import org.firstinspires.ftc.teamcode.Processors.Processor;
import org.firstinspires.ftc.teamcode.Processors.Robot;
import org.firstinspires.ftc.teamcode.Processors.RobotInterface;
import org.firstinspires.ftc.teamcode.Processors.Sampling_Auto_Processor;
import org.firstinspires.ftc.teamcode.Processors.Sampling_to_Depo_Auto_Processor;

import java.util.ArrayList;
import java.util.List;

@Autonomous(group="Autonomous", name="DepoMovement")
public class DepoMovement extends LinearOpMode implements RobotInterface {

    public static Hardware_Pushbot hardwarePushbot = new Hardware_Pushbot();
    public boolean latchmode;
    public boolean depo;
    List<Base_Processor> autonomous_processors;


    Robot robot;

    public DepoMovement(boolean Depo) {
        this.depo = Depo;
    }

    public DepoMovement() {


    }

    @Override

    public Hardware_Pushbot getHardware_Pushbot(){ return hardwarePushbot;}
    public Robot getRobot() {
        return robot;
    }

    public void runOpMode() {
        hardwarePushbot.init(this.hardwareMap);
        robot  = new Robot((LinearOpMode)this);
        robot.init();
        autonomous_processors = new ArrayList<Base_Processor>();
        autonomous_processors.add( new Sampling_to_Depo_Auto_Processor(this, true));
            // new Latching_Auto_Processor(this),
            //new Latching_Movement_Auto_Processor(this),
            //new Sampling_Auto_Processor(this),
            //new Sampling_to_Depo_Auto_Processor(this, depo),
            //new Retreat_Auto_Processor(this, depo),

            // new Crater_Movement_Auto_Processor(this)
                /*new GlyphClawAutoProcessor(this),

                new PictographProcessor(this),

                new JewelSensorAutoArmProcessor(this, baseColor),

                new GlyphEncoderAutoProcessors(this, baseColor, relicSide)
*/




        for (Processor processor : autonomous_processors) {

            processor.init();

        }

        // Send telemetry message to signify robot waiting;

        telemetry.addData("Say", "Hello Driver");    //

        telemetry.update();


        // Wait for the game to start (driver presses PLAY)

        waitForStart();


        // run until the end of the match (driver presses STOP)

        for (Processor processor : autonomous_processors) {

            if (opModeIsActive()) {
                processor.process();
            }

            // Pace this loop so jaw action is reasonable speed.

            sleep(50);

            telemetry.update();


        }

    }


    public Processor getProcessors(Class clazz) {

        for (Processor processor : autonomous_processors)
            if (clazz.isInstance(processor)) {

                return processor;

            }

        return null;

    }




}