package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vec3 {
    public double x;
    public double y;
    public double z;

    public Vec3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void log(String name){
        SmartDashboard.putNumber(name + "X", this.x);
        SmartDashboard.putNumber(name + "Y", this.y);
        SmartDashboard.putNumber(name + "Z", this.z);

        double readableX = Math.round(this.x * 10) / 10;
        double readableY = Math.round(this.y * 10) / 10;
        double readableZ = Math.round(this.z * 10) / 10;

        SmartDashboard.putString(name, "< " + readableX + " , " + readableY + " , " + readableZ + " >");
    }

    public static Vec3 add(Vec3 v1, Vec3 v2){
        return new Vec3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vec3 subtract(Vec3 v1, Vec3 v2){
        return new Vec3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static Vec3 scalarMultiply(Vec3 v, double s){
        return new Vec3(v.x * s, v.y * s, v.z * s);
    }

    public double magnitude(){
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public static Vec3 normalize(Vec3 v){
        return Vec3.scalarMultiply(v, 1 / v.magnitude());
    }

    public static double dot(Vec3 v1, Vec3 v2){
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Vec3 rotateX(Vec3 v, double dTheta){
        double theta = Math.atan2(v.y, v.z) + dTheta;
        double radius = Math.sqrt(v.y * v.y + v.z * v.z);

        double y = Math.sin(theta) * radius;
        double z = Math.cos(theta) * radius;

        return new Vec3(v.x, y, z);
    }

    public static Vec3 rotateY(Vec3 v, double dTheta){
        double theta = Math.atan2(v.z, v.x) + dTheta;
        double radius = Math.sqrt(v.x * v.x + v.z * v.z);

        double x = Math.cos(theta) * radius;
        double z = Math.sin(theta) * radius;

        return new Vec3(x, v.y, z);
    }
}
