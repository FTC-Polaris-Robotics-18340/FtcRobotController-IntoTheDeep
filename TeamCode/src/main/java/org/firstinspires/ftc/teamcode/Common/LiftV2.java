package org.firstinspires.ftc.teamcode.Common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LiftV2 {
    MotorEx SlideLeft;
    MotorEx SlideRight;
    final double uptimeMillis = 300;

    public LiftV2 (HardwareMap hardwareMap){
        MotorEx SL = new MotorEx(hardwareMap, "LiftLeft");
        SL.setRunMode(Motor.RunMode.RawPower);
        SL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        this.SlideLeft = SL;

        MotorEx SR = new MotorEx(hardwareMap,"LiftRight");
        SR.setRunMode(Motor.RunMode.RawPower);
        SR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        this.SlideRight = SR;
    }

    public Action slidesToScoreSpec(){
        return new Action() {
            private boolean initialized = false;
            private double startTime;
            @Override
            public boolean run(@NonNull TelemetryPacket packet){
                if (!initialized) {
                    SlideLeft.set(-1);
                    SlideRight.set(-1);
                    startTime = System.currentTimeMillis();
                    initialized = true;
                }

                if (System.currentTimeMillis() - startTime > uptimeMillis*2) {
                    SlideLeft.set(-0.02);
                    SlideRight.set(-0.02);
                    return false;
                }
                return true;
            }
        };
    }

    public Action slidesDownToGround(){
        return new Action() {
            private boolean initialized = false;
            private double startTime;
            @Override
            public boolean run(@NonNull TelemetryPacket packet){
                if (!initialized) {
                    SlideLeft.set(1);
                    SlideRight.set(1);
                    startTime = System.currentTimeMillis();
                    initialized = true;
                }

                if (System.currentTimeMillis() - startTime > uptimeMillis * 5) {
                    SlideLeft.set(-0.02);
                    SlideRight.set(-0.02);
                    return false;
                }
                return true;
            }
        };
    }
}
