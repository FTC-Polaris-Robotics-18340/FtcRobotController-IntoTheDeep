package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(20, 20)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(90, 65, Math.toRadians(180), Math.toRadians(180), 17)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder((new Pose2d(8, -63.5, Math.toRadians(90))))
                        .splineToLinearHeading(new Pose2d(-10, -33, Math.toRadians(90)), Math.toRadians(90)) // tab1 Score 1

                        .setTangent(Math.toRadians(0))                                                           // tab2 push 1
                        .splineToLinearHeading(new Pose2d(37, -36, Math.toRadians(90)), Math.toRadians(0))
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(37, -8, Math.toRadians(90)), Math.toRadians(90))
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(45,  -8, Math.toRadians(180)), Math.toRadians(0))
                        .setTangent(Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(45, -44, Math.toRadians(180)), Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(45, -40, Math.toRadians(180)), Math.toRadians(270))

                        .setTangent(Math.toRadians(90))                                                           // tab3 push 2
                        .splineToLinearHeading(new Pose2d(42, -10, Math.toRadians(180)), Math.toRadians(90))
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(54,  -10, Math.toRadians(180)), Math.toRadians(0))
                        .setTangent(Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(54, -44, Math.toRadians(180)), Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(54, -40, Math.toRadians(180)), Math.toRadians(270))

                        .setTangent(Math.toRadians(90))                                                          // tab4 push 3
                        .splineToLinearHeading(new Pose2d(52, -10, Math.toRadians(180)), Math.toRadians(90))
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(62,  -10, Math.toRadians(180)), Math.toRadians(0))
                        .setTangent(Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(62, -44, Math.toRadians(180)), Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(50, -40, Math.toRadians(180)), Math.toRadians(270))

                        .setTangent(Math.toRadians(270))                                                          // tab5 turn to pick spec
                        .splineToLinearHeading(new Pose2d(48, -37, Math.toRadians(90)), Math.toRadians(270))

                        .lineTo(new Vector2d(48, -49.5))

                        .setTangent(Math.toRadians(135))                                                           // tab6 score spec after pick
                        .splineToLinearHeading(new Pose2d(-7, -20, Math.toRadians(90)), Math.toRadians(90))
                        .lineTo(new Vector2d(-7, -33))

                        .setTangent(Math.toRadians(270))                                                          // tab7 pick spec
                        .splineToLinearHeading(new Pose2d(48, -37, Math.toRadians(90)), Math.toRadians(270))

                        .lineTo(new Vector2d(48, -49.5))

                        .setTangent(Math.toRadians(135))                                                          // tab8 score spec
                        .splineToLinearHeading(new Pose2d(-5, -20, Math.toRadians(90)), Math.toRadians(90))
                        .lineTo(new Vector2d(-5, -33))

                        .setTangent(Math.toRadians(270))                                                          // tab7 pick spec
                        .splineToLinearHeading(new Pose2d(48, -37, Math.toRadians(90)), Math.toRadians(270))

                        .lineTo(new Vector2d(48, -49.5))

                        .setTangent(Math.toRadians(135))                                                          // tab8 score spec
                        .splineToLinearHeading(new Pose2d(-3, -20, Math.toRadians(90)), Math.toRadians(90))
                        .lineTo(new Vector2d(-3, -33))

                        .setTangent(Math.toRadians(270))                                                          // tab7 pick spec
                        .splineToLinearHeading(new Pose2d(48, -37, Math.toRadians(90)), Math.toRadians(270))

                        .lineTo(new Vector2d(48, -49.5))

                        .setTangent(Math.toRadians(135))                                                          // tab8 score spec
                        .splineToLinearHeading(new Pose2d(0, -20, Math.toRadians(90)), Math.toRadians(90))
                        .lineTo(new Vector2d(0, -33))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}