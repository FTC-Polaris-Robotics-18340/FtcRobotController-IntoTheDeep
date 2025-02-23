package org.firstinspires.ftc.teamcode.Common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TeleActions {
    private SimpleServo Coax;
    private SimpleServo V4B;
    private SimpleServo ExtRight;
    private SimpleServo ExtLeft;
    private SimpleServo OuttakeRightWrist;
    private SimpleServo OuttakeLeftWrist;
    private SimpleServo OuttakeRotation;

    private SimpleServo OuttakeRight;
    private SimpleServo OuttakeLeft;
    private SimpleServo OuttakeClaw;


    private SimpleServo IntakeClaw;

    private SimpleServo IntakeRotation;
    private MotorEx LiftRight;
    private MotorEx LiftLeft;

    public TeleActions(HardwareMap hardwareMap){
        Coax = new SimpleServo(hardwareMap, "ICoax", 0.0, 1.0);
        V4B = new SimpleServo(hardwareMap, "IV4B", 0.0, 1.0);



        ExtLeft = new SimpleServo(hardwareMap, "ExtL", 0.0, 0.5);
        ExtRight = new SimpleServo(hardwareMap, "ExtR", 0.0, 0.5);

        OuttakeRight = new SimpleServo(hardwareMap, "ORA", 0.0, 1.0);
        OuttakeLeft = new SimpleServo(hardwareMap, "OLA", 0.0, 1.0);

        IntakeRotation = new SimpleServo(hardwareMap, "Irot",0.0,1.0);
        IntakeClaw = new SimpleServo(hardwareMap,"IC", 0.0, 1.0 );

        OuttakeRotation = new SimpleServo(hardwareMap,"Orot",0.0,1.0);
        OuttakeClaw = new SimpleServo(hardwareMap,"Oclaw",0.0,1.0 );

        OuttakeRightWrist = new SimpleServo(hardwareMap, "ORW", 0.0, 1.0);
        OuttakeLeftWrist = new SimpleServo(hardwareMap, "OLW", 0.0, 1.0);

        LiftLeft = new MotorEx(hardwareMap, "LiftLeft");
        LiftLeft.setRunMode(Motor.RunMode.RawPower);
        LiftLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        LiftRight = new MotorEx(hardwareMap, "LiftRight");
        LiftRight.setRunMode(Motor.RunMode.RawPower);
        LiftRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public Action transferRoutine() { // gamepad2 a sample mode
        return new SequentialAction(
                this.transferCV4B(),
                new SleepAction(0.5),
                this.transferIC(),
                new SleepAction(0.25),
                this.transferWrist(),
                new SleepAction(0.75),
                this.transferArm()
        );
    }

    private Action transferCV4B() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                Coax.setPosition(0.13);
                V4B.setPosition(0.57);

                return false;
            }
        };
    }
    private Action transferIC() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                IntakeClaw.setPosition(1);
                return false;
            }
        };
    }

    private Action transferWrist() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                    V4B.setPosition(0.1);
                    IntakeRotation.setPosition(0.03);
                    OuttakeRotation.setPosition(0.7);
                    OuttakeClaw.setPosition(0.4);
                    OuttakeRightWrist.setPosition(0);
                    OuttakeLeftWrist.setPosition(1);
                    IntakeClaw.setPosition(0.95);
                    Coax.setPosition(1);
                return false;
            }
        };
    }
    private Action transferArm() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                OuttakeLeft.setPosition(0.88);
                OuttakeRight.setPosition(0.12);
                ExtLeft.setPosition(0.45);
                ExtRight.setPosition(0.55);
                return false;
            }
        };
    }
    public Action ClawToClawRoutine() {
        return new SequentialAction(
                this.C2C(),
                new SleepAction(0.5),
                this.SafetyClaw(),
                new SleepAction(0.5),
                this.SampleArmUp()


        );
    }
    private Action C2C() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                OuttakeClaw.setPosition(1);
                return false;
            }
        };
    }
    private Action SafetyClaw() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                IntakeClaw.setPosition(0.4);
                ExtLeft.setPosition(0.9);
                ExtRight.setPosition(0.1);
                return false;
            }
        };
    }
    private Action SampleArmUp() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                OuttakeRight.setPosition(0.7);
                OuttakeLeft.setPosition(0.3);
                return false;
            }
        };
    }
    public Action SpecimenIntakeRoutine() {
        return new SequentialAction(
                this.CV4BDown(),
                new SleepAction(0.5),
                this.transferIC(),
                new SleepAction(0.25),
                this.BackToRobot()


        );
    }
    private Action CV4BDown() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                Coax.setPosition(0.13);
                V4B.setPosition(0.57);;
                return false;
            }
        };
    }
    private Action BackToRobot() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                V4B.setPosition(0.4);
                ExtRight.setPosition(0.7);
                ExtLeft.setPosition(0.3);
                return false;
            }
        };
    }


}


