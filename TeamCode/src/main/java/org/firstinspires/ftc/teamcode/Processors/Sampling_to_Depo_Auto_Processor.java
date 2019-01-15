package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Sampling_to_Depo_Auto_Processor extends Base_Processor {


    public Sampling_Auto_Processor.GoldPosition goldPosition = Sampling_Auto_Processor.GoldPosition.CENTER;
    double center = (double) 36.8180374315;

    private boolean depo;

    public Sampling_to_Depo_Auto_Processor(LinearOpMode opMode, boolean depo) {
        super(opMode);
        this.depo = depo;
    }

    @Override
    public void init() {

    }

    @Override
    public void process() {

        Robot robot = ((RobotInterface) opMode).getRobot();


        if (depo) {

            if (goldPosition == Sampling_Auto_Processor.GoldPosition.CENTER) {
                robot.straight(this, false, Robot.MAT_DIGONAL * 2.0 - (12.56 + (2 * Robot.HALF_ROBOT))-10);//knocks off jewel along th side of one tile
            } else if (goldPosition == Sampling_Auto_Processor.GoldPosition.LEFT) {

                robot.turn45(this, false);//45 degree anti clockwise

                robot.moveXto(this, false, 2 * Robot.MAT_SIZE -2.0* Robot.HALF_ROBOT);//knocks off jewel along th side of one tile

                robot.turn60(this, false);//90 clockwise
                robot.moveYto(this, true, -2.0 * Robot.MAT_SIZE);

            } else if (goldPosition == Sampling_Auto_Processor.GoldPosition.RIGHT) {
                robot.turn45(this, true);//45 degree clockwise
                //Adding fudge
                robot.moveYto(this, false, -1.0 * (2.5 * Robot.MAT_SIZE - Robot.HALF_ROBOT + 3.0));//knocks off jewel along th side of one tile

                //retreat
                robot.moveYto(this, true, -Robot.MAT_SIZE); // Fix error rate as move to 23.5


                robot.turn90(this, false);//45 degree clockwise
                // Adding fudge
                robot.movediagonalXto(this, (Robot.MAT_SIZE * 2.5 - 15.0)); //Get me on the diagonal to the last col center
                robot.turn90(this, false);//45 degree clockwise
            }


        } else {
            // Sample and move the last column
            if (goldPosition == Sampling_Auto_Processor.GoldPosition.CENTER) {
                robot.straight(this, true, Robot.MAT_DIGONAL * 1.5 - (12.56 + (2 * Robot.HALF_ROBOT)));//knocks off jewel along th side of one tile
                robot.turn60(this, true);//45 degree clockwise
                robot.moveYto(this, true, -Robot.MAT_SIZE); // Fix error rate as move to Robot.MAT_SIZE/2
                robot.turn60(this, true);//45 degree clockwise
                robot.movediagonalXto(this, Robot.MAT_SIZE * 2.5 - 8.0); //Get me on the diagonal to the last col center
            } else if (goldPosition == Sampling_Auto_Processor.GoldPosition.LEFT) {

                robot.turn45(this, false);//45 degree anti clockwise

                robot.moveXto(this, false, 2.0 * Robot.MAT_SIZE - Robot.HALF_ROBOT);//knocks off jewel along th side of one tile

                robot.turn90(this, true);//90 clockwise
                robot.moveYto(this, true, 0);
                robot.turn60(this, true);
                robot.movediagonalXto(this, Robot.MAT_SIZE * 2.5);
            } else if (goldPosition == Sampling_Auto_Processor.GoldPosition.RIGHT) {
                robot.turn45(this, true);//45 degree clockwise
                //Adding fudge
                robot.moveYto(this, false, -1.0 * (2.0 * Robot.MAT_SIZE - Robot.HALF_ROBOT + 3.0));//knocks off jewel along th side of one tile

                //retreat
                robot.moveYto(this, true, -Robot.MAT_SIZE); // Fix error rate as move to 23.5


                robot.turn60(this, true);//45 degree clockwise
                // Adding fudge
                robot.movediagonalXto(this, (Robot.MAT_SIZE * 2.5 - 15.0)); //Get me on the diagonal to the last col center
            }

            // Robot is facing the wall
            //
            if (depo) {
                robot.straight(this, true, 21.0);
            } else {
                robot.turn45(this, false);//90 anticlockwise
                robot.moveYto(this, true, Robot.MAT_SIZE * 2.0);
            }
        }

    }

    public void setGoldPosition(Sampling_Auto_Processor.GoldPosition goldposition) {
        this.goldPosition = goldposition;
    }

}
