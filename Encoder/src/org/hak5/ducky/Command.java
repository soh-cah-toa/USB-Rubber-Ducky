package org.hak5.ducky;

/**
 * Represents the basic functionality that each valid command should
 * implement.
 * 
 * This guarantees that each command provides a way to execute itself and
 * retrieve its name, alias (if any), and related information on the command
 * to be displayed along with the <code>--help</code> switch.
 * 
 * @author Kevin Polulak
 */
public interface Command {
    /** Code to run when command name is found in file. */
    public void action(State state, String args);

    /** Command name (for example, DEFAULT_DELAY, DOWNARROW, WINDOWS). */
    public String getName();
    
    /** Alternative shorthand name (for example, DEFAULTDELAY, DOWN, GUI). */
    public String getAltName();
    
    /** Related command info for use with <code>--help</code> switch. */
    public String getHelp();
}