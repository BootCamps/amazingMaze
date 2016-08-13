
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
	private static int[][] horizontalArrays;
	private static int[][] verticalArrays;
	private static int q;
	private static int z;
	private static int entryPoint;
	private static int c;

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

		horizontalArrays = initializeArray(horizontal, vertical);

		verticalArrays = initializeArray(horizontal, vertical);

		q = 0;
		z = 0;
		entryPoint = generateRandom(horizontal);

		// 130:170
		printingFirstLine(horizontal, entryPoint);

		c = 1;
		horizontalArrays[entryPoint][1] = c;
		c++;

		xCoordinate = entryPoint;
		yCoordinate = 1;
		mayValidateStartXPosition(xCoordinate);

		while (target != -1) {
			switch (target) {
			case 210:
				if (xCoordinate != horizontal){
					xCoordinate++;
				}else{
					if (yCoordinate != vertical){
						xCoordinate = 1;
						yCoordinate++;
					}else{
						xCoordinate = 1;
						yCoordinate = 1;
					}
				}
				target = 260;
				continue;
			case 260:
				if (horizontalArrays[xCoordinate][yCoordinate] == 0)
					target = 210;
				else
					mayValidateStartXPosition(xCoordinate);
				continue;
			case 280:
				if (horizontalArrays[xCoordinate - 1][yCoordinate] != 0)
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
				if (horizontalArrays[xCoordinate][yCoordinate - 1] != 0)
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
				if (horizontalArrays[xCoordinate + 1][yCoordinate] != 0)
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
				if (yCoordinate != vertical)
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
				if (horizontalArrays[xCoordinate][yCoordinate + 1] != 0)
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
				if (horizontalArrays[xCoordinate + 1][yCoordinate] != 0)
					target = 530;
				else
					target = 450;
				continue;
			case 450:
				if (yCoordinate != vertical)
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
				if (horizontalArrays[xCoordinate][yCoordinate + 1] != 0)
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
				if (yCoordinate != vertical)
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
				if (horizontalArrays[xCoordinate][yCoordinate + 1] != 0)
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
				if (horizontalArrays[xCoordinate][yCoordinate - 1] != 0)
					target = 790;
				else
					target = 620;
				continue;
			case 620:
				if (xCoordinate == horizontal)
					target = 720;
				else{
					if (horizontalArrays[xCoordinate + 1][yCoordinate] != 0)
						target = 720;
					else
						target = 640;
				}
				continue;
			case 640:
				if (yCoordinate != vertical)
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
				if (horizontalArrays[xCoordinate][yCoordinate + 1] != 0)
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
				if (yCoordinate != vertical)
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
				if (horizontalArrays[xCoordinate][yCoordinate + 1] != 0)
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
				if (horizontalArrays[xCoordinate + 1][yCoordinate] != 0)
					target = 880;
				else
					target = 810;
				continue;
			case 810:
				if (yCoordinate != vertical)
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
				if (horizontalArrays[xCoordinate][yCoordinate + 1] != 0)
					target = 1020;
				else
					target = 850;
				continue;
			case 850:
				entryPoint = generateRandom(2);
				if (entryPoint == 1)
					target = 1020;
				else if (entryPoint == 2)
					target = 1090;
				else
					target = 1020;
				continue;
			case 880:
				if (yCoordinate != vertical)
					target = 910;
				else{
					if (z == 1)
						target = 210;
					else{
						q = 1;
						target = 1090;
					}
				}
				continue;
			case 910:
				if (horizontalArrays[xCoordinate][yCoordinate + 1] != 0)
					target = 210;
				else
					target = 1090;
				continue;
			case 940:
				horizontalArrays[xCoordinate - 1][yCoordinate] = c;
				c++;
				verticalArrays[xCoordinate - 1][yCoordinate] = 2;
				xCoordinate--;
				if (c == horizontal * vertical + 1)
					exitLoop();
				else{
					q = 0;
					mayValidateStartXPosition(xCoordinate);
				}
				continue;
			case 980:
				horizontalArrays[xCoordinate][yCoordinate - 1] = c;
				target = 990;
				continue;
			case 990:
				c++;
				verticalArrays[xCoordinate][yCoordinate - 1] = 1;
				yCoordinate--;
				if (c == horizontal * vertical + 1)
					exitLoop();
				else{
					q = 0;
					mayValidateStartXPosition(xCoordinate);
				}
				continue;
			case 1020:
				horizontalArrays[xCoordinate + 1][yCoordinate] = c;
				c++;
				if (verticalArrays[xCoordinate][yCoordinate] == 0)
					target = 1050;
				else
					target = 1040;
				continue;
			case 1040:
				verticalArrays[xCoordinate][yCoordinate] = 3;
				target = 1060;
				continue;
			case 1050:
				verticalArrays[xCoordinate][yCoordinate] = 2;
				target = 1060;
				continue;
			case 1060:
				xCoordinate++;
				if (c == horizontal * vertical + 1)
					exitLoop();
				else
					target = 600;
				continue;
			case 1090:
				if (q == 1){
					z = 1;
					target = 1160;
				}else{
					horizontalArrays[xCoordinate][yCoordinate + 1] = c;
					c++;
					if (verticalArrays[xCoordinate][yCoordinate] == 0){
						verticalArrays[xCoordinate][yCoordinate] = 1;
					}else{
						verticalArrays[xCoordinate][yCoordinate] = 3;
					}
					target = 1130;
				}
				continue;
			case 1130:
				yCoordinate++;
				if (c == vertical * horizontal + 1)
					exitLoop();
				else
					mayValidateStartXPosition(xCoordinate);
				continue;
			case 1160:
				if (verticalArrays[xCoordinate][yCoordinate] == 0)
					target = 1180;
				else
					target = 1170;
				continue;
			case 1170:
				verticalArrays[xCoordinate][yCoordinate] = 3;
				q = 0;
				target = 210;
				continue;
			case 1180:
				verticalArrays[xCoordinate][yCoordinate] = 1;
				q = 0;
				xCoordinate = 1;
				yCoordinate = 1;
				target = 260;
				continue;
			}

		}

		drawMaze(horizontal, vertical, verticalArrays);
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
			drawWall(horizontal, verticalArray, j);
			drawPath(horizontal, verticalArray, j);
		}
	}

	private static void drawPath(int horizontal, int[][] verticalArray, int j) {
		for (int i = 1; i <= horizontal; i++) {
			if (verticalArray[i][j] == 0 || verticalArray[i][j] == 2)
				print(":--"); // 1300, 1340
			else
				print(":  "); // 1320
		}

		print(":"); // 1360
		println();
	}

	private static void drawWall(int horizontal, int[][] verticalArray, int j) {
		print("I"); // 1210

		for (int i = 1; i <= horizontal; i++) {
			if (verticalArray[i][j] >= 2)
				print("   "); // 1240
			else
				print("  I"); // 1260
		}

		print(" "); // 1280
		println();
	}

	private static boolean isInvalidMazeDimension(int horizontal, int vertical) {
		return horizontal == 1 || vertical == 1;
	}

	private static void printingFirstLine(int h, int x) {
		for (int i = 1; i <= h; i++) {
			if (i == x)
				print(":  ");
			else
				print(":--");
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
