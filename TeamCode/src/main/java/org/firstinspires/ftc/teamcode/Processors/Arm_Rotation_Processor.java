package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Base_Autonomous;

public class Arm_Rotation_Processor extends Base_Processor {

    public Arm_Rotation_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    double ZEROPOWER = 0;

    private static final double STEADYARM = 0.1;

    final int SLEEP_MS = 50;

    final double MIN_POWER = 0.10;

    final double MAX_POWER = 0.60;

    public double dif=MAX_POWER-MIN_POWER;

    private boolean lastTriggerLeft = true;

    private boolean triggerOn = true;


    @Override
    public void init() {
        stopArm();
        getHardwarePushbot().leftrotationArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        getHardwarePushbot().rightrotationArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void process() {
        if (getGamepad(1).x) {

            triggerOn = true;

        } else if (getGamepad(1).y) {

            triggerOn = false;

            getHardwarePushbot().leftrotationArm.setPower(ZEROPOWER);
            getHardwarePushbot().rightrotationArm.setPower(ZEROPOWER);

        }
        if (getGamepad(1).left_stick_y>0.00) {
            stopArm();
            double trigger_val=(getGamepad(1).left_stick_y)*dif;
            double POWER=0.2+trigger_val;
            moveArm(DcMotorSimple.Direction.FORWARD, POWER);
        } else if (getGamepad(1).left_stick_y<0.00) {
            stopArm();
            double trigger_val=(getGamepad(1).left_stick_y)*dif;
            double POWER=0.2+trigger_val;
            moveArm(DcMotorSimple.Direction.REVERSE, POWER);
        }else if (getGamepad(1).left_stick_y==0.00){
            //steadyArm();
        }
    }

    protected void steadyArm() {
        getHardwarePushbot().rightrotationArm.setPower(STEADYARM);
        getHardwarePushbot().leftrotationArm.setPower(STEADYARM);
    }

    protected void stopArm (){

        getHardwarePushbot().rightrotationArm.setPower(ZEROPOWER);
        getHardwarePushbot().leftrotationArm.setPower(ZEROPOWER);
    }

    protected void moveArm(DcMotorSimple.Direction direction, double POWER){
        getHardwarePushbot().leftrotationArm.setDirection(direction);
        getHardwarePushbot().rightrotationArm.setDirection(direction);

        getHardwarePushbot().leftrotationArm.setPower(POWER);
        getHardwarePushbot().rightrotationArm.setPower(POWER);
    }
}
