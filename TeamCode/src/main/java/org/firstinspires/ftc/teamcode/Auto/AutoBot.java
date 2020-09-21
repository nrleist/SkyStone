package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware;

public class AutoBot {
    public AutoBot(Hardware hardware, LinearOpMode mode ){
        this.bot = hardware;
        this.opMode = mode;
    }

    private Hardware bot;
    private LinearOpMode opMode;

    public void waitUntilTime(int time) {
        while(((30 - opMode.getRuntime()) > time) && opMode.opModeIsActive()){
            opMode.idle();
        }
    }
}
