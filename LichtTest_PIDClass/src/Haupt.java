import lejos.nxt.LightSensor;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.util.PIDController;
import lejos.nxt.Button;
import lejos.nxt.NXTMotor;
import lejos.nxt.MotorPort;
import java.lang.*;

public class Haupt {
	public static void main(String[] args)
	{
		int colorID = 7;
		int neutralLight = 0;
		int standardSpeed = 70;
		int lightStandHIGH = 0; // represent white Frabe
		int lightStandLOW = 0;  //represent schwarze Farbe
		

		LightSensor ls = new LightSensor(SensorPort.S4, true);

		NXTMotor rm= new NXTMotor(MotorPort.C);
		NXTMotor lm= new NXTMotor(MotorPort.B);
		rm.stop();
		lm.stop();
		rm.setPower(standardSpeed);
		lm.setPower(standardSpeed);
		
		/*Codeteil LightSensor
		 * 
		 * 
		 * */
		System.out.println("Calibrate low_bitte schwarze Oberflaeche");
		Button.RIGHT.waitForPressAndRelease();
		ls.calibrateLow();
		lightStandLOW = ls.getLightValue();
		System.out.println("tiefe Licht ist" + lightStandLOW);
		System.out.println("Calibrate high, bitte weisse Oberflaeche ");
		Button.RIGHT.waitForPressAndRelease();
		lightStandHIGH = ls.getLightValue();
		System.out.println("hoehe Licht ist" + lightStandHIGH);
		neutralLight = (lightStandHIGH + lightStandLOW)/2;
		System.out.println("neutral Licht ist" + neutralLight);
		
		
		/*Codeteil Run
		 * 
		 * 
		 * */
		Button.RIGHT.waitForPressAndRelease();
		boolean err= false;
		int lightWert = 0;
		PIDImpl pidC = new PIDImpl
						( standardSpeed,neutralLight,
						  2, 1, 1, 
						  5, -5,
						  10, -10);
		int[] akPower = new int[2];
		akPower[0] = lm.getPower();
		akPower[1] = rm.getPower();
		int[] sollPower = new int[2];
		sollPower[0] = lm.getPower();
		sollPower[1] = rm.getPower();
		rm.forward();
		lm.forward();
		while (!Button.LEFT.isPressed()) {
//			Button.RIGHT.waitForPressAndRelease();
			lightWert = ls.readValue();
			System.out.println("aktuelle Lichtwert ist " + lightWert);
			System.out.println("AkPower Left + right:" + akPower[0] +" "+ akPower[1]);
			sollPower = pidC.compute(akPower,lightWert,colorID);
			System.out.println("sollPower Left + right:" + sollPower[0] +" "+ sollPower[1]);
			lm.setPower(sollPower[0]);
			rm.setPower(sollPower[1]);
			akPower[0] = lm.getPower();
			akPower[1] = rm.getPower();
		}
		rm.stop();
		lm.stop();
	}
	

	

}

