package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Common.Extension;
import org.firstinspires.ftc.teamcode.Common.Limelight;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "AUTO Testing", group = "Autonomous")
public class AutoTesting extends LinearOpMode {

    private Limelight3A limelight;
    private MecanumDrive drive;

    public void runOpMode() {
        Pose2d initialPose = new Pose2d(8, -61, Math.toRadians(90));

        drive = new MecanumDrive(hardwareMap, initialPose);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        final Extension extension = new Extension(hardwareMap);

        Pose2d toBasket_lastPose = new Pose2d(0, -33, Math.toRadians(90));
        Pose2d pushSample3_lastPose = new Pose2d(50, -40, Math.toRadians(180));
        Pose2d turnToSamplePose = new Pose2d(50, -57, Math.toRadians(90));
        Pose2d pickSpecimenPose = new Pose2d(40, -57, Math.toRadians(90));
        Pose2d scoreSpecimenPose = new Pose2d(0, -33, Math.toRadians(90));

        final double minTransVel = 100;
        final double minProfAccel = -70;
        final double maxProfAccel = 100;

        final double minTransVelStraight = 120;
        final double minProfAccelStraight = -80;
        final double maxProfAccelStraight = 120;

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .splineToLinearHeading(toBasket_lastPose, Math.toRadians(90), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight));

        TrajectoryActionBuilder tab2 = tab1.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(34, -36, Math.toRadians(90)), Math.toRadians(0), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel)) // new ProfileAccelConstraint(-100, 100)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(34, -8, Math.toRadians(90)), Math.toRadians(90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(45,  -8, Math.toRadians(180)), Math.toRadians(0), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(45, -44, Math.toRadians(180)), Math.toRadians(270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .splineToLinearHeading(new Pose2d(45, -40, Math.toRadians(180)), Math.toRadians(270));

        TrajectoryActionBuilder tab3 = tab2.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(42, -10, Math.toRadians(180)), Math.toRadians(90), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(54,  -10, Math.toRadians(180)), Math.toRadians(0), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(54, -44, Math.toRadians(180)), Math.toRadians(270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .splineToLinearHeading(new Pose2d(54, -40, Math.toRadians(180)), Math.toRadians(270));

        TrajectoryActionBuilder tab4 = tab3.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(52, -10, Math.toRadians(180)), Math.toRadians(90), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(62,  -10, Math.toRadians(180)), Math.toRadians(0), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(62, -44, Math.toRadians(180)), Math.toRadians(270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .splineToLinearHeading(pushSample3_lastPose, Math.toRadians(270));

        TrajectoryActionBuilder tab5 = tab4.endTrajectory().fresh()
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(turnToSamplePose, Math.toRadians(270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab6 = tab5.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(scoreSpecimenPose, Math.toRadians(90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab7 = tab6.endTrajectory().fresh()
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab8 = tab7.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(scoreSpecimenPose, Math.toRadians(90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab9 = tab8.endTrajectory().fresh()
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab10 = tab9.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(scoreSpecimenPose, Math.toRadians(90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab11 = tab10.endTrajectory().fresh()
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab12 = tab11.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(scoreSpecimenPose, Math.toRadians(90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));


        Action toBasket = tab1.build();
        Action pushSample1 = tab2.build();
        Action pushSample2 = tab3.build();
        Action pushSample3 = tab4.build();
        Action turnToSpecimen = tab5.build();
        Action scoreSpecimenFromTurn = tab6.build();
        Action pickSpecimenFirst = tab7.build();
        Action scoreSpecimenFromPickFirst = tab8.build();
        Action pickSpecimenSecond = tab9.build();
        Action scoreSpecimenFromPickSecond = tab10.build();
        Action pickSpecimenThird = tab11.build();
        Action scoreSpecimenFromPickThird = tab12.build();

        limelight.start();

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                extension.Retract(),
                                toBasket
                        ),
                        pushSample1,
                        pushSample2,
                        pushSample3
                )
        );

        aprilTagCorrection(pushSample3_lastPose, 2.5, 5);

        Actions.runBlocking(
                new SequentialAction(
                        turnToSpecimen,
                        scoreSpecimenFromTurn
                )
        );

        aprilTagCorrection(scoreSpecimenPose, 1, 5);

        Actions.runBlocking(
                new SequentialAction(
                        pickSpecimenFirst,
                        scoreSpecimenFromPickFirst
                )
        );

        aprilTagCorrection(scoreSpecimenPose, 1, 5);

        Actions.runBlocking(
                new SequentialAction(
                        pickSpecimenSecond,
                        scoreSpecimenFromPickSecond
                )
        );

        aprilTagCorrection(scoreSpecimenPose, 1, 5);

        Actions.runBlocking(
                new SequentialAction(
                        pickSpecimenThird,
                        scoreSpecimenFromPickThird
                )
        );

        aprilTagCorrection(scoreSpecimenPose, 1, 5);

    }

    public void aprilTagCorrection(Pose2d correctionPose, double distanceError, double headingError) {
        if (limelight.getLatestResult() != null && limelight.getLatestResult().isValid()) {
            telemetry.addData("Result is: ", limelight.getLatestResult().isValid());
            telemetry.addData("Bot Pose in Pose3d is: ", limelight.getLatestResult().getBotpose().toString());

            Pose3D result = limelight.getLatestResult().getBotpose();

            Pose2d endPose = new Pose2d(Limelight.metersToInches(result.getPosition().x), Limelight.metersToInches(result.getPosition().y), Math.toRadians(result.getOrientation().getYaw()));

            telemetry.addData("Bot Pose in Pose2d is: ", endPose.toString());
            telemetry.addData("X/Y error", Limelight.distanceBetweenPose(endPose, correctionPose));
            telemetry.addData("Heading error", Limelight.headingDifferencePose(endPose, correctionPose));

            if (Limelight.distanceBetweenPose(endPose, correctionPose) > distanceError ||
                    Limelight.headingDifferencePose(endPose, correctionPose) > headingError){
                Action correctionA = drive.actionBuilder(endPose)
                        .splineToLinearHeading(correctionPose, Math.toRadians(270))
                        .build();

                Actions.runBlocking(
                        new SequentialAction(
                                correctionA
                        )
                );
            } else {
                telemetry.addLine("No Correction Needed!");
            }

            telemetry.update();
        }
    }
}