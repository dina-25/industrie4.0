import lejos.nxt.LightSensor;
import lejos.nxt.addon.ColorHTSensor;

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
		boolean commandTurnRight = true;
		int aktColor = 0;
		int colorID = 7;
		int neutralLight = 0;
		int standardSpeed = 60;
		int lightStandHIGH = 0; // represent white Frabe
		int lightStandLOW = 0;  //represent schwarze Farbe
		
		int colorStandBlack =7;
		int colorStandGreen =1;
		int colorStandYellow =3;
		int colorStandRed =0;	
		int colorStandWhite = 6;
		LightSensor ls = new LightSensor(SensorPort.S4, true);
		ColorHTSensor cs = new ColorHTSensor(SensorPort.S3);
		
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
		
//		System.out.println("ColorSens schwarze Oberflaeche");
//		Button.RIGHT.waitForPressAndRelease();
//		cs.initBlackLevel();
//		System.out.println("ColorSens weiﬂ Oberflaeche");
//		Button.RIGHT.waitForPressAndRelease();
//		cs.initWhiteBalance();
		
		/*Codeteil ColorSensor
		 * 
		 * 
		 * */
		//schwarze
//		System.out.println("Set bitte schwarze Oberflaeche");
//		Button.RIGHT.waitForPressAndRelease();
//		colorStandBlack = cs.getColorID();
//		System.out.println("schwarze Farbe ID ist" + colorStandBlack);
		//Green
		System.out.println("Set bitte green Oberflaeche");
		Button.RIGHT.waitForPressAndRelease();
		colorStandGreen = cs.getColorID();
		System.out.println("green Farbe ID ist" + colorStandGreen);
//		//Yellow
//		System.out.println("Set bitte gelbe Oberflaeche");
//		Button.RIGHT.waitForPressAndRelease();
//		colorStandYellow = cs.getColorID();
//		System.out.println("Gelbe Farbe ID ist" + colorStandYellow);
		//Red
		System.out.println("Set bitte rote Oberflaeche");
		Button.RIGHT.waitForPressAndRelease();
		colorStandRed = cs.getColorID();
		System.out.println("rote Farbe ID ist" + colorStandRed);
//		//White
//		System.out.println("Set bitte weisse Oberflaeche");
//		Button.RIGHT.waitForPressAndRelease();
//		colorStandWhite = cs.getColorID();
//		System.out.println("weisse Farbe ID ist" + colorStandWhite);
//		Button.RIGHT.waitForPressAndRelease();
		
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
			aktColor = cs.getColorID();
			if (aktColor == colorStandGreen) {break;}
			else if (commandTurnRight && aktColor == colorStandRed) {
				lm.setPower((int)(standardSpeed*1.5));
				rm.setPower(standardSpeed/3);
			} 
			else {
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
		}
		rm.stop();
		lm.stop();
	}
	
	private static void turnRight(	ColorHTSensor cs,
									int standardSpeed,
									NXTMotor lm,
									NXTMotor rm) {

		lm.setPower((int)(standardSpeed));
		rm.setPower((int)(0));

	}
	

	

}

