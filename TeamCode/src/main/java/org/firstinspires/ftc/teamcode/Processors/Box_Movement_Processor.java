package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Box_Movement_Processor extends Base_Processor {

    final double POWER = 0.05;
    final double CLAW_SPEED = 0.05;
    public boolean lastYUp;
    public double clawOffset = 0;
    public boolean lastXLeft = true;

    public Box_Movement_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {

    }

    @Override
    public void process() {
        if (getGamepad(1).left_stick_y > 0.00) {
            double Position = getHardwarePushbot().verticalServo.getPosition() - 0.05;
            getHardwarePushbot().verticalServo.setPosition(Position);
        } else if (getGamepad(1).left_stick_y < 0.00) {
            double Position = getHardwarePushbot().verticalServo.getPosition() + 0.05;
            getHardwarePushbot().verticalServo.setPosition(Position);
        }
        getTelemetry().addData("Servo Position", getHardwarePushbot().verticalServo.getPosition());
    }
}
