import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class Maze {
	public static class Coord {
		public int r;
		public int c;
		public int stepnum;
		
		public Coord(int r, int c, int stepnum) {
			super();
			this.r = r;
			this.c = c;
			this.stepnum = stepnum;
		}
		
		public String toString() {
			return "R: " + r + "; C: " + c;
		}
	}
	public static void main(String[] args) throws IOException {
		String[] maze = {"XX X", "XX X", "X  X", "XSXX", "XXXX"};
		char[][] grid = new char[maze.length][];
		
		Coord start = null;
		for (int i = 0; i < maze.length; i++) {
			String line = maze[i];
			grid[i] = line.toCharArray();
			
			int c = line.indexOf("S");
			if (c != -1) {
				start = new Coord(i, c, 1);
			}
		}
		
		ArrayList<Coord> steps = transverse(grid, start);
		for (Coord step: steps) {
			System.out.println(step);
		}
	}
	
	public static ArrayList<Coord> transverse(char[][] grid, Coord start /*, Coord finish*/) {
		int rows = grid.length, cols = grid[0].length;
		int[][] steps = new int[rows][cols];
		
		LinkedList<Coord> locs = new LinkedList<Coord>();
		locs.add(start);
		ArrayList<Coord> moves = new ArrayList<Coord>();
		Coord last = null;
		
		while (!locs.isEmpty()) {
			Coord loc = locs.removeFirst();
			int r = loc.r, c = loc.c, stepnum = loc.stepnum;
			if (r < 0 || c < 0 || r >= rows || c >= cols) {
				last = loc;
				break; //continue
			}
			/*
			 * if (r == finish.r && c == finish.c) {
			 * 	last = loc;
			 * 	break;
			 * }
			 */
			
			if (steps [r][c] > 0) continue;
			if (grid[r][c] == 'X') continue; //Wall
			
			steps[r][c] = stepnum;
			
			locs.add(new Coord(r, c+1, stepnum+1));
			locs.add(new Coord(r, c-1, stepnum+1));
			locs.add(new Coord(r+1, c, stepnum+1));
			locs.add(new Coord(r-1, c, stepnum+1));
		}
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				System.out.print(steps[r][c]);
			}
			System.out.println();
		}
		
		if (last != null) {
			if (last.r < 0) last.r = 0;
			if (last.c < 0) last.c = 0;
			if (last.r > rows - 1) last.r = rows - 1;
			if (last.c > cols - 1) last.c = cols - 1;
			Coord next = last;
			moves.add(last);
			for (int i = last.stepnum-2; i > 0; i--) {
				System.out.println(i + ": " + next);
				int r = next.r;
				int c = next.c;
				if (c > 0 && steps[r][c-1] == i) {
					next = new Coord(r,c-1, i);
				}else if (c < cols - 1 && steps[r][c+1] == i) {
					next = new Coord(r,c+1, i);
				}else if (r < rows - 1 && steps[r+1][c] == i) {
					next = new Coord(r+1,c, i);
				}else {
					next = new Coord(r,c-1, i);
				}
				moves.add(0,next);
				grid[r][c] = 'o';
			}
		}
		return moves;
	}
}