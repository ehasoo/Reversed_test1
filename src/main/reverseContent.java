/** This code reads data from file and writes reversed data with RandomAcessFile
 * 
 */
/**
 * @author Janek Ehasoo
 *
 */
package main;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.lang.Math;

public class reverseContent {
	public static void main(String[] args) throws IOException, FileNotFoundException{
		long starttime = System.currentTimeMillis();
		String filename = "aaa.txt";
		//* Try/catch for opening Random access file and collecting data for results.
		try {
			RandomAccessFile file = new RandomAccessFile(filename ,"rw");
			long filesize = file.length();
			double filesizeMB = getRounded(filesize / 1048576.0); 
			double filesizekB = getRounded(filesize / 1024.0);
			System.out.println("file name: " + filename + ", Size: " + filesizeMB + "MB");
			revContent(file);			
		    long completedIn = (System.currentTimeMillis() - starttime);
		    double sec = getRounded(completedIn / 1000.0);
		    double transformtime = getRounded(filesizekB / sec);
		    System.out.print("duration: " + sec + "s");
		    System.out.println(", Speed: " + transformtime + "kB/s");
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("IOException:");
			e.printStackTrace();
		}
	}
	
	//* Reversing binary content in random access file
	private static void revContent(RandomAccessFile file) {
		int x, y;
	    try {
	    	for (long i = 0, j = file.length() - 1; i < j; i++, j--) {
	  	      file.seek(i);
	  	      x = file.read();
	  	      file.seek(j);
	  	      y = file.read();
	  	      file.seek(j);
	  	      file.write(x);
	  	      file.seek(i);
	  	      file.write(y);
	  	    } 
	    } catch (IOException e){
	    	System.out.println("IOException:");
	    }		
	}
		
	//* Rounding data 
	private static double getRounded(double d) {
		return (double)Math.round( (d) * 100) / 100;
	}

}