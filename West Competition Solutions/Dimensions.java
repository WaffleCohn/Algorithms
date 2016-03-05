/**Solution to Dimensions
 * PWSH 2016 invitational
 * @author Jiaming chen
 */
import java.io.*;
import java.util.*;

public class Dimensions {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("dimensions.in")); //access data from file
		int t = in.nextInt(); //get number of test cases
		cases:
		while (t-- > 0) {
			int d = in.nextInt(); //get number of dimensions
			
			int[] l = new int[d]; //get length of each dimension
			for (int i = 0; i < d; i++) 
				l[i] = in.nextInt();
			
			char[] m = in.next().toCharArray(); //get maze
			
			int a; //find starting point
			for (a = 0; a < m.length; a++)
				if (m[a] == 'A')
					break;
			
			boolean[] v = new boolean[m.length]; //records visited cells
			
			Queue<Integer> q = new LinkedList<>(); //records locations to search
			q.add(a); //add starting location
			q.add(-1); //-1 will be used to separate cells of different step lengths
			int s = 0; //records number of steps needed
			while (q.size() > 1) { //check cells until only -1 remains in the queue
				int p = q.remove(); //get next cell
				if (p == -1) { //if the -1 separator is obtained, increase the step and replace the separator at the end
					s++;
					q.add(-1);
					continue;
				}
				if (v[p]) //ignore visited cells
					continue;
				v[p] = true; //mark the cell as visited
				switch (m[p]) {
				case '#': //ignore walls
					continue;
				case 'B': //if the end is reached, print the step number and move on to the next case
					System.out.println(s);
					continue cases;
				default: //in all other cases, add all valid adjacent cells to the queue
					int div = 1; //divisor used to get the position of the cell along a dimension
					for (int i = 0; i < d; i++) { //add up to two cells per dimension
						int pos = p / div % l[i]; //get position of cell along a dimension
						if (pos > 0) //add the lower cell if the current cell is not at the lower edge
							q.add(p - div);
						if (pos < l[i] - 1) //add the higher cell if the current cell is not at the higher edge
							q.add(p + div);
						div *= l[i]; //update divisor
					}
				}
			}
			System.out.println("No Solution"); //output if no solution is reached
		}
		
	}

}
