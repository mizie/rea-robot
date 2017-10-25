package com.rea.toyrobot.simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class Simulator {

	public static final String EXIT_COMMAND = "exit";
	public static final int BOARD_SIZE_X = 5;
	public static final int BOARD_SIZE_Y = 5;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Toy Robot Simulator '" + EXIT_COMMAND + "' to quit");
		System.out.println("Commands :");
		System.out.println("PLACE X,Y,F --> int:x (0 -" + BOARD_SIZE_X + ") and int:y (0 -" + BOARD_SIZE_Y
				+ "), F : {NORTH|EAST|SOUTH|WEST}");
		System.out.println("MOVE");
		System.out.println("LEFT");
		System.out.println("RIGHT");
		System.out.println("REPORT");

		/*
		 * create a new board
		 */
		Board theBoard = new Board(BOARD_SIZE_X, BOARD_SIZE_Y);

		/*
		 * Create a new robot
		 */
		Robot theRobot = new Robot(theBoard);

		while (true) {
			System.out.print("> ");
			String input = br.readLine().trim();
			System.out.println(input);

			if (input.startsWith("PLACE")) {
				/*
				 * int x = 0; int y = 0; String facing = Robot.DIRECTION_NORTH;
				 */

				int xNum = -1, yNum = -1;
				String facing = null;

				String[] tempInput = input.split(" ");
				if (tempInput.length == 2) {
					String commInput = tempInput[0];
					String commParam = tempInput[1];

					if (!commInput.equals("PLACE")) {
						System.out.println("I don't find PLACE command");
					} else {
						/*
						 * split the input
						 */
						String[] parts = commParam.split(",");
						if (parts.length == 3) {
							String x = parts[0];
							String y = parts[1];
							facing = parts[2];

							if (isNumeric(x)) {
								xNum = Integer.parseInt(x);
							}

							if (isNumeric(y)) {
								yNum = Integer.parseInt(y);
							}
						} else {
							System.out.println("I don't find a valid command");
						}
					}
				} else {
					System.out.println("I don't find a valid command");
				}

				/**
				 * negative check
				 */
				if (xNum < 0 || yNum < 0) {
					System.out.println("x,y is not integer");
				}
				else if (xNum > BOARD_SIZE_X || yNum > BOARD_SIZE_Y) {
					System.out
							.println("i can't place the robot, please set x <=" + BOARD_SIZE_X + " Y<=" + BOARD_SIZE_Y);
				}
				else if (theRobot.isValidCommand(facing)==false) {
					System.out.println("i can't find the valid F value");
				} else {

					if (theRobot.place(xNum, yNum, facing)) {
						System.out.println("Success");
					} else {
						System.out.println("Failed");
					}
				}

			}

			else if (input.startsWith("MOVE")) {
				if (theRobot.move()) {
					System.out.println("Success");
				} else {
					System.out.println("Failed");
				}
			}

			else if (input.startsWith("LEFT")) {
				theRobot.rotateLeft();
			}

			else if (input.startsWith("RIGHT")) {
				theRobot.rotateRight();
			}

			else if (input.startsWith("REPORT")) {
				System.out.println(theRobot.report());
			}

			else if (input.length() == EXIT_COMMAND.length() && input.toLowerCase().equals(EXIT_COMMAND)) {
				System.out.println("Exiting.");
				return;
			} else {
				System.out.println("I don't find a valid command");
			}

		}
	}

	public static boolean isNumeric(String str) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
}
