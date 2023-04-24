package frc.robot.subsystems.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.util.Ray3;
import frc.robot.util.Vec3;

public class Camera {
    private Vec3 position; // x and z with respect to axis of rotation, y to same scale
    private Vec3 direction;

    private NetworkTable limelight;

    public Camera(Vec3 position, Vec3 direction, String nt){
        this.position = position;
        this.direction = direction;

        this.limelight = NetworkTableInstance.getDefault().getTable(nt);
    }

    public boolean hasTarget(){
        return this.limelight.getEntry("tv").getDouble(0) == 1;
    }

    public double targetPitch(){
        return this.limelight.getEntry("ty").getDouble(0) * Math.PI / 180;
    }

    public double targetYaw(){
        return this.limelight.getEntry("tx").getDouble(0) * Math.PI / 180;
    }

    public Ray3 getRay(){
        // euler order is potentially not negligible, hopefully having parallel camera directions accounts for this
        return new Ray3(this.position, Vec3.rotateY(Vec3.rotateX(this.direction, this.targetPitch()), this.targetYaw()));
    }
}
