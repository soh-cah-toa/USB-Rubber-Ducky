package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>RIGHTARROW/RIGHT</code> command. Represents the
 * Right Arrow key used to move the cursor to the right.
 *
 * @author Kevin Polulak
 */
public class RightArrow implements Command {
    public final java.lang.String name    = "RIGHTARROW";
    public final java.lang.String altName = "RIGHT";
    public final java.lang.String help    = name + " | " + altName;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x4F);
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