package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Latching_Auto_Processor extends Base_Processor {

    public Latching_Auto_Processor(LinearOpMode opMode) {
        super(opMode);
    }
    public int SLEEPTIME=1000;


    @Override
    public void init() {

    }

    @Override
    public void process() {
        expand();
        unlatch();
    }

    public void unlatch() {
        getHardwarePushbot().latchServo.setPosition(0.45);
    }

    public void expand() {
        getHardwarePushbot().latching_linear.setPower(0);
        getHardwarePushbot().latching_linear.setDirection(DcMotorSimple.Direction.REVERSE);
        getHardwarePushbot().latching_linear.setPower(0.5);
        sleep(SLEEPTIME);
    }

    public void latch() {
        getHardwarePushbot().latchServo.setPosition(1.00);
    }

    public void pullup() {
        getHardwarePushbot().latching_linear.setPower(0.00);
        getHardwarePushbot().latching_linear.setDirection(DcMotorSimple.Direction.FORWARD);
        getHardwarePushbot().latching_linear.setPower(0.5);
        sleep(SLEEPTIME);
    }

}
