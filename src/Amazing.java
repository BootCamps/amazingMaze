
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

	int getRandomDirection(int paramValue) {
		return generateRandom(paramValue);
	}

	public void generateMaze(int width, int height) {
		maze = new Maze(width, height);
		if (maze.isInvalidMazeDimension())
			return;
		initializeMaze(width, height);
		initiateAMove(xCoordinate, width, height);
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

	private void makeAMoveWhenAllDirectionsAreRestricted(int width, int height) {
		direction = getRandomDirection(3);
		if (direction == 1){
			moveUp(width, height);
			makeNextMove(width, height);
		}else if (direction == 2){
			breakWallAndMoveRight(width, height);
		}else{
			makeAMove(width, height);
		}
	}

	private void makeAMoveWhenRightWallIsPresent(int width, int height) {
		direction = getRandomDirection(2);
		if (direction == 1)
			breakWallAndMoveRight(width, height);
		else
			makeAMove(width, height);
	}

	private void makeAMoveWhenRightMoveAloneIsRestricted(int width, int height) {
		direction = getRandomDirection(2);
		if (direction == 1){
			moveUp(width, height);
			makeNextMove(width, height);
		}else{
			makeAMove(width, height);
		}
	}

	private void makeAMoveWhenRightMoveIsRestricted(int width, int height) {
		direction = getRandomDirection(3);
		if (direction == LEFT_DIRECTION){
			moveLeft(width, height);
			makeNextMove(width, height);
		}else if (direction == 2){
			moveUp(width, height);
			makeNextMove(width, height);
		}else
			makeAMove(width, height);
	}

	private void makeAMoveWhenRightAndLeftMoveIsRestricted(int width, int height) {
		if ( isDownMovementRestricted(height)){
			moveUp(width, height);
			makeNextMove(width, height);
		}else {
			validateIfAtBottomRow(height);
			makeAMoveWhenRightMoveAloneIsRestricted(width, height);
		}
	}

	private void makeAMoveWhenRightMoveAloneIsPossible(int width, int height) {
		direction = getRandomDirection(2);
		if (direction == LEFT_DIRECTION){
			moveLeft(width, height);
			makeNextMove(width, height);
		}else {
			breakWallAndMoveRight(width, height);
		}
	
	}

	private void makeAMoveWhenUpMoveIsRestricted(int width, int height) {
		if (isRightMoveRestricted(width)) {
			if (isDownMovementRestricted(height)) {
				findNextMove(width, height);
			} else {
				validateIfAtBottomRow(height);
				makeAMove(width, height);
			}
		} else if (isBottomRowAlreadyVisited(height)){
			breakWallAndMoveRight(width, height);
		} else if(!isAtBottomEdge(height)){
			makeAMoveWhenRightWallIsPresent(width, height);
		} else if (blocked) {
			breakWallAndMoveRight(width, height);
		} else {
			moveUp(width, height);
			makeNextMove(width, height);
		}
	}

	private void makeAMoveWhenLeftAndUpMoveIsRestricted(int width, int height) {
		direction = getRandomDirection(3);
		if (direction == LEFT_DIRECTION){
			moveLeft(width, height);
			makeNextMove(width, height);
		}else if (direction == 2)
			breakWallAndMoveRight(width, height);
		else
			makeAMove(width, height);

	}

	private void makeAMoveWhenDownMovementIsRestricted(int width, int height) {
		direction = getRandomDirection(3);
		if (direction == LEFT_DIRECTION){
			moveLeft(width, height);
			makeNextMove(width, height);
		}else if (direction == 2){
			moveUp(width, height);
			makeNextMove(width, height);
		}else
			breakWallAndMoveRight(width, height);
	}

	private void makeAMoveWhenDownMoveAloneIsPossible(int width, int height) {
		direction = getRandomDirection(2);
		if (direction == LEFT_DIRECTION){
			moveLeft(width, height);
			makeNextMove(width, height);
		}else{
			makeAMove(width, height);
		}
	}

	private void makeAMoveWhenRightAndDownMoveIsRestricted(int width, int height) {
		direction = getRandomDirection(2);
		if (direction == LEFT_DIRECTION){
			moveLeft(width, height);
			makeNextMove(width, height);
		}else if (direction == 2){
			moveUp(width, height);
			makeNextMove(width, height);
		}else
			makeAMove(width, height);
	}

	private void makeAMoveWhenDownMoveIsRestricted(int width, int height) {
		direction = getRandomDirection(2);
		if (direction == 1){
			moveUp(width, height);
			makeNextMove(width, height);
		}else{
			breakWallAndMoveRight(width, height);
		} 
	}

	private void validateIfAtBottomRow(int height) {
		if (isAtBottomEdge(height)) {
			reachedBottomRow = true;
		}
	}

	private boolean isTopRowAlreadyVisited() {
		return maze.isAVisitedPosition(xCoordinate - 1, yCoordinate);
	}

	private boolean isBottomRowAlreadyVisited(int height) {
		return !isAtBottomEdge(height) && maze.isAVisitedPosition(xCoordinate, yCoordinate + 1);
	}

	private boolean isUpMoveRestricted() {
		return isAtTheTopEdge() || maze.isAVisitedPosition(xCoordinate, yCoordinate - 1);
	}

	private boolean isRightMoveRestricted(int width) {
		return isAtRightEdge(width) || maze.isAVisitedPosition(xCoordinate + 1, yCoordinate);
	}

	private boolean isDownMovementRestricted(int height) {
		return isBottomRowAlreadyVisited(height) || (isAtBottomEdge(height) && blocked);
	}

	private void moveDown() {
		maze.markPositionAsVisited(xCoordinate, yCoordinate + 1);
		openWall(RIGHT_WALL);
		goDown();
		incrementCurrentPosition();
	}

	private void moveRight() {
		maze.markPositionAsVisited(xCoordinate + 1, yCoordinate);
		openWall(BOTTOM_WALL);
		goRight();
		incrementCurrentPosition();
	}

	private void moveUp(int width, int height) {
		maze.markPositionAsVisited(xCoordinate, yCoordinate - 1);
		makeWall(xCoordinate, yCoordinate-1, RIGHT_WALL);
		goUp();
		incrementCurrentPosition();
	}

	private void moveLeft(int width, int height) {
		maze.markPositionAsVisited(xCoordinate - 1, yCoordinate);
		makeWall(xCoordinate-1, yCoordinate, BOTTOM_WALL);
		goLeft();
		incrementCurrentPosition();
	}

	private void openWall(int keepWall) {
		if (maze.getMaze()[xCoordinate][yCoordinate] == CLOSE) {
			maze.getMaze()[xCoordinate][yCoordinate] = keepWall;
		} else {
			maze.getMaze()[xCoordinate][yCoordinate] = OPEN;
		}
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
			initiateAMove(xCoordinate, width, height);
	}

	private void makeNextMove(int width, int height) {
		
		if(!isAtBottomEdge(height)){
			reachedBottomRow = false;
		}
		
		if (!isAtExitPoint(width, height)) {
			initiateAMove(xCoordinate, width, height);
		}
	}

	private void makeAMove(int width, int height) {
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

	private void initiateAMove(int xCoordinate, int width, int height) {
		if (isAtLeftEdge() || isTopRowAlreadyVisited()) {
			if (isUpMoveRestricted()) {
				makeAMoveWhenUpMoveIsRestricted(width, height);
			} else if (isRightMoveRestricted(width)){
				makeAMoveWhenRightAndLeftMoveIsRestricted(width, height);
			}else if (isDownMovementRestricted(height)){
				makeAMoveWhenDownMoveIsRestricted(width, height);
			}else {
				validateIfAtBottomRow(height);
				makeAMoveWhenAllDirectionsAreRestricted(width, height);
			}
		} else if (isUpMoveRestricted()) {
			if (isRightMoveRestricted(width)){
				if (isDownMovementRestricted(height)) {
					moveLeft(width, height);
					makeNextMove(width, height);
				} else {
					validateIfAtBottomRow(height);
					makeAMoveWhenDownMoveAloneIsPossible(width, height);
				}
			}else if (isDownMovementRestricted(height)){
				makeAMoveWhenRightMoveAloneIsPossible(width, height);
			}else {
				validateIfAtBottomRow(height);
				makeAMoveWhenLeftAndUpMoveIsRestricted(width, height);
			}
		} else if (isRightMoveRestricted(width)) {
			if (isDownMovementRestricted(height)) {
				makeAMoveWhenRightAndDownMoveIsRestricted(width, height);
			} else {
				validateIfAtBottomRow(height);
				makeAMoveWhenRightMoveIsRestricted(width, height);
			}
		} else {
			makeAMoveWhenDownMovementIsRestricted(width, height);
		}
	}

	private void breakWallAndMoveRight(int width, int height) {
		moveRight();
		if (!isAtExitPoint(width, height))
			if (isUpMoveRestricted()) {
				makeAMoveWhenUpMoveIsRestricted(width, height);
			} else if (isRightMoveRestricted(width)){
				makeAMoveWhenRightAndLeftMoveIsRestricted(width, height);
			}else if (isDownMovementRestricted(height)){
				makeAMoveWhenDownMoveIsRestricted(width, height);
			}else {
				validateIfAtBottomRow(height);
				makeAMoveWhenAllDirectionsAreRestricted(width, height);
			}
	}

	private void makeWall(int xCoordinate, int yCoordinate, int wallToBuild) {
		maze.getMaze()[xCoordinate][yCoordinate] = wallToBuild;
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

	private boolean isAtLeftEdge() {
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
