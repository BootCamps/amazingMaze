
public class Maze {

	static StringBuffer result = new StringBuffer();

	void clear() {
		result.setLength(0);
	}

	void println() {
		result.append("\n");
	}

	public void print(String text) {
		result.append(text);
	}

	void drawHeader() {
		clear();
		print("Amazing - Copyright by Creative Computing, Morristown, NJ");
		println();
	}

	boolean isInvalidMazeDimension(int horizontal, int vertical) {
		return horizontal == 1 || vertical == 1;
	}

	int[][] initializeArray(int h, int v) {
		int[][] verticalArray = new int[h + 1][v + 1];
		for (int i = 0; i <= h; i++) {
			verticalArray[i] = new int[v + 1];
		}
		return verticalArray;
	}

	void drawMaze(int horizontal, int vertical, int[][] verticalArray) {
		for (int j = 1; j <= vertical; j++) {
			drawCorridors(horizontal, verticalArray, j);
			drawPath(horizontal, verticalArray, j);
		}
	}

	void drawEntryForMaze(int h, int x) {
		for (int i = 1; i <= h; i++) {
			if (i == x)
				drawLeftCorridor();
			else
				drawBottomWall();
		}
		// 180
		print(":");
		println();
	}

	void drawOpenCorridor() {
		print("   "); // 1240
	}

	void drawPath(int horizontal, int[][] verticalArray, int j) {
		for (int i = 1; i <= horizontal; i++) {
			if (verticalArray[i][j] == 0 || verticalArray[i][j] == 2)
				drawBottomWall();
			else
				drawLeftCorridor();
		}

		print(":");
		println();
	}

	void drawCorridors(int horizontal, int[][] verticalArray, int j) {
		print("I");

		for (int i = 1; i <= horizontal; i++) {
			if (verticalArray[i][j] >= 2)
				drawOpenCorridor();
			else
				drawRightWall();
		}

		print(" ");
		println();
	}

	void drawRightWall() {
		print("  I");
	}

	void drawBottomWall() {
		print(":--");
	}

	void drawLeftCorridor() {
		print(":  ");
	}

}
