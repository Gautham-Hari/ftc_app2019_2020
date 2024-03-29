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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="TestOpMode", group="Opmode")
public class TestOpMode extends OpMode
{
    // Declare OpMode members.
   DcMotor rightfDrive, rightbDrive, leftfDrive, leftbDrive;

   double motorPowerRight, motorPowerLeft, straifRPower, straifLPower;
    @Override
    public void init() {

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        rightfDrive = hardwareMap.get(DcMotor.class, "rightf");
        rightbDrive = hardwareMap.get(DcMotor.class, "rightb");
        leftfDrive  = hardwareMap.get(DcMotor.class, "leftf");
        leftbDrive = hardwareMap.get(DcMotor.class, "leftb");


        leftfDrive.setDirection(DcMotor.Direction.FORWARD);
        leftbDrive.setDirection(DcMotor.Direction.FORWARD);
        rightfDrive.setDirection(DcMotor.Direction.REVERSE);
        rightbDrive.setDirection(DcMotor.Direction.REVERSE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Value:", "Right: ",motorPowerRight, "  Left: ", motorPowerLeft );

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry


        motorPowerRight = gamepad1.right_stick_y;
        motorPowerLeft = gamepad1.left_stick_y;

        rightbDrive.setPower(motorPowerRight);
        rightfDrive.setPower(motorPowerRight);
        leftfDrive.setPower(motorPowerLeft);
        leftbDrive.setPower(motorPowerLeft);

        straifRPower = gamepad1.right_stick_x;
        straifLPower = gamepad1.left_stick_x;

        if (straifRPower>0)
             {
              rightfDrive.setPower(straifRPower);
              leftbDrive.setPower(straifRPower);
              leftfDrive.setPower(-straifRPower);
              rightbDrive.setPower(-straifRPower);
             }
        if (straifLPower<0) {
            rightfDrive.setPower(straifLPower);
            leftbDrive.setPower(straifLPower);
            leftfDrive.setPower(-straifLPower);
            rightbDrive.setPower(-straifLPower);
        }


        telemetry.addData("Value of GamPd 1 X ="," ", + straifRPower);
        telemetry.update();








     



    }
}