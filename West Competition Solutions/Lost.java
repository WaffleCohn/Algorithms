/**Solution to Lost in Si Wong
*  Plano West Invitational 2016
*  @author Aneesh Adhikari-Desai
*/

import java.io.*;
import java.util.*;
import java.awt.Point;	//class to hold a point coordinate

public class Lost{

	public static void main(String[] args) throws IOException{

		Scanner scn = new Scanner(new File("lost.in"));	//open file for input
		int count = scn.nextInt();	//get number of test cases

		scn.nextLine();	//flush buffer

		for(int i = 0; i < count; i++){
			int x = scn.nextInt();	//store width and height, respectively
			int y = scn.nextInt();

			scn.nextLine();	//flush buffer

			int startX = -1;
			int startY = -1;	//initialize start and end point variables for future use
			int finishX = -1;
			int finishY = -1;

			boolean[][] been = new boolean[y][x];	//array to store all visited points
			char[][] grid = new char[y][x];			//array to store the grid itself

			for(int k = 0; k < y; k++){
				String line = scn.nextLine();
				grid[k] = line.toCharArray();	//store line in array

				if(line.indexOf("a") != -1){	//if starting point found, store coordinates
					startX = k;					
					startY = line.indexOf("a");
				}
				if(line.indexOf("A") != -1){	//same for ending point
					finishX = k;
					finishY = line.indexOf("A");
				}
				
			}

			Queue<Node> iterate = new LinkedList<Node>();	//instantiate queue for bfs maze search

			iterate.offer(new Node(new Point(startX, startY), null));	//add starting location to queue

			Node ans = null;	//initialize 'answer' node variable		

			while(!iterate.isEmpty()){
				Node curr = iterate.poll();	//grab next point to consider
				Point pt = curr.current;	//store point variable
				
				

				if(pt.x < 0 || pt.y < 0 || pt.x == grid.length || pt.y == grid[0].length){	//if out of bounds, go to next point
					continue;
				}

				if(grid[pt.x][pt.y] == 'S' || been[pt.x][pt.y]){	//if wall or already visited, go to next point
					continue;
				}


				if(grid[pt.x][pt.y] == 'A'){	//if answer point has been found, store in ans and stop loop
					ans = curr;
					break;
				}

				been[pt.x][pt.y] = true;	//mark point as visited

				iterate.offer(new Node(new Point(pt.x + 1, pt.y), curr));
				iterate.offer(new Node(new Point(pt.x - 1, pt.y), curr));	//iterate in every cardinal direction
				iterate.offer(new Node(new Point(pt.x, pt.y + 1), curr));
				iterate.offer(new Node(new Point(pt.x, pt.y - 1), curr));


			}

			while(true){
				Point pt = ans.current;		//traverse child-parent structure of answer node, marking
				grid[pt.x][pt.y] = '#';		//every location with a # as specified
				ans = ans.parent;
				if(ans == null)
					break;
			}

			grid[startX][startY] = 'a';		//remark starting and ending points
			grid[finishX][finishY] = 'A';


			for(int k = 0; k < y; k++){
				for(int j = 0; j < x; j++){
					System.out.print(grid[k][j]);		//display grid
				}
				System.out.println();
			}

			System.out.println();	//blank line between test cases
		}




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
