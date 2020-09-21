package org.firstinspires.ftc.teamcode.DriverOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

public class TeleDrive {
    public TeleDrive(Hardware hardware, LinearOpMode mode){
        this.bot = hardware;
        this.opMode = mode;
    }

    private Hardware bot;
    private LinearOpMode opMode;

    private void setPowerForward(double speed) {
        bot.leftFrontWheel.setPower(speed);
        bot.rightFrontWheel.setPower(speed);
        bot.leftBackWheel.setPower(speed);
        bot.rightBackWheel.setPower(speed);
    }

    private void setPowerRight(double speed) {
        bot.leftFrontWheel.setPower(speed);
        bot.rightFrontWheel.setPower(-(speed));
        bot.leftBackWheel.setPower(-(speed));
        bot.rightBackWheel.setPower(speed);
    }

    private void setPowerTurnRight(double speed) {
        bot.leftFrontWheel.setPower(speed);
        bot.rightFrontWheel.setPower(-(speed));
        bot.leftBackWheel.setPower(speed);
        bot.rightBackWheel.setPower(-(speed));
    }

    public void stopWheels() {
        bot.leftFrontWheel.setPower(0);
        bot.rightFrontWheel.setPower(0);
        bot.leftBackWheel.setPower(0);
        bot.rightBackWheel.setPower(0);
    }

    public void forward() {
        if(opMode.gamepad1.dpad_up) {
            setPowerForward(0.5);
            while(opMode.opModeIsActive() && opMode.gamepad1.dpad_up) {
                opMode.idle();
            }
            stopWheels();
        }
    }

    public void backwards() {
        if(opMode.gamepad1.dpad_down) {
            setPowerForward(-0.5);
            while(opMode.opModeIsActive() && opMode.gamepad1.dpad_down) {
                opMode.idle();
            }
            stopWheels();
        }
    }

    public void right() {
        if(opMode.gamepad1.dpad_right) {
            setPowerRight(0.5);
            while(opMode.opModeIsActive() && opMode.gamepad1.dpad_right) {
                opMode.idle();
            }
            stopWheels();
        }
    }

    public void left() {
        if(opMode.gamepad1.dpad_left) {
            setPowerRight(-0.5);
            while(opMode.opModeIsActive() && opMode.gamepad1.dpad_left) {
                opMode.idle();
            }
            stopWheels();
        }
    }

    public void turnRight() {
        if(opMode.gamepad1.right_bumper) {
            setPowerTurnRight(0.5);
            while(opMode.opModeIsActive() && opMode.gamepad1.right_bumper) {
                opMode.idle();
            }
            stopWheels();
        }
    }

    public void turnLeft() {
        if(opMode.gamepad1.left_bumper) {
            setPowerTurnRight(-0.5);
            while(opMode.opModeIsActive() && opMode.gamepad1.left_bumper) {
                opMode.idle();
            }
            stopWheels();
        }
    }

    public void buttonDrive() {
        forward();
        backwards();
        left();
        right();
        turnLeft();
        turnRight();
    }
}
