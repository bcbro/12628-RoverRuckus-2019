package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import static org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot.MID_SERVO;

public class Box_Movement_Processor extends Base_Processor{
    public Box_Movement_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    public double clawOffset = 0;

    final double POWER=0.05;

    final double CLAW_SPEED = 0.05;
    @Override
    public void init() {

    }

    @Override
    public void process() {
        if (getGamepad(1).dpad_left || getGamepad(1).dpad_right) {
            while (getGamepad(1).dpad_left) {
                stop();
                getHardwarePushbot().horizontalServo.setDirection(DcMotorSimple.Direction.FORWARD);
                getHardwarePushbot().horizontalServo.setPower(POWER);
            }
            while (getGamepad(1).dpad_right) {
               stop();
               getHardwarePushbot().horizontalServo.setDirection(DcMotorSimple.Direction.REVERSE);
               getHardwarePushbot().horizontalServo.setPower(POWER);
            }
            getHardwarePushbot().horizontalServo.setPower(0.00);
        }
        if (getGamepad(1).dpad_up)

            clawOffset += CLAW_SPEED;

        else if (getGamepad(1).dpad_down)

            clawOffset -= CLAW_SPEED;



        // Move both servos to new position.  Assume servos are mirror image of each other.

        clawOffset = Range.clip(clawOffset, -0.5, 0.5);

        getHardwarePushbot().verticalServo.setPosition(MID_SERVO + clawOffset);

    }
    public void stop() {
        getHardwarePushbot().horizontalServo.setPower(0);
    }
}
