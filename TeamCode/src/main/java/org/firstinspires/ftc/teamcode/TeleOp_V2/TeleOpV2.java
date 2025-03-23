package org.firstinspires.ftc.teamcode.TeleOp_V2;

import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Common.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Common.TeleActions;

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

//    private ColorSensor colorSensor;;
//    private double redValue;
//    private double greenValue;
//    private double blueValue;
//    private double alphaValue; //Light Intensity
//    private double TargetValue = 1000;
//private ColorSensor colorSensor;;
    private double redValue;
    private double greenValue;
    private double blueValue;
    private double alphaValue; //Light Intensity
    private double targetValue = 1000;
    private TeleActions teleActions;

//    public void initColorSensor(){
//        colorSensor = hardwareMap.get(ColorSensor.class,"colorSensor");
//    }
//    public void getColor(){
//        redValue = colorSensor.red();
//        greenValue = colorSensor.green();
//        blueValue = colorSensor.blue();
//        alphaValue = colorSensor.alpha();
//    }
    public void colorTelemetry(){
        telemetry.addData("redValue",redValue);
        telemetry.addData("greenValue", greenValue);
        telemetry.addData("blueValue", blueValue);
        telemetry.addData("alphaValue", alphaValue);
        telemetry.update();
    }



    private void HardwareStart() {
        robot = new RobotV2();
        robot.init(hardwareMap);
        drive = new MecanumDrive(robot.FrontLeft, robot.FrontRight, robot.BackLeft, robot.BackRight);
        teleActions = new TeleActions(hardwareMap);

        gamepad1Ex = new GamepadEx(gamepad1);
        gamepad2Ex = new GamepadEx(gamepad2);
        //initColorSensor();

        // Init Actions

        clawMoving = false;
        extpos = 0.55;
        robot.IntakeClaw.setPosition(0);
        robot.Coax.setPosition(0.13);
        if ((robot.Coax.getPosition() == 0.13)){
            robot.V4B.setPosition(0.4);
            robot.OuttakeLeftWrist.setPosition(0.65);
            robot.OuttakeRightWrist.setPosition(0.35);
            robot.OuttakeLeft.setPosition(0.5);
            robot.OuttakeRight.setPosition(0.5);
            robot.OuttakeRotation.setPosition(0.7);
        }
    }
    //gamepad1, right bumber goes to close than open


    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime TimePassed = new ElapsedTime();
        int v_state = 0;

        waitForStart();
        HardwareStart();
        waitForStart();
        //getColor();
        //colorTelemetry();

        while (opModeIsActive()) {
            drive.driveRobotCentric(
                    gamepad1Ex.getLeftX(),
                    gamepad1Ex.getLeftY(),
                    gamepad1Ex.getRightX(),
                    true
            );
            //getColor();
            //colorTelemetry();
//            switch (v_state)
//            {
//                case 0:
//                    robot.Coax.setPosition(1);
//                    TimePassed.reset();
//                    v_state++;
//                    break;
//                case 1:
//                    if (TimePassed.time() >= 2.0)
//                    {
//                        robot.IntakeClaw.setPosition(1);
//                        v_state = 0;
//                    }
//            }
//           STATE MACHINE FOR WAITS ABOVE
//           COLOR SENSOR BELOW
//            if(alphaValue > targetValue){
//                run action
//            } else {
//                run other action
//            }




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
                    v_state = 0;
                } else if (gamepad1Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                    switch (v_state){
                        case 0:
                            robot.OuttakeClaw.setPosition(1);
                            TimePassed.reset();
                            v_state++;
                            break;
                        case 1:

                            if (TimePassed.time()>=0.5){
                                robot.OuttakeLeft.setPosition(0.5);
                                robot.OuttakeRight.setPosition(0.5);
                                robot.OuttakeLeftWrist.setPosition(0.65);
                                robot.OuttakeRightWrist.setPosition(0.35);
                                TimePassed.reset();
                                v_state++;
                            }
                            break;
                        case 2:
                            if (TimePassed.time()>=0.5){
                                robot.OuttakeRotation.setPosition(0.7);
                            }
                            v_state = 0;
                            break;
                    }
//                    robot.OuttakeClaw.setPosition(1);
//                    robot.OuttakeLeftWrist.setPosition(0.65);
//                    robot.OuttakeRightWrist.setPosition(0.35);
//                    robot.OuttakeLeft.setPosition(0.5);
//                    robot.OuttakeRight.setPosition(0.5);
//                    robot.OuttakeRotation.setPosition(0.7);

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
                    switch (v_state)
                    {
                        case 0:
                            robot.Coax.setPosition(0.13);
                            robot.V4B.setPosition(0.57);
                            TimePassed.reset();
                            v_state++;
                            break;
                        case 1:
                            if (TimePassed.time() >= 0.5)
                            {
                                robot.IntakeClaw.setPosition(1);
                                v_state++;
                                TimePassed.reset();

                            }
                            break;
                        case 3:
                            TimePassed.reset();
                            if (TimePassed.time()>=0.25){
                                robot.V4B.setPosition(0.4);
                                extpos = 0.7;
                                v_state = 0;
                            }
                            break;

                    }

//                    robot.Coax.setPosition(0.13);
//                    robot.V4B.setPosition(0.57);
//                    TimeUnit.MILLISECONDS.sleep(500);
//                    robot.IntakeClaw.setPosition(1);
//                    TimeUnit.MILLISECONDS.sleep(250);
//                    robot.V4B.setPosition(0.4);
//                    extpos = 0.7;
//                    Actions.runBlocking(
//                            new SequentialAction(
//                                    teleActions.transferRoutine()
//                            )
//                    );

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
                    switch (v_state)
                    {
                        case 0:
                            robot.Coax.setPosition(0.13);
                            robot.V4B.setPosition(0.57);
                            TimePassed.reset();
                            v_state++;
                            break;
                        case 1:

                            if ((TimePassed.time()) >= 0.5)
                            {
                                robot.IntakeClaw.setPosition(1);
                                v_state++;
                                TimePassed.reset();

                            }
                            break;
                        case 2:
                            if (TimePassed.time()>=0.2){
                                robot.V4B.setPosition(0.1);
                                robot.IntakeRotation.setPosition(0.03);
                                robot.OuttakeRotation.setPosition(0.7);
                                robot.OuttakeClaw.setPosition(0.4);
                                robot.OuttakeRightWrist.setPosition(0);
                                robot.OuttakeLeftWrist.setPosition(1);
                                robot.IntakeClaw.setPosition(0.95);
                                robot.Coax.setPosition(1);
                                robot.OuttakeLeft.setPosition(0.88);
                                robot.OuttakeRight.setPosition(0.12);
                                v_state++;
                                TimePassed.reset();
                            }
                            break;
                        case 3:
                            if (TimePassed.time()>=0.5){
                                robot.IntakeClaw.setPosition(0.61);
                                robot.OuttakeLeft.setPosition(0.88);
                                robot.OuttakeRight.setPosition(0.12);
                                extpos = 0.5;
                            }

                            break;


                    }
//                    robot.Coax.setPosition(0.13);
//                    robot.V4B.setPosition(0.57);
//                    TimeUnit.MILLISECONDS.sleep(500);
//                    robot.IntakeClaw.setPosition(1);
//                    TimeUnit.MILLISECONDS.sleep(250);
//                    robot.V4B.setPosition(0.1);
//                    robot.IntakeRotation.setPosition(0.03);
//                    robot.OuttakeRotation.setPosition(0.7);
//                    robot.OuttakeClaw.setPosition(0.4);
//                    robot.OuttakeRightWrist.setPosition(0);
//                    robot.OuttakeLeftWrist.setPosition(1);
//                    robot.IntakeClaw.setPosition(0.95);
//                    robot.Coax.setPosition(1);
//                    TimeUnit.MILLISECONDS.sleep(750);
//                    robot.OuttakeLeft.setPosition(0.88);
//                    robot.OuttakeRight.setPosition(0.12);
//                    extpos = 0.55;
//                    Actions.runBlocking(
//                            new SequentialAction(
//                                    teleActions.transferRoutine()
//                            )
//                    );
                }

                if (gamepad2Ex.getButton(GamepadKeys.Button.B)) {
                    robot.Coax.setPosition(0.13);
                    robot.V4B.setPosition(0.4);
                    extpos = 0.1;
                    robot.IntakeClaw.setPosition(0);
                }
                if(gamepad2Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)){
                    v_state = 0;
                }
                if(gamepad2Ex.getButton(GamepadKeys.Button.DPAD_DOWN)){
                    switch (v_state)
                    {
                        case 0:
                            robot.OuttakeClaw.setPosition(1);
                            TimePassed.reset();
                            v_state++;
                            break;
                        case 1:
                            if (TimePassed.time() >= 0.5)
                            {
                                robot.IntakeClaw.setPosition(0.4);
                                v_state++;
                                TimePassed.reset();

                            }
                            break;
                        case 3:
                            if (TimePassed.time()>=0.5){
                                robot.OuttakeRight.setPosition(0.7);
                                robot.OuttakeLeft.setPosition(0.3);
                            }
                            break;

                    }
//                    robot.OuttakeClaw.setPosition(1);
//                    TimeUnit.MILLISECONDS.sleep(500);
//                    robot.IntakeClaw.setPosition(0.4);
//                    extpos = 0.1;
//                    TimeUnit.MILLISECONDS.sleep(500);
//                    robot.OuttakeRight.setPosition(0.7);
//                    robot.OuttakeLeft.setPosition(0.3);
//                    Actions.runBlocking(
//                            new SequentialAction(
//                                    teleActions.ClawToClawRoutine()
//                            )
//                    );

                }
                if (gamepad1Ex.getButton(GamepadKeys.Button.A)) {
                    robot.OuttakeClaw.setPosition(1);
                }
                if (gamepad1Ex.getButton(GamepadKeys.Button.B)) {
                    robot.OuttakeClaw.setPosition(0.4);
                }
                robot.ExtLeft.setPosition(1 - extpos);
                robot.ExtRight.setPosition(extpos);

            }


           // telemetry.addData("Extension Position Variable", extpos);
            telemetry.addData("Extension Left Position", robot.ExtLeft.getPosition());
            telemetry.addData("Extension Right Position", robot.ExtRight.getPosition());
            telemetry.addData("TimePassed", TimePassed.time());
            telemetry.update();


        }
    }
}



