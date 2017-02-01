package org.usfirst.frc.team3274.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team3274.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    /*
     * ----- button map -----
     * here are the button codes for the xbox controller
     * a - 0
     * b - 1
     * x - 2
     * y - 3
     * left bumper - 4
     * right bumper - 5
     * left trigger - 6
     * right trigger - 7
     * select / back - 8
     * start - 9
     * left analogue stick press - 10
     * right analogue stick press - 11
     * dpad up - 12
     * dpad down - 13
     * dpad left - 14
     * dpad right - 15
     */
    
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
    
    /* Nathan make some buttons */
    Joystick joyStick = new Joystick(0); // set to ID 1 
    
//    in DriverStation
    
    public Joystick getJoystick() {
        return this.joyStick;
    }
}
