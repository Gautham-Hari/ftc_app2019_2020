/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: test OpMode", group="Linear Opmode")
//@Disabled
public class TeamBasicOpMode_Linear extends LinearOpMode {


    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftfr = null;
    private DcMotor leftback = null;
    private DcMotor rightfr = null;
    private DcMotor rightback = null;

    private CRServo leftWheels = null;
    private CRServo rightWheels = null;

    double leftfrPower;
    double leftbackPower;
    double rightfrPower;
    double rightbackPower;

    double leftPower;
    double rightPower;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftfr  = hardwareMap.get(DcMotor.class, "leftf");
        leftback  = hardwareMap.get(DcMotor.class, "leftb");
        rightfr = hardwareMap.get(DcMotor.class, "rightf");
        rightback  = hardwareMap.get(DcMotor.class, "rightb");

        leftWheels  = hardwareMap.get(CRServo.class, "lw");
        rightWheels = hardwareMap.get(CRServo.class, "rw");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftfr.setDirection(DcMotor.Direction.FORWARD);
        leftback.setDirection(DcMotor.Direction.FORWARD);
        rightfr.setDirection(DcMotor.Direction.REVERSE);
        rightback.setDirection(DcMotor.Direction.REVERSE);

        leftWheels.setDirection(CRServo.Direction.REVERSE);
        rightWheels.setDirection(CRServo.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry


            double straifPower = gamepad1.right_stick_x;
            double tankPower = gamepad1.right_stick_y;
            double turnPower = gamepad1.left_stick_x;


            if (turnPower < -0.1){
                leftfrPower = Range.clip(-turnPower, -1.0, 1.0) ;
                leftbackPower = Range.clip(-turnPower, -1.0, 1.0) ;
                rightfrPower = Range.clip(turnPower, -1.0, 1.0) ;
                rightbackPower = Range.clip(turnPower, -1.0, 1.0) ;
            }
            else if (turnPower > 0.1){
                leftfrPower = Range.clip(-turnPower, -1.0, 1.0) ;
                leftbackPower = Range.clip(-turnPower, -1.0, 1.0) ;
                rightfrPower = Range.clip(turnPower, -1.0, 1.0) ;
                rightbackPower = Range.clip(turnPower, -1.0, 1.0) ;
            }
            else if (straifPower < -0.1){
                leftfrPower = Range.clip(-straifPower, -1.0, 1.0) ;
                leftbackPower = Range.clip(straifPower, -1.0, 1.0) ;
                rightfrPower = Range.clip(straifPower, -1.0, 1.0) ;
                rightbackPower = Range.clip(-straifPower, -1.0, 1.0) ;
            }
            else if (straifPower > 0.1){
                leftfrPower = Range.clip(-straifPower, -1.0, 1.0) ;
                leftbackPower = Range.clip(straifPower, -1.0, 1.0) ;
                rightfrPower = Range.clip(straifPower, -1.0, 1.0) ;
                rightbackPower = Range.clip(-straifPower, -1.0, 1.0) ;
            }
            else if (tankPower < -0.1){
                leftbackPower = Range.clip(tankPower, -1.0, 1.0) ;
                rightbackPower = Range.clip(tankPower, -1.0, 1.0) ;
                leftfrPower = Range.clip(tankPower, -1.0, 1.0) ;
                rightfrPower = Range.clip(tankPower, -1.0, 1.0) ;
            }
            else if (tankPower > 0.1){
                leftfrPower = Range.clip(tankPower, -1.0, 1.0) ;
                rightfrPower = Range.clip(tankPower, -1.0, 1.0) ;
                leftbackPower = Range.clip(tankPower, -1.0, 1.0) ;
                rightbackPower = Range.clip(tankPower, -1.0, 1.0) ;
            }
            else {
                tankPower = 0.0;
                turnPower = 0.0;
                leftfrPower = Range.clip(tankPower, -1.0, 1.0) ;
                rightfrPower = Range.clip(tankPower, -1.0, 1.0) ;
                leftbackPower = Range.clip(tankPower, -1.0, 1.0) ;
                rightbackPower = Range.clip(tankPower, -1.0, 1.0) ;
            }

            //pickup mechanism
            double gather = gamepad2.right_stick_y;

            if ( gather>0.0 ) {
                leftPower = Range.clip(-gather, -1.0, 1.0);
                rightPower = Range.clip(-gather, -1.0, 1.0);
            }
            else if (gather< 0.0) {
                leftPower = Range.clip(-gather, -1.0, 1.0);
                rightPower = Range.clip(-gather, -1.0, 1.0);
            }
            else {
            gather = 0.0;
            leftPower    = 0.0 ;
            rightPower   = 0.0 ;
            }


            // Send calculated power to wheels
            leftfr.setPower(leftfrPower);
            leftback.setPower(leftbackPower);
            rightfr.setPower(rightfrPower);
            rightback.setPower(rightbackPower);

            leftWheels.setPower(leftPower);
            rightWheels.setPower(rightPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left fr (%.2f), right fr (%.2f), left back (%.2f), right back (%.2f)", leftfrPower, rightfrPower, leftbackPower, rightbackPower);
            telemetry.addData("Servos", "left wheels (%.2f), right wheel (%.2f)", leftPower, rightPower);
            telemetry.update();


        }
    }
}
