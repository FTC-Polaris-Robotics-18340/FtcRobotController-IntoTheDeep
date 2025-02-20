package org.firstinspires.ftc.teamcode.Common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OuttakeRot {
    SimpleServo ClawRot;

    public OuttakeRot(HardwareMap hardwareMap){
        ClawRot = new SimpleServo(hardwareMap, "Orot", 0.0, 1.0);
    }


    public Action scorePos() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ClawRot.setPosition(0.7);
                return false;
            }
        };
    }

    public Action pickPos() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ClawRot.setPosition(0);
                return false;
            }
        };
    }
}
