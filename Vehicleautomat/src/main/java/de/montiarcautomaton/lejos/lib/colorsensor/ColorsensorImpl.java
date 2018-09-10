package de.montiarcautomaton.lejos.lib.colorsensor;

import de.montiarcautomaton.lejos.lib.Datatypes.SensorPort;
import de.montiarcautomaton.runtimes.timesync.implementation.IComputable;
import lejos.nxt.ColorSensor;


class ColorsensorImpl implements IComputable<ColorsensorInput, ColorsensorResult> {
	private ColorSensor nxtColorSensor;
	private SensorPort sensorPort;
	private Integer sensorWert;
	
	public ColorsensorImpl(SensorPort sensorPort) {
		this.sensorPort = sensorPort;
		this.nxtColorSensor = new ColorSensor(toNxtPort(sensorPort));
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
    public ColorsensorResult getInitialValues() {
       	return new ColorsensorResult();
    }
	
	@Override
    public ColorsensorResult compute(ColorsensorInput input) {
		// TODO return the actuelle ColorID from 17(white) to 0(black)
		return(new ColorsensorResult(nxtColorSensor.getColorID()));
		
    }

}