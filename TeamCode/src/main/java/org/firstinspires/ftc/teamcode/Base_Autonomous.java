package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Processors.Base_Processor;
//import org.firstinspires.ftc.teamcode.Processors.Latching_Auto_Processor;
//import org.firstinspires.ftc.teamcode.Processors.Latching_Processor;
import org.firstinspires.ftc.teamcode.Processors.Depo_Movement_Auto_Processor;
import org.firstinspires.ftc.teamcode.Processors.Latching_Auto_Processor;
import org.firstinspires.ftc.teamcode.Processors.Latching_Movement_Auto_Processor;
import org.firstinspires.ftc.teamcode.Processors.Processor;
import org.firstinspires.ftc.teamcode.Processors.Sampling_Auto_Processor;
import org.firstinspires.ftc.teamcode.Processors.Sampling_Movement_Auto_Processor;
import org.firstinspires.ftc.teamcode.Processors.Team_Marker_Auto_Processor;

import java.util.Arrays;
import java.util.List;

public class Base_Autonomous extends LinearOpMode {
    public boolean latchmode;
    public static Hardware_Pushbot hardwarePushbot= new Hardware_Pushbot();
    List<Base_Processor> Autonomous_processors;

    @Override

    public void runOpMode() {
        hardwarePushbot.init(this.hardwareMap);
        Autonomous_processors = Arrays.asList(
                new Latching_Auto_Processor(this),
                new Latching_Movement_Auto_Processor(this, true),
                new Sampling_Movement_Auto_Processor(this),
                new Sampling_Auto_Processor(this),
                new Depo_Movement_Auto_Processor(this),
                new Team_Marker_Auto_Processor(this)
                /*new GlyphClawAutoProcessor(this),

                new PictographProcessor(this),

                new JewelSensorAutoArmProcessor(this, baseColor),

                new GlyphEncoderAutoProcessors(this, baseColor, relicSide)
*/

        );



        for (Processor processor : Autonomous_processors) {

            processor.init();

        }

        // Send telemetry message to signify robot waiting;

        telemetry.addData("Say", "Hello Driver");    //

        telemetry.update();



        // Wait for the game to start (driver presses PLAY)

        waitForStart();



        // run until the end of the match (driver presses STOP)

        for (Processor processor : Autonomous_processors) {

            if (opModeIsActive()) {
                processor.process();
            }

            // Pace this loop so jaw action is reasonable speed.

            sleep(50);

            telemetry.update();



        }

    }







    public Processor  getProcessors(Class clazz){

        for (Processor processor : Autonomous_processors)
            if (clazz.isInstance(processor)) {

                return processor;

            }

        return null;

    }



}