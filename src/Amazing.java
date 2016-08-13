
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
	static int target = 0; // where GOTO goes
	public static Random random = new Random(0);
	private static int xCoordinate;
	private static int yCoordinate;
	private static boolean[][] visitedPositionsArray;
	private static int[][] verticalArrays;
	private static boolean q;
	private static boolean z;
	private static int entryPoint;
	private static int currentPosition;
	static Maze maze;

	public static void main(String[] args) {
		generateMaze(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println(Maze.result);
	}

	public static int generateRandom(int count) {
		return (int) (count * random.nextFloat()) + 1;
	}

	public static void generateMaze(int horizontal, int vertical) {
		maze = new Maze();
		maze.drawHeader();
		if (maze.isInvalidMazeDimension(horizontal, vertical))
			return;
		initializeMaze(horizontal, vertical);
		maze.drawMaze(horizontal, vertical, verticalArrays);
	}

	private static void case510Method(int horizontal, int vertical) {
		entryPoint = generateRandom(2);
		if (entryPoint == 1)
			case940Movement(horizontal, vertical);
		else if (entryPoint == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else
			case530Method(horizontal, vertical);
	}

	private static void case600Method(int horizontal, int vertical) {
		if (yCoordinate - 1 == 0 || isAVisitedPosition(xCoordinate, yCoordinate - 1)) {
			case790Method(horizontal, vertical);
		} else if (xCoordinate == horizontal || isAVisitedPosition(xCoordinate + 1, yCoordinate))
			case720Method(horizontal, vertical);
		else if ((isZTrue() && !isWithinBottomEdge(vertical))
				|| isWithinBottomEdge(vertical) && isAVisitedPosition(xCoordinate, yCoordinate + 1))
			mayMakeRandomCalculationForTwo(horizontal, vertical);
		else if (isWithinBottomEdge(vertical)) {
			mayMakeRandomCalculationForThree(horizontal, vertical);
		} else {
			assignQ(true);
			mayMakeRandomCalculationForThree(horizontal, vertical);
		}
	}

	private static void case530Method(int horizontal, int vertical) {
		if (isWithinBottomEdge(vertical) && isAVisitedPosition(xCoordinate, yCoordinate + 1)|| !isWithinBottomEdge(vertical) && isZTrue()) {
			case940Movement(horizontal, vertical);
		} else {
			if (!isWithinBottomEdge(vertical)) {
				assignQ(true);
			}
			entryPoint = generateRandom(2);
			if (entryPoint == 2)
				case1090Movement(horizontal, vertical);
			else
				case940Movement(horizontal, vertical);
		}
	}

	private static void case390Method(int horizontal, int vertical) {
		entryPoint = generateRandom(3);
		if (entryPoint == 1)
			case940Movement(horizontal, vertical);
		else if (entryPoint == 2)
			mayMoveUp(horizontal, vertical);
		else if (entryPoint == 3)
			case1090Movement(horizontal, vertical);
		else
			case410Method(horizontal, vertical);
	}

	private static void case410Method(int horizontal, int vertical) {
		entryPoint = generateRandom(2);
		if (entryPoint == 1)
			case940Movement(horizontal, vertical);
		else if (entryPoint == 2)
			mayMoveUp(horizontal, vertical);
		else
			case430Method(horizontal, vertical);
	}

	private static void case280Method(int horizontal, int vertical) {
		if (isAVisitedPosition(xCoordinate - 1, yCoordinate))
			case600Method(horizontal, vertical);
		else if (yCoordinate - 1 == 0 || isAVisitedPosition(xCoordinate, yCoordinate - 1))
			case430Method(horizontal, vertical);
		else if (xCoordinate == horizontal || isAVisitedPosition(xCoordinate + 1, yCoordinate)){
			case350Method(horizontal, vertical);
		}else{
			entryPoint = generateRandom(3);
			if (entryPoint == 1)
				case940Movement(horizontal, vertical);
			else if (entryPoint == 2)
				mayMoveUp(horizontal, vertical);
			else if (entryPoint == 3)
				moveRightAndMarkPosition(horizontal, vertical);
			else
				case350Method(horizontal, vertical);
		}
	}

	private static void case350Method(int horizontal, int vertical) {
		if ((!isWithinBottomEdge(vertical) && isZTrue())
				|| isWithinBottomEdge(vertical) && isAVisitedPosition(xCoordinate, yCoordinate + 1))
							case410Method(horizontal, vertical);
		else if (isWithinBottomEdge(vertical))
			case390Method(horizontal, vertical);
		else {
			assignQ(true);
			case390Method(horizontal, vertical);
		}
	}

	private static void case430Method(int horizontal, int vertical) {
		if (xCoordinate == horizontal || isAVisitedPosition(xCoordinate + 1, yCoordinate))
			case530Method(horizontal, vertical);
		else if (isWithinBottomEdge(vertical)) {
			if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
				case510Method(horizontal, vertical);
			else
				case490Method(horizontal, vertical);
		} else {
			if (isZTrue())
				case510Method(horizontal, vertical);
			else {
				assignQ(true);
				case490Method(horizontal, vertical);
			}
		}
	}

	private static void case720Method(int horizontal, int vertical) {
		if (isWithinBottomEdge(vertical)) {
			if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
				mayMoveUp(horizontal, vertical);
			else
				case760Method(horizontal, vertical);
		} else {
			if (isZTrue())
				mayMoveUp(horizontal, vertical);
			else {
				assignQ(true);
				case760Method(horizontal, vertical);
			}
		}
	}

	private static void case760Method(int horizontal, int vertical) {
		entryPoint = generateRandom(2);
		if (entryPoint == 2)
			case1090Movement(horizontal, vertical);
		else
			mayMoveUp(horizontal, vertical);
	}

	private static void case790Method(int horizontal, int vertical) {
		if (xCoordinate == horizontal || isAVisitedPosition(xCoordinate + 1, yCoordinate)) {
			if (isWithinBottomEdge(vertical)) {
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					mayMakeAMove(horizontal, vertical);
				else
					case1090Movement(horizontal, vertical);
			} else if (isZTrue()) {
				mayMakeAMove(horizontal, vertical);
			} else {
				assignQ(true);
				case1090Movement(horizontal, vertical);
			}
		} else if (isWithinBottomEdge(vertical)) {
			if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
				moveRightAndMarkPosition(horizontal, vertical);
			else {
				entryPoint = generateRandom(2);
				if (entryPoint == 2)
					case1090Movement(horizontal, vertical);
				else
					moveRightAndMarkPosition(horizontal, vertical);
			}
		} else {
			if (isZTrue())
				moveRightAndMarkPosition(horizontal, vertical);
			else {
				assignQ(true);
				mayMoveUpAndValidate(horizontal, vertical);
			}
		}
	}

	private static void case490Method(int horizontal, int vertical) {
		entryPoint = generateRandom(3);
		if (entryPoint == 1)
			case940Movement(horizontal, vertical);
		else if (entryPoint == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else if (entryPoint == 3)
			case1090Movement(horizontal, vertical);
		else
			case510Method(horizontal, vertical);
	}

	private static void case940Movement(int horizontal, int vertical) {
		markPositionAsVisited(xCoordinate - 1, yCoordinate);
		moveOneStep();
		verticalArrays[xCoordinate - 1][yCoordinate] = 2;
		moveLeft();
		mayValidatePostMovement(horizontal, vertical);
	}

	private static void case1090Movement(int horizontal, int vertical) {
		if (q) {
			assignZ(true);
			if (verticalArrays[xCoordinate][yCoordinate] == 0) {
				verticalArrays[xCoordinate][yCoordinate] = 1;
				assignQ(false);
				positionAtTopLeftCorner();
				makeAMoveIfNotVisited(horizontal, vertical);

			} else {
				verticalArrays[xCoordinate][yCoordinate] = 3;
				assignQ(false);
				mayMakeAMove(horizontal, vertical);
			}
		} else {
			markPositionAsVisited(xCoordinate, yCoordinate + 1);
			moveOneStep();
			if (verticalArrays[xCoordinate][yCoordinate] == 0) {
				verticalArrays[xCoordinate][yCoordinate] = 1;
			} else {
				verticalArrays[xCoordinate][yCoordinate] = 3;
			}
			moveDown();
			if (isAtEndPoint(vertical, horizontal))
				exitLoop();
			else
				mayValidateStartXPosition(xCoordinate, horizontal, vertical);

		}
	}

	private static void moveRightAndMarkPosition(int horizontal, int vertical) {
		markPositionAsVisited(xCoordinate + 1, yCoordinate);
		moveOneStep();
		if (verticalArrays[xCoordinate][yCoordinate] == 0) {
			verticalArrays[xCoordinate][yCoordinate] = 2;
		} else {
			verticalArrays[xCoordinate][yCoordinate] = 3;
		}
		moveRight();
		if (isAtEndPoint(horizontal, vertical))
			exitLoop();
		else
			case600Method(horizontal, vertical);
	}

	private static void mayMoveUp(int horizontal, int vertical) {
		markPositionAsVisited(xCoordinate, yCoordinate - 1);
		mayMoveUpAndValidate(horizontal, vertical);
	}

	private static boolean isZTrue() {
		return z;
	}

	private static void mayMoveUpAndValidate(int horizontal, int vertical) {
		moveOneStep();
		verticalArrays[xCoordinate][yCoordinate - 1] = 1;
		moveUp();
		mayValidatePostMovement(horizontal, vertical);
	}

	private static void makeAMoveIfNotVisited(int horizontal, int vertical) {
		if (!isAVisitedPosition(xCoordinate, yCoordinate))
			mayMakeAMove(horizontal, vertical);
		else
			mayValidateStartXPosition(xCoordinate, horizontal, vertical);
	}

	private static void mayMakeRandomCalculationForTwo(int horizontal, int vertical) {
		entryPoint = generateRandom(2);
		if (entryPoint == 1)
			mayMoveUp(horizontal, vertical);
		else if (entryPoint == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else
			case720Method(horizontal, vertical);
	}

	private static void mayMakeRandomCalculationForThree(int horizontal, int vertical) {
		entryPoint = generateRandom(3);
		if (entryPoint == 1)
			mayMoveUp(horizontal, vertical);
		else if (entryPoint == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else if (entryPoint == 3)
			case1090Movement(horizontal, vertical);
		else
			mayMakeRandomCalculationForTwo(horizontal, vertical);
	}

	private static void mayValidatePostMovement(int horizontal, int vertical) {
		if (isAtEndPoint(horizontal, vertical))
			exitLoop();
		else {
			assignQ(false);
			mayValidateStartXPosition(xCoordinate, horizontal, vertical);
		}
	}

	private static void assignZ(boolean zState) {
		z = zState;
	}

	private static void assignQ(boolean stateValue) {
		q = stateValue;
	}

	private static void initializeMaze(int horizontal, int vertical) {
		visitedPositionsArray = new boolean[horizontal + 1][vertical + 1];
		verticalArrays = maze.initializeArray(horizontal, vertical);

		assignQ(false);
		assignZ(false);
		entryPoint = generateRandom(horizontal);

		// 130:170
		maze.drawEntryForMaze(horizontal, entryPoint);

		currentPosition = 1;
		markPositionAsVisited(entryPoint, 1);
		moveOneStep();

		xCoordinate = entryPoint;
		yCoordinate = 1;
		mayValidateStartXPosition(xCoordinate, horizontal, vertical);
	}

	private static boolean isAVisitedPosition(int xCoordinate, int yCoordinate) {
		return visitedPositionsArray[xCoordinate][yCoordinate];
	}

	private static void markPositionAsVisited(int xCordinate, int yCoordinate) {
		visitedPositionsArray[xCordinate][yCoordinate] = true;
	}

	private static boolean isWithinBottomEdge(int vertical) {
		return yCoordinate != vertical;
	}

	private static int moveOneStep() {
		return currentPosition++;
	}

	private static void moveLeft() {
		xCoordinate--;
	}

	private static void moveUp() {
		yCoordinate--;
	}

	private static boolean isAtEndPoint(int horizontal, int vertical) {
		return currentPosition == horizontal * vertical + 1;
	}

	private static void mayMakeAMove(int horizontal, int vertical) {
		if (xCoordinate != horizontal) {
			moveRight();
		} else {
			if (isWithinBottomEdge(vertical)) {
				positionToExtremeLeft();
				moveDown();
			} else {
				positionAtTopLeftCorner();
			}
		}
		makeAMoveIfNotVisited(horizontal, vertical);
	}

	private static void positionToExtremeLeft() {
		xCoordinate = 1;
	}

	private static void moveDown() {
		yCoordinate++;
	}

	private static void moveRight() {
		xCoordinate++;
	}

	private static void positionAtTopLeftCorner() {
		positionToExtremeLeft();
		yCoordinate = 1;
	}

	private static void exitLoop() {
		target = -1;
	}

	private static void mayValidateStartXPosition(int xCoordinate, int horizontal, int vertical) {
		if (xCoordinate - 1 == 0)
			case600Method(horizontal, vertical);
		else
			case280Method(horizontal, vertical);
	}
}
