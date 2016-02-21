
public class Levenshtein {
	// O(n^2) levenshtein algorithm
	public static int levenshtein(String from, String to) {
		int[][] map = new int[to.length() + 1][from.length() + 1];

		// Fill the side bars
		for (int i = 0; i < from.length(); i++) {
			map[0][i + 1] = i;
		}
		for (int i = 0; i < to.length(); i++) {
			map[i + 1][0] = i;
		}

		for (int r = 1; r <= to.length(); r++) {
			for (int c = 1; c <= from.length(); c++) {
				if (to.charAt(r - 1) == from.charAt(c - 1)) {
					map[r][c] = map[r - 1][c - 1]; // Same character.
				} else {
					map[r][c] = 1 + Math.min(Math.min(map[r][c - 1] /* Deletion */, map[r - 1][c - 1] /* Replacement */), map[r - 1][c] /* Insertion */);
				}
			}
		}
		return map[to.length()][from.length()];
	}
}
