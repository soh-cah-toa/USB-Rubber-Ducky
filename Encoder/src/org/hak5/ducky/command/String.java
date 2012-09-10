package org.hak5.ducky.command;

import org.hak5.ducky.State;
import org.hak5.ducky.Command;

/**
 * Implementation of the <code>STRING</code> command. Used to input a character
 * string.
 *
 * @author Kevin Polulak
 */
public class String implements Command {
    public java.lang.String name    = "STRING";
    public java.lang.String altName = "";
    public java.lang.String help    = name + " [a...z A...Z 0..9 !...) "
                                    + "`~ += _- \"\' :; <, >. ?/ \\|]";

    @Override
    public void action(State state, java.lang.String args) {
        for (int j = 0; j < args.length(); j++) {
            char c = args.charAt(j);
            state.addCharToFile(c);

            // Auto shift
            byte shiftByte = 0x00;

            if ((int) c >= 65 && (int) c <= 90) {
                // Switch capital letters
                shiftByte = 0x02;
            } else {
                switch (c) {
                case '~':
                case '!':
                case '@':
                case '#':
                case '$':
                case '%':
                case '^':
                case '&':
                case '*':
                case '(':
                case ')':
                case '_':
                case '+':
                case '}':
                case '{':
                case '|':
                case '"':
                case ':':
                case '?':
                case '>':
                case '<':
                    // Shift
                    shiftByte = 0x02;
                    break;
                }
            }

            state.addByteToFile((int) shiftByte);
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