package de.montiarcautomaton.lejos.lib.logger;

import de.montiarcautomaton.runtimes.timesync.implementation.IComputable;

public class LoggerImpl implements IComputable<LoggerInput, LoggerResult> {
  
  @Override
  public LoggerResult getInitialValues() {
    return new LoggerResult();
  }
  
  @Override
  public LoggerResult compute(LoggerInput input) {
    // TODO print input message to console 
	System.out.println(input.getLog());
	//this.writeComment(input);
	//this.finishLine();
    return new LoggerResult();
  }
  
}
