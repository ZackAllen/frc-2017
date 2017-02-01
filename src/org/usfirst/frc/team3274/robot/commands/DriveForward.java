package org.usfirst.frc.team3274.robot.commands;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Used to make the robot drive forward a certain distance in autonomous.
 * 
 * @author Dell-Laptop-FLL
 *
 */
public class DriveForward extends Command {

    private double driveForwardSpeed;
    private double distance;
    private double error;

    public DriveForward() {
        this(10);
    }

    public DriveForward(double dist) {
        this(dist, 0.5);
    }

    public DriveForward(double dist, double maxSpeed) {
        requires(Robot.drivetrain);
        this.distance = dist;
        this.driveForwardSpeed = maxSpeed;
    }

    /** 
     * Called just before this Command runs the first time.
     */
    @Override
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    @Override
    protected void execute() {
    }

    /**
     * Make this return true when this Command no longer needs to run execute().
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called once after isFinished returns true.
     */
    @Override
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the
     * same(non-Javadoc)
     * 
     * {@see edu.wpi.first.wpilibj.command.Command#interrupted()}
     * 
     * subsystems is scheduled to run.
     */
    @Override
    protected void interrupted() {
    }
}
