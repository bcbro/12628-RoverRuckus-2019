package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Processors.Arm_Extension_Processor;
import org.firstinspires.ftc.teamcode.Processors.Base_Processor;
//import org.firstinspires.ftc.teamcode.Processors.Arm_Extension_Processor;
import org.firstinspires.ftc.teamcode.Processors.Arm_Rotation_Processor;
import org.firstinspires.ftc.teamcode.Processors.Box_Movement_Processor;
//import org.firstinspires.ftc.teamcode.Processors.Intake_Motor_Processor;
import org.firstinspires.ftc.teamcode.Processors.Intake_Motor_Processor;
import org.firstinspires.ftc.teamcode.Processors.Latching_Processor;
//import org.firstinspires.ftc.teamcode.Processors.Movement_Processor;
import org.firstinspires.ftc.teamcode.Processors.Processor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//import org.firstinspires.ftc.teamcode.Processors.Latching_Auto_Processor;
//import org.firstinspires.ftc.teamcode.Processors.Latching_Processor;
@TeleOp(name="TeleOp", group="Pushbot:")
public class Base_TeleOp extends LinearOpMode {


    public static Hardware_Pushbot hardwarePushbot= new Hardware_Pushbot();
    List<Base_Processor> TeleOp_processors;



    @Override
    public void runOpMode() {
        try {
            hardwarePushbot.init(this.hardwareMap);
            telemetry.addData("Say","looks good");    //
            telemetry.update();
            sleep(1000);
        }catch(Exception e){
            telemetry.addData("Say", e.getMessage());    //
            telemetry.update();
            sleep(1000);
        }


        TeleOp_processors = Arrays.asList(
                new Arm_Rotation_Processor(this),
                //new Movement_Processor(this),
                new Box_Movement_Processor(this),
                new Latching_Processor(this),
                new Arm_Extension_Processor(this),
                new Intake_Motor_Processor(this)
        );



        for (Processor processor : TeleOp_processors) {

            processor.init();

            telemetry.addData("Say", "looks good" + processor.getClass().getSimpleName());    //
            telemetry.update();

        }

        // Send telemetry message to signify robot waiting;

        telemetry.addData("Say", "Hello Driver");    //

        telemetry.update();



        // Wait for the game to start (driver presses PLAY)

        waitForStart();



        // run until the end of the match (driver presses STOP)
    while (opModeIsActive()){
        for (Processor processor : TeleOp_processors) {

            if (opModeIsActive()) {
                processor.process();
            }
        }

            // Pace this loop so jaw action is reasonable speed.

            sleep(50);
        telemetry.addData("Date", new Date().toString());
        telemetry.update();


        }



    }


    public static Hardware_Pushbot getHardwarePushbot(){
        return hardwarePushbot;
    }

    public static void setHardwarePushbot(Hardware_Pushbot hardwarePushbot) {
        Base_TeleOp.hardwarePushbot = hardwarePushbot;
    }


    public Processor  getProcessors(Class clazz){

        for (Processor processor : TeleOp_processors)
            if (clazz.isInstance(processor)) {

                return processor;

            }

        return null;

    }



}