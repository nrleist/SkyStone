package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class Hardware {

    public DcMotor leftFrontWheel = null;
    public DcMotor rightFrontWheel = null;
    public DcMotor leftBackWheel = null;
    public DcMotor rightBackWheel = null;
    public WebcamName webcamName = null;

    public void init(HardwareMap hardwareMap){
        leftFrontWheel  = hardwareMap.get(DcMotor.class, "leftFrontWheel");
        rightFrontWheel = hardwareMap.get(DcMotor.class, "rightFrontWheel");
        leftBackWheel  = hardwareMap.get(DcMotor.class, "leftBackWheel");
        rightBackWheel = hardwareMap.get(DcMotor.class, "rightBackWheel");

        webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftFrontWheel.setDirection(DcMotor.Direction.REVERSE);
        rightFrontWheel.setDirection(DcMotor.Direction.FORWARD);
        leftBackWheel.setDirection(DcMotor.Direction.REVERSE);
        rightBackWheel.setDirection(DcMotor.Direction.FORWARD);

        leftFrontWheel.setPower(0);
        rightFrontWheel.setPower(0);
        leftBackWheel.setPower(0);
        rightBackWheel.setPower(0);

        leftFrontWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
