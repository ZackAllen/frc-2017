package org.usfirst.frc.team3274.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;

/**
 * The DriveTrain subsystem controls the robot's chassis and reads in
 * information about it's speed and position.
 */
public class DriveTrain extends Subsystem {

    // Four wheels
    SpeedController _frontLeftMotor = new CANTalon(1);
    SpeedController _frontRightMotor = new CANTalon(2);
    SpeedController _rearLeftMotor = new CANTalon(3);
    SpeedController _rearRightMotor = new CANTalon(4);

    private RobotDrive drive;
// private Encoder rightEncoder = new Encoder(1, 2, true, EncodingType.k4X);
// private Encoder leftEncoder = new Encoder(3, 4, false, EncodingType.k4X);

    public DriveTrain() {

        /*
         * About 'Casting' variables In the code above, we have defined the
         * wheels as SpeedControllers, a Java Interface and not a Class. For
         * some reason the methods below expects Motors. Via the magic of
         * polymorphism we can change the SpeedControllers into CANTalons.
         */
        // Configure drive motors
        LiveWindow.addActuator("DriveTrain", "Front Left CIM",
                (CANTalon) _frontLeftMotor);
        LiveWindow.addActuator("DriveTrain", "Front Right CIM",
                (CANTalon) _frontRightMotor);
        LiveWindow.addActuator("DriveTrain", "Back Left CIM",
                (CANTalon) _rearLeftMotor);
        LiveWindow.addActuator("DriveTrain", "Back Right CIM",
                (CANTalon) _rearRightMotor);

        // Configure the RobotDrive.
        drive = new RobotDrive((CANTalon) _frontLeftMotor,
                (CANTalon) _rearLeftMotor, (CANTalon) _frontRightMotor,
                (CANTalon) _rearRightMotor);

        drive.setSafetyEnabled(true);

// // Configure encoders
// rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
// leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
//
// if (Robot.isReal()) { // Converts to feet
// rightEncoder.setDistancePerPulse(0.0785398);
// leftEncoder.setDistancePerPulse(0.0785398);
// } else { // Convert to feet 4in diameter wheels with 360 tick simulated
// // encoders
// rightEncoder.setDistancePerPulse(
// (4.0/* in */ * Math.PI) / (360.0 * 12.0/* in/ft */));
// leftEncoder.setDistancePerPulse(
// (4.0/* in */ * Math.PI) / (360.0 * 12.0/* in/ft */));
// }
//
// LiveWindow.addSensor("DriveTrain", "Right Encoder", rightEncoder);
// LiveWindow.addSensor("DriveTrain", "Left Encoder", leftEncoder);
    }

    /**
     * When other commands aren't using the drivetrain, allow tank drive with
     * the joystick.
     */
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }

    /**
     * Drive the wheels on one side forward with one stick and the wheels on
     * another side forward with another stick.
     * 
     * @param joy
     *            PS3 style joystick to use as the input for tank drive.
     */
    public void tankDrive(Joystick joy) {
        this.tankDrive(joy.getRawAxis(1), joy.getRawAxis(5));
    }

    /**
     * Drive by giving left and right side values
     * 
     * @param leftAxis
     *            Left sides value
     * @param rightAxis
     *            Right sides value
     */
    /*
     * Nathan, if you are anything like me, you may be saying to yourself 'But
     * this method has the same name as the one above it! Why and how does this
     * work!? This makes me scared and unsure about the future!'. Well fear not
     * because there is a simple explanation. Java allows us to 'overload'
     * methods, that is define the same method multiple times, but with
     * different numbers of parameters. This is useful if we want a version of
     * the method to do a default and a version that we can supply values to.
     */

    // Checks to see if a number (variable) is in a certain range (rangeFrom)
    // from a certain point (point)
    public boolean inRange(double variable, double point, double rangeFrom) {
        boolean result = false;
        if (variable < (point + rangeFrom) && variable > (point - rangeFrom)) {
            result = true;
        }
        return result;
    }

    /**
     * Drive the wheels on one side forward with one stick and the wheels on
     * another side forward with another stick.
     * 
     * @param leftAxis
     *            left stick y-axis
     * @param rightAxis
     *            right stick y-axis
     */
    public void tankDrive(double leftAxis, double rightAxis) {
        double lJoyStickVal = 0.0;
        double rJoyStickVal = 0.0;

        // Setting up Deadzones
        if ((leftAxis < 0.5) && (leftAxis > -0.5)) {
            lJoyStickVal = 0.0;
        } else {
            lJoyStickVal = leftAxis;
        }

        if ((rightAxis < 0.5) && (rightAxis > -0.5)) {
            rJoyStickVal = 0.0;
        } else {
            rJoyStickVal = rightAxis;
        }

        drive.tankDrive(-1 * lJoyStickVal, -rJoyStickVal);
        Timer.delay(0.005); // wait for a motor update time

    }

    /**
     * Use y-axis on left stick for power and x-axis on right stick for turning.
     * 
     * @param joy
     *            joystick
     */
    public void carDrive(Joystick joy) {
        this.carDrive(joy.getRawAxis(1), joy.getRawAxis(4));
    }

    /**
     * Use y-axis on left stick for power and x-axis on right stick for turning.
     * 
     * @param leftAxis
     *            y-axis of left stick
     * @param rightAxis
     *            x-axis of right stick
     */
    public void carDrive(double leftAxis, double rightAxis) {

        boolean inLDeadZone = false;

        double lJoyStickVal = 0.0;
        double rJoyStickVal = 0.0;

        double leftPower;
        double rightPower;

        // Setting up Deadzones
        if ((leftAxis < 0.5) && (leftAxis > -0.5)) {
            lJoyStickVal = 0.0;
            inLDeadZone = true;
        } else {
            lJoyStickVal = leftAxis;
        }

        if ((rightAxis < 0.5) && (rightAxis > -0.5)) {
            rJoyStickVal = 0.0;
        } else {
            rJoyStickVal = rightAxis;
        }

        // convert to left and right power with floating point errors in mind
        leftPower = lJoyStickVal;
        rightPower = lJoyStickVal;

        if (rJoyStickVal < -0.01) {
            leftPower = leftPower * (1 - rJoyStickVal);
            leftPower = leftPower * -1;
        } else if (rJoyStickVal > 0.01) {
            rightPower = rightPower * (1 - Math.abs(rJoyStickVal));
        }

        // make robot turn left or right without moving forward if the y-axis of
        // the left stick is in a dead zone
        if (inLDeadZone) {

            double pow = Math.abs(rJoyStickVal);

            if (rJoyStickVal > 0.01) {
                leftPower = -pow;
                rightPower = pow;
            } else if (rJoyStickVal < -0.01) {
                leftPower = pow;
                rightPower = -pow;
            }
        }

        drive.tankDrive(-leftPower, -rightPower);
        Timer.delay(0.005); // wait for a motor update time
    }

    /**
     * Stop the drivetrain from moving.
     */
    public void stop() {
        drive.tankDrive(0, 0);
    }

// /**
// * @return The encoder getting the distance and speed of left side of the
// * drivetrain.
// */
// public Encoder getLeftEncoder() {
// return leftEncoder;
// }
//
//
// /**
// * @return The encoder getting the distance and speed of right side of the
// * drivetrain.
// */
// public Encoder getRightEncoder() {
// return rightEncoder;
// }
//
// /**
// * @return The current angle of the drivetrain.
// */
// public double getAngle() {
// return gyro.getAngle();
// }
}
