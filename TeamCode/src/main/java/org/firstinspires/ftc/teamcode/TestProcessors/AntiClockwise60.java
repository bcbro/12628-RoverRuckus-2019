package org.firstinspires.ftc.teamcode.TestProcessors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor.ModeType.Turn60antiClockwise;

@Autonomous(group= "Autonomous", name= "60AntiCLockwise")
public class AntiClockwise60 extends Turn_Processor {
    public AntiClockwise60() {
        super(Turn60antiClockwise);
    }
}
