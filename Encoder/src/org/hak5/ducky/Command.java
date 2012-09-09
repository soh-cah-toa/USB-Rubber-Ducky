package org.hak5.ducky;

/**
 * @author Kevin Polulak
 *
 * Represents the basic functionality that each valid command should
 * implement.
 * 
 * This guarantees that each command provides a way to execute itself and
 * retrieve its name, alias (if any), and related information on the command
 * to be displayed along with the <code>--help</code> switch.
 */
public interface Command {
    /** Code to run when command name is found in file. */
    public void action(State state, String args);

    public String getName();    /** Command name. */
    public String getAltName(); /** Alternative shorthand name. */
    public String getHelp();    /** Command info for <code>-h</code> switch. */
}