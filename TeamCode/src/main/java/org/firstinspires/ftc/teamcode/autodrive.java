package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "autodrive")
public class autodrive extends LinearOpMode {
    public final int LAUNCHER_TARGET_VELOCITY = 1250; //2678 RPM
    public final int LAUNCHER_MIN_VELOCITY = 1200; //2571 RPM

    // 4-Wheel Mecanum Drive Configuration (No Sensors)
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotorEx launcher = null;
    private DcMotor intake = null;
    private CRServo leftIntakeServo = null;
    private CRServo rightIntakeServo = null;
    private CRServo windmillServo = null;


    // Helper function for driving forward
    public void drive_forward(double power, long time) {
        leftFrontDrive.setPower(power);
        leftBackDrive.setPower(power);
        rightFrontDrive.setPower(power);
        rightBackDrive.setPower(power);
        sleep(time);
        stop_robot();
    }

    // Helper function for driving backward
    public void drive_backward(double power, long time) {
        leftFrontDrive.setPower(-power);
        leftBackDrive.setPower(-power);
        rightFrontDrive.setPower(-power);
        rightBackDrive.setPower(-power);
        sleep(time);
        stop_robot();
    }

    // Helper function for strafing right
    public void strafe_right(double power, long time) {
        leftFrontDrive.setPower(power);
        leftBackDrive.setPower(-power);
        rightFrontDrive.setPower(-power);
        rightBackDrive.setPower(power);
        sleep(time);
        stop_robot();
    }

    // Helper function for strafing left
    public void strafe_left(double power, long time) {
        leftFrontDrive.setPower(-power);
        leftBackDrive.setPower(power);
        rightFrontDrive.setPower(power);
        rightBackDrive.setPower(-power);
        sleep(time);
        stop_robot();
    }


    // Utility function to cut power to all motors cleanly
    public void stop_robot() {
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
    }

    public void shoot() {
        launcher.setVelocity(LAUNCHER_TARGET_VELOCITY);
        while (launcher.getVelocity() < LAUNCHER_MIN_VELOCITY) {
            windmillServo.setPower(0);
        }
        windmillServo.setPower(1);
        sleep(4);
        windmillServo.setPower(0);
        launcher.setVelocity(LAUNCHER_TARGET_VELOCITY);

    }

    @Override
    public void runOpMode() {
        // Hardware Mapping
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBackDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        windmillServo = hardwareMap.get(CRServo.class, "windmillServo");
        leftIntakeServo = hardwareMap.get(CRServo.class, "leftIntakeServo");
        rightIntakeServo = hardwareMap.get(CRServo.class, "rightIntakeServo");

        // Reverse the left side so positive power moves the whole robot forward
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        leftFrontDrive.setZeroPowerBehavior(BRAKE);
        rightFrontDrive.setZeroPowerBehavior(BRAKE);
        leftBackDrive.setZeroPowerBehavior(BRAKE);
        rightBackDrive.setZeroPowerBehavior(BRAKE);
        intake.setZeroPowerBehavior(BRAKE);

        rightIntakeServo.setDirection(DcMotorSimple.Direction.REVERSE);
        windmillServo.setDirection(DcMotorSimple.Direction.REVERSE);

        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        drive_forward(1.0, 1);

//        if (opModeIsActive()) {
//            // Execute autonomous movement sequence
//            drive_forward(1.0, 1000);
//            turn_left(0.5, 1000);
//            drive_forward(1.0, 1500);
//            drive_backward(1.0, 1000);
//
//            // Main loop
////            while (opModeIsActive()) {
////                // Put loop blocks here
////            }
//        }
    }
}

