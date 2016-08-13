
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
		maze=new Maze();
		maze.drawHeader();
		if (maze.isInvalidMazeDimension(horizontal, vertical))
			return;

		initializeMaze(horizontal, vertical);

		while (target != -1) {
			switch (target) {
			case 280:
				if (isAVisitedPosition(xCoordinate - 1, yCoordinate))
					target = 600;
				else {
					if (yCoordinate - 1 == 0)
						target = 430;
					else
						target = 300;
				}
				continue;
			case 300:
				if (isAVisitedPosition(xCoordinate, yCoordinate - 1))
					target = 430;
				else {
					if (xCoordinate == horizontal || isAVisitedPosition(xCoordinate + 1, yCoordinate))
							target = 350;
					else
							target = 330;
				}
				continue;
			case 330:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					mayMoveUp(horizontal, vertical);
				else if (entryPoint == 3)
					moveRightAndMarkPosition(horizontal, vertical);
				else
					target = 350;
				continue;
			case 350:
				if ((!isWithinBottomEdge(vertical) && isZTrue()) || isWithinBottomEdge(vertical) && isAVisitedPosition(xCoordinate, yCoordinate + 1))
						target = 410;
				else if(isWithinBottomEdge(vertical))
						target = 390;
				else{
					assignQ(true);
					target = 390;
				}
				continue;
			case 390:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					mayMoveUp(horizontal, vertical);
				else if (entryPoint == 3)
					target = 1090;
				else
					target = 410;
				continue;
			case 410:
				entryPoint = generateRandom(2);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					mayMoveUp(horizontal, vertical);
				else
					target = 430;
				continue;
			case 430:
				if (xCoordinate == horizontal || isAVisitedPosition(xCoordinate + 1, yCoordinate))
					target = 530;
				else if (isWithinBottomEdge(vertical))
					target = 480;
				else{
					if (isZTrue())
						target = 510;
					else
						target = 470;
				}
				continue;
			case 470:
				assignQ(true);
				target = 490;
				continue;
			case 480:
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					target = 510;
				else
					target = 490;
				continue;
			case 490:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					moveRightAndMarkPosition(horizontal, vertical);
				else if (entryPoint == 3)
					target = 1090;
				else
					target = 510;
				continue;
			case 510:
				entryPoint = generateRandom(2);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					moveRightAndMarkPosition(horizontal, vertical);
				else
					target = 530;
				continue;
			case 530:
				if (isWithinBottomEdge(vertical) && isAVisitedPosition(xCoordinate, yCoordinate + 1)){
					target = 940;
				}else if(isWithinBottomEdge(vertical)){
					target = 570;
				}else if (!isWithinBottomEdge(vertical) && isZTrue()){
						target = 940;
				}else{
						assignQ(true);
						target = 570;
				}
				continue;
			case 570:
				entryPoint = generateRandom(2);
				if (entryPoint == 2)
					target = 1090;
				else
					target = 940;
				continue;
			case 600:
				if (yCoordinate - 1 == 0 || isAVisitedPosition(xCoordinate, yCoordinate - 1)) {
					target = 790;
				} else if (xCoordinate == horizontal || isAVisitedPosition(xCoordinate + 1, yCoordinate))
					target = 720;
				else if ((isZTrue() && !isWithinBottomEdge(vertical))
						|| isWithinBottomEdge(vertical) && isAVisitedPosition(xCoordinate, yCoordinate + 1))
					mayMakeRandomCalculationForTwo(horizontal, vertical);
				else if (isWithinBottomEdge(vertical)) {
					mayMakeRandomCalculationForThree(horizontal, vertical);
				} else {
					assignQ(true);
					mayMakeRandomCalculationForThree(horizontal, vertical);
				}
				continue;
			case 720:
				if (isWithinBottomEdge(vertical))
					target = 750;
				else {
					if (isZTrue())
						mayMoveUp(horizontal, vertical);
					else
						target = 740;
				}
				continue;
			case 740:
				assignQ(true);
				target = 760;
				continue;
			case 750:
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					mayMoveUp(horizontal, vertical);
				else
					target = 760;
				continue;
			case 760:
				entryPoint = generateRandom(2);
				if (entryPoint == 2)
					target = 1090;
				else
					mayMoveUp(horizontal, vertical);
				continue;
			case 790:
				if (xCoordinate == horizontal || isAVisitedPosition(xCoordinate + 1, yCoordinate)) {
					target = 880;
				} else if (isWithinBottomEdge(vertical))
					target = 840;
				else {
					if (isZTrue())
						moveRightAndMarkPosition(horizontal, vertical);
					else {
						assignQ(true);
						mayMoveUpAndValidate(horizontal, vertical);
					}
				}
				continue;
			case 840:
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					moveRightAndMarkPosition(horizontal, vertical);
				else {
					entryPoint = generateRandom(2);
					if (entryPoint == 2)
						target = 1090;
					else
						moveRightAndMarkPosition(horizontal, vertical);
				}
				continue;
			case 880:
				if (isWithinBottomEdge(vertical)){
					if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
						mayMakeAMove(horizontal, vertical);
					else
						target = 1090;
				}else  if (isZTrue()){
						mayMakeAMove(horizontal, vertical);
				}else {
					assignQ(true);
					target = 1090;
				}
				continue;
			case 940:
				markPositionAsVisited(xCoordinate - 1, yCoordinate);
				moveOneStep();
				verticalArrays[xCoordinate - 1][yCoordinate] = 2;
				moveLeft();
				mayValidatePostMovement(horizontal, vertical);
				continue;
			case 1090:
				if (q ) {
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
						mayValidateStartXPosition(xCoordinate);

				}
				continue;
			}

		}

		maze.drawMaze(horizontal, vertical, verticalArrays);
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
			target = 600;
	}

	private static void mayMoveUp(int horizontal, int vertical) {
		markPositionAsVisited(xCoordinate, yCoordinate - 1);
		mayMoveUpAndValidate(horizontal, vertical);
	}

	private static boolean isZTrue() {
		return z ;
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
			mayValidateStartXPosition(xCoordinate);
	}

	private static void mayMakeRandomCalculationForTwo(int horizontal, int vertical) {
		entryPoint = generateRandom(2);
		if (entryPoint == 1)
			mayMoveUp(horizontal, vertical);
		else if (entryPoint == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else
			target = 720;
	}

	private static void mayMakeRandomCalculationForThree(int horizontal, int vertical) {
		entryPoint = generateRandom(3);
		if (entryPoint == 1)
			mayMoveUp(horizontal, vertical);
		else if (entryPoint == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else if (entryPoint == 3)
			target = 1090;
		else
			mayMakeRandomCalculationForTwo(horizontal, vertical);
	}

	private static void mayValidatePostMovement(int horizontal, int vertical) {
		if (isAtEndPoint(horizontal, vertical))
			exitLoop();
		else {
			assignQ(false);
			mayValidateStartXPosition(xCoordinate);
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
		mayValidateStartXPosition(xCoordinate);
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

	private static void mayValidateStartXPosition(int xCoordinate) {
		if (xCoordinate - 1 == 0)
			target = 600;
		else
			target = 280;
	}
}
