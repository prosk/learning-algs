import java.io.*;
import java.util.*;

public class AbSum {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new AbSum().run();		
        out.close();
    }

    void run() {
	    int a = readInt();
        int b = readInt();
        out.println(a + b); 
    }    	

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
	    while(!tok.hasMoreTokens()) {
	        String line = readLine();
	        if (line == null) return null;
	        tok = new StringTokenizer(line); 	 	
        }
        return tok.nextToken();
    } 

    String readLine() {
    	try {
	        return br.readLine();	
 	    } catch(IOException e) {
	        throw new RuntimeException(e);	
        }
    }	
} 
