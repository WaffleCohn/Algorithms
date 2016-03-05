/**Solution to Pai-Bo
 * PWSH 2016 invitational
 * @author Jiaming Chen
 */
import java.io.*;
import java.util.*;
public class Paibo {

	//stores victory conditions for both players
	static boolean[][][] v1;
	static boolean[][][] v2;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("paibo.in"));
		//loop through test cases
		int t = in.nextInt();
		while (t-- > 0) {
			//create an empty board of the appropriate size
			char[][] b = new char[in.nextInt()][in.nextInt()];
			//create arrays for victory conditions of appropriate size
			v1 = new boolean[in.nextInt()][][];
			v2 = new boolean[in.nextInt()][][];
			
			//populate victory condition arrays
			for (int i = 0; i < v1.length; i++) {
				v1[i] = new boolean[in.nextInt()][in.nextInt()];
				for (int j = 0; j < v1[i].length; j++) {
					String s = in.next();
					for (int k = 0; k < v1[i][j].length; k++)
						v1[i][j][k] = s.charAt(k) == 'X';
				}
			}
			for (int i = 0; i < v2.length; i++) {
				v2[i] = new boolean[in.nextInt()][in.nextInt()];
				for (int j = 0; j < v2[i].length; j++) {
					String s = in.next();
					for (int k = 0; k < v2[i][j].length; k++)
						v2[i][j][k] = s.charAt(k) == 'X';
				}
			}
			
			//determine victor
			switch (m(b, 'A')) {
			case 'A': System.out.println("1st Player"); break;
			case 'B': System.out.println("2nd Player"); break;
			default: System.out.println("Tie"); break;
			}
		}
		
		
	}
	
	/**
	 * determines the victory of a given board state if both players play perfectly
	 * @param b the board state
	 * @param p the current player
	 * @return 'A' if player A wins, 'B' if player B wins, null character if tie
	 */
	static char m(char[][] b, char p) {
		//if the current player has lost, report that the previous player has won
		if (cv(b, p == 'A' ? v2 : v1, p == 'A' ? 'B' : 'A')) {
			return p == 'A' ? 'B' : 'A';
		}
		
		//if the board is full, report a tie
		if (cf(b))
			return 0;
		
		boolean t = false; //store possibility of a tie
		
		//check the outcomes of all possible plays
		for (int r = 0; r < b.length; r++)
			for (int c = 0; c < b[r].length; c++) {
				//if the location is full already, skip it
				if (b[r][c] != 0)
					continue;
				//clone board and play on the selected cell
				char[][] cl = c(b);
				cl[r][c] = p;
				
				char o = m(cl, p == 'A' ? 'B' : 'A'); //store outcome of play
				//if the current player wins report that the current player wins
				if (o == p)
					return p;
				//if there is a tie store that there is the possibility of a tie
				if (o == 0)
					t = true;
			}
		//if there is no possibility of victory for the current player and there
		//is a play that leads to a tie, report a tie, otherwise report a victory
		//for the other player
		return t ? 0 : p == 'A' ? 'B' : 'A';
	}
	
	/**
	 * clone a board state
	 * @param b a board state
	 * @return a clone of the board state
	 */
	static char[][] c(char[][] b) {
		char[][] cl = new char[b.length][b[0].length]; //make new board
		
		//copy the values from the original board to the new board
		for (int r = 0; r < b.length; r++)
			for (int c = 0; c < b[r].length; c++)
				cl[r][c] = b[r][c];
		
		//return the clone of the board
		return cl;
	}
	
	/**
	 * checks for victory
	 * @param b a board state
	 * @param v an array of victory conditions
	 * @param ch the character to check for victory
	 * @return true if the player has won, false otherwise
	 */
	static boolean cv(char[][] b, boolean[][][] v, char ch) {
		//loop through all victory conditions
		for (boolean[][] va : v) {
			//loop through all possible placement of the victory condition
			for (int r = 0; r <= b.length - va.length; r++)
				point:
				for (int c = 0; c <= b[r].length - va[0].length; c++) {
					//loop through all points in the victory condition
					for (int rr = 0; rr < va.length; rr++)
						for (int cc = 0; cc < va[rr].length; cc++) 
							//if a required point is missing, try the next placement
							if (va[rr][cc] && b[r + rr][c + cc] != ch) 
								continue point;
					//if no points are missing, report that the player has won
					return true;	
				}
		}
		//if the player has not won, report that the player has not won
		return false;
	}
	
	/**
	 * checks if board is full
	 * @param b a board state
	 * @return true if the board has no empty spaces, false otherwise
	 */
	static boolean cf(char[][] b) {
		for (char[] r : b)
			for (char c : r)
				if (c == 0)
					return false;
		return true;
	}
}
