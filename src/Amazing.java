
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
	static StringBuffer result = new StringBuffer();
	private static int xCoordinate;
	private static int yCoordinate;
	private static boolean[][] visitedPositionsArray;
	private static int[][] verticalArrays;
	private static int q;
	private static int z;
	private static int entryPoint;
	private static int currentPosition;

	public static void main(String[] args) {
		generateMaze(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println(result);
	}

	private static void clear() {
		result.setLength(0);
	}

	private static void println() {
		result.append("\n");
	}

	public static void print(String text) {
		result.append(text);
	}

	public static int generateRandom(int count) {
		return (int) (count * random.nextFloat()) + 1;
	}

	public static void generateMaze(int horizontal, int vertical) {
		clear();
		print("Amazing - Copyright by Creative Computing, Morristown, NJ");
		println();

		if (isInvalidMazeDimension(horizontal, vertical))
			return;
		initializeArray(horizontal, vertical, new int[horizontal + 1][vertical + 1]);

		visitedPositionsArray = new boolean[horizontal + 1][vertical + 1];

		verticalArrays = initializeArray(horizontal, vertical);

		q = 0;
		z = 0;
		entryPoint = generateRandom(horizontal);

		// 130:170
		printingFirstLine(horizontal, entryPoint);

		currentPosition = 1;
		markPositionAsVisited(entryPoint, 1);
		moveOneStep();

		xCoordinate = entryPoint;
		yCoordinate = 1;
		mayValidateStartXPosition(xCoordinate);

		while (target != -1) {
			switch (target) {
			case 260:
				if (!isAVisitedPosition(xCoordinate, yCoordinate))
					mayMakeAMove(horizontal, vertical);
				else
					mayValidateStartXPosition(xCoordinate);
				continue;
			case 280:
				if (isAVisitedPosition(xCoordinate-1, yCoordinate ))
					target = 600;
				else
					target = 290;
				continue;
			case 290:
				if (yCoordinate - 1 == 0)
					target = 430;
				else
					target = 300;
				continue;
			case 300:
				if (isAVisitedPosition(xCoordinate, yCoordinate -1))
					target = 430;
				else
					target = 310;
				continue;
			case 310:
				if (xCoordinate == horizontal)
					target = 350;
				else
					target = 320;
				continue;
			case 320:
				if(isAVisitedPosition(xCoordinate+1, yCoordinate ))
					target = 350;
				else
					target = 330;
				continue;
			case 330:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					target = 980;
				else if (entryPoint == 3)
					target = 1020;
				else
					target = 350;
				continue;
			case 350:
				if (isWithinBottomEdge(vertical))
					target = 380;
				else
					target = 360;
				continue;
			case 360:
				if (z == 1)
					target = 410;
				else
					target = 370;
				continue;
			case 370:
				q = 1;
				target = 390;
				continue;
			case 380:
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					target = 410;
				else
					target = 390;
				continue;
			case 390:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					target = 980;
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
					target = 980;
				else
					target = 430;
				continue;
			case 430:
				if (xCoordinate == horizontal)
					target = 530;
				else
					target = 440;
				continue;
			case 440:
				if (isAVisitedPosition(xCoordinate+1, yCoordinate))					
					target = 530;
				else
					target = 450;
				continue;
			case 450:
				if (isWithinBottomEdge(vertical))
					target = 480;
				else
					target = 460;
				continue;
			case 460:
				if (z == 1)
					target = 510;
				else
					target = 470;
				continue;
			case 470:
				q = 1;
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
					target = 1020;
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
					target = 1020;
				else
					target = 530;
				continue;
			case 530:
				if (isWithinBottomEdge(vertical))
					target = 560;
				else
					target = 540;
				continue;
			case 540:
				if (z == 1)
					target = 940;
				else
					target = 550;
				continue;
			case 550:
				q = 1;
				target = 570;
				continue;
			case 560:
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					target = 940;
				else
					target = 570;
				continue;
			case 570:
				entryPoint = generateRandom(2);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					target = 1090;
				else
					target = 940;
				continue;
			case 600:
				if (yCoordinate - 1 == 0)
					target = 790;
				else
					target = 610;
				continue;
			case 610:
				if(isAVisitedPosition(xCoordinate, yCoordinate-1 ))
					target = 790;
				else
					target = 620;
				continue;
			case 620:
				if (xCoordinate == horizontal)
					target = 720;
				else{
					if(isAVisitedPosition(xCoordinate+1, yCoordinate ))
						target = 720;
					else
						target = 640;
				}
				continue;
			case 640:
				if (isWithinBottomEdge(vertical))
					target = 670;
				else
					target = 650;
				continue;
			case 650:
				if (z == 1)
					target = 700;
				else{
					q = 1;
					target = 680;
				}
				continue;
			case 670:
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					target = 700;
				else
					target = 680;
				continue;
			case 680:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 980;
				else if (entryPoint == 2)
					target = 1020;
				else if (entryPoint == 3)
					target = 1090;
				else
					target = 700;
				continue;
			case 700:
				entryPoint = generateRandom(2);
				if (entryPoint == 1)
					target = 980;
				else if (entryPoint == 2)
					target = 1020;
				else
					target = 720;
				continue;
			case 720:
				if (isWithinBottomEdge(vertical))
					target = 750;
				else
					target = 730;
				continue;
			case 730:
				if (z == 1)
					target = 980;
				else
					target = 740;
				continue;
			case 740:
				q = 1;
				target = 760;
				continue;
			case 750:
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					target = 980;
				else
					target = 760;
				continue;
			case 760:
				entryPoint = generateRandom(2);
				target = 770;
				continue;
			case 770:
				if (entryPoint == 1)
					target = 980;
				else if (entryPoint == 2)
					target = 1090;
				else
					target = 980;
				continue;
			case 790:
				if (xCoordinate == horizontal)
					target = 880;
				else
					target = 800;
				continue;
			case 800:
				if (isAVisitedPosition(xCoordinate+1, yCoordinate))
					target = 880;
				else
					target = 810;
				continue;
			case 810:
				if (isWithinBottomEdge(vertical))
					target = 840;
				else
					target = 820;
				continue;
			case 820:
				if (z == 1)
					target = 1020;
				else
					target = 830;
				continue;
			case 830:
				q = 1;
				target = 990;
				continue;
			case 840:
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					target = 1020;
				else
					target = 850;
				continue;
			case 850:
				entryPoint = generateRandom(2);
				if (entryPoint == 2)
					target = 1090;
				else
					target = 1020;
				continue;
			case 880:
				if (isWithinBottomEdge(vertical))
					target = 910;
				else{
					if (z == 1)
						mayMakeAMove(horizontal, vertical);
					else{
						q = 1;
						target = 1090;
					}
				}
				continue;
			case 910:
				if (isAVisitedPosition(xCoordinate, yCoordinate + 1))
					mayMakeAMove(horizontal, vertical);
				else
					target = 1090;
				continue;
			case 940:
				markPositionAsVisited(xCoordinate-1, yCoordinate);
				moveOneStep();
				verticalArrays[xCoordinate - 1][yCoordinate] = 2;
				moveLeft();
				if (isAtEndPoint(horizontal, vertical))
					exitLoop();
				else{
					q = 0;
					mayValidateStartXPosition(xCoordinate);
				}
				continue;
			case 980:
				markPositionAsVisited(xCoordinate, yCoordinate-1);
				target = 990;
				continue;
			case 990:
				moveOneStep();
				verticalArrays[xCoordinate][yCoordinate - 1] = 1;
				moveUp();
				if (isAtEndPoint(horizontal, vertical))
					exitLoop();
				else{
					q = 0;
					mayValidateStartXPosition(xCoordinate);
				}
				continue;
			case 1020:
				markPositionAsVisited(xCoordinate+1, yCoordinate);
				moveOneStep();
				if (verticalArrays[xCoordinate][yCoordinate] == 0){
					verticalArrays[xCoordinate][yCoordinate] = 2;
				}else{
					verticalArrays[xCoordinate][yCoordinate] = 3;
				}
				moveRight();
				if (isAtEndPoint(horizontal, vertical))
					exitLoop();
				else
					target = 600;
				continue;
			case 1090:
				if (q == 1){
					z = 1;
					if (verticalArrays[xCoordinate][yCoordinate] == 0){
						verticalArrays[xCoordinate][yCoordinate] = 1;
						q = 0;
						positionAtTopLeftCorner();
						target = 260;
	
					}else
						target = 1170;
				}else{
					markPositionAsVisited(xCoordinate, yCoordinate+1);
					moveOneStep();
					if (verticalArrays[xCoordinate][yCoordinate] == 0){
						verticalArrays[xCoordinate][yCoordinate] = 1;
					}else{
						verticalArrays[xCoordinate][yCoordinate] = 3;
					}
					target = 1130;
				}
				continue;
			case 1130:
				moveDown();
				if (isAtEndPoint(vertical, horizontal))
					exitLoop();
				else
					mayValidateStartXPosition(xCoordinate);
				continue;
			case 1170:
				verticalArrays[xCoordinate][yCoordinate] = 3;
				q = 0;
				mayMakeAMove(horizontal, vertical);
				continue;
			}

		}

		drawMaze(horizontal, vertical, verticalArrays);
	}

	private static boolean isAVisitedPosition(int xCoordinate, int yCoordinate) {
		return visitedPositionsArray[xCoordinate][yCoordinate] ;
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
		if (xCoordinate != horizontal){
			moveRight();
		}else{
			if (isWithinBottomEdge(vertical)){
				positionToExtremeLeft();
				moveDown();
			}else{
				positionAtTopLeftCorner();
			}
		}
		target = 260;
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

	private static void drawMaze(int horizontal, int vertical, int[][] verticalArray) {
		for (int j = 1; j <= vertical; j++) {
			drawCorridors(horizontal, verticalArray, j);
			drawPath(horizontal, verticalArray, j);
		}
	}

	private static void drawPath(int horizontal, int[][] verticalArray, int j) {
		for (int i = 1; i <= horizontal; i++) {
			if (verticalArray[i][j] == 0 || verticalArray[i][j] == 2)
				draweBottomWall();
			else 
				drawRightWall();
		}

		print(":"); // 1360
		println();
	}

	private static void drawRightWall() {
		print(":  "); // 1320
	}

	private static void draweBottomWall() {
		print(":--");
	}

	private static void drawCorridors(int horizontal, int[][] verticalArray, int j) {
		print("I"); // 1210

		for (int i = 1; i <= horizontal; i++) {
			if (verticalArray[i][j] >= 2)
				leavePathOpen();
			else
				print("  I"); // 1260
		}

		print(" "); // 1280
		println();
	}

	private static void leavePathOpen() {
		print("   "); // 1240
	}

	private static boolean isInvalidMazeDimension(int horizontal, int vertical) {
		return horizontal == 1 || vertical == 1;
	}

	private static void printingFirstLine(int h, int x) {
		for (int i = 1; i <= h; i++) {
			if (i == x)
				drawRightWall();
			else
				draweBottomWall();
		}
		// 180
		print(":");
		println();
	}

	private static int[][] initializeArray(int h, int v) {
		int[][] verticalArray = new int[h + 1][v + 1];
		initializeArray(h, v, verticalArray);
		return verticalArray;
	}

	private static void initializeArray(int h, int v, int[][] horizontalArray) {
		for (int i = 0; i <= h; i++) {
			horizontalArray[i] = new int[v + 1];
		}
	}
}
