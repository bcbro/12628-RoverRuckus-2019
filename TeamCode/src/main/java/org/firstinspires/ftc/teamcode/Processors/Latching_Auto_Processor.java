package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Hardware_Pushbot;

public class Latching_Auto_Processor extends Base_Processor implements RobotInterface{

    public int SLEEPTIME = 3500;


    final double START_POSITION = 0.1;
    final double LATCH_POSITION= 0.8;

    public Latching_Auto_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {
        opMode.telemetry.addData(" Latching_Auto_Processor init :", "start");
        opMode.telemetry.update();

        latch();
        getHardwarePushbot().latchServo.setPosition(LATCH_POSITION);
    }

    @Override
    public void process() {
        Robot  robot =  ((RobotInterface)opMode).getRobot();
        expand();
        unlatch();
        getTelemetry().addData("Power", String.valueOf(getHardwarePushbot().latching_linear.getPower()));
    }

    public void unlatch() {
        getHardwarePushbot().latchServo.setPosition(START_POSITION);
    }

    public void expand() {
        opMode.telemetry.addData(" Latching_Auto_Processor expand :", "start");
        opMode.telemetry.update();
        getHardwarePushbot().latching_linear.setDirection(DcMotorSimple.Direction.FORWARD);
        getHardwarePushbot().latching_linear.setPower(1.00);
        opMode.telemetry.addData(" Latching_Auto_Processor expand :", "sleep");
        opMode.telemetry.update();
        sleep(SLEEPTIME);
       // getHardwarePushbot().latching_linear.setPower(0);
        opMode.telemetry.addData(" Latching_Auto_Processor expand :", "done");
        opMode.telemetry.update();
    }

    public void latch() {
        getHardwarePushbot().latchServo.setPosition(LATCH_POSITION);
    }

    public void pullup() {
        getHardwarePushbot().latching_linear.setDirection(DcMotorSimple.Direction.REVERSE);
        getHardwarePushbot().latching_linear.setPower(1.00);
        sleep(SLEEPTIME);
        getHardwarePushbot().latching_linear.setPower(0);
    }


    @Override
    public Robot getRobot() {
        return null;
    }

    @Override
    public Hardware_Pushbot getHardware_Pushbot() {
        return null;
    }
}
