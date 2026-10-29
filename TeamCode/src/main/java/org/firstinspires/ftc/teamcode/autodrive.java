package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "autodrive")
public class autodrive extends LinearOpMode {

    // 4-Wheel Mecanum Drive Configuration (No Sensors)
    DcMotor left_front_drive;
    DcMotor right_front_drive;
    DcMotor left_back_drive;
    DcMotor right_back_drive;

    // Helper function for driving forward
    public void drive_forward(double power, long time) {
        left_front_drive.setPower(power);
        left_back_drive.setPower(power);
        right_front_drive.setPower(power);
        right_back_drive.setPower(power);
        sleep(time);
        stop_robot();
    }

    // Helper function for driving backward
    public void drive_backward(double power, long time) {
        left_front_drive.setPower(-power);
        left_back_drive.setPower(-power);
        right_front_drive.setPower(-power);
        right_back_drive.setPower(-power);
        sleep(time);
        stop_robot();
    }

    // Helper function for strafing right
    public void strafe_right(double power, long time) {
        left_front_drive.setPower(power);
        left_back_drive.setPower(-power);
        right_front_drive.setPower(-power);
        right_back_drive.setPower(power);
        sleep(time);
        stop_robot();
    }

    // Helper function for strafing left
    public void strafe_left(double power, long time) {
        left_front_drive.setPower(-power);
        left_back_drive.setPower(power);
        right_front_drive.setPower(power);
        right_back_drive.setPower(-power);
        sleep(time);
        stop_robot();
    }

    // Helper function for turning right (point turn)
    public void turn_right(double power, long time) {
        left_front_drive.setPower(power);
        left_back_drive.setPower(power);
        right_front_drive.setPower(-power);
        right_back_drive.setPower(-power);
        sleep(time);
        stop_robot();
    }

    // Helper function for turning left (point turn)
    public void turn_left(double power, long time) {
        left_front_drive.setPower(-power);
        left_back_drive.setPower(-power);
        right_front_drive.setPower(power);
        right_back_drive.setPower(power);
        sleep(time);
        stop_robot();
    }

    // Utility function to cut power to all motors cleanly
    public void stop_robot() {
        left_front_drive.setPower(0);
        left_back_drive.setPower(0);
        right_front_drive.setPower(0);
        right_back_drive.setPower(0);
    }

    @Override
    public void runOpMode() {
        // Hardware Mapping
        left_front_drive = hardwareMap.get(DcMotor.class, "left_front_drive");
        right_front_drive = hardwareMap.get(DcMotor.class, "right_front_drive");
        left_back_drive = hardwareMap.get(DcMotor.class, "left_back_drive");
        right_back_drive = hardwareMap.get(DcMotor.class, "right_back_drive");

        // Reverse the left side so positive power moves the whole robot forward
        left_front_drive.setDirection(DcMotor.Direction.REVERSE);
        left_back_drive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive()) {
            // Execute autonomous movement sequence
            drive_forward(1.0, 1000);
            turn_left(0.5, 1000);
            drive_forward(1.0, 1500);
            drive_backward(1.0, 1000);

            // Main loop
//            while (opModeIsActive()) {
//                // Put loop blocks here
//            }
        }
    }
}

