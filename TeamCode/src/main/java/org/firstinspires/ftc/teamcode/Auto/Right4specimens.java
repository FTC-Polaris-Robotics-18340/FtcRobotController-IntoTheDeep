package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Common.Claw;
import org.firstinspires.ftc.teamcode.Common.Extension;
import org.firstinspires.ftc.teamcode.Common.Fourbar;
import org.firstinspires.ftc.teamcode.Common.Lift;
import org.firstinspires.ftc.teamcode.Common.Rotation;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "Right4specimens", group = "Autonomous")
public class Right4specimens extends LinearOpMode {
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(8, -61, Math.toRadians(90));

        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Fourbar fourbar = new Fourbar(hardwareMap);
        Rotation rotation = new Rotation(hardwareMap);
        Extension extension = new Extension(hardwareMap);
        Lift lift = new Lift(hardwareMap);






       /* TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .splineToConstantHeading(new Vector2d(0, -38), Math.toRadians(90));
        Action park = tab1.endTrajectory().fresh()
                //.lineToYConstantHeading(-38)
                .splineToConstantHeading(new Vector2d(60,-60), Math.toRadians(180))
                .build();*/



        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(8, -34, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(3)
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(38, -34, Math.toRadians(90)), Math.toRadians(0))
                .setTangent(Math.toRadians(100))
                .splineToLinearHeading(new Pose2d(54, 0, Math.toRadians(90)), Math.toRadians(0))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(54, -56, Math.toRadians(90)) , Math.toRadians(270))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(60, 0, Math.toRadians(90)), Math.toRadians(0))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(60, -56, Math.toRadians(90)), Math.toRadians(270))
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(54, -56, Math.toRadians(90)), Math.toRadians(180))
                .waitSeconds(1)
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(0, -34, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(2)
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(54, -56, Math.toRadians(90)), Math.toRadians(0))
                .waitSeconds(1)
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(2, -34, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(2)
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(54, -56, Math.toRadians(90)), Math.toRadians(0))
                .waitSeconds(1)
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(4, -34, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(2)
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(54, -56, Math.toRadians(90)), Math.toRadians(0));


        Action back = tab1.endTrajectory().fresh()

                .build();


        Action park = tab1.endTrajectory().fresh()
                .splineToLinearHeading(new Pose2d(28, 10, Math.toRadians(90)), Math.toRadians(100))
                .build();



                //.splineToConstantHeading(new Vector2d(60,-30), Math.toRadians(180));




        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(claw.ClawOpen());
        Actions.runBlocking(fourbar.FourBarUp());
        Actions.runBlocking(rotation.RotationHorizontal());


        waitForStart();

        if (isStopRequested()) return;

        Action toChambers = tab1.build();




        Actions.runBlocking(
                new SequentialAction(
                        toChambers,
                        back
                        //claw.ClawClose(),
                        //fourbar.FourBarDown(),
                        //lift.SlidesToBar(),
                        //new SleepAction(30)
                )
        );
    }
}
/*
spline 1(to chambers)

linear slides up

four bar set position 90 degrees

coaxial set position 90 degrees

linear slides down (not fully)

claw open

linear slides down fully

spline 2 (park)
 */
// auto fix