package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Team_Marker_Auto_Processor extends Base_Processor{
    public static final long SLEEPTIME = 50;

    public Team_Marker_Auto_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    public double POSITION=0.45;
    static final double INCREMENT   = 0.05;
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.45;

    @Override
    public void init() {

    }

    @Override
    public void process() {
        getHardwarePushbot().teamMarker.setPosition(MAX_POS);
        while (POSITION>MIN_POS) {
            POSITION -=INCREMENT;
            getHardwarePushbot().teamMarker.setPosition(POSITION);
            sleep(SLEEPTIME);
        }
    }
}
