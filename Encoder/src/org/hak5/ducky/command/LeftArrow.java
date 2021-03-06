package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>LEFTARROW/LEFT</code> command. Represents the\
 * Left Arrow key used to move the cursor to the left.
 *
 * @author Kevin Polulak
 */
public class LeftArrow implements Command {
    public final java.lang.String name    = "LEFTARROW";
    public final java.lang.String altName = "LEFT";
    public final java.lang.String help    = name;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x50);
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