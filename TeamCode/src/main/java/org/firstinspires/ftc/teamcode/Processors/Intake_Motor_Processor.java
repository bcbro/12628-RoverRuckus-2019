package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Intake_Motor_Processor extends Base_Processor {
    public Intake_Motor_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {
        getHardwarePushbot().intake_motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void process() {
        if (getGamepad(1).b) {
            intake();
        } else {
            stop();
        }
    }

    public void intake() {
        getHardwarePushbot().intake_motor.setPower(0.75);
    }

    public void stop() {
        getHardwarePushbot().intake_motor.setPower(0.00);
    }
}
