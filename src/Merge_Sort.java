/*
 * Author: Sergey Bordulev
 * e-mail: skm2003@yandex.ru
 * 
 * The program supports merging two sorted files of different lengths. 
 * Sorting is done in ascending order.
 * To run the program, you must write in the command line:
 * After the program name (Merge_Sort), specify '-s' to sort the string file, or '-i' to sort the integer values. 
 * After the letter, a space-separated name of the output file and input file names separated by a space. 
 * 
 * The program creates the output file with the name you specify.
 * Example of running a program to sort files containing integers:
 * java Marge_Sort -i out.txt in1.txt in2.txt
 * 
 * Example of running a program to sort files containing strings:
 * java Marge_Sort -s out.txt s1.txt s2.txt
 * 
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Merge_Sort {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		if(args[0].equals("-s")) {
			try {
				BufferedReader brLeft = new BufferedReader(new FileReader(args[2]));
				BufferedReader brRight = new BufferedReader(new FileReader(args[3]));
				FileWriter frw = new FileWriter(args[1]);
				
				String line1 = brLeft.readLine();
				String line2 = brRight.readLine();
			    int frt = 0;
			    
			    boolean fileLeft = true; 
			    boolean fileRight = true; 
			
			    while (fileLeft || fileRight) {
			    	if (fileLeft == false) {
			    		frw.write(line2 + "\r\n");
			    		frt = 2;
			    	} else if (fileRight == false) {
			    		frw.write(line1 + "\r\n");
			    		frt = 1;
			    	} else {
			    		int ans = line1.compareTo(line2);
			    		if (ans < 0) {
			    			frw.write(line1 + "\r\n");
			    			frt = 1;
			    		} else if (ans > 0) {
			    			frw.write(line2 + "\r\n");
			    			frt = 2;
			    		} else if (ans == 0) {
			    			frw.write(line1 + "\r\n");
			    			frt = 1;
			    		}
			    	}
			    	if (frt == 1) {
			    		line1 = brLeft.readLine();
			    		if (line1 == null)
			    			fileLeft = false;
			    	} else {
			    		line2 = brRight.readLine();
			    		if (line2 == null)
			    			fileRight = false;
			    	}
			    }
			    frw.close(); 
			    brLeft.close(); 
			    brRight.close();
			    } catch (Exception exc) { 
			    	System.out.println("Error IO: " + exc); 
			    } 

		} else if (args[0].equals("-i")){
			try	{	
				BufferedReader brLeft = new BufferedReader (new FileReader (args[2]));
				BufferedReader brRight = new BufferedReader (new FileReader (args[3]));	
				FileWriter frw = new FileWriter (args[1]);
				
				String line1 = brLeft.readLine();
				String line2 = brRight.readLine();
				String lfwr;
				
				while (line2!=null || line1!=null) {
					if (line2==null) {
						lfwr = line1 + "\r\n";
						frw.write(lfwr);
						line1 = brLeft.readLine();
					} else if (line1 == null) {
						lfwr = line2 + "\r\n";
						frw.write(lfwr);
						line2 = brRight.readLine();
					} else if (Integer.parseInt(line1) <= Integer.parseInt(line2)) {
						lfwr = line1 + "\r\n";
						frw.write(lfwr);
						line1 = brLeft.readLine();
					} else {
						lfwr = line2 + "\r\n";
						frw.write(lfwr);
						line2 = brRight.readLine();
					}	
				}
				brLeft.close();
				brRight.close();
				frw.close();
			} catch (IOException exc) {
				System.out.println("Error IO: " + exc);
			}
		}
		
		long finish = System.currentTimeMillis();
		double timeCunsumedMilles = finish - start;
		System.out.println("The program worked: " + timeCunsumedMilles/1000 + " seconds");
	}
}
