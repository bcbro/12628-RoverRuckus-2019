package org.firstinspires.ftc.teamcode.TestProcessors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor;

import static org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor.ModeType.Turn45antiClockWise;

@Autonomous(group="Autonomous", name="AntiClockwise45")
public class AntiClockwise45 extends Turn_Processor {
    public AntiClockwise45() {
        super(Turn45antiClockWise);
    }
}
