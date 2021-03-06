package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>BREAK/PAUSE</code> command. Represents the
 * Break or Pause key used to pause the screen output.
 *
 * @author Kevin Polulak
 */
public class Break implements Command {
    public final java.lang.String name    = "BREAK";
    public final java.lang.String altName = "PAUSE";
    public final java.lang.String help    = name;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x48);
        state.addByteToFile(0x00);
    }
    
    @Override
    public java.lang.String getName() {
    	return name;
    }
    
    @Override
    public java.lang.String getAltName() {
    	return altName;
    }
    
    @Override
    public java.lang.String getHelp() {
    	return help;
    }
}