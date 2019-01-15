package org.firstinspires.ftc.teamcode.TestProcessors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor.ModeType.Turn45antiClockWise;
import static org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor.ModeType.Turn90antiClockWise;

@Autonomous(group="Autonomous", name="AntiClockwise90")
public class AntiClockwise90 extends Turn_Processor {
    public AntiClockwise90() {
        super(Turn90antiClockWise);
    }
}
