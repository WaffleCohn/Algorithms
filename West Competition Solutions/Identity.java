/**Solution to Identity
 * PWSH 2016 invitational
 * @author Jiaming Chen
 */
import java.io.*;
import java.util.*;

public class Identity {

    public static void main(String[] args) throws IOException {
    	Scanner in = new Scanner(new File("identity.in")); //access data from file
    	
    	int i = in.nextInt(); //get number of test cases
    	
    	while (i-- > 0) {
    		int n = in.nextInt(); //get number
    		int b = in.nextInt(); //get base
    		String s = ""; //store representation of number in given base
    		while (n != 0) {
    			int r = n % b; //calculate remainder
    			n /= b; //remove last digit in given base
    			if (r < 0) { //convert negative remainder to positive remainder
    				r -= b;
    				n++;
    			}
    			s = r + " " + s; //append digit to the beginning of the string
    		}
    		System.out.println(s); //output solution
    	}
    	in.close(); //close output stream, not required
    }
    
    
}