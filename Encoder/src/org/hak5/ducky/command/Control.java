package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>CONTROL/CTRL</code> command. Represents the Ctrl
 * key used to change the function of other keypresses as a shortcut modifier.
 *
 * @author Kevin Polulak
 */
public class Control implements Command {
    public final java.lang.String name    = "CONTROL";
    public final java.lang.String altName = "CTRL";
    public final java.lang.String help    = name + " | " + altName
        + " [(BREAK | PAUSE) | F1...F12 | (ESCAPE | ESC) | Single Char]";

    @Override
    public void action(State state, java.lang.String args) {
        // FIXME Make this more extensible using Escape and Pause objects
        if (args.equals("ESCAPE") || args.equals("ESC"))
            state.addByteToFile(0x29);
        else if (args.equals("PAUSE") || args.equals("BREAK"))
            state.addByteToFile(0x48);
        else if (state.getCurrentLine().length != 1)
            if (state.isFunctionKey(args))
                state.addFunctionKeyToFile(args);
            else
                state.addCharToFile(args.charAt(0));
        else
            state.addByteToFile(0x00);

        state.addByteToFile(0x01);
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