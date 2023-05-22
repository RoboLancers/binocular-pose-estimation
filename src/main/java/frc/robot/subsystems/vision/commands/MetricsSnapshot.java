package frc.robot.subsystems.vision.commands;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.vision.Camera;
import frc.robot.util.Statistics;

public class MetricsSnapshot extends CommandBase {
    private String metricMode = "targetpose_cameraspace";

    private ArrayList<Double> xPositions;
    private ArrayList<Double> yPositions;
    private ArrayList<Double> zPositions;

    private ArrayList<Double> xRotations;
    private ArrayList<Double> yRotations;
    private ArrayList<Double> zRotations;

    private String camID;
    private Camera camera;

    public MetricsSnapshot(String camID, Camera camera){
        this.camID = camID;
        this.camera = camera;
    }

    @Override
    public void execute(){
        double[] output = this.camera.getTag(this.metricMode);

        // TODO: these indices are always wacky when we try to use them, pos and rot may be flipped
            double xPosition = output[0];
            double yPosition = output[1];
            double zPosition = output[2];

            double xRotation = output[3];
            double yRotation = output[4];
            double zRotation = output[5];

        SmartDashboard.putNumber(camID + " posX", xPosition);
        SmartDashboard.putNumber(camID + " posY", yPosition);
        SmartDashboard.putNumber(camID + " posZ", zPosition);

        SmartDashboard.putNumber(camID + " rotX", xRotation);
        SmartDashboard.putNumber(camID + " rotY", yRotation);
        SmartDashboard.putNumber(camID + " rotZ", zRotation);

        xPositions.add(xPosition);
        yPositions.add(yPosition);
        zPositions.add(zPosition);

        xRotations.add(xRotation);
        yRotations.add(yRotation);
        zRotations.add(zRotation);
    }

    @Override
    public void end(boolean interrupted){
        Statistics xPositionData = new Statistics(xPositions);
        Statistics yPositionData = new Statistics(yPositions);
        Statistics zPositionData = new Statistics(zPositions);

        Statistics xRotationData = new Statistics(xRotations);
        Statistics yRotationData = new Statistics(yRotations);
        Statistics zRotationData = new Statistics(zRotations);

        xPositionData.log(camID + " posX data");
        yPositionData.log(camID + " posY data");
        zPositionData.log(camID + " posX data");

        xRotationData.log(camID + " rotX data");
        yRotationData.log(camID + " rotY data");
        zRotationData.log(camID + " rotZ data");
    }
}
