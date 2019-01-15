package org.firstinspires.ftc.teamcode.TestProcessors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor;

import static org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor.ModeType.Turn45ClockWise;

@Autonomous(group="Autonomous", name="Clockwise45")
public class Clockwise45 extends Turn_Processor {
    public Clockwise45() {
        super(Turn45ClockWise);
    }
}
