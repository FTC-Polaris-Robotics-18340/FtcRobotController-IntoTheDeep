package org.firstinspires.ftc.teamcode.Common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutoActions {
    SimpleServo OutClaw;
    SimpleServo OutClawRot;
    SimpleServo ArmLeft;
    SimpleServo ArmRight;
    SimpleServo OutWristRight;
    SimpleServo OutWristLeft;

    public AutoActions (HardwareMap hardwareMap){
        OutClaw = new SimpleServo(hardwareMap, "Oclaw", 0.0, 1.0);
        OutClawRot = new SimpleServo(hardwareMap, "Orot", 0.0, 1.0);
        ArmRight = new SimpleServo(hardwareMap, "ORA", 0.0, 1.0);
        ArmLeft = new SimpleServo(hardwareMap, "OLA", 0.0, 1.0);

        OutWristRight = new SimpleServo(hardwareMap, "ORW", 0.0, 1.0);
        OutWristLeft = new SimpleServo(hardwareMap, "OLW", 0.0, 1.0);
    }

    public Action scoringRoutine() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                OutWristRight.setPosition(0.3);
                OutWristLeft.setPosition(0.7); // Out Wrist Score Pos ^

                OutClaw.setPosition(1); // Out Claw Close

                OutClawRot.setPosition(0.7); // Out Claw Rot Score Pos ^

                ArmRight.setPosition(0.1);
                ArmLeft.setPosition(0.9); // Arm Score Spec ^

//                OutClaw.setPosition(0.4); // Out Claw Open
                return false;
            }
        };
    }

    public Action pickRoutine() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                OutWristRight.setPosition(0.35);
                OutWristLeft.setPosition(0.65); // Out Wrist Pick Pos ^

                OutClawRot.setPosition(0); // Out Claw Rot Pick Pos

                ArmRight.setPosition(1);
                ArmLeft.setPosition(0); // Arm Pick Spec ^

                OutClaw.setPosition(0.4); // Out Claw Open
                return false;
            }
        };
    }

    public Action scoreReadyRoutine() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                OutWristRight.setPosition(0.35);
                OutWristLeft.setPosition(0.65); // Out Wrist Ready Pos ^

                OutClaw.setPosition(1); // Out Claw Close

                ArmRight.setPosition(0.3);
                ArmLeft.setPosition(0.7); // Arm Score Ready Pos ^

                OutClawRot.setPosition(0.7); // Out Claw Rot Score Pos
                return false;
            }
        };
    }

    public Action initRoutine() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                OutWristRight.setPosition(0.6);
                OutWristLeft.setPosition(0.4); // Out Wrist Ready Pos ^

                OutClaw.setPosition(1); // Out Claw Close

                ArmRight.setPosition(0.1);
                ArmLeft.setPosition(0.9); // Arm Score Ready Pos ^

                OutClawRot.setPosition(0.7); // Out Claw Rot Score Pos
                return false;
            }
        };
    }
}
