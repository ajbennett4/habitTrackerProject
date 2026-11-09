/**
 * Parses class data into a machine readable text file
 * */

package fileio;

import java.io.PrintWriter;
import java.util.HashMap;
import java.io.BufferedOutputStream;

public class TextEncoder {
	private String ObjectName;
	private PrintWriter formatedWriter;
	
	public TextEncoder(String ObjectName, BufferedOutputStream fileOutStream) {
		this.ObjectName = ObjectName;
		this.formatedWriter = new PrintWriter(fileOutStream);
	}
	
	public void addFields(HashMap<String, String> fields) {
		//stub
	}
	
	public void addField(String field, String value) {
		//stub
	}
	
	public void save() {
		//stub
	}
}