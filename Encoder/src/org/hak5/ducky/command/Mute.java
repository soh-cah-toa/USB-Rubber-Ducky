package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>MUTE</code> command. Represents the Mute key
 * present on some keyboards meant to turn of the speaker volume.
 *
 * @author Kevin Polulak
 */
public class Mute implements Command {
    public final java.lang.String name    = "MUTE";
    public final java.lang.String altName = "";
    public final java.lang.String help    = name;

    public void action(State state, java.lang.String args) {
        state.addByteToFile(0xE2);
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