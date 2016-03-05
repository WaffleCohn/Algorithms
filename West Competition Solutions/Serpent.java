/**The Tale of the Serpent Solution
 * @author Marcus Deng
 */


import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Serpent 
{
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner (new File("serpent.in")); //Scanner
		int num = scan.nextInt();
		
		for (int a = 0; a < num; a++) //Iterate through datasets
		{
			int dist = scan.nextInt(); //Get test case
			double x = 0; //Start from origin
			double y = 0;
			
			int temp = 0;
			int count = 1;
			int dir = 0; //0 = E, 1 = N, 2 = W, 3 = S
			
			while (dist > 0)
			{
				if (temp < 2) //move before changing change in x and y; ex: go right and up one before going left and down two
				{
					dist -= count;
					temp ++;
				}
				if (dist <= 0) //if total distance travelled equal to case distance, add/subtract back the intended distance to make sure it doesn't go over
				{
					if (dir == 0)
						x += count + dist;
					else if (dir == 1)
						y += count + dist;
					else if (dir == 2)
						x -= count + dist;
					else if (dir == 3)
						y -= count + dist;
				}
				else //modifies the change in x and y; ex: after every 2 moves the change is incremented by +/- 1
				{
					if (dir == 0)
						x += count;
					else if (dir == 1)
						y += count;
					else if (dir == 2)
						x -= count;
					else if (dir == 3)
						y -= count;
				}
				dir ++; //rotates direction
				if (dir == 4) //resets direction back to east
					dir = 0;
				
				if (temp == 2)//move twice before increasing change in distance in x and y
				{
					temp = 0;
					count ++;
				}
			}
			out.printf("(%.2f, %.2f)\n", x, y);
		}

	}

}
