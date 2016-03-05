import java.util.LinkedList;

/**
 * This class consists of a bordered-maze. Ie its walls are thin lines that lie in two adjacent cells.
 *
 * In a grid of R rows and C columns, there can be up to R x (C+1) vertical walls And (R+1) x C horizontal walls.
 *
 * Example, in a grid of 2x3 the _ and | are the walls possible in the grid: <code>
 *  _ _ _
 * |_|_|_|
 * |_|_|_|
 * </code> As seen above, the horizontal walls make a 3x3 array and the vertical walls make a 2x4 array.
 * 
 * @author Henry
 *
 */
public class BorderedMazes {
	/**
	 * Coord class used to hold a row and column position
	 * 
	 * @author Henry
	 *
	 */
	public static class Coord {
		public int r;
		public int c;

		public Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "(R: " + r + " C: " + c + ")";
		}
	}

	/**
	 * Breadth-first transversal
	 * 
	 * @param hwalls the horizontal walls
	 * @param vwalls the vertical walls
	 * @param start the starting point
	 * @param finish the finish point
	 */
	public static void bft(boolean hwalls[][], boolean vwalls[][], Coord start, Coord finish) {
		int rows = vwalls.length;
		int cols = hwalls[0].length;
		boolean flood[][] = new boolean[rows][cols];
		LinkedList<Coord> locs = new LinkedList<Coord>();

		locs.add(start);

		while (!locs.isEmpty()) {
			Coord loc = locs.removeFirst();
			int r = loc.r, c = loc.c;
			if (r < 0 || c < 0 || r >= rows || c >= cols) {
				continue;
			}
			if (r == finish.r && c == finish.c) {
				// To do: something when we reached exit.
			}

			if (flood[r][c]) {
				continue;
			}
			flood[r][c] = true;

			if (!vwalls[r][c + 1]) {
				locs.add(new Coord(r, c + 1));
			}
			if (!vwalls[r][c]) {
				locs.add(new Coord(r, c - 1));
			}
			if (!hwalls[r + 1][c]) {
				locs.add(new Coord(r + 1, c));
			}
			if (!hwalls[r][c]) {
				locs.add(new Coord(r - 1, c));
			}
		}
	}
	//
	// public static void main(String[] args) throws IOException {
	// Scanner in = new Scanner(new File("Maze.txt"));
	// while (true) {
	// int rows = in.nextInt();
	// int cols = in.nextInt();
	//
	// if (rows == 0 && cols == 0) {
	// return;
	// }
	//
	// in.nextLine();
	// boolean[][] hwalls = new boolean[rows + 1][cols];
	// boolean[][] vwalls = new boolean[rows][cols + 1];
	//
	// for (int r = 0; r < rows; r++) {
	// String line = in.nextLine();
	// for (int c = 0; c < cols; c++) {
	// int walls = Integer.parseInt(line.substring(c, c + 1), 16);
	// if ((walls & 8) > 0) { // North
	// hwalls[r][c] = true;
	// }
	// if ((walls & 4) > 0) { // East
	// vwalls[r][c + 1] = true;
	// }
	// if ((walls & 2) > 0) { // South
	// hwalls[r + 1][c] = true;
	// }
	// if ((walls & 1) > 0) { // West
	// vwalls[r][c] = true;
	// }
	// }
	// }
	//
	// displayMaze(hwalls, vwalls);
	//
	// // Find start and end.
	// ArrayList<Coord> ends = new ArrayList<Coord>();
	// for (int r = 0; r < rows; r++) {
	// if (!vwalls[r][0]) {
	// ends.add(new Coord(r, 0));
	// }
	// if (!vwalls[r][cols]) {
	// ends.add(new Coord(r, cols - 1));
	// }
	// }
	// for (int c = 0; c < cols; c++) {
	// if (!hwalls[0][c]) {
	// ends.add(new Coord(0, c));
	// }
	// if (!hwalls[rows][c]) {
	// ends.add(new Coord(rows - 1, c));
	// }
	// }
	//
	// boolean been[][] = new boolean[rows][cols];
	// dft(hwalls, vwalls, been, ends.get(0), ends.get(1));
	// }
	// }

	/**
	 * Utility method for copying a matrix (2-d array).
	 * 
	 * @param mat
	 * @return the copied matrix.
	 */
	public static boolean[][] copyMatrix(boolean[][] mat) {
		boolean[][] copy = new boolean[mat.length][];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = mat[i].clone();
		}
		return copy;
	}

	/**
	 * Depth-first transversal of the maze.
	 * 
	 * @param hwalls the horizontal walls
	 * @param vwalls the vertical walls
	 * @param been a 2-d array describing where we have been
	 * @param loc the current location
	 * @param finish the finish line
	 */
	public static void dft(boolean hwalls[][], boolean vwalls[][], boolean[][] been, Coord loc, Coord finish) {
		int rows = vwalls.length;
		int cols = hwalls[0].length;
		int r = loc.r, c = loc.c;

		if (r < 0 || c < 0 || r >= rows || c >= cols) {
			return;
		}

		if (been[r][c]) {
			return;
		}
		been[r][c] = true;

		if (r == finish.r && c == finish.c) {
			// DO something with finish...
		}

		if (!vwalls[r][c + 1]) {
			dft(hwalls, vwalls, copyMatrix(been), new Coord(r, c + 1), finish);
		}
		if (!vwalls[r][c]) {
			dft(hwalls, vwalls, copyMatrix(been), new Coord(r, c - 1), finish);
		}
		if (!hwalls[r + 1][c]) {
			dft(hwalls, vwalls, copyMatrix(been), new Coord(r + 1, c), finish);
		}
		if (!hwalls[r][c]) {
			dft(hwalls, vwalls, copyMatrix(been), new Coord(r - 1, c), finish);
		}
	}

	/**
	 * Displays the maze into standard output, using | for vertical walls and _ for horizontal walls.
	 * 
	 * @param hwalls the horizontal walls
	 * @param vwalls the vertical walls
	 */
	public static void displayMaze(boolean[][] hwalls, boolean[][] vwalls) {
		int rows = vwalls.length;
		int cols = hwalls[0].length;
		for (int r = -1; r < rows; r++) {
			boolean hasV = r > -1;
			for (int c = -1; c < cols; c++) {
				boolean hasH = c > -1;
				if (hasH) {
					System.out.print(hwalls[r + 1][c] ? "_" : " ");
				}
				if (hasV) {
					System.out.print(vwalls[r][c + 1] ? "|" : " ");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
