package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Latching_Processor extends Base_Processor {
    final int SLEEPTIME = 3000;
    final double START_POSITION = 0.48;
    final double LATCH_POSITION= 0.8;

    int LATCH_WAIT_TIME = 2000;
    boolean latchOn = false;

    long lastlatchMs =  0;

    public boolean expandlast;


    public Latching_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {
    }

    @Override
    public void process() {
        if (getGamepad(2).a) {
           // unlatch();
           // expand();
            //sleep(SLEEPTIME);
            getHardwarePushbot().latching_linear.setPower(0.00);


            if (System.currentTimeMillis() - lastlatchMs  > LATCH_WAIT_TIME) {
                if (latchOn) {
                    unlatch();
                } else if (!latchOn) {
                    latch();
                }
            }
            //pullup();
            //sleep(SLEEPTIME);
        }
        if (getGamepad(2).left_bumper) {
            pullup();
        }
        else if (getGamepad(2).right_bumper) {
            expand();
        }
        if(getGamepad(2).y){
            stop();
        }
    }

    public void unlatch() {
        latchOn=false;
        lastlatchMs  = System.currentTimeMillis();
        getHardwarePushbot().latchServo.setPosition(START_POSITION);
    }

    public void expand() {
        if(!expandlast) {
            getHardwarePushbot().latching_linear.setPower(0);
        }
        getHardwarePushbot().latching_linear.setDirection(DcMotorSimple.Direction.FORWARD);
        getHardwarePushbot().latching_linear.setPower(1);
        expandlast=true;
    }

    public void latch() {
        latchOn=true;
        lastlatchMs  = System.currentTimeMillis();
        getHardwarePushbot().latchServo.setPosition(LATCH_POSITION);
    }

    public void pullup() {
        if(expandlast) {
            getHardwarePushbot().latching_linear.setPower(0);
        }
        getHardwarePushbot().latching_linear.setDirection(DcMotorSimple.Direction.REVERSE);
        getHardwarePushbot().latching_linear.setPower(1);
        expandlast=false;
    }
    public void stop() {
        getHardwarePushbot().latching_linear.setPower(0);
    }

}
