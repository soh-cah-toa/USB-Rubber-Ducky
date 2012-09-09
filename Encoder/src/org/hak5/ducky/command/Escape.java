package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>ESCAPE/ESC</code> command. Represents the Esc
 * key used to initiate an escape sequence.
 *
 * @author Kevin Polulak
 */
public class Escape implements Command {
    public final java.lang.String name    = "ESCAPE";
    public final java.lang.String altName = "ESC";
    public final java.lang.String help    = name + " | " + altName;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x29);
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