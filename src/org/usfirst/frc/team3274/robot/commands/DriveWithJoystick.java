package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3274.robot.OI;
import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.subsystems.DriveTrain;;

/**
 * This command allows xbox joystick to drive the robot. It is always running
 * except when interrupted by another command.
 */
public class DriveWithJoystick extends Command {
    public DriveWithJoystick() {
        requires(Robot.drivetrain);
    }

    /**
     * Do the driving
     * @see edu.wpi.first.wpilibj.command.Command#execute()
     */
    @Override
    protected void execute() {
        Robot.drivetrain.carDrive(Robot.oi.getJoystick());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }
}