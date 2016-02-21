
public class Combinations {
	public static void combination(int n, int p /* TO DO: add any more related arguments for permutation */) {
		// Special cases
		if (p > n || p <= 0) {
			return;
		}

		int[] ind = new int[p];
		int d = 1;

		// Initializes indexes at its minimum state.
		for (int i = 0; i < p; i++) {
			ind[i] = i;
		}
		d = p - 1;

		while (true) {
			if (ind[d] >= n - (p - d - 1)) {
				// Carry over to the next largest depth
				d--;
				if (d == -1) {
					// We have reached the end.
					break;
				}
			} else if (d < p - 1) {
				// Refill the index number
				d++;
				ind[d] = ind[d - 1] + 1;
				continue;
			} else {
				// TO DO: Some action here for each iteration.
			}
			ind[d]++;
		}
	}
}
