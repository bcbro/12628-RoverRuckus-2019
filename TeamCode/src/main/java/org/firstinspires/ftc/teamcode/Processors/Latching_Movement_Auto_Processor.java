package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Latching_Movement_Auto_Processor extends Base_Processor {
    public Latching_Movement_Auto_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {
        getHardwarePushbot().leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        getHardwarePushbot().rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void process() {
        encoderDrive(-2, -2, 6);
        getHardwarePushbot().leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        getHardwarePushbot().rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
