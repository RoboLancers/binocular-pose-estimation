package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Ray3;
import frc.robot.util.Vec3;

public class Vision extends SubsystemBase {
    private Camera cam1;
    private Camera cam2;

    public Vision(Camera cam1, Camera cam2){
        this.cam1 = cam1;
        this.cam2 = cam2;
    }

    public Vec3 estimateTargetPosition(){
        if (!this.cam1.hasTarget() || !this.cam2.hasTarget()) return null;

        Ray3 ray1 = this.cam1.getRay();
        ray1.c.log("Ray1 Pos");
        ray1.v.log("Ray1 Dir");

        Ray3 ray2 = this.cam2.getRay();
        ray2.c.log("Ray2 Pos");
        ray2.v.log("Ray2 Dir");
        
        double dot = Vec3.dot(ray1.v, ray2.v);
        SmartDashboard.putNumber("Ray Dir Dot Product", dot);

        if (dot * dot == 1) return null;

        Vec3 deltaC = Vec3.subtract(ray2.c, ray1.c);
        deltaC.log("Delta Ray Pos");
        
        double num = Vec3.dot(deltaC, Vec3.subtract(Vec3.scalarMultiply(ray2.v, dot), ray1.v));
        double den = dot * dot - 1;

        double t1 = num / den;
        double t2 = t1 * dot - Vec3.dot(ray2.v, deltaC);

        Vec3 p1 = Vec3.add(ray1.c, Vec3.scalarMultiply(ray1.v, t1));
        p1.log("Estimate1 Pos");

        Vec3 p2 = Vec3.add(ray2.c, Vec3.scalarMultiply(ray2.v, t2));
        p2.log("Estimate2 Pos");

        Vec3 pAvg = Vec3.scalarMultiply(Vec3.add(p1, p2), 0.5);
        pAvg.log("Estimate Avg Pos");

        double error = 0.5 * Vec3.subtract(p1, p2).magnitude();
        SmartDashboard.putNumber("Estimate Separation", error);
        
        return pAvg;
    }
}
