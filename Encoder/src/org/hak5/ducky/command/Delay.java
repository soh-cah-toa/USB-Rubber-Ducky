package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>DELAY</code> command. Creates of moment of pause
 * between the next command. Time is in microseconds.
 * 
 * @author Kevin Polulak
 */
public class Delay implements Command {
    public final java.lang.String name    = "DELAY";
    public final java.lang.String altName = "";
    public final java.lang.String help    = name + " [Time in millisecond * 10]";

    @Override
    public void action(State state, java.lang.String args) {
        int delay = Integer.parseInt(args.trim());

        while (delay > 0) {
            state.addByteToFile(0x00);

            if (delay > 255) {
                state.addByteToFile(0xFF);
                delay = delay - 255;
            } else {
                state.addByteToFile(delay);
                delay = 0;
            }
        }

        state.setDelayOverride(true);
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