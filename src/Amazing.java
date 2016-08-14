
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
	 private static final int CLOSED = 0;
	int target = 0; // where GOTO goes
	public  Random random = new Random(0);
	private  int xCoordinate;
	private  int yCoordinate;
	private  boolean q;
	private  boolean z;
	int entryPoint;
	private  int currentPosition;
	 Maze maze;

	public static void main(String[] args) {
		Amazing amazing=new Amazing();
		amazing.generateMaze(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println(amazing.maze.toString());
	}

	public  int generateRandom(int count) {
		return (int) (count * random.nextFloat()) + 1;
	}

	public  void generateMaze(int width, int height) {
		maze = new Maze(width,height);
		if (maze.isInvalidMazeDimension())
			return;
		initializeMaze(width, height);
	}


	private  void case600Method(int horizontal, int vertical) {
		if (yCoordinate - 1 == 0 || maze.isAVisitedPosition(xCoordinate, yCoordinate - 1)) {
			case790Method(horizontal, vertical);
		} else if (xCoordinate == horizontal || maze.isAVisitedPosition(xCoordinate + 1, yCoordinate))
			case720Method(horizontal, vertical);
		else if ((isZTrue() && !isWithinBottomEdge(vertical))
				|| isWithinBottomEdge(vertical) && maze.isAVisitedPosition(xCoordinate, yCoordinate + 1))
			mayMakeRandomCalculationForTwo(horizontal, vertical);
		else if (isWithinBottomEdge(vertical)) {
			mayMakeRandomCalculationForThree(horizontal, vertical);
		} else {
			assignQ(true);
			mayMakeRandomCalculationForThree(horizontal, vertical);
		}
	}

	private  void case530Method(int horizontal, int vertical) {
		if (isWithinBottomEdge(vertical) && maze.isAVisitedPosition(xCoordinate, yCoordinate + 1)
				|| !isWithinBottomEdge(vertical) && isZTrue()) {
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

	private  void case390Method(int horizontal, int vertical) {
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

	private  void case410Method(int horizontal, int vertical) {
		entryPoint = generateRandom(2);
		if (entryPoint == 1)
			case940Movement(horizontal, vertical);
		else if (entryPoint == 2)
			mayMoveUp(horizontal, vertical);
		else
			case430Method(horizontal, vertical);
	}

	private  void case430Method(int horizontal, int vertical) {
		if (xCoordinate == horizontal || maze.isAVisitedPosition(xCoordinate + 1, yCoordinate))
			case530Method(horizontal, vertical);
		else if (isWithinBottomEdge(vertical) && maze.isAVisitedPosition(xCoordinate, yCoordinate + 1)
				|| !isWithinBottomEdge(vertical) && isZTrue())
			case510Method(horizontal, vertical);
		else {
			if (!isWithinBottomEdge(vertical))
				assignQ(true);

			case490Method(horizontal, vertical);
		}
	}

	private  void case490Method(int horizontal, int vertical) {
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

	private  void case510Method(int horizontal, int vertical) {
		entryPoint = generateRandom(2);
		if (entryPoint == 1)
			case940Movement(horizontal, vertical);
		else if (entryPoint == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else
			case530Method(horizontal, vertical);
	}

	private  void case940Movement(int horizontal, int vertical) {
		maze.markPositionAsVisited(xCoordinate - 1, yCoordinate);
		incrementCurrentPosition();
		maze.getMaze()[xCoordinate - 1][yCoordinate] = 2;
		moveLeft();
		mayValidatePostMovement(horizontal, vertical);
	}

	
	
	private  void case280Method(int horizontal, int vertical) {
		if (maze.isAVisitedPosition(xCoordinate - 1, yCoordinate))
			case600Method(horizontal, vertical);
		else if (yCoordinate - 1 == 0 || maze.isAVisitedPosition(xCoordinate, yCoordinate - 1))
			case430Method(horizontal, vertical);
		else if (xCoordinate == horizontal || maze.isAVisitedPosition(xCoordinate + 1, yCoordinate)) {
			case350Method(horizontal, vertical);
		} else {
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

	private  void case350Method(int horizontal, int vertical) {
		if ((!isWithinBottomEdge(vertical) && isZTrue())
				|| isWithinBottomEdge(vertical) && maze.isAVisitedPosition(xCoordinate, yCoordinate + 1))
			case410Method(horizontal, vertical);
		else if (isWithinBottomEdge(vertical))
			case390Method(horizontal, vertical);
		else {
			assignQ(true);
			case390Method(horizontal, vertical);
		}
	}



	private  void case720Method(int horizontal, int vertical) {
		if (isWithinBottomEdge(vertical)) {
			if (maze.isAVisitedPosition(xCoordinate, yCoordinate + 1))
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

	private  void case760Method(int horizontal, int vertical) {
		entryPoint = generateRandom(2);
		if (entryPoint == 2)
			case1090Movement(horizontal, vertical);
		else
			mayMoveUp(horizontal, vertical);
	}

	private  void case790Method(int horizontal, int vertical) {
		if (xCoordinate == horizontal || maze.isAVisitedPosition(xCoordinate + 1, yCoordinate)) {
			if (isWithinBottomEdge(vertical)) {
				if (maze.isAVisitedPosition(xCoordinate, yCoordinate + 1))
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
			if (maze.isAVisitedPosition(xCoordinate, yCoordinate + 1))
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


	private  void case1090Movement(int horizontal, int vertical) {
		if (q) {
			assignZ(true);
			if (maze.getMaze()[xCoordinate][yCoordinate] == CLOSED) {
				maze.getMaze()[xCoordinate][yCoordinate] = 1;
				assignQ(false);
				positionAtTopLeftCorner();
				makeAMoveIfNotVisited(horizontal, vertical);

			} else {
				maze.getMaze()[xCoordinate][yCoordinate] = 3;
				assignQ(false);
				mayMakeAMove(horizontal, vertical);
			}
		} else {
			maze.markPositionAsVisited(xCoordinate, yCoordinate + 1);
			incrementCurrentPosition();
			if (maze.getMaze()[xCoordinate][yCoordinate] == 0) {
				maze.getMaze()[xCoordinate][yCoordinate] = 1;
			} else {
				maze.getMaze()[xCoordinate][yCoordinate] = 3;
			}
			moveDown();
			if (!isAtEndPoint(vertical, horizontal)) {
				makeAMove(xCoordinate, horizontal, vertical);
			}
		}
	}

	private  void moveRightAndMarkPosition(int horizontal, int vertical) {
		maze.markPositionAsVisited(xCoordinate + 1, yCoordinate);
		incrementCurrentPosition();
		if (maze.getMaze()[xCoordinate][yCoordinate] == 0) {
			maze.getMaze()[xCoordinate][yCoordinate] = 2;
		} else {
			maze.getMaze()[xCoordinate][yCoordinate] = 3;
		}
		moveRight();
		if (!isAtEndPoint(horizontal, vertical))
			case600Method(horizontal, vertical);
	}

	private  void mayMoveUp(int horizontal, int vertical) {
		maze.markPositionAsVisited(xCoordinate, yCoordinate - 1);
		mayMoveUpAndValidate(horizontal, vertical);
	}

	private  boolean isZTrue() {
		return z;
	}

	private  void mayMoveUpAndValidate(int horizontal, int vertical) {
		incrementCurrentPosition();
		maze.getMaze()[xCoordinate][yCoordinate - 1] = 1;
		moveUp();
		mayValidatePostMovement(horizontal, vertical);
	}

	private  void makeAMoveIfNotVisited(int horizontal, int vertical) {
		if (!maze.isAVisitedPosition(xCoordinate, yCoordinate))
			mayMakeAMove(horizontal, vertical);
		else
			makeAMove(xCoordinate, horizontal, vertical);
	}

	private  void mayMakeRandomCalculationForTwo(int horizontal, int vertical) {
		entryPoint = generateRandom(2);
		if (entryPoint == 1)
			mayMoveUp(horizontal, vertical);
		else if (entryPoint == 2)
			moveRightAndMarkPosition(horizontal, vertical);
		else
			case720Method(horizontal, vertical);
	}

	private  void mayMakeRandomCalculationForThree(int horizontal, int vertical) {
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

	private  void mayValidatePostMovement(int horizontal, int vertical) {
		if (!isAtEndPoint(horizontal, vertical)) {
			assignQ(false);
			makeAMove(xCoordinate, horizontal, vertical);
		}
	}

	private  void assignZ(boolean zState) {
		z = zState;
	}

	private  void assignQ(boolean stateValue) {
		q = stateValue;
	}

	private  void initializeMaze(int width, int height) {

		assignQ(false);
		assignZ(false);
		
		entryPoint = generateRandom(width);
		maze.setEntryPoint(entryPoint);
		maze.markPositionAsVisited(entryPoint, 1);
		
		currentPosition = 1;
		incrementCurrentPosition();
		xCoordinate = entryPoint;
		yCoordinate = 1;

		makeAMove(xCoordinate, width, height);
	}

	private  boolean isWithinBottomEdge(int vertical) {
		return yCoordinate != vertical;
	}

	private  int incrementCurrentPosition() {
		return currentPosition++;
	}

	private  void moveLeft() {
		xCoordinate--;
	}

	private  void moveUp() {
		yCoordinate--;
	}

	private  boolean isAtEndPoint(int horizontal, int vertical) {
		return currentPosition == horizontal * vertical + 1;
	}

	private  void mayMakeAMove(int horizontal, int vertical) {
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

	private  void positionToExtremeLeft() {
		xCoordinate = 1;
	}

	private  void moveDown() {
		yCoordinate++;
	}

	private  void moveRight() {
		xCoordinate++;
	}

	private  void positionAtTopLeftCorner() {
		positionToExtremeLeft();
		yCoordinate = 1;
	}

	private  void makeAMove(int xCoordinate, int horizontal, int vertical) {
		if (xCoordinate - 1 == 0)
			case600Method(horizontal, vertical);
		else
			case280Method(horizontal, vertical);
	}
}
