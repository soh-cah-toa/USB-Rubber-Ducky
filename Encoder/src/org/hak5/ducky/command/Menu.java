package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>MENU/APP</code> command. Brings up the context
 * menu, similar to a right-click.
 *
 * @author Kevin Polulak
 */
public class Menu implements Command {
    public final java.lang.String name    = "MENU";
    public final java.lang.String altName = "APP";
    public final java.lang.String help    = name + " | " + altName;

    @Override
    public void action(State state, java.lang.String args) {
        state.addByteToFile(0x65);
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