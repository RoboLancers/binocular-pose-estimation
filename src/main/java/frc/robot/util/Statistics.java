package frc.robot.util;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Statistics {
    public double min;
    public double max;
    public double range;
    public double average;

    public Statistics(ArrayList<Double> data){
        this.min = Double.POSITIVE_INFINITY;
        this.max = Double.NEGATIVE_INFINITY;
        this.average = 0;

        for(double x : data){
            if (x < this.min) this.min = x;
            if (x > this.max) this.max = x;

            this.average += x;
        }

        this.range = max - min;

        this.average /= data.size();
    }

    public void log(String dataID){
        SmartDashboard.putNumber(dataID + " min", this.min);
        SmartDashboard.putNumber(dataID + " max", this.max);
        SmartDashboard.putNumber(dataID + " range", this.range);
        SmartDashboard.putNumber(dataID + " avg", this.average);
    }
}
