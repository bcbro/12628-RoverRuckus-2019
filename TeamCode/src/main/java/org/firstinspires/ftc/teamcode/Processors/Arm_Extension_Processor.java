package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Arm_Extension_Processor extends Base_Processor {
    final int SLEEP_MS = 50;
    final double MIN_POWER = 0.10;
    final double MAX_POWER = 0.60;
    double zeropower = 0;
    private boolean lastTriggerLeft = true;
    private boolean triggerOn = true;

    public Arm_Extension_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {
        getHardwarePushbot().extensionArm.setPower(0);
        getHardwarePushbot().extensionArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void process() {
            // Use gamepad left & right trigger  to move the arm up and down
            if (getGamepad(1).left_trigger > 0.0) {
                if (!lastTriggerLeft) {
                    stopArm();
                    lastTriggerLeft = true;
                }
                getHardwarePushbot().extensionArm.setPower(getPower(getGamepad(1).left_trigger));

                getHardwarePushbot().extensionArm.setDirection(DcMotor.Direction.REVERSE);

            } else if (getGamepad(1).right_trigger > 0.0) {

                if (lastTriggerLeft) {

                    stopArm();

                    lastTriggerLeft = false;

                }

                getHardwarePushbot().extensionArm.setPower(getPower(getGamepad(1).right_trigger));

                getHardwarePushbot().extensionArm.setDirection(DcMotor.Direction.FORWARD);

            } else {

                getHardwarePushbot().extensionArm.setPower(zeropower);

            }

            getTelemetry().addData("Trigger", " left[%.2f] right[%.2f] power [%.2f] [%s]",

                    getGamepad(1).left_trigger, getGamepad(1).right_trigger,

                    getHardwarePushbot().extensionArm.getPower(), getHardwarePushbot().extensionArm.getDirection().name());


        }


    protected void stopArm() {
        getHardwarePushbot().extensionArm.setPower(0);
        sleep(SLEEP_MS);

    }


    protected double getPower(double triggerVal) {
        if (triggerVal <= 0) return 0.0;
        return MIN_POWER + triggerVal * (MAX_POWER - MIN_POWER);

    }
}
