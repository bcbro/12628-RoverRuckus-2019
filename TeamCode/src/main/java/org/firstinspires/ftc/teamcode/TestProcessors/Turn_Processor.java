package org.firstinspires.ftc.teamcode.TestProcessors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Processors.Base_Processor;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

//@Autonomous(group="Autonomous", name="TurnTest")
public  class Turn_Processor extends LinearOpMode {

    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double FUDGE_FACTOR = 36/53;

    private ElapsedTime runtime = new ElapsedTime();

    private ModeType modeType;

    protected Turn_Processor(ModeType modeType) {
        this.modeType = modeType;
    }

    enum  ModeType {
        Turn45ClockWise,
        Turn45antiClockWise,
        Turn90ClockWise,
        Turn90antiClockWise,
        Turn60Clockwise,
        Turn60antiClockwise,
    }

    //public enum TURN
    public DcMotor leftDrive;
    public DcMotor rightDrive;

    public void runOpMode() {
        leftDrive=hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive=hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        if (opModeIsActive()) {
            switch(modeType){
                case Turn45ClockWise:
                    turn45(true );
                    break;
                case Turn45antiClockWise:
                    turn45( false);
                    break;
                case Turn90ClockWise:
                    turn90( true);
                    break;
                case Turn90antiClockWise:
                    turn90( false);
                    break;
                case Turn60Clockwise:
                    turn60(true);
                    break;
                case Turn60antiClockwise:
                    turn60(false);
                    break;
            }
        }
    }

    public void turn90 ( boolean clockwise ){
        int angle =90;
        if (clockwise) {
            encoderDrive((angle / 45) * 3.25, -((angle / 45) * 3.25), (angle / 45) * 4);
        } else{
            encoderDrive(-((angle / 45) * 3.25), (angle / 45) * 3.25, (angle / 45) * 4);
        }
    }

    public void turn45( boolean clockwise ){
        int angle=45;
        if (clockwise){
            encoderDrive(-((angle/45)*3.75), (angle/45)*3.75, (angle/45)*3);
        }
        else{
            encoderDrive((angle/45)*3.75, -((angle/45)*3.75), (angle/45)*3);
        }
    }
    public void turn60(boolean clockwise) {
        int angle=60;
        if(clockwise) {
            encoderDrive((angle/45)*4.75, -((angle/45)*4.75), 9);
        }
        else {
            encoderDrive(-((angle/45)*4.75), (angle/45)*4.75, 9);
        }
    }

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
        while ((leftDrive.isBusy() && rightDrive.isBusy())) {
            telemetry.update();
        }

        // Stop all motion;
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        // Turn off RUN_TO_POSITION
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sleep(250);   // optional pause after each move
    }

}
