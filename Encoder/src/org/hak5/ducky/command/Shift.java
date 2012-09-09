package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>SHIFT</code> command. Represents the Shift key
 * used to type uppercase characters.
 *
 * @author Kevin Polulak
 */
public class Shift implements Command {
    public final java.lang.String name    = "SHIFT";
    public final java.lang.String altName = "";
    public final java.lang.String help    = name
        + " [ DELETE | HOME | INSERT | PAGEUP | PAGEDOWN | (WINDOWS | GUI)\n"
        + "         | (UPARROW | DOWNARROW |LEFTARROW | RIGHTARROW) | TAB]";

    @Override
    public void action(State state, java.lang.String args) {
        if (args.length() != 1) {
            if (args.equals("HOME"))
                state.addByteToFile(0x4A);
            else if (args.equals("TAB"))
                state.addByteToFile(0x2B);
            else if (args.equals("WINDOWS") || args.equals("GUI"))
                state.addByteToFile(0xE3);
            else if (args.equals("INSERT"))
                state.addByteToFile(0x49);
            else if (args.equals("PAGEUP"))
                state.addByteToFile(0x4B);
            else if (args.equals("PAGEDOWN"))
                state.addByteToFile(0x4E);
            else if (args.equals("DELETE"))
                state.addByteToFile(0x4C);
            else if (args.equals("END"))
                state.addByteToFile(0x4D);
            else if (args.equals("UPARROW"))
                state.addByteToFile(0x52);
            else if (args.equals("DOWNARROW"))
                state.addByteToFile(0x51);
            else if (args.equals("LEFTARROW"))
                state.addByteToFile(0x50);
            else if (args.equals("RIGHTARROW"))
                state.addByteToFile(0x4F);

            state.addByteToFile(0xE1);
        } else {
            state.addByteToFile(0xE1);
            state.addByteToFile(0x00);
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