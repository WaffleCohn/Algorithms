import java.util.*;

public class AStar
{
	// Uses asterisks as visited, # as walls, and . as not visited
	// S is start X is finish

	/**
	 * Object to store x and y
	 */
	private static class Position implements Comparable<Position>
	{
		private int x;
		private int y;

		public Position(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		public int getX()
		{
			return x;
		}

		public void setX(int x)
		{
			this.x = x;
		}

		public int getY()
		{
			return y;
		}

		public void setY(int y)
		{
			this.y = y;
		}

		/**
		 * @param width  width of maze
		 * @param height height of maze
		 * @return neighbor positions
		 */
		public ArrayList<Position> getNeighbors(int width, int height)
		{
			ArrayList<Position> neighbors = new ArrayList<Position>();

			if (x > 0)
				neighbors.add(new Position(x - 1, y));
			if (x < width - 1)
				neighbors.add(new Position(x + 1, y));
			if (y > 0)
				neighbors.add(new Position(x, y - 1));
			if (y < height - 1)
				neighbors.add(new Position(x, y + 1));

			return neighbors;
		}

		@Override
		public int compareTo(Position o)
		{
			if (y != o.y)
				return y - o.y;
			return x - o.x;
		}

		@Override
		public boolean equals(Object o)
		{
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Position position = (Position) o;

			if (x != position.x) return false;
			return y == position.y;

		}

		@Override
		public int hashCode()
		{
			int result = x;
			result = 31 * result + y;
			return result;
		}

		@Override
		public String toString()
		{
			return "Position{" +
					"x=" + x +
					", y=" + y +
					'}';
		}
	}

	/**
	 * calculate path from start to finish or return null if path not found
	 * @param maze 2d char array with # = walls
	 * @param xStart starting pos
	 * @param yStart starting pos
	 * @param xFin fin pos
	 * @param yFin fin pos
	 * @return 2d boolean array with path true and others false
	 */
	public static boolean[][] AStar(final char[][] maze, final int xStart, final int yStart, final int xFin, final int yFin)
	{
		// The set of nodes already evaluated.
		TreeSet<Position> closedSet = new TreeSet<Position>();
		// The set of currently discovered nodes still to be evaluated.
		// Initially, only the start node is known.
		TreeSet<Position> openSet = new TreeSet<Position>();
		openSet.add(new Position(xStart, yStart));

		// For each node, which node it can most efficient be reach from.
		// If a node can be reached from many nodes, cameFrom will eventually contain the
		// most efficient previous step.
		TreeMap<Position, Position> cameFrom = new TreeMap<Position, Position>();

		// For each node, the cost of getting from the start node to that node.
		TreeMap<Position, Integer> gScore = new TreeMap<Position, Integer>();
		// The cost of going from start to start is zero.
		gScore.put(new Position(xStart, yStart), 0);

		// For each node, the total cost of getting from the start node to the goal
		// by passing by that node. That value is partly known, partly heuristic.
		TreeMap<Position, Integer> fScore = new TreeMap<Position, Integer>();
		// For the first node, that value is completely heuristic.
		fScore.put(new Position(xStart, yStart), heuristicCostEstimate(xStart, yStart, xFin, yFin));

		while (!openSet.isEmpty())
		{
			Position current = openSet.pollFirst();

			//Found ending
			if (current.getX() == xFin && current.getY() == yFin)
			{
				//reconstruct path
				boolean[][] path = new boolean[maze.length][maze[0].length];

				path[xFin][yFin] = true;

				while (cameFrom.containsKey(current))
				{
					current = cameFrom.get(current);
					path[current.getX()][current.getY()] = true;
				}

				return path;
			}

			//mark current as closed
			closedSet.add(current);

			ArrayList<Position> neighbors = current.getNeighbors(maze.length, maze[0].length);
			//remove unusable positions
			for (int i = neighbors.size() - 1; i >= 0; i--)
			{
				if (maze[neighbors.get(i).getX()][neighbors.get(i).getY()] == '#')
				{
					System.out.println(neighbors.get(i));
					neighbors.remove(i);
				}
			}
			//for other positions branching out from current
			for (Position neighborPos : neighbors)
			{
				System.out.println("test: "+ neighborPos);
				//System.out.println(closedSet);
				if (closedSet.contains(neighborPos))
				{
					//System.out.println("test:( "+ neighborPos);
					continue; //ignore already evaluated positions
				}
				// The distance from start to goal passing through current and the neighbor.
				int tentativeGScore;
				if (!gScore.containsKey(neighborPos))
					gScore.put(neighborPos, 999999); //use large gScore values for unknown nodes
				tentativeGScore = gScore.get(neighborPos) + 1;
				//System.out.println(tentativeGScore);
				if (!openSet.contains(neighborPos))
					openSet.add(neighborPos); // Discover a new node
				else if (tentativeGScore >= gScore.get(neighborPos))
					continue; // This is not a better path.

				// This path is the best until now. Record it!
				cameFrom.put(neighborPos, current);
				gScore.put(neighborPos, tentativeGScore);
				fScore.put(neighborPos, gScore.get(neighborPos) + heuristicCostEstimate(neighborPos.getX(), neighborPos.getY(), xFin, yFin));
			}
		}
		//Path was NOT found
		System.out.println("Path not found");
		return null;
	}

	/**
	 * literally distance formula
	 * @param xStart
	 * @param yStart
	 * @param xFin
	 * @param yFin
	 * @return rounded distance
	 */
	private static int heuristicCostEstimate(final int xStart, final int yStart, final int xFin, final int yFin)
	{
		return (int) Math.round(Math.sqrt((xStart - xFin) * (xStart - xFin) + (yStart - yFin) * (yStart - yFin)));
	}

	public static void main(String[] args)
	{
		char[][] grid = {{'#', '.', '#'},
				{'#', '.', '#'},
				{'X', '.', '#'}};
		boolean[][] ans = AStar(grid, 0, 1, 0, 2);

		for (char[] chars : grid)
		{
			System.out.println(Arrays.toString(chars));
		}
		for (boolean[] an : ans)
		{
			System.out.println(Arrays.toString(an));
		}
	}

}