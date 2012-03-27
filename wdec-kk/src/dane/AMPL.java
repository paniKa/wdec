package dane;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ojalgo.netio.Message;

import se.optimatika.ampl.JAMPL;

public class AMPL {

	private JAMPL myAMPL = null;
	private String myModel;
	private String myData;
	static DataInputStream dos;
	static String last_prompt = "";

	public AMPL() {
		openFiles();
		myAMPL = startAmpl();


	}

	public JAMPL startAmpl() {

		if (myAMPL != null) {
			myAMPL.terminate();
			myAMPL = null;
		}
		try {
			myAMPL = new JAMPL();
		} catch (final Exception e) {
			System.err.println(e + " starting " + "AMPL");
		}

		return myAMPL;
	}

	public String readfile(final String fname) {
		int n;
		final byte buf[] = new byte[1000];
		String s = new String("");
		FileInputStream in;

		try {
			in = new FileInputStream(fname);
		} catch (final FileNotFoundException e) {
			System.err.println(e + " can't open " + fname);
			return e + " can't open " + fname;
		}
		try {
			while ((n = in.read(buf)) > 0) {
				final String s1 = new String(buf, 0, 0, n);
				s = s + s1;
			}
		} catch (final IOException e) {
			System.err.println(e + " readfile error");
			return e + " readfile error";
		}
		return s;
	}

	private void openFiles() {
		myModel = readfile("myModel.mod");
		myData = readfile("myData.txt");
//System.out.println(myModel);
//System.out.println(myData);
	}

	public void solve() {
		openFiles();
		this.sendCommand("reset;\n" + myModel + "\n" + "data;\n" + myData
				+ "solve;\n");
		
	}

	
	public Float display(String s){
		ArrayList<String> messages = this.sendCommand("display " + s);
		for(String k : messages){
			k = k.replaceAll("\\n", "");
			k = k.replaceAll("display "+ s + " = ", "");
			k = k.replaceAll(";", "");
			

			return Float.parseFloat(k);	
		}
		
		return null;
	}
	
	public ArrayList<String> sendCommand(final String aCommand) {
		final Message tmpMessage = new Message(aCommand);
		final List<Message> tmpReply = myAMPL.communicate(tmpMessage);
		
		return this.appendToTranscript(tmpReply);
	}
	
	private ArrayList<String> appendToTranscript(final List<Message> someMessages) {
		ArrayList<String> s = new ArrayList<String>();
        for (int i = 0; i < someMessages.size() - 1; i++) {
            	s.add(someMessages.get(i).toString());
        	//System.out.println(someMessages.get(i).toString());
        }
        return s;
    }

	public String getResult() {
		return myAMPL.getLastReturnArgument();
		//myAMPL.
	}
	
	
}