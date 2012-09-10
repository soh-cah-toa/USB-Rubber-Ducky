package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>ALT</code> command. Represents the Alt key used
 * to change the function of other keypresses as a shortcut modifier.
 *
 * @author Kevin Polulak
 */
public class Alt implements Command {
    public final java.lang.String name    = "ALT";
    public final java.lang.String altName = "";
    public final java.lang.String help    = name
        + " [END | (ESCAPE | ESC) | F1...F12 | Single Char | SPACE | TAB]";

    @Override
    public void action(State state, java.lang.String args) {
        // FIXME Make this more extensible by using Command objects
        if (args.length() != 1) {
            if (args.equals("ESCAPE")
                    || args.equals("ESC"))
                state.addByteToFile(0x29);
            else if (args.equals("SPACE"))
                state.addByteToFile(0x2C);
            else if (args.equals("TAB"))
                state.addByteToFile(0x2B);
            else if (args.length() != 1)
                if (state.isFunctionKey(args))
                    state.addFunctionKeyToFile(args);
				else
                    state.addCharToFile(args.charAt(0));
			else
                state.addByteToFile(0x00);
		} else {
            state.addByteToFile(0x00);
        }

        state.addByteToFile(0xE2);
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