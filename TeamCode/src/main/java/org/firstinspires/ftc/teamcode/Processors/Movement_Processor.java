/*
package org.firstinspires.ftc.teamcode.Processors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Movement_Processor extends Base_Processor{
        public Movement_Processor(LinearOpMode opMode) {
            super(opMode);
        }

        double left;
        double right;
        double drive;
        double turn;
        double max;

        @Override
        public void init() {

        }

        @Override
        public void process() {
            // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
            // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right.
            // This way it's also easy to just drive straight, or just turn.
            drive = getGamepad(2).left_stick_x;
            turn  = getGamepad(2).right_stick_y;
            left  = drive + turn;
            right = drive - turn;
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0)
            {
                left /= max;
                right /= max;
            }
            getHardwarePushbot().leftDrive.setPower(left);
            getHardwarePushbot().rightDrive.setPower(right);
            getTelemetry().addData("Move",  " left[%.2f] right[%.2f]", left,right);
        }



    }

*/
