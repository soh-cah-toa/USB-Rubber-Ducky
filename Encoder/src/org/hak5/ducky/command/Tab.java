package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>TAB</code> command. Represents the Tab key used
 * to advance the cursor to the next tab stop.
 *
 * @author Kevin Polulak
 */
public class Tab implements Command {
    public final java.lang.String name    = "TAB";
    public final java.lang.String altName = "";
    public final java.lang.String help    = name + " [[UPARROW | UP] | [WINDOWS | GUI]]";

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x2B);
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