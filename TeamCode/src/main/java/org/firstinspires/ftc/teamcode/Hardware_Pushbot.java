package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//all motors are described from the front
public class Hardware_Pushbot {

    /* Public OpMode members. */

    public DcMotor leftDrive = null; //motors for driving from the front, the left
    public DcMotor rightDrive = null; //motors for driving from the front, the right

    public DcMotor leftrotationArm = null; //rotational arm for lifting on the right
    public DcMotor rightrotationArm = null; //rotational arm for lifting to stabilize and strengthen the axle on the left

    public DcMotor extensionArm = null; //arm that extends the XRAil

    public DcMotor intake_motor = null; //rotating motor to pull in the minerals

    public Servo verticalServo = null; //moves the box for collecting across the y-axe
    //public Servo horizontalServo = null; //moves the box for collection across the z-axe

    public DcMotor latching_linear = null; // moves the cascading lift up
    public Servo latchServo = null; //actually latches on

    public Servo teamMarker = null;// drops the team marker into the depo
    /* local OpMode members. */

    HardwareMap hwMap = null;

    private ElapsedTime period = new ElapsedTime();



    /* Constructor */

    public Hardware_Pushbot() {


    }



    /* Initialize standard Hardware interfaces */

    public void init(HardwareMap ahwMap){
        init(ahwMap, true);
    }


    public void init(HardwareMap ahwMap, boolean auto) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        extensionArm = hwMap.get(DcMotor.class, "extensionArm");
        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        leftrotationArm = hwMap.get(DcMotor.class, "leftrotationArm");
        rightrotationArm = hwMap.get(DcMotor.class, "rightrotationArm");
        intake_motor = hwMap.get(DcMotor.class, "intakeMotor");
        if (auto) {
            leftDrive.setDirection(DcMotorSimple.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Set to REVERSE if using AndyMark motors
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        }else{
            rightDrive.setDirection(DcMotorSimple.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
            leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        leftrotationArm.setDirection(DcMotorSimple.Direction.REVERSE);
        rightrotationArm.setDirection(DcMotorSimple.Direction.REVERSE);

        intake_motor.setDirection(DcMotorSimple.Direction.FORWARD);
        extensionArm.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);


        leftrotationArm.setPower(0);

        extensionArm.setPower(0);
        intake_motor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and initialize ALL installed servos.
        latching_linear = hwMap.get(DcMotor.class, "latching_linear");

        latchServo = hwMap.get(Servo.class, "latchServo");
        verticalServo = hwMap.get(Servo.class, "yServo");
        //horizontalServo = hwMap.get(Servo.class, "zServo");
        teamMarker = hwMap.get(Servo.class, "teamMarker");
    }
}