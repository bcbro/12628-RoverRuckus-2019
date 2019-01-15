package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Team_Marker_Auto_Processor extends Base_Processor {
    public static final long SLEEPTIME = 50;
    static final double INCREMENT = 0.10;
    static final double MAX_POS = 1.0;     // Maximum rotational position
    static final double MIN_POS = 0.40;
    public double POSITION = 1.0;
    public double CURRENT_POSITION;
    public Team_Marker_Auto_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init() {
        getHardwarePushbot().teamMarker.setPosition(MAX_POS);
    }

    @Override
    public void process() {

        getHardwarePushbot().teamMarker.setPosition(MAX_POS);
        CURRENT_POSITION=getHardwarePushbot().teamMarker.getPosition();
        while (CURRENT_POSITION > MIN_POS) {
            CURRENT_POSITION=getHardwarePushbot().teamMarker.getPosition();
            POSITION -= INCREMENT;
            getHardwarePushbot().teamMarker.setPosition(POSITION);
            sleep(SLEEPTIME);
        }
    }
}
