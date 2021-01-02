import java.util.Arrays;

public class InvasionProtocol {

	private static final int[][] panelSolved = new int[5][5];
	private final String[] input;
	private final String[][] panel;

	public static void main(String[] args) {

		String[][] panel = {
				{"55", "1C", "BD", "BD", "1C"},
				{"BD", "1C", "55", "E9", "1C"},
				{"55", "E9", "55", "55", "1C"},
				{"BD", "BD", "1C", "BD", "BD"},
				{"55", "1C", "1C", "55", "1C"}};

		String[] input = {"E9", "1C", "1C", "1C", "E9"};

		InvasionProtocol panel5x5 = new InvasionProtocol(panel, input);
	}

	public InvasionProtocol(String[][] panel, String[] input) {

		this.panel = panel;
		this.input = input;
		if (solve(this.panel, this.input, 0, 0, 0)) {

			printSolve();

		} else {

			System.out.println("\u001B[31mERROR: The panel has no solution");

		}
	}

	private static boolean solve(String[][] panel, String[] input, int x, int y, int index) {

		if (index < input.length) {

			if (index % 2 == 0) {

				while (y < panel[0].length && ! (panelSolved[x][y] == 0 && panel[x][y].equals(input[index]))) {

					y++;

				}

				if (y < panel[0].length) {

					// This write a temporal result
					panelSolved[x][y] = index + 1;

					if (solve(panel, input, 0, y, index + 1)) {

						// This write the final result
						panelSolved[x][y] = index + 1;
						return true;

					} else {

						// This delete a temporal result if it is not correct
						panelSolved[x][y] = 0;

						return solve(panel, input, x, y + 1, index);
					}

				} else return x + 1 < panel.length && solve(panel, input, x + 1, 0, index);

			} else {

				while (x < panel[0].length && ! (panelSolved[x][y] == 0 && panel[x][y].equals(input[index]))) {

					x++;

				}

				if (x < panel.length) {

					panelSolved[x][y] = index + 1;

					if (solve(panel, input, x, 0, index + 1)) {

						panelSolved[x][y] = index + 1;
						return true;

					} else {

						panelSolved[x][y] = 0;

						return solve(panel, input, x + 1, y, index);
					}

				} else return false;
			}
		}

		return true;

	}

	private static void printSolve() {

		for (int[] ints : panelSolved) {

			System.out.println(Arrays.toString(ints));
		}
	}

}
