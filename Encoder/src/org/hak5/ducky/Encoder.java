package org.hak5.ducky;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ListIterator;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

// FIXME BREAK and PLAY both have PAUSE as an alias. One has to go.

/**
 * Parses command-line options before starting the state machine that is the
 * interpreter.
 *
 * @author Jason Appelbaum
 * @author Kevin Polulak
 */
public class Encoder {
	private static final String VERSION = "2.0";
	
	/**
	 * Main entry point to <code>duckencoder.jar<code>.
	 * 
	 * @param args command-line arguments
	 */
    public static void main(String[] args) {
        String inputFile  = null;
        String outputFile = null;

        // Display help message by default without arguments
        if (args.length == 0) {
            displayHelp();
            System.exit(1);
        }

        // TODO Consider using Scanner class to parse arguments

        // Process command-line switches
        for (int i = 0; i < args.length; i++) {
            // XXX This --gui switch seems to do nothing. Is it needed?
            if (args[i].equals("--gui") || args[i].equals("-g"))
                System.out.println("Launch GUI");
            else if (args[i].equals("--help") || args[i].equals("-h"))
                displayHelp();
            else if (args[i].equals("-i"))
                inputFile = args[++i];
            else if (args[i].equals("-o"))
                outputFile = args[++i];
            else {
                displayHelp();
                break;
            }
        }

        // Read input DuckyScript file
        if (inputFile != null) {
            String fileText = null;

            if (inputFile.contains(".rtf")) {
                try {
                    FileInputStream stream = new FileInputStream(inputFile);
                    RTFEditorKit kit = new RTFEditorKit();
                    Document doc = kit.createDefaultDocument();

                    kit.read(stream, doc, 0);
                    fileText = doc.getText(0, doc.getLength());
                } catch (IOException e) {
                    String errMsg = "[ERROR] Failed to read RTF file: "
                                  + inputFile;

                    System.err.println(errMsg);
                } catch (BadLocationException e) {
                    String errMsg = "[ERROR] Failed to read from invalid "
                                  + "location within file: "
                                  + inputFile;

                    System.err.println(errMsg);
                }
            } else {
                DataInputStream in = null;

                try {
                    File f = new File(inputFile);
                    byte[] buffer = new byte[(int) f.length()];

                    in = new DataInputStream(new FileInputStream(f));
                    in.readFully(buffer);

                    fileText = new String(buffer);
                } catch (IOException e) {
                    String errMsg = "[ERROR] Failed to read text file: "
                                  + inputFile;

                    System.err.println(errMsg);
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) { // Ignore exception
                    }
                }
            }

            State state = State.newInstance(fileText,
                                           (outputFile == null) ? "inject.bin"
                                           : outputFile);

            state.begin();
        }
    }

    /**
     * Displays general help information on command-line arguments and valid
     * DuckyScript commands. Called when <code>--help</code> is used and when
     * either no arguments or an invalid argument is given.
     */
    public static void displayHelp() {
        StringBuilder message = new StringBuilder();
        
        message.append("Hak5 Duck Encoder " + VERSION + "\n\n"
            + "Usage: java -jar duckencoder.jar -i [file]\t\t\tEncode specified file\n"
            + "   Or: java -jar duckencoder.jar -i [file] -o [file]\tEncode to specified file\n"
            + "\nArguments:\n"
            + "   -i [file] \t\tInput DuckyScript file\n"
            + "   -o [file] \t\tOutput file\n"
            + "\nScript Commands:\n");
        
        ListIterator<Command> cmdListIter = CommandList.newInstance().listIterator();
        
        while (cmdListIter.hasNext())
        	message.append(cmdListIter.next().getHelp() + "\n");
        
        System.out.println(message.toString());
    }
}