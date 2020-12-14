package com.bowling.main;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/*
 * Main Class for Bowling Score Management System
 */
public class BowlingScoreManagementSystem {

	static Logger logger = Logger.getLogger(BowlingScoreManagementSystem.class.getName());

	public final static int STRIKE_SCORE = 10;
	public final static int SPARE_SCORE = 10;
	private final static String DELIMITER = "-";
	private static String[] scores;
	private static Scanner scanScore = null;
	private static  final String BONUS_ADDITIONAL_THROW_MSG = "Additional bonus throw(s) are not available for a strike/knocking all pins in bonus throw(s)";
	public static void main(String[] args) {
		userInput();
		calculateFinalScore();
	}

	public static void userInput() {
		scanScore = new Scanner(System.in);
		System.out.println("Please enter your bowling score:");
		String inputScore = scanScore.nextLine();
		if (inputScore != null && inputScore.indexOf(DELIMITER) != -1) {
			Pattern pattern = Pattern.compile(DELIMITER);
			scores = pattern.split(inputScore);
		} else {
			logger.severe("Please Enter Input format: " + "X-X-X-X-X-X-X-X-X-XXX (or) 45-54-36-27- (or) 5/-5/-5/");
		}
	}

	public static int calculateFinalScore() {
		int frameCount = 0;
		int scoreCount = 0;
		boolean spareBonus = false;
		boolean spareCheck = false;
		if (scores != null) {
			for (String score : scores) {
				frameCount++;
				for (int throwCount = 0; throwCount < score.length(); throwCount++) {
					char charc = score.charAt(throwCount);
					if (charc == 'X' || charc == 'x') {
						// user gets bonus throw, but check if bonus throw has been thrown, if not goto
						// else clause
						if (frameCount == 10 && score.length() > 1) {
							if (spareBonus || throwCount ==3) {
								logger.warning(BONUS_ADDITIONAL_THROW_MSG);
								break;
							}
							if (spareCheck) {
								spareBonus = true;
							}
							scoreCount = scoreCount + STRIKE_SCORE;
						} else {
							scoreCount = scoreCount + getStrikeScore();
							break;
						}
					} else if (charc == '/') { // Spare check
						if (frameCount == 10) {
							spareCheck = true;
							scoreCount = scoreCount + SPARE_SCORE;
						} else {
							scoreCount = scoreCount + SPARE_SCORE;
							break;
						}
					} else {
						if (spareBonus || throwCount ==3) {
							logger.warning(BONUS_ADDITIONAL_THROW_MSG);
							break;
						}
						scoreCount = scoreCount + Character.getNumericValue(charc);
					}
				}
			}
			logger.info("Final Score is: " + scoreCount);
		}
		return scoreCount;

	}

	/**
	 * @return the strikeScore
	 */
	public static int getStrikeScore() {
		return STRIKE_SCORE + 10 + 10;
	}
	

}
