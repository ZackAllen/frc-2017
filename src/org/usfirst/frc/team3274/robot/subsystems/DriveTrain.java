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

        // Configure drive motors
        LiveWindow.addActuator("DriveTrain", "Front Left CIM",
                (CANTalon) _frontLeftMotor);
        LiveWindow.addActuator("DriveTrain", "Front Right CIM",
                (CANTalon) _frontRightMotor);
        LiveWindow.addActuator("DriveTrain", "Back Left CIM",
                (CANTalon) _frontRightMotor);
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
     * @param joy
     *            PS3 style joystick to use as the input for tank drive.
     */
    public void tankDrive(Joystick joy) {
        this.tankDrive(joy.getRawAxis(1), joy.getRawAxis(5));
    }

    /**
     * @param leftAxis
     *            Left sides value
     * @param rightAxis
     *            Right sides value
     */
    public void tankDrive(double leftAxis, double rightAxis) {
        double lJoyStickVal = 0.0;
        double rJoyStickVal = 0.0;

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

        drive.tankDrive(lJoyStickVal, -rJoyStickVal);
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
