package org.firstinspires.ftc.teamcode.Common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    SimpleServo ArmLeft;
    SimpleServo ArmRight;

    public Arm (HardwareMap hwMap){
        ArmRight = new SimpleServo(hwMap, "ORA", 0.0, 1.0);
        ArmLeft = new SimpleServo(hwMap, "OLA", 0.0, 1.0);
    }

    public Action scoreReadyPosition() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ArmRight.setPosition(0.3);
                ArmLeft.setPosition(0.7);
                return false;
            }
        };
    }

    public Action scoreSpec() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ArmRight.setPosition(0);
                ArmLeft.setPosition(1);
                return false;
            }
        };
    }

    public Action pickSpec() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ArmRight.setPosition(1);
                ArmLeft.setPosition(0);
                return false;
            }
        };
    }
}
