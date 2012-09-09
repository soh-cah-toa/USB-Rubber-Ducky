package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * @author Kevin Polulak
 *
 * Implementation of the <code>WINDOWS/GUI</code> command. Represents the
 * Windows key ("Meta" or "Super" key on some systems) used to toggle the
 * Start Menu and as a shortcut modifier.
 */
public class Windows implements Command {
    public final java.lang.String name    = "WINDOWS";
    public final java.lang.String altName = "GUI";
    public final java.lang.String help    = name + " | " + altName + " [Single Char]";

    @Override
    public void action(State state, java.lang.String args) {
        if (args.length() == 1) {
            state.addByteToFile(0xE3);
            state.addByteToFile(0x00);
        } else {
            state.addCharToFile(args.charAt(0));
            state.addByteToFile(0x08);
        }
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