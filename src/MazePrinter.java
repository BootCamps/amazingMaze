
public class MazePrinter {
	private StringBuffer result = new StringBuffer();

	public String drawMaze(int[][] maze, int width, int height, int entryPoint) {
		drawHeader();
		drawNorthernEntrance(width, entryPoint);
		for (int j = 1; j <= height; j++) {
			drawCorridors(width, maze, j);
			drawPath(width, maze, j);
		}
		return result.toString();
	}

	private void clear() {
		result.setLength(0);
	}

	private void println() {
		result.append("\n");
	}

	private void print(String text) {
		result.append(text);
	}

	private void drawHeader() {
		clear();
		print("Amazing - Copyright by Creative Computing, Morristown, NJ");
		println();
	}

	private void drawOpenCorridor() {
		print("   "); // 1240
	}

	private void drawPath(int horizontal, int[][] verticalArray, int j) {
		for (int i = 1; i <= horizontal; i++) {
			if (verticalArray[i][j] == 0 || verticalArray[i][j] == 2)
				drawBottomWall();
			else
				drawLeftCorridor();
		}
	
		print(":");
		println();
	}

	private void drawCorridors(int horizontal, int[][] verticalArray, int j) {
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

	private void drawRightWall() {
		print("  I");
	}

	private void drawBottomWall() {
		print(":--");
	}

	private void drawLeftCorridor() {
		print(":  ");
	}

	private void drawNorthernEntrance(int width, int entryPosition) {
		for (int i = 1; i <= width; i++) {
			if (i == entryPosition)
				drawLeftCorridor();
			else
				drawBottomWall();
		}
		print(":");
		println();
	}
}
