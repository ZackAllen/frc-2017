package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Starts all the commands that need to be started without the use of buttons at
 * the start of teleop.
 * 
 * @author Anthony 'AJ' Snarr
 *
 */
public class RunTeleop extends CommandGroup {

    /**
     * Add commands in group here.
     */
    public RunTeleop() {
        // make the robot respond to joystick input for wheel
        // movement
        //addParallel(new DriveWithJoystick());

        // add other commands here
        // example: addSequential(new Command());

    }
}
