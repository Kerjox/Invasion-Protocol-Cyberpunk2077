import java.util.Arrays;

public class InvasionProtocol {

	private final int[][] panelSolved;
	private String solve;

	public InvasionProtocol(String panel, String input) {
		
		int panelDimensions = getDimensions(getNumberValues(panel));

		this.panelSolved = new int[panelDimensions][panelDimensions];

		if (panelDimensions == 10){

			this.solve = "ERROR: The panel is incorrect";
			return;

		}else this.solve = "";


		if (solve(decodePanel(panel), decodeInput(input), 0, 0, 0)) {

			for (int[] ints : panelSolved) {

				this.solve = this.solve.concat(Arrays.toString(ints)).concat("\n");

			}
			System.out.println(this.solve);

		} else {

			this.solve = "ERROR: The panel has no solution";

		}
	}

	public String getSolve() {

		return this.solve;
	}

	private boolean solve(String[][] panel, String[] input, int x, int y, int index) {

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

	private static String[][] decodePanel(String panel) {
		
		int panelDimensions = getDimensions(getNumberValues(panel));
		int indexArray = 0;

		String[][] _panel = new String[panelDimensions][panelDimensions];

		for (int x = 0; x < _panel.length; x++) {

			for (int y = 0; y < _panel[0].length; y++) {

				_panel[x][y] = extractValue(panel, indexArray);
				indexArray++;
			}

		}

		return _panel;

	}
	
	private static int getDimensions(int numberValues) {

		int panelDimensions = 0;
		
		while (panelDimensions < 10 && panelDimensions * panelDimensions != numberValues) {

			panelDimensions++;
		}
		
		return panelDimensions;
	}

	private static int getNumberValues(String data) {

		int numberValues = 1;

		for (int i = 0; i < data.length(); i++) {

			if (data.charAt(i) == ',') numberValues++;
		}
		return numberValues;
	}

	private static String[] decodeInput(String input) {


		int numberValues = getNumberValues(input);

		String[] _input = new String[numberValues];

		for (int i = 0; i < numberValues; i++) {

			_input[i] = extractValue(input, i);

		}

		return _input;

	}

	private static String extractValue(String data, int index) {

		String value = "";
		int cont = 0;
		int firstIndex = 0;
		int lastIndex;

		while (cont != index + 1) {

			lastIndex = data.indexOf(",", firstIndex);
			if (lastIndex < firstIndex) {
				value = data.substring(firstIndex);
			} else value = data.substring(firstIndex, lastIndex);
			firstIndex = lastIndex + 1;
			cont++;
		}

		return value;
	}

}
