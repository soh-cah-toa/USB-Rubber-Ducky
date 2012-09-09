package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * @author Kevin Polulak
 *
 * Implementation of the <code>DOWNARROW/DOWN</code> command. Represents the
 * Down Arrow key used to move the cursor down.
 */
public class DownArrow implements Command {
    public final java.lang.String name    = "DOWNARROW";
    public final java.lang.String altName = "DOWN";
    public final java.lang.String help    = name + " | " + altName;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x51);
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