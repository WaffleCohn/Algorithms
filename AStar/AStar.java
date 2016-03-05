import java.util.*;

public class AStar
{
	// Uses asterisks as visited, # as walls, and . as not visited
	// S is start X is finish
	public static boolean recurse(char[][] grid, int r, int c){
		if(r >= grid.length || r < 0 || c > grid[0].length || c < 0) return false;
		if(grid[r][c] == 'X') return true;
		if(grid[r][c] == '*' || grid[r][c] == '#') return false;
		grid[r][c] = '*';

		grid = grid.clone();
		return recurse(grid, r+1, c) || recurse(grid, r-1, c) || recurse(grid, r, c+1) || recurse(grid, r, c-1);
	}

	public static void AStar(int xStart, int yStart, int xFin, int yFin)
	{
		ArrayList<>
	}

	public static void main(String[] args){
		char[][] grid = {{'#','.','#'},
				{'#','.','#'},
				{'X','.','#'}};
		System.out.println(recurse(grid, 0, 1));
	}

}