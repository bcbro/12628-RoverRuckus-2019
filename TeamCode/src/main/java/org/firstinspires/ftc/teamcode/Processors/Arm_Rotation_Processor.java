package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Arm_Rotation_Processor extends Base_Processor {

    private static final double STEADYARM = 0.1;
    final int SLEEP_MS = 50;
    final double MIN_POWER = 0.00;
    final double MAX_POWER = 0.45;
    double ZEROPOWER = 0;
    private boolean lastTriggerLeft = true;
    private boolean triggerOn = true;

    public Arm_Rotation_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {
        stopArm();
        getHardwarePushbot().leftrotationArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        getHardwarePushbot().rightrotationArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void process() {
        if (getGamepad(1).right_trigger > 0.00) {
            stopArm();
            moveArm(DcMotorSimple.Direction.FORWARD, getPower(getGamepad(1).right_trigger));
        } else if (getGamepad(1).left_trigger > 0.00) {
            stopArm();
            moveArm(DcMotorSimple.Direction.REVERSE, getPower(getGamepad(1).left_trigger));
        }
    }

    protected void steadyArm() {
        getHardwarePushbot().rightrotationArm.setPower(STEADYARM);
        getHardwarePushbot().leftrotationArm.setPower(STEADYARM);
    }

    protected void stopArm() {
        getHardwarePushbot().rightrotationArm.setPower(ZEROPOWER);
        getHardwarePushbot().leftrotationArm.setPower(ZEROPOWER);
    }

    protected void moveArm(DcMotorSimple.Direction direction, double POWER) {
        getHardwarePushbot().leftrotationArm.setDirection(direction);
        getHardwarePushbot().rightrotationArm.setDirection(direction);

        getHardwarePushbot().leftrotationArm.setPower(POWER);
        getHardwarePushbot().rightrotationArm.setPower(POWER);
    }

    protected double getPower(double triggerVal) {
        if (triggerVal <= 0) return 0.0;
        return MIN_POWER + triggerVal * (MAX_POWER - MIN_POWER);
    }
}
