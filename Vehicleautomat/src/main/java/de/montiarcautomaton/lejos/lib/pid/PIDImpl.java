package de.montiarcautomaton.lejos.lib.pid;

import de.montiarcautomaton.runtimes.timesync.implementation.IComputable;
import lejos.util.PIDController;
import de.montiarcautomaton.lejos.lib.pid.*;


class PIDImpl implements IComputable<PIDInput, PIDResult> {
	//private final Float kp,ki,kd;
	private PIDController pidC;
	public static final double colorSlot = 10.0/17;
	public PIDImpl() {
//		this.kp = kp;
//		this.ki = ki;
//		this.kd = kd;
		pidC = new PIDController(0,10);
		pidC.setPIDParam(PIDController.PID_DEADBAND, 1);
		pidC.setPIDParam(PIDController.PID_I, 0);
		pidC.setPIDParam(PIDController.PID_I_LIMITHIGH, 20);
		pidC.setPIDParam(PIDController.PID_I_LIMITLOW, -20);
		pidC.setPIDParam(PIDController.PID_LIMITHIGH, 5);
		pidC.setPIDParam(PIDController.PID_LIMITLOW, -5);
		pidC.setPIDParam(PIDController.PID_RAMP_POWER, 0);
		//Kp, Ki, Kd
		pidC.setPIDParam(PIDController.PID_KP, 10);
		pidC.setPIDParam(PIDController.PID_KI, 1);
		pidC.setPIDParam(PIDController.PID_KD, 0);
		
	}
	
	/**
	 * @param colorID aus Colorsensor fuer aktuelle Fahrband
	 * @return neutrale Wert fuer Lightsensor
	 */
	private double calcNeutral(Integer colorID) {
		return (colorSlot * colorID + 40);
	}

  
	@Override
    public PIDResult getInitialValues() {
		return new PIDResult();
  	}
	
  	@Override
    public PIDResult compute(PIDInput input) {
  	// TODO geben resultierte Speed fuer zwei Motor aus.
  		int bias = (int)(input.getLightLevel() - calcNeutral(input.getColorID()));
  		int result = pidC.doPID(bias);
  		double leftSpeed = input.getLeftSpeed();
  		double rightSpeed = input.getRightSpeed();
  		leftSpeed *= 1 - 1.0/result;
  		rightSpeed *= 1 + 1.0/result;
  		return new PIDResult( (int)leftSpeed, (int)rightSpeed );
  	}

}