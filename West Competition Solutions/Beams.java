/**Solution to Beams of Fire
 * PWSH 2016 invitational
 * @author Unknown Man
 */
import java.io.*;
import java.util.*;
public class Beams
{	
	public static void main(String[] args) throws IOException
	{
		//read input
		Scanner in=new Scanner(new File("beams.in"));
		int t = in.nextInt();
		while(t-- > 0)
		{
			//receive values
			int x=in.nextInt();
			int y=in.nextInt();
			int c=in.nextInt();
			
			//perform binary search on possible solutions
			double max=x,min=0;
			while (max-min>0.000001)
			{
				//calculate value to test
				double mid=(min+max)/2;
				//calculate heights of triangles
				double d1=Math.sqrt(x*x-mid*mid);
				double d2=Math.sqrt(y*y-mid*mid);
				/*|\       /|
				 *| \     /	|
				 *|  \   /	|
				 *|   \ /	|
				 *|A   X    |B
				 *|   /|\   |
				 *|  / | \  |
				 *| / h|  \ |
				 *|/   |   \|
				 *----------- 
				 *     W
				 *Assume W is split at the intersection between h and W into W1 (left half)
				 *and W2 (right half). We know that W/A = W2/h since the triangles formed by
				 *both line segments share a right angle and their lower right angles and thus
				 *are proportional. Likewise, we know that W/B = W1/h. By adding the equations,
				 *we get W/A + W/B = (W2 + W1)/h. Since W1 + W2 = W, we get W/A + W/B = W/h.
				 *Dividing by W yields 1/A + 1/B = 1/h. Multiplying by ABh yields hB + hA = h(A + B) = AB.
				 *Therefore, we can use the formula h(A + B) = AB in order to verify that a
				 *solution is correct.
				 */
				if(c*(d1+d2)==(d1*d2)){
					min=max=mid;
					break;
				}
				//if the solution was not found, change the search boundaries accordingly
				else if(c*(d1+d2)>(d1*d2)) max=mid;
				else min=mid;
			}
			//output answer
			System.out.printf("%.4f\n",(min+max)/2);
		}
	}
}