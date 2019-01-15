package org.firstinspires.ftc.teamcode.TestProcessors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor.ModeType.Turn45ClockWise;
import static org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor.ModeType.Turn90ClockWise;

@Autonomous(group="Autonomous", name="Clockwise90")
public class Clockwise90 extends Turn_Processor {
    public Clockwise90() {
        super(Turn90ClockWise);
    }
}
