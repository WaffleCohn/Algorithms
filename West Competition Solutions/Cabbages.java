/**Problem: My Cabbages!
 * Plano West Invitational 2016
 * @author Aneesh Adhikari-Desai
 */

import java.util.*;
import java.io.*;
import java.awt.Point;	//class to hold coordinate point

public class Cabbages {



	public static void main(String[] args) throws IOException{

		Scanner scn = new Scanner(new File("cabbages.in"));	//open file for input

		while(scn.hasNextLine()){
			int x = scn.nextInt();
			int y = scn.nextInt();	//get dimensions of grid

			int cStartX = -1;
			int aStartX = -1;		//initialize starting points
			int cStartY = -1;
			int aStartY = -1;

			scn.nextLine();	//flush buffer

			char[][] grid = new char[x][y];	//instantiate grid

			for(int i = 0; i < x; i++){
				String line = scn.nextLine();
				grid[i] = line.toCharArray();	//store maze in grid array

				if(line.indexOf("A") != -1){
					aStartX = i;					//find and store coordinates for Aang
					aStartY = line.indexOf("A");
				}

				if(line.indexOf("C") != -1){
					cStartX = i;					//and merchant
					cStartY = line.indexOf("C");
				}

			}

			Queue<Node> aangSearch = new LinkedList<Node>();		//instantiate queues for breadth first search
			Queue<Node> cabbageSearch = new LinkedList<Node>();

			aangSearch.offer(new Node(new Point(aStartX, aStartY), null));		//add initial points
			cabbageSearch.offer(new Node(new Point(cStartX, cStartY), null));

			Node aangAns = null;
			Node cabbAns = null;	//save the paths as a node object (end to front traversal)

			while(!aangSearch.isEmpty()){
				
				Node curr = aangSearch.poll();	//grab next point from queue
				Point pt = curr.current;		//save value of point

				if(pt.x < 0 || pt.y < 0 || pt.x == grid.length || pt.y == grid[0].length){
					aangAns = curr;							//if escaped, save the end node and break
					break;
				}

				if(grid[pt.x][pt.y] == '@'){	//do not proceed if on a wall
					continue;
				}

				aangSearch.offer(new Node(new Point(pt.x + 1, pt.y), curr));
				aangSearch.offer(new Node(new Point(pt.x - 1, pt.y), curr));	//iterate in every direction and add to queue
				aangSearch.offer(new Node(new Point(pt.x, pt.y + 1), curr));
				aangSearch.offer(new Node(new Point(pt.x, pt.y - 1), curr));

			}

			while(!cabbageSearch.isEmpty()){		//repeat for cabbage merchant
				
				Node curr = cabbageSearch.poll();
				Point pt = curr.current;

				if(pt.x < 0 || pt.y < 0 || pt.x == grid.length || pt.y == grid[0].length){
					cabbAns = curr;
					break;
				}

				if(grid[pt.x][pt.y] == '@'){
					continue;
				}

				cabbageSearch.offer(new Node(new Point(pt.x + 1, pt.y), curr));
				cabbageSearch.offer(new Node(new Point(pt.x - 1, pt.y), curr));
				cabbageSearch.offer(new Node(new Point(pt.x, pt.y + 1), curr));
				cabbageSearch.offer(new Node(new Point(pt.x, pt.y - 1), curr));

			}

			/*At this point, aangAns and cabbAns have been populated, but these are references
			*to the last position of each character. In order to compare positions with respect to time,
			*the nodes must be traversed step by step backwards into a stack in order to have a front-to-back
			*order
			*/

			Deque<Point> aangPath = new LinkedList<Point>();	//stacks to store path
			Deque<Point> cabbPath = new LinkedList<Point>();

			while(true){
				aangPath.push(aangAns.current);	//add current position to stack
				aangAns = aangAns.parent;	//iterate one back in node family
				if(aangAns == null)		//if original parent found, exit
					break;
			}

			while(true){
				cabbPath.push(cabbAns.current);	//repeat for cabbage man
				cabbAns = cabbAns.parent;
				if(cabbAns == null)
					break;
			}

			/*By this point, each stack contains the paths travelled by Aang and the cabbage merchant
			*in a front to back fashion, with the first position at the top of the stack, and each position
			*in the stack representing that position at a common time, so whether or not they attempted to
			*occupy the same space at the same time can be determined
			*/

			boolean collide = false;	//whether or not a collision has been found

			while(!aangPath.isEmpty() && !cabbPath.isEmpty()){
				if(aangPath.pop().equals(cabbPath.pop())){	//compare positions at a common time, removing them from the stacks
					collide = true;
					break;
				}

			}

			if(collide)
				System.out.println("My Cabbages!");	//if a collision was found
			else if(aangPath.isEmpty())
				System.out.println("Aang");	//if aang got out first
			else
				System.out.println("Cabbage Merchant");	//if the cabbage merchant got out first


		}

		scn.close();

	}

}


/**	The Node class serves to keep track of a point object and the preceding point object, with the original
*	node having a parent of null
*
*/
class Node{

	public Point current;	
	public Node parent;		

	/**Instantiates object with a given position and parent
	*  @param a the current point
	*  @param b the parent of the current point
	*/
	public Node(Point a, Node b){
		current = a;
		parent = b;
	}

}