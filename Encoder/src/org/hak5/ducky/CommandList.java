package org.hak5.ducky;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Command table holding an entry for every valid command. The CommandList
 * is searched through by the interpreter for a match.
 * 
 * Implemented as a singleton object since the interpreter should never use
 * more than one list. Therefore, the <code>new</code> operator will
 * throw an exception. The newInstance method should be used instead.
 * 
 * @author Kevin Polulak
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
        
        commands.add(new org.hak5.ducky.command.Alt());
        commands.add(new org.hak5.ducky.command.Break());
        commands.add(new org.hak5.ducky.command.CapsLock());
        commands.add(new org.hak5.ducky.command.Control());
        commands.add(new org.hak5.ducky.command.DefaultDelay());
        commands.add(new org.hak5.ducky.command.Delay());
        commands.add(new org.hak5.ducky.command.Delete());
        commands.add(new org.hak5.ducky.command.DownArrow());
        commands.add(new org.hak5.ducky.command.End());
        commands.add(new org.hak5.ducky.command.Enter());
        commands.add(new org.hak5.ducky.command.Escape());
        commands.add(new org.hak5.ducky.command.Home());
        commands.add(new org.hak5.ducky.command.Insert());
        commands.add(new org.hak5.ducky.command.LeftArrow());
        commands.add(new org.hak5.ducky.command.Menu());
        commands.add(new org.hak5.ducky.command.Mute());
        commands.add(new org.hak5.ducky.command.NumLock());
        commands.add(new org.hak5.ducky.command.PageDown());
        commands.add(new org.hak5.ducky.command.PageUp());
        commands.add(new org.hak5.ducky.command.Play());
        commands.add(new org.hak5.ducky.command.PrintScreen());
        commands.add(new org.hak5.ducky.command.RightArrow());
        commands.add(new org.hak5.ducky.command.ScrollLock());
        commands.add(new org.hak5.ducky.command.Shift());
        commands.add(new org.hak5.ducky.command.Space());
        commands.add(new org.hak5.ducky.command.Stop());
        commands.add(new org.hak5.ducky.command.String());
        commands.add(new org.hak5.ducky.command.SystemPower());
        commands.add(new org.hak5.ducky.command.SystemSleep());
        commands.add(new org.hak5.ducky.command.SystemWake());
        commands.add(new org.hak5.ducky.command.Tab());
        commands.add(new org.hak5.ducky.command.UpArrow());
        commands.add(new org.hak5.ducky.command.VolumeDown());
        commands.add(new org.hak5.ducky.command.VolumeUp());
        commands.add(new org.hak5.ducky.command.Windows());

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
    
    /**
     * Returns a list iterator over the Command elements in this list (in
     * proper sequence).
     * 
     * @return a list iterator over the Command elements in this list
     */
    public ListIterator<Command> listIterator() {
    	return commands.listIterator();
    }
}