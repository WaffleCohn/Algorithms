import java.util.*;

public class Knapsack {
	/**
	 * This class represents an item with a value and cost.
	 */
	public static class Item {
		public int value;
		public int cost;
		public String name;

		public Item(String name, int value, int cost) {
			this.name = name;
			this.value = value;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static ArrayList<Item> leastCost(Item[] inv, int cap) {
		int[][] tbl = new int[cap + 1][inv.length];
		for (int i = 0; i < inv.length; i++) {
			for (int c = 0; c <= cap; c++) {
				int leaveVal = i > 0 ? tbl[c][i - 1] : 0; // the value without taking item

				if (c >= inv[i].cost) {
					int takeVal = inv[i].value + (i > 0 ? tbl[c - inv[i].cost][i - 1] : 0);
					if (takeVal > leaveVal) {
						tbl[c][i] = takeVal;
						continue;
					}
				}
				tbl[c][i] = leaveVal;
			}
		}

		for (int r = 0; r < cap + 1; r++) {
			for (int c = 0; c < inv.length; c++) {
				System.out.printf("%5d", tbl[r][c]);
			}
			System.out.println();
		}

		ArrayList<Item> items = new ArrayList<Item>();
		int c = cap;
		for (int i = inv.length - 1; i > 0 && c > 0; i--) {
			if (tbl[c][i] > tbl[c][i - 1]) {
				c -= inv[i].cost;
				items.add(0, inv[i]);
			}
		}
		if (c >= inv[0].cost) {
			items.add(0, inv[0]);
		}
		return items;
	}

	public static void main(String[] args) {
		Item[] invs = { new Item("A", 5, 2), new Item("B", 7, 3), new Item("C", 2, 3), new Item("D", 2, 1), new Item("E", 1, 3), new Item("F", 2, 2),
				new Item("G", 6, 4) };
		System.out.println(leastCost(invs, 10));
	}
}
