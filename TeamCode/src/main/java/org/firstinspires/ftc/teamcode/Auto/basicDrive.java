package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware;

public class basicDrive {
    public basicDrive(Hardware hardware, LinearOpMode mode ){
        this.bot = hardware;
        this.opMode = mode;
    }

    private Hardware bot;
    private LinearOpMode opMode;

    public void stopWheels() {
        bot.leftFrontWheel.setPower(0);
        bot.rightFrontWheel.setPower(0);
        bot.leftBackWheel.setPower(0);
        bot.rightBackWheel.setPower(0);
    }

    public void setPowerForward(double power) {
        bot.leftFrontWheel.setPower(power);
        bot.rightFrontWheel.setPower(power);
        bot.leftBackWheel.setPower(power);
        bot.rightBackWheel.setPower(power);
    }

    public void setPowerStrafeRight(double power) {
        bot.leftFrontWheel.setPower(power);
        bot.rightFrontWheel.setPower(-(power));
        bot.leftBackWheel.setPower(-(power));
        bot.rightBackWheel.setPower(power);
    }

    public void setPowerTurnRight(double power) {
        bot.leftFrontWheel.setPower(power);
        bot.rightFrontWheel.setPower(-(power));
        bot.leftBackWheel.setPower(power);
        bot.rightBackWheel.setPower(-(power));
    }
}
