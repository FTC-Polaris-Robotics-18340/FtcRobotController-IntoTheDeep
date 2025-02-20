package org.firstinspires.ftc.teamcode.Common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OuttakeWrist {
    SimpleServo ClawWristRight;
    SimpleServo ClawWristLeft;

    public OuttakeWrist(HardwareMap hardwareMap){
        ClawWristRight = new SimpleServo(hardwareMap, "ORW", 0.0, 1.0);
        ClawWristLeft = new SimpleServo(hardwareMap, "OLW", 0.0, 1.0);
    }

    public Action scorePosition() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ClawWristRight.setPosition(0.7);
                ClawWristLeft.setPosition(0.3);
                return false;
            }
        };
    }

    public Action scoreReadyPosition() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ClawWristRight.setPosition(0.65);
                ClawWristLeft.setPosition(0.35);
                return false;
            }
        };
    }

    public Action pickPosition() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ClawWristRight.setPosition(0.65);
                ClawWristLeft.setPosition(0.35);
                return false;
            }
        };
    }
}
