
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
	private boolean shallContinue;
	private boolean shallExit;
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
	}

	private void case280Method(int horizontal, int vertical) {
		if (maze.isAVisitedPosition(xCoordinate - 1, yCoordinate))
			case600Method(horizontal, vertical);
		else if (isUpMovePossible())
			case430Method(horizontal, vertical);
		else if (isRightMovePossible(horizontal)) {
			if ((isAtBottomEdge(vertical) && shallExit) || isDownMovePossible(vertical)){
				case410Method(horizontal, vertical);
			}else {
				setContinueFlag(vertical);
				case390Method(horizontal, vertical);
			}
		} else {
			direction = switchDirection(3);
			if (direction == LEFT_DIRECTION)
				moveLeft(horizontal, vertical);
			else if (direction == 2)
				moveUp(horizontal, vertical);
			else if (direction == 3)
				moveRightAndMarkPosition(horizontal, vertical);
			else
				if ((isAtBottomEdge(vertical) && shallExit) || isDownMovePossible(vertical)){
					case410Method(horizontal, vertical);
				}else {
					setContinueFlag(vertical);
					case390Method(horizontal, vertical);
				}
		}
	}

	private boolean isAtBottomEdge(int vertical) {
		return yCoordinate == vertical;
	}

	private void case390Method(int horizontal, int vertical) {
		direction = switchDirection(3);
		if (direction == LEFT_DIRECTION)
			moveLeft(horizontal, vertical);
		else if (direction == 2)
			moveUp(horizontal, vertical);
		else if (direction == 3)
			case1090Movement(horizontal, vertical);
		else
			case410Method(horizontal, vertical);
	}

	private void case410Method(int horizontal, int vertical) {
		direction = switchDirection(2);
		if (direction == LEFT_DIRECTION)
			moveLeft(horizontal, vertical);
		else if (direction == 2)
			moveUp(horizontal, vertical);
		else
			case430Method(horizontal, vertical);
	}

	private void case430Method(int horizontal, int vertical) {
		if (isRightMovePossible(horizontal))
			case530Method(horizontal, vertical);
		else if (isDownMovePossible(vertical) || isAtBottomEdge(vertical) && shallExit)
			changeDirectionAndMove(horizontal, vertical);
		else {
			if (isAtBottomEdge(vertical))
				shallContinue = true;

			case490Method(horizontal, vertical);
		}
	}

	private boolean isDownMovePossible(int vertical) {
		return !isAtBottomEdge(vertical) && maze.isAVisitedPosition(xCoordinate, yCoordinate + 1);
	}

	private void case490Method(int horizontal, int vertical) {
		direction = switchDirection(3);
		if (direction == LEFT_DIRECTION)
			moveLeft(horizontal, vertical);
		else if (direction == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else if (direction == 3)
			case1090Movement(horizontal, vertical);
		else
			changeDirectionAndMove(horizontal, vertical);
	}

	private int switchDirection(int paramValue) {
		return generateRandom(paramValue);
	}

	private void changeDirectionAndMove(int horizontal, int vertical) {
		direction = switchDirection(2);
		if (direction == LEFT_DIRECTION)
			moveLeft(horizontal, vertical);
		else if (direction == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else
			case530Method(horizontal, vertical);
	}

	private void case530Method(int horizontal, int vertical) {
		if (isDownMovePossible(vertical) || isAtBottomEdge(vertical) && shallExit) {
			moveLeft(horizontal, vertical);
		} else {
			setContinueFlag(vertical);
			direction = switchDirection(2);
			if (direction == 2)
				case1090Movement(horizontal, vertical);
			else
				moveLeft(horizontal, vertical);
		}
	}

	private void case600Method(int horizontal, int vertical) {
		if (isUpMovePossible()) {
			case790Method(horizontal, vertical);
		} else if (isRightMovePossible(horizontal))
			case720Method(horizontal, vertical);
		else if ((shallExit && isAtBottomEdge(vertical)) || isDownMovePossible(vertical))
			mayMakeADownMove(horizontal, vertical);
		else {
			setContinueFlag(vertical);
			mayMakeRandomCalculationForThree(horizontal, vertical);
		}
	}

	private void setContinueFlag(int vertical) {
		if (isAtBottomEdge(vertical)) {
			shallContinue = true;
		}
	}

	private void case720Method(int horizontal, int vertical) {
		if (isNotAtBottomEdge(vertical)) {
			if (maze.isAVisitedPosition(xCoordinate, yCoordinate + 1))
				moveUp(horizontal, vertical);
			else
				case760Method(horizontal, vertical);
		} else if (shallExit)
			moveUp(horizontal, vertical);
		else {
			setContinueFlag(vertical);
			case760Method(horizontal, vertical);
		}
	}

	private boolean isNotAtBottomEdge(int vertical) {
		return yCoordinate != vertical;
	}

	private void case760Method(int horizontal, int vertical) {
		direction = switchDirection(2);
		if (direction == 2)
			case1090Movement(horizontal, vertical);
		else
			moveUp(horizontal, vertical);
	}

	private void case790Method(int horizontal, int vertical) {
		if (isRightMovePossible(horizontal)) {
			if (isNotAtBottomEdge(vertical)) {
				if (maze.isAVisitedPosition(xCoordinate, yCoordinate + 1))
					findNextMove(horizontal, vertical);
				else
					case1090Movement(horizontal, vertical);
			} else if (shallExit) {
				findNextMove(horizontal, vertical);
			} else {
				setContinueFlag(vertical);
				case1090Movement(horizontal, vertical);
			}
		} else if (isNotAtBottomEdge(vertical)) {
			if (maze.isAVisitedPosition(xCoordinate, yCoordinate + 1))
				moveRightAndMarkPosition(horizontal, vertical);
			else {
				direction = switchDirection(2);
				if (direction == 2)
					case1090Movement(horizontal, vertical);
				else
					moveRightAndMarkPosition(horizontal, vertical);
			}
		} else if (shallExit) {
			moveRightAndMarkPosition(horizontal, vertical);
		} else {
			setContinueFlag(vertical);
			mayMoveUpAndValidate(horizontal, vertical);
		}
	}

	private void moveLeft(int horizontal, int vertical) {
		maze.markPositionAsVisited(xCoordinate - 1, yCoordinate);
		maze.getMaze()[xCoordinate - 1][yCoordinate] = BOTTOM_WALL;
		xCoordinate--;
		incrementCurrentPosition();
		mayValidatePostMovement(horizontal, vertical);
	}

	private void case1090Movement(int horizontal, int vertical) {
		if (shallContinue) {
			shallExit = true;
			shallContinue = false;
			if (maze.getMaze()[xCoordinate][yCoordinate] == CLOSE) {
				maze.getMaze()[xCoordinate][yCoordinate] = RIGHT_WALL;
				positionAtTopLeftCorner();
				makeAMoveIfNotVisited(horizontal, vertical);

			} else {
				maze.getMaze()[xCoordinate][yCoordinate] = OPEN;
				findNextMove(horizontal, vertical);
			}
		} else {
			goDown();
			if (!isAtExitPoint(vertical, horizontal)) {
				makeAMove(xCoordinate, horizontal, vertical);
			}
		}
	}

	private boolean isUpMovePossible() {
		return yCoordinate - 1 == 0 || maze.isAVisitedPosition(xCoordinate, yCoordinate - 1);
	}

	private boolean isRightMovePossible(int horizontal) {
		return xCoordinate == horizontal || maze.isAVisitedPosition(xCoordinate + 1, yCoordinate);
	}

	private void moveRightAndMarkPosition(int horizontal, int vertical) {
		goRight();
		if (!isAtExitPoint(horizontal, vertical))
			case600Method(horizontal, vertical);
	}

	private void goDown() {
		maze.markPositionAsVisited(xCoordinate, yCoordinate + 1);
		incrementCurrentPosition();
		openWall(RIGHT_WALL);
		moveDown();
	}

	private void goRight() {
		maze.markPositionAsVisited(xCoordinate + 1, yCoordinate);
		incrementCurrentPosition();
		openWall(BOTTOM_WALL);
		moveRight();
	}

	private void openWall(int keepWall) {
		if (maze.getMaze()[xCoordinate][yCoordinate] == 0) {
			maze.getMaze()[xCoordinate][yCoordinate] = keepWall;
		} else {
			maze.getMaze()[xCoordinate][yCoordinate] = OPEN;
		}
	}

	private void moveUp(int horizontal, int vertical) {
		maze.markPositionAsVisited(xCoordinate, yCoordinate - 1);
		mayMoveUpAndValidate(horizontal, vertical);
	}

	private void mayMoveUpAndValidate(int horizontal, int vertical) {
		incrementCurrentPosition();
		maze.getMaze()[xCoordinate][yCoordinate - 1] = 1;
		moveUp();
		mayValidatePostMovement(horizontal, vertical);
	}

	private void makeAMoveIfNotVisited(int horizontal, int vertical) {
		if (!maze.isAVisitedPosition(xCoordinate, yCoordinate))
			findNextMove(horizontal, vertical);
		else
			makeAMove(xCoordinate, horizontal, vertical);
	}

	private void mayMakeADownMove(int horizontal, int vertical) {
		direction = switchDirection(2);
		if (direction == LEFT_DIRECTION)
			moveUp(horizontal, vertical);
		else if (direction == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else
			case720Method(horizontal, vertical);
	}

	private void mayMakeRandomCalculationForThree(int horizontal, int vertical) {
		direction = switchDirection(3);
		if (direction == LEFT_DIRECTION)
			moveUp(horizontal, vertical);
		else if (direction == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else if (direction == 3)
			case1090Movement(horizontal, vertical);
		else
			mayMakeADownMove(horizontal, vertical);
	}

	private void mayValidatePostMovement(int horizontal, int vertical) {
		if (!isAtExitPoint(horizontal, vertical)) {
			shallContinue = false;
			makeAMove(xCoordinate, horizontal, vertical);
		}
	}

	private void initializeMaze(int width, int height) {

		shallContinue = false;
		shallExit = false;
		int entryPosition = generateRandom(width);
		maze.setEntryPoint(entryPosition);
		maze.markPositionAsVisited(entryPosition, 1);

		currentPosition = 1;
		incrementCurrentPosition();
		xCoordinate = entryPosition;
		yCoordinate = 1;

		makeAMove(xCoordinate, width, height);
	}

	private int incrementCurrentPosition() {
		return currentPosition++;
	}

	private void moveUp() {
		yCoordinate--;
	}

	private boolean isAtExitPoint(int horizontal, int vertical) {
		return currentPosition == horizontal * vertical + 1;
	}

	private void findNextMove(int width, int height) {
		if (xCoordinate == width) {
			if (isNotAtBottomEdge(height)) {
				positionAtStartX();
				moveDown();
			} else {
				positionAtTopLeftCorner();
			}
		} else {
			moveRight();
		}
		makeAMoveIfNotVisited(width, height);
	}

	private void positionAtStartX() {
		xCoordinate = 1;
	}

	private void moveDown() {
		yCoordinate++;
	}

	private void moveRight() {
		xCoordinate++;
	}

	private void positionAtTopLeftCorner() {
		positionAtStartX();
		yCoordinate = 1;
	}

	private void makeAMove(int xCoordinate, int horizontal, int vertical) {
		if (xCoordinate - 1 == 0)
			case600Method(horizontal, vertical);
		else
			case280Method(horizontal, vertical);
	}
}
