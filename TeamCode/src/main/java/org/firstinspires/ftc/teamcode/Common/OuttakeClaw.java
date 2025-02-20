package org.firstinspires.ftc.teamcode.Common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OuttakeClaw {
    SimpleServo Claw;

    public OuttakeClaw(SimpleServo c, SimpleServo c2){
        Claw = c;
    }

    public OuttakeClaw(HardwareMap hardwareMap){
        Claw = new SimpleServo(hardwareMap, "Oclaw", 0.0, 1.0);
    }


    public Action ClawClose() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                Claw.setPosition(1);
                return false;
            }
        };
    }

    public Action ClawOpen() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                Claw.setPosition(0.4);
                return false;
            }
        };
    }
}
