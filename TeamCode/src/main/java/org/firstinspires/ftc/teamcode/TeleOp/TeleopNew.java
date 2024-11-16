package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Common.Extension;
import org.firstinspires.ftc.teamcode.Common.SlideGroup;
import org.firstinspires.ftc.teamcode.TeleOp.RobotNew;

@TeleOp(name = "Teleop-New")
public class TeleopNew extends LinearOpMode {
    static MecanumDrive drive;
    static GamepadEx gamepad1Ex;
    static GamepadEx gamepad2Ex;
    static RobotNew robot;
    static Extension extension;
    static SlideGroup slides;

    private  void HardwareStart(){
        robot = new RobotNew();
        robot.init(hardwareMap);
        drive = new MecanumDrive(robot.FrontLeft, robot.FrontRight, robot.BackLeft, robot.BackRight);

//        extension = new Extension(robot.Extension);
//        slides = new SlideGroup(robot.Slides);

        gamepad1Ex = new GamepadEx(gamepad1);
        gamepad2Ex = new GamepadEx(gamepad2);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareStart();
        waitForStart();

        while(opModeIsActive()) {
            drive.driveRobotCentric(
                    gamepad1Ex.getLeftX(),
                    gamepad1Ex.getLeftY(),
                    gamepad1Ex.getRightX(),
                    true
            );
            if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_DOWN)) { // Slides Down
                robot.SlideLeft.set(-0.6);
                robot.SlideRight.set(0.6);
            } else if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_UP)) { // Slides UP
                robot.SlideLeft.set(0.8);
                robot.SlideRight.set(-0.8);
            } else { // Hold Slide Position
//                robot.Slides.set(-0.009375);
                robot.SlideLeft.set(0.15);
                robot.SlideRight.set(-0.2);
            }

            /*if (gamepad2Ex.getButton(GamepadKeys.Button.X)){
                slides.resetEncoder();
                slides.slidesToPosition(10);
            }*/

            if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_RIGHT)) { // Extension In
                robot.ExtensionLeft.set(1);
                robot.ExtensionRight.set(-1);
            } else if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_LEFT)) { // Extension Out
                robot.ExtensionLeft.set(-1);
                robot.ExtensionRight.set(1);
            } else {
                robot.ExtensionLeft.set(0);
                robot.ExtensionRight.set(0);
            }

            if(gamepad2Ex.getButton(GamepadKeys.Button.A)){
                robot.SlideLeft.setRunMode(MotorEx.RunMode.PositionControl);
                robot.SlideRight.setRunMode(MotorEx.RunMode.PositionControl);
                robot.SlideLeft.stopAndResetEncoder();
                robot.SlideRight.stopAndResetEncoder();
                robot.SlideLeft.setTargetPosition(1000);
                robot.SlideRight.setTargetPosition(1000);
            }
        }
    }
}
