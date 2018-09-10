package de.montiarcautomaton.lejos.lib.timer;

import de.montiarcautomaton.lejos.lib.timer.Datatypes.TimerCmd;
import de.montiarcautomaton.lejos.lib.timer.Datatypes.TimerSignal;
import de.montiarcautomaton.runtimes.timesync.implementation.IComputable;


public class TimerImpl implements IComputable<TimerInput, TimerResult> {
  private final int delay;
  private boolean isRunning;
  private long deadline;
  
  public TimerImpl(Integer delay) {
    this.delay = delay;
  }
  
  @Override
  public TimerResult getInitialValues() {
    return new TimerResult();
  }
  
  @Override
  public TimerResult compute(TimerInput input) {
	//TODO if timer cmd is single: wait the time defined in delay, then alert
	  if(input.getCmd()==TimerCmd.SINGLE)
	  {
		  //wait
		  try {
		  Thread.sleep(delay);
		  }catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  //return alert
		  return new TimerResult(TimerSignal.ALERT);
	  }
	//TODO if timer cmd is double: wait two times the delay time, then alert
	  if(input.getCmd()==TimerCmd.DOUBLE)
	  {
		  //wait x2
		  try {
		  Thread.sleep(2*delay);
		  }catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  //return alert
		  return new TimerResult(TimerSignal.ALERT);
	  }
	//TODO the time has to be checked every tick of the architecture
	  
//    return new TimerResult(TimerSignal.SLEEP);
	  return null;
  }
  
}
