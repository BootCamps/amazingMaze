
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
				target = 260;
				if (xCoordinate != horizontal) {
					xCoordinate++;
				} else {
					xCoordinate = 1;
					if (yCoordinate != vertical) {
						yCoordinate++;
					} else {
						yCoordinate = 1;
					}
				}
				continue;
			case 260:
				if (horizontalArray[xCoordinate][yCoordinate] == 0)
					target = 210;
				else
					target = 270;
				continue;
			case 270:
				if (xCoordinate - 1 == 0)
					target = 600;
				else
					target = 280;
				continue;
			case 280:
				if (horizontalArray[xCoordinate - 1][yCoordinate] != 0)
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
				if (horizontalArray[xCoordinate][yCoordinate - 1] != 0)
					target = 430;
				else {
					if (xCoordinate == horizontal || horizontalArray[xCoordinate + 1][yCoordinate] != 0)
						target = 350;
					else {
						target = 330;
					}
				}
				continue;
			case 330:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					target = 980;
				else
					target = 1020;
				continue;
			case 350:
				if (yCoordinate != vertical) {
					if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
						target = 410;
					else
						target = 390;
				} else {
					if (z == 1)
						target = 410;
					else {
						q = 1;
						target = 390;
					}
				}
				continue;
			case 390:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					target = 980;
				else
					target = 1090;
				continue;
			case 410:
				entryPoint = generateRandom(2);
				if (entryPoint == 1)
					target = 940;
				else
					target = 980;
				continue;
			case 430:
				if (xCoordinate == horizontal || horizontalArray[xCoordinate + 1][yCoordinate] != 0)
					target = 530;
				else
					target = 450;
				continue;
			case 450:
				if (yCoordinate != vertical) {
					if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
						target = 510;
					else
						target = 490;
				} else {
					if (z == 1)
						target = 510;
					else {
						q = 1;
						target = 490;
					}
				}
				continue;

			case 490:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 940;
				else if (entryPoint == 2)
					target = 1020;
				else
					target = 1090;
				continue;
			case 510:
				entryPoint = generateRandom(2);
				if (entryPoint == 1)
					target = 940;
				else
					target = 1020;
				continue;
			case 530:
				if (yCoordinate != vertical)
					target = 560;
				else {
					if (z == 1)
						target = 940;
					else {
						q = 1;
						target = 570;
					}
				}
				continue;
			case 560:
				if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
					target = 940;
				else
					target = 570;
				continue;
			case 570:
				entryPoint = generateRandom(2);
				if (entryPoint == 2)
					target = 1090;
				else
					target = 940;
				continue;
			case 600:
				if (yCoordinate - 1 == 0)
					target = 790;
				else {
					if (horizontalArray[xCoordinate][yCoordinate - 1] != 0)
						target = 790;
					else {
						if (xCoordinate == horizontal)
							target = 720;
						else {
							if (horizontalArray[xCoordinate + 1][yCoordinate] != 0)
								target = 720;
							else {
								if (yCoordinate != vertical) {
									if (horizontalArray[xCoordinate][yCoordinate + 1] != 0) {
										entryPoint = generateRandom(2);
										if (entryPoint == 1)
											target = 980;
										else
											target = 1020;
									}
									else
										target = 680;
								} else {
									if (z == 1) {
										entryPoint = generateRandom(2);
										if (entryPoint == 1)
											target = 980;
										else
											target = 1020;
									}
									else {
										q = 1;
										target = 680;
									}
								}
							}
						}
					}
				}
				continue;
			case 680:
				entryPoint = generateRandom(3);
				if (entryPoint == 1)
					target = 980;
				else if (entryPoint == 2)
					target = 1020;
				else
					target = 1090;
				continue;
			case 720:
				if (yCoordinate != vertical)
					target = 750;
				else {
					if (z == 1)
						target = 980;
					else {
						q = 1;
						entryPoint = generateRandom(2);
						if (entryPoint == 1)
							target = 980;
						else
							target = 1090;
					}
				}
				continue;
			case 750:
				if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
					target = 980;
				else {
					entryPoint = generateRandom(2);
					if (entryPoint == 1)
						target = 980;
					else
						target = 1090;
				}
				continue;
			case 790:
				if (xCoordinate == horizontal || horizontalArray[xCoordinate + 1][yCoordinate] != 0) {
					if (yCoordinate != vertical) {
						if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
							target = 210;
						else
							target = 1090;
					}
						
					else  {
						if (z == 1)
							target = 210;
						else{
							q = 1;
							target = 1090;
						}
					}
				} else {
					if (yCoordinate != vertical)
						if (horizontalArray[xCoordinate][yCoordinate + 1] != 0)
							target = 1020;
						else{
							entryPoint = generateRandom(2);
							if (entryPoint == 2)
								target = 1090;
							else
								target = 1020;
						}
					else {
						if (z == 1)
							target = 1020;
						else {
							q = 1;
							c++;
							verticalArray[xCoordinate][yCoordinate - 1] = 1;
							yCoordinate--;
							q = checkExitStep(horizontal, vertical, q, c);
						}
					}
				}
				continue;
			case 940:
				horizontalArray[xCoordinate - 1][yCoordinate] = c;
				c++;
				verticalArray[xCoordinate - 1][yCoordinate] = 2;
				xCoordinate--;
				q = checkExitStep(horizontal, vertical, q, c);
				continue;
			case 980:
				horizontalArray[xCoordinate][yCoordinate - 1] = c;
				c++;
				verticalArray[xCoordinate][yCoordinate - 1] = 1;
				yCoordinate--;
				q = checkExitStep(horizontal, vertical, q, c);
				continue;
			case 1020:
				horizontalArray[xCoordinate + 1][yCoordinate] = c;
				c++;
				if (verticalArray[xCoordinate][yCoordinate] == 0) {
					verticalArray[xCoordinate][yCoordinate] = 2;
				} else {
					verticalArray[xCoordinate][yCoordinate] = 3;
				}
				xCoordinate++;
				if (c == horizontal * vertical + 1)
					target = -1;
				else
					target = 600;
				continue;
			case 1090:
				if (q == 1) {
					z = 1;
					q = 0;
					if (verticalArray[xCoordinate][yCoordinate] == 0) {
						verticalArray[xCoordinate][yCoordinate] = 1;
						xCoordinate = 1;
						yCoordinate = 1;
						target = 260;

					} else {
						verticalArray[xCoordinate][yCoordinate] = 3;
						target = 210;
					}
				} else {

					horizontalArray[xCoordinate][yCoordinate + 1] = c;
					c++;
					if (verticalArray[xCoordinate][yCoordinate] == 0) {
						verticalArray[xCoordinate][yCoordinate] = 1;
					} else {
						verticalArray[xCoordinate][yCoordinate] = 3;
					}
					yCoordinate++;
					if (c == vertical * horizontal + 1)
						target = -1;
					else
						target = 270;
				}
				continue;
			}

		}

		// 1200:
		for (int j = 1; j <= vertical; j++) {
			drawWall(horizontal, verticalArray, j);
			drawPath(horizontal, verticalArray, j);
		}
	}

	private static int checkExitStep(int horizontal, int vertical, int q, int c) {
		if (c == horizontal * vertical + 1)
			target = -1;
		else {
			q = 0;
			target = 270;
		}
		return q;
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
