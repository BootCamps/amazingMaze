
/**
 * + The original program is by Jack Hauber, and the source is
 * "Basic Computer Games." Used with permission of David Ahl;
 * see www.SwapMeetDave.com.
 * + This exercise was inspired by Alan Hensel's use of Amazing
 * as a refactoring challenge.
 * + This transliteration to Java was created by Bill Wake, William.Wake@acm.org
 */
import java.util.Random;

public class Amazing {
	private static final int LEFT_DIRECTION = 1;

	private static final int BOTTOM_WALL = 2;
	private static final int RIGHT_WALL = 1;
	private static final int OPEN = 3;
	private static final int CLOSE = 0;
	public Random random = new Random(0);
	private int xCoordinate;
	private int yCoordinate;
	private boolean reachedBottomRow;
	private boolean blocked;
	int direction;
	private int currentPosition;
	Maze maze;

	public static void main(String[] args) {
		Amazing amazing = new Amazing();
		amazing.generateMaze(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println(amazing.maze.toString());
	}

	public int generateRandom(int count) {
		return (int) (count * random.nextFloat()) + 1;
	}

	public void generateMaze(int width, int height) {
		maze = new Maze(width, height);
		if (maze.isInvalidMazeDimension())
			return;
		initializeMaze(width, height);
		makeAMove(xCoordinate, width, height);
	}

	private void case280Method(int width, int height) {
		if (maze.isAVisitedPosition(xCoordinate - 1, yCoordinate))
			case600Method(width, height);
		else if (isUpMovePossible())
			case430Method(width, height);
		else if (isRightMovePossible(width)) {
			if ((isAtBottomEdge(height) && blocked) || isDownMovementPossible(height)) {
				changeDirectionWhenRightMovementIsPossible(width, height, 2);
			} else {
				setContinueFlag(height);
				changeDirectionWhenRightMovementIsPossible(width, height, 3);
			}
		} else {
			changeDirectionWhenUpAndRightMoveIsBlocked(width, height);
		}
	}

	private void changeDirectionWhenUpAndRightMoveIsBlocked(int width, int height) {
		direction = getRandomDirection(3);
		if (direction == LEFT_DIRECTION)
			moveLeft(width, height);
		else if (direction == 2)
			moveUpAndMarkPosition(width, height);
		else
			moveRightAndMarkPosition(width, height);
	}

	private void changeDirectionWhenRightMovementIsPossible(int width, int height, int maxRandomValue) {
		direction = getRandomDirection(maxRandomValue);
		if (direction == LEFT_DIRECTION)
			moveLeft(width, height);
		else if (direction == 2)
			moveUpAndMarkPosition(width, height);
		else
			case1090Movement(width, height);
	}

	private void case430Method(int width, int height) {
		if (isRightMovePossible(width))
			case530Method(width, height);
		else if (isDownMovementPossible(height) || isAtBottomEdge(height) && blocked)
			changeDirectionAndMoveLeftOrRight(width, height);
		else {
			setContinueFlag(height);
			case490Method(width, height);
		}
	}

	private void case490Method(int width, int height) {
		direction = getRandomDirection(3);
		if (direction == LEFT_DIRECTION)
			moveLeft(width, height);
		else if (direction == 2)
			moveRightAndMarkPosition(width, height);
		else
			case1090Movement(width, height);

	}

	private void case530Method(int width, int height) {
		if (isDownMovementPossible(height) || isAtBottomEdge(height) && blocked) {
			moveLeft(width, height);
		} else {
			setContinueFlag(height);
			
			direction = getRandomDirection(2);
			if (direction == 1)
				moveLeft(width, height);
			else
				case1090Movement(width, height);
		}
	}

	private void case600Method(int width, int height) {
		if (isUpMovePossible()) {
			case790Method(width, height);
		} else if (isRightMovePossible(width))
			case720Method(width, height);
		else if ((blocked && isAtBottomEdge(height)) || isDownMovementPossible(height))
			changeDirection(width, height, 2);
		else {
			setContinueFlag(height);
			changeDirection(width, height, 3);
		}
	}

	private void case720Method(int width, int height) {
		if ((isAtBottomEdge(height) && blocked) || isBottomRowAlreadyVisited(height))
			moveUpAndMarkPosition(width, height);
		else {
			setContinueFlag(height);
			changeDirectionWhenNotInBottomRow(width, height);
		}
	}

	private void case790Method(int width, int height) {
		if (isRightMovePossible(width)) {
			if (!isAtBottomEdge(height) && maze.isAVisitedPosition(xCoordinate, yCoordinate + 1)
					|| isAtBottomEdge(height) && blocked) {
				findNextMove(width, height);
			} else {
				setContinueFlag(height);
				case1090Movement(width, height);
			}
		} else if (!isAtBottomEdge(height)) {
			if (maze.isAVisitedPosition(xCoordinate, yCoordinate + 1))
				moveRightAndMarkPosition(width, height);
			else {
				changeDirectionWhenRightWallIsPresent(width, height);
			}
		} else if (!isRightMovePossible(width) && blocked) {
			moveRightAndMarkPosition(width, height);
		} else {
			setContinueFlag(height);
			moveUp(width, height);
		}
	}

	private void changeDirectionWhenRightWallIsPresent(int width, int height) {
		direction = getRandomDirection(2);
		if (direction == 1)
			moveRightAndMarkPosition(width, height);
		else
			case1090Movement(width, height);
	}

	private void case1090Movement(int width, int height) {
		if (reachedBottomRow) {
			blocked = true;
			reachedBottomRow = false;
			openWall(RIGHT_WALL);
			findNextMove(width, height);
		} else {
			moveDown();
			makeNextMove(width, height);
		}
	}

	private boolean isDownMovementPossible(int height) {
		return isBottomRowAlreadyVisited(height);
	}

	private void changeDirectionAndMoveLeftOrRight(int width, int height) {
		direction = getRandomDirection(2);
		if (direction == LEFT_DIRECTION)
			moveLeft(width, height);
		else {
			moveRightAndMarkPosition(width, height);
		}

	}

	int getRandomDirection(int paramValue) {
		return generateRandom(paramValue);
	}

	private void setContinueFlag(int height) {
		if (isAtBottomEdge(height)) {
			reachedBottomRow = true;
		}
	}

	private void changeDirectionWhenNotInBottomRow(int width, int height) {
		direction = getRandomDirection(2);
		if (direction == 2)
			case1090Movement(width, height);
		else
			moveUpAndMarkPosition(width, height);
	}

	private boolean isBottomRowAlreadyVisited(int height) {
		return !isAtBottomEdge(height) && maze.isAVisitedPosition(xCoordinate, yCoordinate + 1);
	}

	private boolean isUpMovePossible() {
		return isAtTheTopEdge() || maze.isAVisitedPosition(xCoordinate, yCoordinate - 1);
	}

	private boolean isRightMovePossible(int width) {
		return isAtRightEdge(width) || maze.isAVisitedPosition(xCoordinate + 1, yCoordinate);
	}

	private void moveRightAndMarkPosition(int width, int height) {
		moveRight();
		if (!isAtExitPoint(width, height))
			case600Method(width, height);
	}

	private void moveDown() {
		maze.markPositionAsVisited(xCoordinate, yCoordinate + 1);
		openWall(RIGHT_WALL);
		incrementCurrentPosition();
		goDown();
	}

	private void moveRight() {
		maze.markPositionAsVisited(xCoordinate + 1, yCoordinate);
		openWall(BOTTOM_WALL);
		incrementCurrentPosition();
		goRight();
	}

	private void openWall(int keepWall) {
		if (maze.getMaze()[xCoordinate][yCoordinate] == CLOSE) {
			maze.getMaze()[xCoordinate][yCoordinate] = keepWall;
		} else {
			maze.getMaze()[xCoordinate][yCoordinate] = OPEN;
		}
	}

	private void moveUpAndMarkPosition(int width, int height) {
		maze.markPositionAsVisited(xCoordinate, yCoordinate - 1);
		moveUp(width, height);
	}

	private void changeDirection(int width, int height, int maxRandomValue) {
		direction = getRandomDirection(maxRandomValue);
		if (direction == LEFT_DIRECTION)
			moveUpAndMarkPosition(width, height);
		else if (direction == 2)
			moveRightAndMarkPosition(width, height);
		else
			case1090Movement(width, height);

	}

	private void initializeMaze(int width, int height) {

		reachedBottomRow = false;
		blocked = false;
		int entryPosition = generateRandom(width);
		maze.setEntryPoint(entryPosition);
		maze.markPositionAsVisited(entryPosition, 1);

		currentPosition = 1;
		incrementCurrentPosition();
		xCoordinate = entryPosition;
		yCoordinate = 1;
	}


	private void findNextMove(int width, int height) {
		
		if (isAtEndPosition(width, height)) {
			moveToTopLeftCorner();
		} else if (isAtRightEdge(width)) {
			goDown();
			moveToLeftEdge();
		} else {
			goRight();
		}
		
		if (!maze.isAVisitedPosition(xCoordinate, yCoordinate))
			findNextMove(width, height);
		else
			makeAMove(xCoordinate, width, height);
	}

	private void makeNextMove(int width, int height) {
		
		if(!isAtBottomEdge(height)){
			reachedBottomRow = false;
		}
		
		if (!isAtExitPoint(width, height)) {
			makeAMove(xCoordinate, width, height);
		}
	}

	private void makeAMove(int xCoordinate, int width, int height) {
		if (isAtLeftEdge(xCoordinate))
			case600Method(width, height);
		else
			case280Method(width, height);
	}

	private void moveUp(int width, int height) {
		incrementCurrentPosition();
		maze.getMaze()[xCoordinate][yCoordinate - 1] = 1;
		goUp();
		makeNextMove(width, height);
	}

	private void moveLeft(int width, int height) {
		maze.markPositionAsVisited(xCoordinate - 1, yCoordinate);
		maze.getMaze()[xCoordinate - 1][yCoordinate] = BOTTOM_WALL;
		goLeft();
		incrementCurrentPosition();
		makeNextMove(width, height);
	}

	private int incrementCurrentPosition() {
		return currentPosition++;
	}

	private void moveToTopLeftCorner() {
		moveToLeftEdge();
		yCoordinate = 1;
	}

	private void moveToLeftEdge() {
		xCoordinate = 1;
	}
	private void goDown() {
		yCoordinate++;
	}

	private void goRight() {
		xCoordinate++;
	}

	private void goUp() {
		yCoordinate--;
	}

	private void goLeft() {
		xCoordinate--;
	}

	private boolean isAtEndPosition(int width, int height) {
		return isAtRightEdge(width) && isAtBottomEdge(height);
	}

	private boolean isAtLeftEdge(int xCoordinate) {
		return xCoordinate - 1 == 0;
	}

	private boolean isAtExitPoint(int width, int height) {
		return currentPosition == width * height + 1;
	}

	private boolean isAtTheTopEdge() {
		return yCoordinate - 1 == 0;
	}

	private boolean isAtRightEdge(int width) {
		return xCoordinate == width;
	}

	private boolean isAtBottomEdge(int height) {
		return yCoordinate == height;
	}
}
