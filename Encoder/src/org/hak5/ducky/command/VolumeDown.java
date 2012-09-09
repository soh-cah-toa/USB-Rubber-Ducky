package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>VOLUMEDOWN</code> command. Represents the Volume
 * Down key present on some keyboards used to turn the speaker volume down.
 *
 * @author Kevin Polulak
 */
public class VolumeDown implements Command {
    public final java.lang.String name    = "VOLUMEDOWN";
    public final java.lang.String altName = "";
    public final java.lang.String help    = name;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0xEA);
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