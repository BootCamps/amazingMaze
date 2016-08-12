
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

	public static void main(String[] args) {
		drawMaze(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
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

	public static void drawMaze(int horizontal, int vertical) {
		clear();
		print("Amazing - Copyright by Creative Computing, Morristown, NJ");
		println();

		if (isInvalidMazeDimension(horizontal, vertical))
			return;

		int[][] horizontalArray = initializeArray(horizontal, vertical);

		int[][] verticalArray = initializeArray(horizontal, vertical);

		int q = 0;
		int z = 0;
		int entryPoint = generateRandom(horizontal);

		// 130:170
		printingFirstLine(horizontal, entryPoint);

		// 190
		int c = 1;
		horizontalArray[entryPoint][1] = c;
		c++;

		// 200
		int xCoordinate = entryPoint;
		int yCoordinate = 1;
		target = 270;

		while (target != -1) {
			switch (target) {
			case 210:
				if (xCoordinate != horizontal)
					target = 250;
				else
					target = 220;
				continue;
			case 220:
				if (yCoordinate != vertical)
					target = 240;
				else
					target = 230;
				continue;
			case 230:
				xCoordinate = 1;
				yCoordinate = 1;
				target = 260;
				continue;
			case 240:
				xCoordinate = 1;
				yCoordinate++;
				target = 260;
				continue;
			case 250:
				xCoordinate++;
				target = 260;
				continue;
			case 260:
				if (horizontalArray[xCoordinate][yCoordinate] == 0)
					target = 210;
				else
					target = 270;
				continue;
			case 270:
				startingXPoint(xCoordinate);
				continue;
			case 280:
				if (horizontalArray[xCoordinate - 1][yCoordinate] != 0)
					target = 600;
				else
					startingYPoint(yCoordinate);
				continue;
			case 300:
				if (horizontalArray[xCoordinate][yCoordinate - 1] != 0)
					target = 430;
				else {
					if (xCoordinate == horizontal)
						target = 350;
					else
						target = 320;
				}
				continue;
			case 320:
				if (horizontalArray[xCoordinate + 1][yCoordinate] != 0)
					target = 350;
				else
					target = 330;
				continue;
			case 330:
				entryPoint = generateRandom(3);
				target = 340;
				continue;
			case 340:
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
				if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
					target = 410;
				else
					target = 390;
				continue;
			case 390:
				entryPoint = generateRandom(3);
				target = 400;
				continue;
			case 400:
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
				target = 420;
				continue;
			case 420:
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
				if (horizontalArray[xCoordinate + 1][yCoordinate] != 0)
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
				if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
					target = 510;
				else
					target = 490;
				continue;
			case 490:
				entryPoint = generateRandom(3);
				target = 500;
				continue;
			case 500:
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
				target = 520;
				continue;
			case 520:
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
					target = 590;
				else
					target = 550;
				continue;
			case 550:
				q = 1;
				target = 570;
				continue;
			case 560:
				if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
					target = 590;
				else
					target = 570;
				continue;
			case 570:
				entryPoint = generateRandom(2);
				target = 580;
				continue;
			case 580:
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					target = 1090;
				else
					target = 590;
				continue;
			case 590:
				target = 940;
				continue;
			case 600:
				if (yCoordinate - 1 == 0)
					target = 790;
				else
					target = 610;
				continue;
			case 610:
				if (horizontalArray[xCoordinate][yCoordinate - 1] != 0)
					target = 790;
				else
					target = 620;
				continue;
			case 620:
				if (xCoordinate == horizontal)
					target = 720;
				else
					target = 630;
				continue;
			case 630:
				if (horizontalArray[xCoordinate + 1][yCoordinate] != 0)
					target = 720;
				else
					target = 640;
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
				else
					target = 660;
				continue;
			case 660:
				q = 1;
				target = 680;
				continue;
			case 670:
				if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
					target = 700;
				else
					target = 680;
				continue;
			case 680:
				entryPoint = generateRandom(3);
				target = 690;
				continue;
			case 690:
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
				target = 710;
				continue;
			case 710:
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
					target = 780;
				else
					target = 740;
				continue;
			case 740:
				q = 1;
				target = 760;
				continue;
			case 750:
				if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
					target = 780;
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
					target = 780;
				continue;
			case 780:
				target = 980;
				continue;
			case 790:
				if (xCoordinate == horizontal)
					target = 880;
				else
					target = 800;
				continue;
			case 800:
				if (horizontalArray[xCoordinate + 1][yCoordinate] != 0)
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
					target = 870;
				else
					target = 830;
				continue;
			case 830:
				q = 1;
				target = 990;
				continue;
			case 840:
				if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
					target = 870;
				else
					target = 850;
				continue;
			case 850:
				entryPoint = generateRandom(2);
				target = 860;
				continue;
			case 860:
				if (entryPoint == 1)
					target = 1020;
				else if (entryPoint == 2)
					target = 1090;
				else
					target = 870;
				continue;
			case 870:
				target = 1020;
				continue;
			case 880:
				if (yCoordinate != vertical)
					target = 910;
				else
					target = 890;
				continue;
			case 890:
				if (z == 1)
					target = 930;
				else
					target = 900;
				continue;
			case 900:
				q = 1;
				target = 920;
				continue;
			case 910:
				if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
					target = 930;
				else
					target = 920;
				continue;
			case 920:
				target = 1090;
				continue;
			case 930:
				target = 1190;
				continue;
			case 940:
				horizontalArray[xCoordinate - 1][yCoordinate] = c;
				target = 950;
				continue;
			case 950:
				c++;
				verticalArray[xCoordinate - 1][yCoordinate] = 2;
				xCoordinate--;
				target = 960;
				continue;
			case 960:
				if (c == horizontal * vertical + 1)
					target = 1200;
				else
					target = 970;
				continue;
			case 970:
				q = 0;
				target = 270;
				continue;
			case 980:
				horizontalArray[xCoordinate][yCoordinate - 1] = c;
				target = 990;
				continue;
			case 990:
				c++;
				target = 1000;
				continue;
			case 1000:
				verticalArray[xCoordinate][yCoordinate - 1] = 1;
				yCoordinate--;
				if (c == horizontal * vertical + 1)
					target = 1200;
				else
					target = 1010;
				continue;
			case 1010:
				q = 0;
				target = 270;
				continue;
			case 1020:
				horizontalArray[xCoordinate + 1][yCoordinate] = c;
				target = 1030;
				continue;
			case 1030:
				c++;
				if (verticalArray[xCoordinate][yCoordinate] == 0)
					target = 1050;
				else
					target = 1040;
				continue;
			case 1040:
				verticalArray[xCoordinate][yCoordinate] = 3;
				target = 1060;
				continue;
			case 1050:
				verticalArray[xCoordinate][yCoordinate] = 2;
				target = 1060;
				continue;
			case 1060:
				xCoordinate++;
				target = 1070;
				continue;
			case 1070:
				if (c == horizontal * vertical + 1)
					target = 1200;
				else
					target = 1080;
				continue;
			case 1080:
				target = 600;
				continue;
			case 1090:
				if (q == 1)
					target = 1150;
				else
					target = 1100;
				continue;
			case 1100:
				horizontalArray[xCoordinate][yCoordinate + 1] = c;
				c++;
				if (verticalArray[xCoordinate][yCoordinate] == 0)
					target = 1120;
				else
					target = 1110;
				continue;
			case 1110:
				verticalArray[xCoordinate][yCoordinate] = 3;
				target = 1130;
				continue;
			case 1120:
				verticalArray[xCoordinate][yCoordinate] = 1;
				target = 1130;
				continue;
			case 1130:
				yCoordinate++;
				if (c == vertical * horizontal + 1)
					target = 1200;
				else
					target = 1140;
				continue;
			case 1140:
				target = 270;
				continue;
			case 1150:
				z = 1;
				target = 1160;
				continue;
			case 1160:
				if (verticalArray[xCoordinate][yCoordinate] == 0)
					target = 1180;
				else
					target = 1170;
				continue;
			case 1170:
				verticalArray[xCoordinate][yCoordinate] = 3;
				q = 0;
				target = 1190;
				continue;
			case 1180:
				verticalArray[xCoordinate][yCoordinate] = 1;
				q = 0;
				xCoordinate = 1;
				yCoordinate = 1;
				target = 260;
				continue;
			case 1190:
				target = 210;
				continue;
			case 1200:
				target = -1;
				continue;
			}

		}

		// 1200:
		for (int j = 1; j <= vertical; j++) {
			drawWall(horizontal, verticalArray, j);
			drawPath(horizontal, verticalArray, j);
		}
	}

	private static void startingYPoint(int yCoordinate) {
		if (yCoordinate - 1 == 0)
			target = 430;
		else
			target = 300;
	}

	private static void startingXPoint(int xCoordinate) {
		if (xCoordinate - 1 == 0)
			target = 600;
		else
			target = 280;
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
