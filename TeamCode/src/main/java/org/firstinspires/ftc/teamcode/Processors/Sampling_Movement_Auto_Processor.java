package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Sampling_Movement_Auto_Processor extends Base_Processor {
    public Sampling_Movement_Auto_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {
        //getHardwarePushbot().leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //getHardwarePushbot().rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void process() {
        encoderDrive( -5,-5,9);
        sleep(1000);
        encoderDrive(-10,-10,5);
    }
}
