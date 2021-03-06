package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>UPARROW/UP</code> command. Represents the Up
 * Arrow key used to move the cursor up.
 *
 * @author Kevin Polulak
 */
public class UpArrow implements Command {
    public final java.lang.String name    = "UPARROW";
    public final java.lang.String altName = "UP";
    public final java.lang.String help    = name;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x52);
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