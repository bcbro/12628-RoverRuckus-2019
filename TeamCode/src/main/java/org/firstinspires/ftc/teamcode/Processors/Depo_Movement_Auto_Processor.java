package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Base_Autonomous;

public class Depo_Movement_Auto_Processor extends Base_Processor{

    Sampling_Auto_Processor.GoldPosition goldPosition;

    public Depo_Movement_Auto_Processor(LinearOpMode opMode, Sampling_Auto_Processor.GoldPosition goldPosition) {
        super(opMode);
    }

    public Depo_Movement_Auto_Processor(LinearOpMode opMode) {
        super(opMode);
    }

    public void setGoldPosition(Sampling_Auto_Processor.GoldPosition goldPosition) {
        this.goldPosition = goldPosition;
    }

    @Override

    public void init() {

    }

    @Override
    public void process() {

    }
}
