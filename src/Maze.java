
public class Maze {

	private int width;
	private int height;
	private  int[][] maze;
	private  boolean[][] visited;
	int entryPoint;
	MazePrinter mazePrinter=new MazePrinter();
	
	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		this.visited = new boolean[width + 1][height + 1];
		this.maze = initializeArray(width, height);
	}

	public int[][] getMaze() {
		return maze;
	}

	public boolean[][] getVisited() {
		return visited;
	}

	boolean isInvalidMazeDimension() {
		return width == 1 || height == 1;
	}

	int[][] initializeArray(int h, int v) {
		int[][] verticalArray = new int[h + 1][v + 1];
		for (int i = 0; i <= h; i++) {
			verticalArray[i] = new int[v + 1];
		}
		return verticalArray;
	}

	void markPositionAsVisited(int xCordinate, int yCoordinate) {
		visited[xCordinate][yCoordinate] = true;
	}

	boolean isAVisitedPosition(int xCoordinate, int yCoordinate) {
		return visited[xCoordinate][yCoordinate];
	}

	public void setEntryPoint(int entryPoint) {
		this.entryPoint = entryPoint;
	}

	@Override
	public String toString(){
		return mazePrinter.drawMaze(maze,width,height,entryPoint);
	}

}
