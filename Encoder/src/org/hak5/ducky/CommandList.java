package org.hak5.ducky;

import java.util.ArrayList;

/**
 * @author Kevin Polulak
 * 
 * Command table holding an entry for every valid command. The CommandList
 * is searched through by the interpreter for a match.
 * 
 * Implemented as a singleton object since the interpreter should never use
 * more than one list. Therefore, the <code>new</code> operator will
 * throw an exception. The newInstance method should be used instead.
 */
public class CommandList {
    private ArrayList<Command> commands;
    private static final CommandList INSTANCE = new CommandList();

    /**
     * Returns a new instance of CommandList.
     *
     * @return new CommandList instance, never null
     */
    public static CommandList newInstance() {
    	return INSTANCE;
    }

    /**
     * Constructor is private on purpose. <i>Do not use.</i>
     */
    private CommandList() {
        commands = new ArrayList<Command>();

        commands.trimToSize();
    }

    /**
     * Returns the number of Command elements in this list.
     *
     * @return the number of Command elements in this list
     */
    public int size() {
        return commands.size();
    }

    /**
     * Returns the Command element at the given position from this list.
     *
     * @param index index of the element to return
     *
     * @return element at the given position
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<code>index < 0 || index >= size()</code>)
     */
    public Command get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        else
            return commands.get(index);
    }
}