package org.hak5.ducky;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Jason Appelbaum
 * @author Kevin Polulak
 * 
 * State machine that acts as the DuckyScript interpreter. Parses input
 * file and acts on each keyword according to a set of commands. Each command 
 * then operates somehow on the state.
 * 
 * Implemented as a singleton object since the interpreter can only have one
 * state. Therefore, the <code>new</code> operator will throw an exception. The
 * newInstance method should be used instead.
 */
public class State {
    private static String[] instructions;
    private static String[] currInstruction;
    private static CommandList commandList;
    private static int defaultDelay;
    private static boolean delayOverride;
    private static List<Byte> file;

    private String outFile;

    private static State INSTANCE = null;
    
    /**
     * Returns a new instance of State.
     *
     * @return new State instance, never null
     */
    public static State newInstance(String inputString, String outFile) {
    	if (INSTANCE == null)
    		INSTANCE = new State(inputString, outFile);
    	
    	return INSTANCE;
    }

    /**
     * Constructor is private on purpose. <i>Do not use.</i>
     */
    private State(String inputString, String outFile) {
		inputString  = inputString.replaceAll("\\r", ""); // CRLF Fix
		instructions = inputString.split("\n");

        file = new ArrayList<Byte>();
        this.outFile = outFile;

        defaultDelay  = 0;
        delayOverride = false;

        commandList = CommandList.newInstance();
        currInstruction = new String[2];
    }
    
    /**
     * Returns the current instruction being evaluated.
     * 
     * @return string representing the current instruction
     */
    public String getCurrentInstruction() {
        return currInstruction[1];
    }

    /**
     * Sets the amount of delay to wait between commands.
     * 
     * @param value time in microseconds
     *
     * @see getDefaultDelay
     */
    public void setDefaultDelay(int value) {
        defaultDelay = value;
    }

    /**
     * Returns the set time to wait between commands.
     *
     * @return time in microseconds
     *
     * @see setDefaultDelay
     */
    public int getDefaultDelay() {
        return defaultDelay;
    }

    /**
     * Toggles whether or not to override the delay value.
     * 
     * @param value boolean true overrides the delay, false restores it
     *
	 * @see getDelayOverride
     */
    public void setDelayOverride(boolean value) {
        delayOverride = value;
    }

    /**
     * Returns the current state of the delay override.
     * 
     * @return boolean true indicates override is on, false means off
     * 
     * @see setDelayOverride
     */
    public boolean getDelayOverride() {
        return delayOverride;
    }
    
    /**
     * Writes a single byte value to output file.
     * 
     * @param value value to write (must be byte-castable)
     */
    public void addByteToFile(Object value) {
        file.add((byte) value);
    }

    /**
     * Writes a single character value to output file.
     * 
     * @param c character to write
     */
	public void addCharToFile(char c) {
        byte value = (byte) 0x99;

		if ((int) c >= 97 && (int) c <= 122)     // Upper-case letters
			value = (byte) (c - 0x5D);
		else if ((int) c >= 65 && (int) c <= 90) // Lower-case letters
			value = (byte) (c - 0x3D);
		else if ((int) c >= 49 && (int) c <= 57) // Digits 0-9
			value = (byte) (c - 0x13);
		else                                     // Punctuation
			switch (c) {
			case ' ':
				value = (byte) 0x2C;
			case '!':
				value = (byte) 0x1e;
			case '@':
				value = (byte) 0x1f;
			case '#':
				value = (byte) 0x20;
			case '$':
				value = (byte) 0x21;
			case '%':
				value = (byte) 0x22;
			case '^':
				value = (byte) 0x23;
			case '&':
				value = (byte) 0x24;
			case '*':
				value = (byte) 0x25;
			case '(':
				value = (byte) 0x26;
			case ')':
			case '0':
				value = (byte) 0x27;
			case '-':
			case '_':
				value = (byte) 0x2D;
			case '=':
			case '+':
				value = (byte) 0x2E;
			case '[':
			case '{':
				value = (byte) 0x2F;
			case ']':
			case '}':
				value = (byte) 0x30;
			case '\\':
			case '|':
				value = (byte) 0x31;
			case ':':
			case ';':
				value = (byte) 0x33;
			case '\'':
			case '"':
				value = (byte) 0x34;
			case '`':
			case '~':
				value = (byte) 0x35;
			case ',':
			case '<':
				value = (byte) 0x36;
			case '.':
			case '>':
				value = (byte) 0x37;
			case '/':
			case '?':
				value = (byte) 0x38;
			}

        addByteToFile(value);
	}

	/**
	 * Writes function key value to output file.
	 * 
	 * @param fKey string to write
	 */
	public void addFunctionKeyToFile(String fKey) {
        byte value = (byte) 0x99;

        switch (fKey.toUpperCase()) {
		case "F1":
			value = (byte) 0x3a;
			break;
		case "F2":
			value = (byte) 0x3b;
			break;
		case "F3":
			value = (byte) 0x3c;
			break;
		case "F4":
			value = (byte) 0x3d;
			break;
		case "F5":
			value = (byte) 0x3e;
			break;
		case "F6":
			value = (byte) 0x3f;
			break;
		case "F7":
			value = (byte) 0x40;
			break;
		case "F8":
			value = (byte) 0x41;
			break;
		case "F9":
			value = (byte) 0x42;
			break;
		case "F10":
			value = (byte) 0x43;
			break;
		case "F11":
			value = (byte) 0x44;
			break;
		case "F12":
			value = (byte) 0x45;
			break;
        }

        addByteToFile(value);
	}

	/**
	 * Tests if the given string is a function key (for example, F1, F2, F3).
	 *
	 * @param possibleFKey string to test
	 * 
	 * @return boolean true indicates string is a function key, otherwise false
	 */
	public boolean isFunctionKey(String possibleFKey) {
		switch (possibleFKey.toUpperCase()) {
		case "F1":
		case "F2":
		case "F3":
		case "F4":
		case "F5":
		case "F6":
		case "F7":
		case "F8":
		case "F9":
		case "F10":
		case "F11":
		case "F12":
			return true;
		default:
			return false;
		}
	}
    
    public void begin() { }
}