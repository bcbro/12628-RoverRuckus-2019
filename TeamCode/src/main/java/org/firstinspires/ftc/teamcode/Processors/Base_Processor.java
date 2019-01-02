package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Base_Autonomous;
import org.firstinspires.ftc.teamcode.Base_TeleOp;
import org.firstinspires.ftc.teamcode.Hardware_Pushbot;

public abstract class Base_Processor implements Processor {

    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    protected LinearOpMode opMode;

    private ElapsedTime runtime = new ElapsedTime();

    Base_Processor(LinearOpMode opMode) {
        this.opMode = opMode;

    }

    public  Hardware_Pushbot getHardwarePushbot() {

        // TODO:  Hack for now
        if (opMode instanceof Base_TeleOp){
            return ((Base_TeleOp)opMode).getHardwarePushbot();
        }
        return Base_Autonomous.hardwarePushbot;
    }

    protected Telemetry getTelemetry() {

        return opMode.telemetry;

    }

    protected Gamepad getGamepad(int i) {

        return (i == 1) ? opMode.gamepad1 : opMode.gamepad2;

    }


    protected void sleep(long msec) {

        opMode.sleep(msec);

    }


    abstract public void init();

    abstract public void process();

    public void encoderDrive(double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        // Determine new target position, and pass to motor controller
        //newLeftTarget = getHardwarePushbot().leftDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
        //newRightTarget = getHardwarePushbot().rightDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
        //getHardwarePushbot().leftDrive.setTargetPosition(newLeftTarget);
        //getHardwarePushbot().rightDrive.setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        //getHardwarePushbot().leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //getHardwarePushbot().rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        //getHardwarePushbot().leftDrive.setPower(Math.abs(0.5));
        //getHardwarePushbot().rightDrive.setPower(Math.abs(0.5));

        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the getHardwarePushbot() will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the getHardwarePushbot() continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        /*while ((runtime.seconds() < timeoutS) &&
                (getHardwarePushbot().leftDrive.isBusy() && getHardwarePushbot().rightDrive.isBusy())) {
            getTelemetry().update();
        }
*/
        // Stop all motion;
        //getHardwarePushbot().leftDrive.setPower(0);
        //getHardwarePushbot().rightDrive.setPower(0);

        // Turn off RUN_TO_POSITION
        //getHardwarePushbot().leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //getHardwarePushbot().rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //  sleep(250);   // optional pause after each move
    }
}

