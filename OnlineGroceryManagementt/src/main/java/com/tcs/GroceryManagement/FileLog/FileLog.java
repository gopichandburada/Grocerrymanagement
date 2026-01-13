
package com.tcs.GroceryManagement.FileLog;

import java.io.FileWriter;
import java.io.PrintWriter;

public class FileLog {
	private static final String FILE_NAME="logging.txt";

	public static synchronized void log(String message) {
	    try(PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME , true ))){
	        out.println(message);
	    }catch(Exception e ) {
	        e.printStackTrace();
	    }
	}
}
