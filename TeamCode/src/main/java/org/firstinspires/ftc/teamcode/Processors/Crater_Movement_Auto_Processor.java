package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Crater_Movement_Auto_Processor extends Base_Processor {
    public Crater_Movement_Auto_Processor(LinearOpMode opMode, boolean depo) {
        super(opMode);
    }

    @Override
    public void init() {

    }

    @Override
    public void process() {
        Robot robot = ((RobotInterface) opMode).getRobot();
        robot.turn90(this, false);
        robot.straight(this, false, Robot.MAT_DIGONAL * 4 - (2 * Robot.HALF_ROBOT));

        //encoderDrive(-62, 62, 13);
    }
}
