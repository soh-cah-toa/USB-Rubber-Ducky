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
    
    public void begin() { }
}