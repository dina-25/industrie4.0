package de.montiarcautomaton.lejos.lib.motor;

import de.montiarcautomaton.lejos.lib.Datatypes.MotorPort;
import de.montiarcautomaton.lejos.lib.motor.Datatypes.MotorCmd;
import de.montiarcautomaton.runtimes.timesync.implementation.IComputable;
import lejos.nxt.NXTMotor;


public class MotorImpl implements IComputable<MotorInput, MotorResult> {
  private final int power;
  private final MotorPort motorPort;
  private final NXTMotor nxtMotor;
  
  public MotorImpl(MotorPort motorPort) {
    this(motorPort, 50);
  }
  
  public MotorImpl(MotorPort motorPort, int power) {
    this.motorPort = motorPort;
    this.power = power;
    
    this.nxtMotor = new NXTMotor(toNxtPort(this.motorPort));
    this.nxtMotor.setPower(this.power);
    this.nxtMotor.stop();
  }
  
  private static lejos.nxt.MotorPort toNxtPort(MotorPort port) {
    switch (port) {
      case A:
        return lejos.nxt.MotorPort.A;
      case B:
        return lejos.nxt.MotorPort.B;
      case C:
        return lejos.nxt.MotorPort.C;
      default:
        throw new RuntimeException("Innvalid motor port.");
    }
  }
  
  @Override
  public MotorResult getInitialValues() {
    return new MotorResult();
  }
  
  
  
  @Override
  public MotorResult compute(MotorInput input) {
	// TODO map motor commands to nxt motor commands
	  //power == speed?
	  //this.power=input.getSpeed();
	  switch(input.getCmd()) {
	  case STOP:
		  nxtMotor.stop();
		  break;
	  case FORWARD:
		  nxtMotor.forward();
		  break;
	  case BACKWARD:
		  nxtMotor.backward();
		  break;
	  }
	  
    return new MotorResult();
  }
  
}
