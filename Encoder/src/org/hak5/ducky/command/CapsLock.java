package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>CAPSLOCK</code> command. Represents the Caps
 * Lock key used to switch text input to uppercase.
 *
 * @author Kevin Polulak
 */
public class CapsLock implements Command {
    public final java.lang.String name    = "CAPSLOCK";
    public final java.lang.String altName = "";
    public final java.lang.String help    = name;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x39);
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