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


    static boolean clawMoving;

    private void HardwareStart() {
        robot = new RobotV2();
        robot.init(hardwareMap);
        drive = new MecanumDrive(robot.FrontLeft, robot.FrontRight, robot.BackLeft, robot.BackRight);

        gamepad1Ex = new GamepadEx(gamepad1);
        gamepad2Ex = new GamepadEx(gamepad2);

        // Init Actions
        extpos = 0.55;
        clawMoving = false;
        robot.IntakeClaw.setPosition(0.1);
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

            if (gamepad2Ex.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                robot.IntakeClaw.setPosition(1);
                TimeUnit.MILLISECONDS.sleep(500);
                robot.V4B.setPosition(0.73);
                robot.Coax.setPosition(0.14);
            } else if (gamepad2Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                robot.Coax.setPosition(0.8);
                robot.V4B.setPosition(0.2);
            }
            if (gamepad2Ex.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON)){
                robot.Coax.setPosition(0.5);

            }
            if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_LEFT)) {
                robot.V4B.setPosition(0.73);
                robot.Coax.setPosition(0.735);
                extpos = 0.55;
            }
            if (gamepad2Ex.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
                robot.IntakeClaw.setPosition(1);
            } else {
                robot.IntakeClaw.setPosition(0);
            }
            if (gamepad1Ex.getButton(GamepadKeys.Button.A)) {
                robot.OuttakeLeft.setPosition(1);
                robot.OuttakeRight.setPosition(0);
            }
            if (gamepad1Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)){
                robot.OuttakeLeft.setPosition(0.75);
                robot.OuttakeRight.setPosition(0.25);



            }



            if (gamepad2Ex.getLeftY() > 0.1) { // Slides UP
                robot.LiftLeft.set(-1);
                robot.LiftRight.set(1);
            } else if (gamepad2Ex.getLeftY() < -0.1) { // Slides Down
                robot.LiftLeft.set(0.7);
                robot.LiftRight.set(-0.7);
            } else { // Hold Slide Position
                robot.LiftLeft.set(0.02);
                robot.LiftRight.set(-0.02);
            }

            telemetry.addData("Extension Left Position", robot.ExtLeft.getPosition());


            if (gamepad2Ex.getRightY() > 0.1) { // Extension Out
                extpos = extpos + 0.005;
            } else if (gamepad2Ex.getRightY() < -0.1) { // Extension In
                extpos = extpos - 0.005;
            }

            if (gamepad1Ex.getButton(GamepadKeys.Button.DPAD_DOWN)) {
                robot.OuttakeRotation.setPosition(1);
                robot.OuttakeLeft.setPosition(1);
                robot.OuttakeRight.setPosition(0);
            } else if (gamepad1Ex.getButton(GamepadKeys.Button.DPAD_UP)) {
                robot.OuttakeRotation.setPosition(0);
                robot.OuttakeLeft.setPosition(0);
                robot.OuttakeRight.setPosition(1);
            }
            if (gamepad1Ex.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>0.1){
                robot.OuttakeClaw.setPosition(1);
            } else {
                robot.OuttakeClaw.setPosition(0);
            }

            if(gamepad1.a){
                robot.ExtLeft.setPosition(0);
                robot.ExtRight.setPosition(1);
            }
            if(gamepad1.b){
                robot.ExtLeft.setPosition(0.2);
                robot.ExtRight.setPosition(0.8);
            }



//            robot.ExtLeft.setPosition(extpos);
//            robot.ExtRight.setPosition(1 - extpos);



            if (extpos > 1) {
                extpos = 1;
            } else if (extpos < 0) {
                extpos = 0;
            }

           // telemetry.addData("Extension Position Variable", extpos);
            telemetry.addData("Extension Left Position", robot.ExtLeft.getPosition());
            telemetry.addData("Extension Right Position", robot.ExtRight.getPosition());
            telemetry.update();


        }
    }
}



