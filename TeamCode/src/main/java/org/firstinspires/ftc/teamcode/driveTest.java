package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Auto.AutoBot;
import org.firstinspires.ftc.teamcode.Auto.basicDrive;



@Autonomous(name="Drive_Test", group="Linear Opmode")
//@Disabled
public class driveTest extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private Hardware hardware = new Hardware();
    private basicDrive drive = new basicDrive(hardware,this);
    private  AutoBot bot = new AutoBot(hardware,this);
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        hardware.init(hardwareMap);

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        drive.setPowerForward(.5);
        sleep(2500);
        drive.stopWheels();

        sleep(500);

        drive.setPowerStrafeRight(0.5);
        sleep(1500);
        drive.stopWheels();

        sleep(500);

        drive.setPowerStrafeRight(-0.5);
        sleep(1000);
        drive.stopWheels();

        sleep(500);

        drive.setPowerForward(-.5);
        sleep(2000);
        drive.stopWheels();

        sleep(500);

        drive.setPowerTurnRight(0.5);
        sleep(750);
        drive.stopWheels();

        sleep(500);

    }
}
