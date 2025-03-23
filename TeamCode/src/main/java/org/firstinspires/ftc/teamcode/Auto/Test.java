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
import org.firstinspires.ftc.teamcode.Common.Arm;
import org.firstinspires.ftc.teamcode.Common.AutoActions;
import org.firstinspires.ftc.teamcode.Common.Extension;
import org.firstinspires.ftc.teamcode.Common.LiftV2;
import org.firstinspires.ftc.teamcode.Common.Limelight;
import org.firstinspires.ftc.teamcode.Common.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Common.OuttakeRot;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "Test", group = "1Autonomous")
public class Test extends LinearOpMode {
    private Limelight3A limelight;
    private MecanumDrive drive;

    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-12, 63.5, Math.toRadians(-90));

        drive = new MecanumDrive(hardwareMap, initialPose);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        final Extension extension = new Extension(hardwareMap);
        final Arm arm = new Arm(hardwareMap);
        final OuttakeClaw outClaw = new OuttakeClaw(hardwareMap);
        final OuttakeRot outRot = new OuttakeRot(hardwareMap);
        final LiftV2 lift = new LiftV2(hardwareMap);

        final AutoActions autoActions = new AutoActions(hardwareMap);

        Pose2d toBasket_lastPose = new Pose2d(0, -33, Math.toRadians(-90));
        /*Pose2d pushSample3_lastPose = new Pose2d(-50, 40, Math.toRadians(-180));
        Pose2d turnToSamplePose = new Pose2d(-48, 37, Math.toRadians(-90));
        Pose2d pickSpecimenPose = new Pose2d(-48, 35, Math.toRadians(-90));
        Pose2d scoreSpecimenPoseFirst = new Pose2d(3, 30, Math.toRadians(-90));
        Pose2d scoreSpecimenPoseSecond = new Pose2d(6, 30, Math.toRadians(-90));
        Pose2d scoreSpecimenPoseThird = new Pose2d(0, 33, Math.toRadians(-90));
        Pose2d scoreSpecimenPoseFourth = new Pose2d(0, 33, Math.toRadians(-90));*/

        final double minTransVel = 90;
        final double minProfAccel = -60;
        final double maxProfAccel = 80;

        final double minTransVelStraight = 120;
        final double minProfAccelStraight = -80;
        final double maxProfAccelStraight = 120;

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .splineToLinearHeading(toBasket_lastPose, Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab2 = drive.actionBuilder(initialPose)
                .splineToLinearHeading(new Pose2d(-12, -63.5, Math.toRadians(-90)), Math.toRadians(0));



        /*TrajectoryActionBuilder tab2 = tab1.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-37, 38, Math.toRadians(-90)), Math.toRadians(0), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-37, 6, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-45, 6, Math.toRadians(-180)), Math.toRadians(0))
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(new Pose2d(-45, 58, Math.toRadians(-180)), Math.toRadians(-270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight));
        //.splineToLinearHeading(new Pose2d(45, -40, Math.toRadians(180)), Math.toRadians(270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight));

        TrajectoryActionBuilder tab4 = tab2.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-33, 4, Math.toRadians(-180)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-54, 4, Math.toRadians(-180)), Math.toRadians(0), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(new Pose2d(-54, 58, Math.toRadians(-180)), Math.toRadians(-270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .splineToLinearHeading(pushSample3_lastPose, Math.toRadians(-270));

//        TrajectoryActionBuilder tab4 = tab3.endTrajectory().fresh()
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(50, -6, Math.toRadians(180)), Math.toRadians(90), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
//                .setTangent(Math.toRadians(0))
//                .splineToLinearHeading(new Pose2d(62,  -6, Math.toRadians(180)), Math.toRadians(0), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
//                .setTangent(Math.toRadians(270))
//                .splineToLinearHeading(new Pose2d(62, -48, Math.toRadians(180)), Math.toRadians(270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
//                .splineToLinearHeading(pushSample3_lastPose, Math.toRadians(270));

        TrajectoryActionBuilder tab5 = tab4.endTrajectory().fresh()
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(turnToSamplePose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab6 = tab5.endTrajectory().fresh()
                .lineToY(53);

        TrajectoryActionBuilder tab7 = tab6.endTrajectory().fresh()
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(-0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33);

        TrajectoryActionBuilder tab8 = tab7.endTrajectory().fresh()
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab9 = tab8.endTrajectory().fresh()
                .lineToY(53);

        TrajectoryActionBuilder tab10 = tab9.endTrajectory().fresh()
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33);

        TrajectoryActionBuilder tab11 = tab10.endTrajectory().fresh()
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab12 = tab11.endTrajectory().fresh()
                .lineToY(51);

        TrajectoryActionBuilder tab13 = tab12.endTrajectory().fresh()
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33);

        TrajectoryActionBuilder tab14 = tab13.endTrajectory().fresh()
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab15 = tab14.endTrajectory().fresh()
                .lineToY(51);

        TrajectoryActionBuilder tab16 = tab15.endTrajectory().fresh()
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33);*/


        Action toChambers = tab1.build();
        Action toBack = tab2.build();

        /*Action pushSample1 = tab2.build();
//        Action pushSample2 = tab3.build();
        Action pushSample3 = tab4.build();
        Action turnToSpecimen = tab5.build();
        Action pickSpecimenAfterTurn = tab6.build();
        Action scoreSpecimenFromTurn = tab7.build();
        Action pickSpecimenFirst = tab8.build();
        Action pickSpecimenFirstForward = tab9.build();
        Action scoreSpecimenFromPickFirst = tab10.build();
        Action pickSpecimenSecond = tab11.build();
        Action pickSpecimenSecondForward = tab12.build();
        Action scoreSpecimenFromPickSecond = tab13.build();
        Action pickSpecimenThird = tab14.build();
        Action pickSpecimenThirdForward = tab15.build();
        Action scoreSpecimenFromPickThird = tab16.build();

        limelight.start();*/

        Actions.runBlocking(
                autoActions.initRoutine()
        );

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                extension.Retract(),
                                toChambers,
                                autoActions.scoreReadyRoutine(),
                                lift.slidesToScoreSpec(),
                                outClaw.ClawOpen(),
                                new SleepAction(3),
                                toBack
                        )
                )
        );

       /* aprilTagCorrection(toBasket_lastPose, 1, 5);

        Actions.runBlocking(
                new SequentialAction(
                        autoActions.scoringRoutine(),

                        new SleepAction(0.5),

                        outClaw.ClawOpen(),

                        new SleepAction(0.5),

                        new ParallelAction(
                                pushSample1,
                                lift.slidesDownToGround()
                        ),

//                        pushSample2,
                        pushSample3
                )
        );

        aprilTagCorrection(pushSample3_lastPose, 2.5, 5);

        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                turnToSpecimen,
                                autoActions.pickRoutine()
                        ),

                        pickSpecimenAfterTurn,
                        outClaw.ClawClose(),

                        new SleepAction(0.5),

                        autoActions.scoreReadyRoutine(),

                        new SleepAction(1),

                        new ParallelAction(
                                outRot.scorePos(),
                                scoreSpecimenFromTurn,
                                lift.slidesToScoreSpec()

                        )
                )
        );

        aprilTagCorrection(scoreSpecimenPoseFirst, 1, 5);

        Actions.runBlocking(
                new SequentialAction(
                        autoActions.scoringRoutine(),

                        new SleepAction(1),

                        outClaw.ClawOpen()
                )
        );

        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                pickSpecimenFirst,
                                autoActions.pickRoutine(),
                                lift.slidesDownToGround()
                        ),

                        pickSpecimenFirstForward,
                        outClaw.ClawClose(),

                        new SleepAction(0.5),

                        autoActions.scoreReadyRoutine(),

                        new SleepAction(1),

                        new ParallelAction(
                                outRot.scorePos(),
                                scoreSpecimenFromPickFirst,
                                lift.slidesToScoreSpec()

                        )
                )
        );

        aprilTagCorrection(scoreSpecimenPoseSecond, 1, 5);

        Actions.runBlocking(
                new SequentialAction(
                        autoActions.scoringRoutine(),

                        new SleepAction(1),

                        outClaw.ClawOpen()
                )
        );

//        Actions.runBlocking(
//                new SequentialAction(
//                        new ParallelAction(
//                                pickSpecimenSecond,
//                                autoActions.pickRoutine(),
//                                lift.slidesDownToGround()
//                        ),
//
//                        pickSpecimenSecondForward,
//                        outClaw.ClawClose(),
//
//                        new SleepAction(0.5),
//
//                        autoActions.scoreReadyRoutine(),
//
//                        new SleepAction(2),
//
//                        new ParallelAction(
//                                scoreSpecimenFromPickSecond,
//                                lift.slidesToScoreSpec()
//                        ),
//
//                        autoActions.scoringRoutine(),
//
//                        new SleepAction(1),
//
//                        outClaw.ClawOpen()
//                )
//        );
//
//        aprilTagCorrection(scoreSpecimenPoseThird, 1, 5);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        new ParallelAction(
//                                pickSpecimenThird,
//                                autoActions.pickRoutine(),
//                                lift.slidesDownToGround()
//                        ),
//
//                        pickSpecimenThirdForward,
//                        outClaw.ClawClose(),
//
//                        new SleepAction(0.5),
//
//                        autoActions.scoreReadyRoutine(),
//
//                        new SleepAction(2),
//
//                        new ParallelAction(
//                                scoreSpecimenFromPickThird,
//                                lift.slidesToScoreSpec()
//                        ),
//
//                        autoActions.scoringRoutine(),
//
//                        new SleepAction(1),
//
//                        outClaw.ClawOpen()
//                )
//        );
//
//        aprilTagCorrection(scoreSpecimenPoseFourth, 1, 5);

    }

    public void aprilTagCorrection(Pose2d correctionPose, double distanceError, double headingError) {
        if (limelight.getLatestResult() != null && limelight.getLatestResult().isValid()) {
            Pose3D result = limelight.getLatestResult().getBotpose();

            Pose2d endPose = new Pose2d(Limelight.metersToInches(result.getPosition().x),
                    Limelight.metersToInches(result.getPosition().y),
                    Math.toRadians(result.getOrientation().getYaw()));

            if (Limelight.distanceBetweenPose(endPose, correctionPose) > distanceError ||
                    Limelight.headingDifferencePose(endPose, correctionPose) > headingError) {
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

            telemetry.update();*/
        }
    }

