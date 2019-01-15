package org.firstinspires.ftc.teamcode.Processors;

import android.support.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Base_Autonomous;

import static java.lang.Math.abs;
import static org.firstinspires.ftc.teamcode.Processors.Robot.Direction.E;
import static org.firstinspires.ftc.teamcode.Processors.Robot.Direction.N;
import static org.firstinspires.ftc.teamcode.Processors.Robot.Direction.S;
import static org.firstinspires.ftc.teamcode.Processors.Robot.Direction.W;
import static org.firstinspires.ftc.teamcode.Processors.Robot.ModeType.Backward;
import static org.firstinspires.ftc.teamcode.Processors.Robot.ModeType.Forward;
import static org.firstinspires.ftc.teamcode.Processors.Robot.ModeType.Turn45ClockWise;
import static org.firstinspires.ftc.teamcode.Processors.Robot.ModeType.Turn45antiClockWise;
import static org.firstinspires.ftc.teamcode.Processors.Robot.ModeType.Turn60Clockwise;
import static org.firstinspires.ftc.teamcode.Processors.Robot.ModeType.Turn60antiClockwise;
import static org.firstinspires.ftc.teamcode.Processors.Robot.ModeType.Turn90ClockWise;
import static org.firstinspires.ftc.teamcode.Processors.Robot.ModeType.Turn90antiClockWise;

public class Robot {


    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double FUDGE_FACTOR = 36/53;


    private static double START_POSITONX =15.0;
    private static double START_POSITONY =-15.0;
    public static double HALF_ROBOT = 9.0;
    public static double MAT_SIZE = 23.5;
    public static double MAT_DIGONAL = Math.sqrt(2*MAT_SIZE*MAT_SIZE);

    private static int testSleep = 5;

    private double x;
    private double y;
    private Direction direction;


    enum  ModeType {
        Turn45ClockWise,
        Turn45antiClockWise,
        Turn90ClockWise,
        Turn90antiClockWise,
        Turn60Clockwise,
        Turn60antiClockwise,
        Forward,
        Backward
    }

    enum Direction {
        N,
        NE,
        E,
        SE,
        S,
        SW,
        W,
        NW
    }
    //public enum TURN
    //public DcMotor leftDrive;
    //public DcMotor rightDrive;

    private ElapsedTime runtime = new ElapsedTime();

    @NonNull
    public LinearOpMode linearOpMode;


    public Robot(@NonNull LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
        x= START_POSITONX;
        y= START_POSITONY;
        direction =Direction.NW;

    }


    private void  changeState( ModeType modeType, double displacement ){
        switch(modeType){
            case  Turn45ClockWise:
                    direction=  Direction.values()[(direction.ordinal() +1)% Direction.values().length];
                    x+=5.0;
                    y-=1.0;
                break;
            case Turn45antiClockWise:
                if(N.equals(direction)) {
                    direction=Direction.NW;
                }else {
                    direction = Direction.values()[(direction.ordinal() - 1)];
                }
                x-=4.5;
                y+=1.5;
                break;



            case Turn60Clockwise:
                direction=  Direction.values()[(direction.ordinal() +1)% Direction.values().length];
                x+=5.0;
                y-=4.0;
                break;
            case Turn60antiClockwise:
                if(N.equals(direction)) {
                    direction=Direction.NW;
                }else {
                    direction = Direction.values()[(direction.ordinal() - 1)];
                }
                x-=4;
                y+=1.5;
                break;

            case Turn90ClockWise:
                direction=  Direction.values()[(direction.ordinal() +2)% Direction.values().length];
                x+=3.5;
                y+=7;
                break;
            case Turn90antiClockWise:
                if(N.equals(direction)) {
                    direction=Direction.W;
                }else  if(Direction.NE.equals(direction)) {
                    direction=Direction.NW;
                } else{
                    direction = Direction.values()[(direction.ordinal() - 2)];
                }
                x-=7;
                y-=2.5;
                break;



            case Forward :
            case Backward :

                int fdirection =modeType.equals(ModeType.Backward)?-1:1;
                int xdirection  = fdirection *( direction.name().endsWith("W")?1:-1);
                int ydirection  = fdirection * (direction.name().startsWith("N")?1:-1);

                if (direction.equals(N) || direction.equals(S)){
                    y+= ydirection *displacement;
                }else      if (direction.equals(W) || direction.equals(E)){
                    x+= xdirection *displacement;
                }else {
                    double side  =  Math.sqrt((displacement*displacement)/2);
                    y+= ydirection *side;
                    x+= xdirection *side;
                }
                break;
/*


                if(N.equals(direction)) {
                    y+=displacement;
                }
                if(S.equals(direction)) {
                    double a=c;
                    y-=a;
                }
                if(W.equals(direction)) {
                    double a=c;
                    x-=a;
                }
                if(E.equals(direction)) {
                    double a=c;
                    x+=a;
                }
                if(NE.equals(direction)) {
                    double a = Math.sqrt(((displacement*displacement)/2);
                    x+=a;
                    y+=a;
                }
                if(NW.equals(direction)) {
                    double a = (2 * c) / Math.sqrt(2);
                    x-=a;
                    y+=a;
                }
                if(SW.equals(direction)) {
                    double a = (2 * c) / Math.sqrt(2);
                    x-=a;
                    y-=a;
                }
                if(SE.equals(direction)) {
                    double a = (2 * c) / Math.sqrt(2);
                    x+=a;
                    y-=a;
                }*/


        }

    }
    public void init(){
        //leftDrive=linearOpMode.hardwareMap.get(DcMotor.class, "leftDrive");
        //rightDrive=linearOpMode.hardwareMap.get(DcMotor.class, "rightDrive");
        //leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
       // leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void turn90 (Base_Processor processor, boolean clockwise ){
        int angle =90;
        //clockwise = !clockwise;
        if (clockwise) {
            processor.encoderDrive((angle / 45) * 3.25, -((angle / 45) * 3.25), (angle / 45) * 4);
            changeState(Turn90ClockWise, 0);
        } else{
            processor.encoderDrive(-((angle / 45) * 3.25), (angle / 45) * 3.25, (angle / 45) * 4);
            changeState(Turn90antiClockWise, 0);
        }

        linearOpMode.telemetry.addData(" turn90 clockwise :", clockwise);
        linearOpMode.telemetry.update();
        testsleep();
    }

    public void turn45(Base_Processor processor, boolean clockwise ){
        double angle=45.0;
        //clockwise = !clockwise;
        if (clockwise){
            processor.encoderDrive((angle/45.0)*3.75, -((angle/45.0)*3.75), (angle/45)*3);
            changeState(Turn45ClockWise, 0);
        }
        else{
            processor.encoderDrive(-((angle/45)*3.75), (angle/45)*3.75, (angle/45)*3);
            changeState(Turn45antiClockWise, 0);
        }
        linearOpMode.telemetry.addData(" turn45 clockwise :", clockwise);
        linearOpMode.telemetry.update();
        testsleep();
    }
    public void turn60(Base_Processor processor, boolean clockwise) {
        double angle=75.0;
        //clockwise = !clockwise;
        if(clockwise) {
            processor.encoderDrive((angle/45.0)*5.00, -((angle/4.05)*5.00), (angle/45.0)*3);
            changeState(Turn60Clockwise, 0);
        }
        else {
            processor.encoderDrive(-((angle/45.0)*5.00), (angle/45.0)*5.00, (angle/45.0)*3);
            changeState(Turn60antiClockwise, 0);
        }
    }

    public void  straight(Base_Processor processor, boolean forward, double distance){
        distance*= (forward? -1.0:1.0);
        linearOpMode.telemetry.addData(" straight distance :",Double.toString(distance));
        linearOpMode.telemetry.update();
        processor.encoderDrive(distance ,distance , 10);
        changeState(forward?Forward:Backward, distance);
        testsleep();
    }

    /**
     * Moves the robot by
     * @param forward
     * @param newY
     */
    public  void moveYto(Base_Processor processor, boolean forward, double newY){
        if (direction.equals(N) || direction.equals(S)){
            double directionsign  = /*(direction.equals(N)? 1.0:-1.0) * */((forward)?1.0:-1.0);
            double displacement =(abs(newY-y)) * directionsign;
            linearOpMode.telemetry.addData(" moveYto  directionsign:" , Double.toString(directionsign)+ "  newY:" +  Double.toString(displacement));
            linearOpMode.telemetry.addData(" moveYto  forward:" ,  Boolean.toString(forward) +"  newY:" + Double.toString(newY));
            linearOpMode.telemetry.update();
            processor.encoderDrive(displacement ,displacement , 10);

            changeState(forward?Forward:Backward, displacement);
        } else {
            //throw new RuntimeException("moveYto when direction not N/S, direction is" + direction );
        }

        testsleep();
    }

    public  void moveXto(Base_Processor processor, boolean forward, double newX){
        if (direction.equals(W) || direction.equals(E)){
            double directionsign  = /*(direction.equals(W)? 1.0:-1.0) * */((forward)?1.0:-1.0);
            double displacement =(abs(newX - x)) * directionsign;
            linearOpMode.telemetry.addData(" moveXto  forward:" ,  Boolean.toString(forward)  , "  newX:" , new Double(newX).toString());
            linearOpMode.telemetry.update();
            processor.encoderDrive(displacement,displacement , 10);
            changeState(forward?Forward:Backward, displacement);
        } else {
           // throw new RuntimeException("moveXto when direction not W/E, direction is " + direction );
        }

        testsleep();
    }


    public void movediagonalXto(Base_Processor processor, double newX){
        double displacement = Math.sqrt(2.0*((newX-x) * (newX-x)));
        linearOpMode.telemetry.addData(" movediagonalXto  newX:" , new Double(newX).toString());
        linearOpMode.telemetry.update();
        processor.encoderDrive(displacement,displacement, 10);
        changeState(Forward, displacement);

        testsleep();
    }

    public void testsleep() {
        if (testSleep > 0) {

            try {
                Thread.sleep(testSleep * 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
/*
    public void encoderDrive(double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        // Determine new target position, and pass to motor controller
        double fudge_factor = 1.0;
        if (leftInches == rightInches) {
            fudge_factor = FUDGE_FACTOR;
        }
        newLeftTarget = leftDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH * fudge_factor);
        newRightTarget = rightDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH * fudge_factor);

        leftDrive.setTargetPosition(newLeftTarget);
        rightDrive.setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        leftDrive.setPower(Math.abs(0.5));
        rightDrive.setPower(Math.abs(0.5));

        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the getHardwarePushbot() will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the getHardwarePushbot() continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
       /* while ((leftDrive.isBusy() && rightDrive.isBusy())) {
            telemetry.update();
        }*/
/*
        // Stop all motion;
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        // Turn off RUN_TO_POSITION
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        try {
            Thread.sleep(250);   // optional pause after each move
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
