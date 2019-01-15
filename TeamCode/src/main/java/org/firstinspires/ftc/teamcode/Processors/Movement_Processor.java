package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Movement_Processor extends Base_Processor {
    double left;
    double right;
    double drive;
    double turn;
    double max;
    public Movement_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {
        getHardwarePushbot().leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        getHardwarePushbot().rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void process() {
        turn = getGamepad(2).right_stick_x;
        drive = getGamepad(2).left_stick_y;
        left = drive - turn;
        right = drive + turn;
        max = (Math.max(Math.abs(left), Math.abs(right)));
        if (max > 1.0) {
            left /= max;
            right /= max;
        }
        getHardwarePushbot().leftDrive.setPower(left);
        getHardwarePushbot().rightDrive.setPower(right);
        getTelemetry().addData("Move", " left[%.2f] right[%.2f]", left, right);
    }


}

