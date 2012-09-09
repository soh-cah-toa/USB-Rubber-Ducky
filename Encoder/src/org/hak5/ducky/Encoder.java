package org.hak5.ducky;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

// FIXME BREAK and PLAY both have PAUSE as an alias. One has to go.

/**
 * @author Jason Appelbaum
 * @author Kevin Polulak
 * 
 * Entry point to <code>duckencoder.jar</code>. Parses command-line options
 * before starting the state machine that is the interpreter.
 */
public class Encoder {
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

    public static void displayHelp() {
    	// TODO Re-implement this to use help messages associated with soon
    	//      to come Command objects.
		String message = "Hak5 Duck Encoder 2.0\n\n"
				+ "Usage: duckencoder -i [file ..]\t\t\tencode specified file\n"
				+ "   or: duckencoder -i [file ..] -o [file ..]\tencode to specified file\n"
				+ "\nArguments:\n"
				+ "   -i [file] \t\tInput DuckyScript file\n"
				+ "   -o [file] \t\tOutput file\n"
				+ "\nScript Commands:\n"
				+ "   ALT [END | (ESCAPE | ESC) | F1...F12 | Single Char | SPACE | TAB]\n"
				+ "   BREAK | PAUSE\n"
				+ "   CAPSLOCK\n"
				+ "   CONTROL | CTRL [(BREAK | PAUSE) | F1...F12 | (ESCAPE | ESC) | Single Char]\n"
				+ "   DEFAULT_DELAY | DEFAULTDELAY [Time in millisecond * 10]\n"
				+ "   DELAY [Time in millisecond * 10]\n"
				+ "   DELETE\n"
				+ "   DOWNARROW | DOWN\n"
				+ "   END\n"
				+ "   ESCAPE | ESC\n"
				+ "   F1...F12\n"
				+ "   HOME\n"
				+ "   INSERT\n"
				+ "   LEFTARROW | LEFT\n"
				+ "   MENU | APP\n"
				+ "   NUMLOCK\n"
				+ "   PAGEDOWN\n"
				+ "   PAGEUP\n"
				+ "   PRINTSCREEN\n"
				+ "   REM\n"
				+ "   RIGHTARROW | RIGHT\n"
				+ "   SCROLLLOCK\n"
				+ "   SHIFT [ DELETE | HOME | INSERT | PAGEUP | PAGEDOWN | (WINDOWS | GUI)\n"
				+ "         | (UPARROW | DOWNARROW |LEFTARROW | RIGHTARROW) | TAB]\n"
				+ "   SPACE\n"
				+ "   STRING [a...z A...Z 0..9 !...) `~ += _- \"\' :; <, >. ?/ \\|]\n"
				+ "   TAB\n" + "   UPARROW | UP\n" + "   WINDOWS | GUI\n";

        System.out.println(message);
    }
}