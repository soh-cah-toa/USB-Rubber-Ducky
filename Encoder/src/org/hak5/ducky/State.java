package org.hak5.ducky;

import java.util.List;
import java.util.ArrayList;

public class State {
    private static String[] instructions;
    private static String[] currInstruction;
    //private static CommandList commandList;
    private static int defaultDelay;
    private static boolean delayOverride;
    private static List<Byte> file;

    private String outFile;

    private static State INSTANCE = null;
    
    public static State newInstance(String inputString, String outFile) {
    	if (INSTANCE == null)
    		INSTANCE = new State(inputString, outFile);
    	
    	return INSTANCE;
    }

    private State(String inputString, String outFile) {
		inputString  = inputString.replaceAll("\\r", ""); // CRLF Fix
		instructions = inputString.split("\n");

        file = new ArrayList<Byte>();
        this.outFile = outFile;

        defaultDelay  = 0;
        delayOverride = false;

        //commandList = CommandList.newInstance();
        currInstruction = new String[2];
    }
    
    public void begin() { }
}