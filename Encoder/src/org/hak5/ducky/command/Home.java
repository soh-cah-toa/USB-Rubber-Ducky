package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>HOME</code> command. Represents the Home key
 * used to move the cursor to the beginning of the current line.
 *
 * @author Kevin Polulak
 */
public class Home implements Command {
    public final java.lang.String name    = "HOME";
    public final java.lang.String altName = "";
    public final java.lang.String help    = name;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x4A);
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