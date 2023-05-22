package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.vision.Camera;
import frc.robot.subsystems.vision.Vision;
import frc.robot.subsystems.vision.commands.MetricsSnapshot;
import frc.robot.util.Controller;

public class RobotContainer {
  Controller driverController = new Controller(0);
  Controller manipulatorController = new Controller(1);

  Drivetrain drivetrain = new Drivetrain();

  Camera cam1 = new Camera(Constants.Vision.camPos1, Constants.Vision.camDir1, Constants.Vision.camNt1);
  Camera cam2 = new Camera(Constants.Vision.camPos2, Constants.Vision.camDir2, Constants.Vision.camNt2);
  Vision vision = new Vision(cam1, cam2);

  public RobotContainer() {
    drivetrain.setDefaultCommand(new RunCommand(() -> {
      drivetrain.arcadeDrive(driverController.getLeftStickY(), driverController.getRightStickX());
    }, drivetrain));

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    Controller.onPress(driverController.A, new InstantCommand(vision::estimateTargetPosition));

    Controller.onBothPress(driverController.LeftTrigger, driverController.RightTrigger, new ParallelCommandGroup(
      new MetricsSnapshot("cam1", cam1),
      new MetricsSnapshot("cam2", cam2)
    ));
  }

  public Command getAutonomousCommand() {
    return new RunCommand(() -> {});
  }
}
