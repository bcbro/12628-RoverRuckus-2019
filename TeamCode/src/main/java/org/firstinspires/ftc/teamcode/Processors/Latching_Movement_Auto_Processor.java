package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Latching_Movement_Auto_Processor extends Base_Processor{
    public boolean back_up;
    public Latching_Movement_Auto_Processor(LinearOpMode opMode, boolean back_up) {
        super(opMode);
        this.back_up=back_up;
    }

    @Override
    public void init() {
        //getHardwarePushbot().leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //getHardwarePushbot().rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void process() {
        if(back_up) {
            encoderDrive(-1,-1,3);
        }
        if(!back_up) {
            encoderDrive(1,1,3);
        }
    }
}
