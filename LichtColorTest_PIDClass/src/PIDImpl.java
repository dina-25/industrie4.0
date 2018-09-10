import lejos.util.PIDController;
import java.lang.*;

class PIDImpl {
	//private final Float kp,ki,kd;

	public static final double colorSlot = 10.0/17;
	PIDController pidC;
	int standardSpeed = 0;
	int neutralLight = 0;
	boolean richtungLastBias = false; // false ist negative
	
	int kp = 2;
	int ki = 1;
	int kd = 0;
	
	public PIDImpl( int standardSpeed, int neutralLight,
					int kp, int ki, int kd,
					int iLHIGH, int iLLOW,
					int lHIGH, int lLOW) {
		pidC = new PIDController(0,10);
		pidC.setPIDParam(PIDController.PID_DEADBAND, 0);
		pidC.setPIDParam(PIDController.PID_I, 0);
		pidC.setPIDParam(PIDController.PID_I_LIMITHIGH, iLHIGH);
		pidC.setPIDParam(PIDController.PID_I_LIMITLOW, iLLOW);
		pidC.setPIDParam(PIDController.PID_LIMITHIGH, lHIGH);
		pidC.setPIDParam(PIDController.PID_LIMITLOW, lLOW);
		pidC.setPIDParam(PIDController.PID_RAMP_POWER, 0);
		//Kp, Ki, Kd
		pidC.setPIDParam(PIDController.PID_KP, kp);
		pidC.setPIDParam(PIDController.PID_KI, ki);
		pidC.setPIDParam(PIDController.PID_KD, kd);
		this.standardSpeed = standardSpeed;
		this.neutralLight = neutralLight;
	}

	public int doPID(int bias) {
		if (richtungLastBias == false) {
			if (bias > 0) {
				pidC.setPIDParam(PIDController.PID_I, 0);
				richtungLastBias = true;
			}
		} else {
			if (bias < 0) {
				pidC.setPIDParam(PIDController.PID_I, 0);
				richtungLastBias = false;
			}
		}
		return pidC.doPID(bias);
	}
	
	//die Abbildungstabelle noch nicht fertigt
	private double calcNeutral(Integer colorID) {
		return this.neutralLight;
	}
	

    public int[] compute(int[] speed, int light, int colorID) {
  	// TODO geben resultierte Speed fuer zwei Motor aus.
  		int bias = (int)(light - calcNeutral(colorID));
  		int result = doPID(bias);
  		double leftSpeed = speed[0];
  		double rightSpeed = speed[1];
  		
  		if (result > 7 || result < -7) {
  			leftSpeed = (double)standardSpeed*2/3   + result*2;
  			rightSpeed = (double)standardSpeed*2/3  - result*2;
  		} else if (result > 4 || result < -4) {
  			leftSpeed = (double)standardSpeed*3/4   + result/1.2;
  			rightSpeed = (double)standardSpeed*3/4  - result/1.2;
  		} else {
  			leftSpeed = (double)standardSpeed   + result/1.5;
  			rightSpeed = (double)standardSpeed  - result/1.5;
  		}
  		int[] res = new int[2];
  		res[0] = (int)leftSpeed;
  		res[1] = (int)rightSpeed;
  		return res;
  	}

}