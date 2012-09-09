package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * @author Kevin Polulak
 *
 * Implementation of the <code>SYSTEMWAKE</code> command. Used to wake the
 * computer from sleep mode.
 */
public class SystemWake implements Command {
    public final java.lang.String name    = "SYSTEMWAKE";
    public final java.lang.String altName = "";
    public final java.lang.String help    = name;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x83);
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