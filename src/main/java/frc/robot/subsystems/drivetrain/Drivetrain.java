package frc.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    private final CANSparkMax leftMotor1 = new CANSparkMax(Constants.Drivetrain.LeftMotors.kLeftMotor1_Port, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax leftMotor2 = new CANSparkMax(Constants.Drivetrain.LeftMotors.kLeftMotor2_Port, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax leftMotor3 = new CANSparkMax(Constants.Drivetrain.LeftMotors.kLeftMotor3_Port, CANSparkMaxLowLevel.MotorType.kBrushless);

    public final MotorControllerGroup leftMotors = new MotorControllerGroup(
            leftMotor1, 
            // leftMotor2, 
            leftMotor3
    );

    private final CANSparkMax rightMotor1 = new CANSparkMax(Constants.Drivetrain.RightMotors.kRightMotor1_Port, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rightMotor2 = new CANSparkMax(Constants.Drivetrain.RightMotors.kRightMotor2_Port, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rightMotor3 = new CANSparkMax(Constants.Drivetrain.RightMotors.kRightMotor3_Port, CANSparkMaxLowLevel.MotorType.kBrushless);

    public final MotorControllerGroup rightMotors = new MotorControllerGroup(
            rightMotor1, 
            // rightMotor2, 
            rightMotor3
    );

    private final DifferentialDrive difDrive = new DifferentialDrive(leftMotors, rightMotors);

    public Drivetrain() {
        rightMotor1.setInverted(true);
        rightMotor2.setInverted(true);
        rightMotor3.setInverted(true);

        leftMotor1.setInverted(false);
        leftMotor2.setInverted(false);
        leftMotor3.setInverted(false);

        leftMotor1.setIdleMode(IdleMode.kBrake);
        leftMotor2.setIdleMode(IdleMode.kCoast);
        leftMotor3.setIdleMode(IdleMode.kBrake);

        rightMotor1.setIdleMode(IdleMode.kBrake);
        rightMotor2.setIdleMode(IdleMode.kCoast);
        rightMotor3.setIdleMode(IdleMode.kBrake);

        leftMotor1.setSmartCurrentLimit(Constants.Drivetrain.kMaxStallAmps); 
        leftMotor2.setSmartCurrentLimit(Constants.Drivetrain.kMaxStallAmps); 
        leftMotor3.setSmartCurrentLimit(Constants.Drivetrain.kMaxStallAmps); 
        rightMotor1.setSmartCurrentLimit(Constants.Drivetrain.kMaxStallAmps); 
        rightMotor2.setSmartCurrentLimit(Constants.Drivetrain.kMaxStallAmps); 
        rightMotor3.setSmartCurrentLimit(Constants.Drivetrain.kMaxStallAmps); 

        leftMotor1.enableVoltageCompensation(12); 
        leftMotor2.enableVoltageCompensation(12); 
        leftMotor3.enableVoltageCompensation(12); 
        rightMotor1.enableVoltageCompensation(12); 
        rightMotor2.enableVoltageCompensation(12);  
        rightMotor3.enableVoltageCompensation(12); 
    }

    public void arcadeDrive(double throttle, double turn) {
        difDrive.arcadeDrive(throttle, turn, false);
    }
}