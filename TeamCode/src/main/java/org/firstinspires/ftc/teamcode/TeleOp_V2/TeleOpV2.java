package org.firstinspires.ftc.teamcode.TeleOp_V2;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.concurrent.TimeUnit;


@TeleOp(name = "TeleOp V2")
public class TeleOpV2  extends LinearOpMode {
    static MecanumDrive drive;
    static GamepadEx gamepad1Ex;
    static GamepadEx gamepad2Ex;
    static RobotV2 robot;
    static double extpos = 0;

    static boolean scoremode = true;

    static boolean clawMoving;

    private void HardwareStart() {
        robot = new RobotV2();
        robot.init(hardwareMap);
        drive = new MecanumDrive(robot.FrontLeft, robot.FrontRight, robot.BackLeft, robot.BackRight);

        gamepad1Ex = new GamepadEx(gamepad1);
        gamepad2Ex = new GamepadEx(gamepad2);

        // Init Actions

        clawMoving = false;
        extpos = 0.55;
        robot.IntakeClaw.setPosition(0);
        robot.Coax.setPosition(0.13);
        robot.V4B.setPosition(0.4);
        robot.OuttakeRight.setPosition(0.2);
        robot.OuttakeLeft.setPosition(0.8);
        robot.OuttakeRotation.setPosition(0.3);
    }
    //gamepad1, right bumber goes to close than open



    @Override
    public void runOpMode() throws InterruptedException {
        HardwareStart();
        waitForStart();

        while (opModeIsActive()) {
            drive.driveRobotCentric(
                    gamepad1Ex.getLeftX(),
                    gamepad1Ex.getLeftY(),
                    gamepad1Ex.getRightX(),
                    true
            );




            if ((gamepad2Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)&&(gamepad2Ex.getButton(GamepadKeys.Button.DPAD_UP)))){
                scoremode = true;
            } else if ((gamepad2Ex.getButton(GamepadKeys.Button.LEFT_BUMPER)&&(gamepad2Ex.getButton(GamepadKeys.Button.DPAD_DOWN)))){
                scoremode = false;
            }

            if (scoremode == true) {
                telemetry.addData("Score Mode", "Specimen");
                if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_LEFT)) {
                    robot.IntakeClaw.setPosition(0);
                } else if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_RIGHT)) {
                    robot.IntakeClaw.setPosition(1);
                }


                if (gamepad2Ex.getLeftY() > 0.01) { // Slides UP
                    robot.LiftLeft.set(-1);
                    robot.LiftRight.set(-1);
                } else if (gamepad2Ex.getLeftY() < -0.01) { // Slides Down
                    robot.LiftLeft.set(1);
                    robot.LiftRight.set(1);
                } else { // Hold Slide Position
                    robot.LiftLeft.set(-0.02);
                    robot.LiftRight.set(-0.02);
                }

                telemetry.addData("Extension Left Position", robot.ExtLeft.getPosition());


          /*  if (gamepad2Ex.getRightY() > 0.1) { // Extension Out
                extpos = extpos + 0.005;
            } else if (gamepad2Ex.getRightY() < -0.1) { // Extension In
                extpos = extpos - 0.005;
            }*/

                if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_DOWN)) {
                    //robot.OuttakeLeftWrist.setPosition(0.7);
                    robot.OuttakeRightWrist.setPosition(0.3);
                    robot.OuttakeRotation.setPosition(0.7);
                    robot.OuttakeLeft.setPosition(0.8);
                    robot.OuttakeRight.setPosition(0.2);
                } else if (gamepad1Ex.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                    robot.OuttakeLeftWrist.setPosition(0.65);
                    robot.OuttakeClaw.setPosition(0.4);
                    robot.OuttakeRightWrist.setPosition(0.35);
                    robot.OuttakeRotation.setPosition(0);
                    robot.OuttakeLeft.setPosition(0);
                    robot.OuttakeRight.setPosition(1);
                } else if (gamepad1Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                    robot.OuttakeClaw.setPosition(1);
                    if ((robot.OuttakeClaw.getPosition()) >= 0.5){
                        robot.OuttakeLeftWrist.setPosition(0.65);
                        robot.OuttakeRightWrist.setPosition(0.35);
                        robot.OuttakeLeft.setPosition(0.5);
                        robot.OuttakeRight.setPosition(0.5);
                        robot.OuttakeRotation.setPosition(0.7);
                    }
                }
                if (gamepad1Ex.getButton(GamepadKeys.Button.A)) {
                    robot.OuttakeClaw.setPosition(1);
                }
                if (gamepad1Ex.getButton(GamepadKeys.Button.B)) {
                    robot.OuttakeClaw.setPosition(0.4);
                }
                if (gamepad2Ex.getButton(GamepadKeys.Button.X)) {
                    robot.IntakeRotation.setPosition(0.35);
                }
                if (gamepad2Ex.getButton(GamepadKeys.Button.Y)) {
                    robot.IntakeRotation.setPosition(0);
                }

                if (gamepad2Ex.getButton(GamepadKeys.Button.A)) {
                    robot.Coax.setPosition(0.13);
                    robot.V4B.setPosition(0.57);
                    TimeUnit.MILLISECONDS.sleep(500);
                    robot.IntakeClaw.setPosition(1);
                    TimeUnit.MILLISECONDS.sleep(250);
                    robot.V4B.setPosition(0.4);
                    extpos = 0.7;

                }

                if (gamepad2Ex.getButton(GamepadKeys.Button.B)) {
                    robot.Coax.setPosition(0.13);
                    robot.V4B.setPosition(0.4);
                    extpos = 0;
                    robot.IntakeClaw.setPosition(0);
                }


                robot.ExtLeft.setPosition(1 - extpos);
                robot.ExtRight.setPosition(extpos);


                if (extpos > 1) {
                    extpos = 1;
                } else if (extpos < 0) {
                    extpos = 0;
                }
            } else if (scoremode == false){
                telemetry.addData("Score Mode", "Samples");
                if (gamepad2Ex.getLeftY() > 0.01) { // Slides UP
                    robot.LiftLeft.set(-1);
                    robot.LiftRight.set(-1);
                } else if (gamepad2Ex.getLeftY() < -0.01) { // Slides Down
                    robot.LiftLeft.set(1);
                    robot.LiftRight.set(1);
                } else { // Hold Slide Position
                    robot.LiftLeft.set(-0.02);
                    robot.LiftRight.set(-0.02);
                }
                if (gamepad2Ex.getButton(GamepadKeys.Button.X)) {
                    robot.IntakeRotation.setPosition(0.37);
                }
                if (gamepad2Ex.getButton(GamepadKeys.Button.Y)) {
                    robot.IntakeRotation.setPosition(0.07);
                }
                if (gamepad2Ex.getButton(GamepadKeys.Button.A)) { // intake transfer pos
                    robot.Coax.setPosition(0.13);
                    robot.V4B.setPosition(0.57);
                    TimeUnit.MILLISECONDS.sleep(500);
                    robot.IntakeClaw.setPosition(1);
                    TimeUnit.MILLISECONDS.sleep(250);
                    robot.V4B.setPosition(0.1);
                    robot.IntakeRotation.setPosition(0.03);
                    robot.OuttakeRotation.setPosition(0.7);
                    robot.OuttakeClaw.setPosition(0.4);
                    robot.OuttakeRightWrist.setPosition(0);
                    robot.OuttakeLeftWrist.setPosition(1);
                    robot.IntakeClaw.setPosition(0.95);
                    robot.Coax.setPosition(1);
                    TimeUnit.MILLISECONDS.sleep(750);
                    robot.OuttakeLeft.setPosition(0.88);
                    robot.OuttakeRight.setPosition(0.12);
                    extpos = 0.55;

                }
                if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_UP)){ // sweeper action
                    robot.IntakeClaw.setPosition(0.4);
                    robot.V4B.setPosition(0.65);
                    TimeUnit.MILLISECONDS.sleep(500);
                    robot.IntakeClaw.setPosition(0);
                    TimeUnit.MILLISECONDS.sleep(100);
                    robot.V4B.setPosition(0.4);
                }

                if (gamepad2Ex.getButton(GamepadKeys.Button.B)) {
                    robot.Coax.setPosition(0.13);
                    robot.V4B.setPosition(0.4);
                    extpos = 0.05;
                    robot.IntakeClaw.setPosition(0);


                }
                if(gamepad2Ex.getButton(GamepadKeys.Button.DPAD_DOWN)){
                    robot.OuttakeClaw.setPosition(1);
                    TimeUnit.MILLISECONDS.sleep(500);
                    robot.IntakeClaw.setPosition(0.4);
                    extpos = 0.1;
                    TimeUnit.MILLISECONDS.sleep(500);
                    robot.OuttakeRight.setPosition(0.7);
                    robot.OuttakeLeft.setPosition(0.3);

                }
                robot.ExtLeft.setPosition(1 - extpos);
                robot.ExtRight.setPosition(extpos);

            }


           // telemetry.addData("Extension Position Variable", extpos);
            telemetry.addData("Extension Left Position", robot.ExtLeft.getPosition());
            telemetry.addData("Extension Right Position", robot.ExtRight.getPosition());
            telemetry.update();


        }
    }
}



