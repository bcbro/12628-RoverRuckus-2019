package org.firstinspires.ftc.teamcode.TestProcessors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor;

import static org.firstinspires.ftc.teamcode.TestProcessors.Turn_Processor.ModeType.Turn60Clockwise;

@Autonomous(group= "Autonomous", name= "60CLockwise")
public class Clockwise60 extends Turn_Processor {
    public Clockwise60() {
        super(Turn60Clockwise);
    }
}