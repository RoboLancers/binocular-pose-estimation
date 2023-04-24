package frc.robot;

import frc.robot.util.Vec3;

public final class Constants {
    public static final class Drivetrain {
        public static class LeftMotors {
            public static final int kLeftMotor1_Port = 10;
            public static final int kLeftMotor2_Port = 15;
            public static final int kLeftMotor3_Port = 11;
        }

        public static class RightMotors {
            public static final int kRightMotor1_Port = 19;
            public static final int kRightMotor2_Port = 18;
            public static final int kRightMotor3_Port = 17;
        }

        public static final int kMaxStallAmps = 45;
    }

    public static final class Vision {
        public static final Vec3 camPos1 = new Vec3(-2, 0, 0);
        public static final Vec3 camDir1 = new Vec3(0, 0, 1);
        public static final String camNt1 = "limelight1";

        public static final Vec3 camPos2 = new Vec3(2, 0, 0);
        public static final Vec3 camDir2 = new Vec3(0, 0, 1);
        public static final String camNt2 = "limelight2";
    }
}
