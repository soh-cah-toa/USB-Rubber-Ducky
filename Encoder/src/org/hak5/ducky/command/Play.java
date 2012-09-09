package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * @author Kevin Polulak
 *
 * Implementation of the <code>PLAY/PAUSE</code> command. Represents the Play
 * key present on some keyboards used to play or pause music.
 */
public class Play implements Command {
    public final java.lang.String name    = "PLAY";
    public final java.lang.String altName = "PAUSE";
    public final java.lang.String help    = name + " | " + altName;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0xCD);
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