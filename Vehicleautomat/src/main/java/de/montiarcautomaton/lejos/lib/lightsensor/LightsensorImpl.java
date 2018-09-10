package de.montiarcautomaton.lejos.lib.lightsensor;

import de.montiarcautomaton.lejos.lib.Datatypes.SensorPort;
import de.montiarcautomaton.lejos.lib.colorsensor.ColorsensorResult;
import de.montiarcautomaton.runtimes.timesync.implementation.IComputable;
import lejos.nxt.LightSensor;

class LightsensorImpl implements IComputable<LightsensorInput, LightsensorResult> {
	private LightSensor nxtLightSensor;
	private SensorPort sensorPort;
	private Integer sensorWert;

  
  public LightsensorImpl(SensorPort sensorPort) {
		this.sensorPort = sensorPort;
		this.nxtLightSensor = new LightSensor(toNxtPort(sensorPort));
		this.sensorWert = 0;
  }
	
	private static lejos.nxt.SensorPort toNxtPort(SensorPort port) {
		switch (port) {
		case S1:
			return lejos.nxt.SensorPort.S1;
		case S2:
			return lejos.nxt.SensorPort.S2;
		case S3:
			return lejos.nxt.SensorPort.S3;
		case S4:
			return lejos.nxt.SensorPort.S4;
		default:
			throw new RuntimeException("Invalid sensor port.");
		}
	}
	
	  @Override
    public LightsensorResult getInitialValues() {
    	return new LightsensorResult();
    }

	  @Override
    public LightsensorResult compute(LightsensorInput input) {
    	return(new LightsensorResult(nxtLightSensor.getLightValue()));
    }

}