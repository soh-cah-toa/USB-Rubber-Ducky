package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>DEFAULT_DELAY/DEFAULTDELAY</code> command.
 * Defines the number of microseconds to wait between each command.
 *
 * @author Kevin Polulak
 */
public class DefaultDelay implements Command {
    public final java.lang.String name    = "DEFAULT_DELAY";
    public final java.lang.String altName = "DEFAULTDELAY";
    public final java.lang.String help    = name + " | " + altName
                                          + " [Time in milliseconds * 10]";

    @Override
    public void action(State state, java.lang.String args) {
        state.setDefaultDelay(Integer.parseInt(args.trim()));
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