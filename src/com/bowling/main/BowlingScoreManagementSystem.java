package com.bowling.main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.bowling.vo.BowlingFrame;

/*
 * Main Class for Bowling Score Management System
 */
public class BowlingScoreManagementSystem {

	static Logger logger = Logger.getLogger(BowlingScoreManagementSystem.class.getName());

	public final static int STRIKE_SCORE = 10;
	public final static int SPARE_SCORE = 10;
	public final static int MAX_SCORE = 10;

	private final static String DELIMITER = "-";
	private static String[] scores;
	private static Scanner scanScore = null;
	//static HashMap<Integer, String> hm = new HashMap<Integer, String>();
	static ArrayList<BowlingFrame> frameArray = new ArrayList<BowlingFrame>();

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

			for (int i = 0; i < scores.length; i++) {
				// hm.put(Integer.valueOf(i), scores[i]);
				BowlingFrame frame = new BowlingFrame(i, scores[i]);
				if (scores[i] != null && scores[i].indexOf("X")!=-1) {
					frame.setStrike(true);
				} 
				if (scores[i] != null && scores[i].indexOf("/")!=-1) {
					frame.setSpare(true);
				}
				frame.setFrameScore(scores[i]);
				frameArray.add(Integer.valueOf(i), frame);
			//	hm.put(Integer.valueOf(i), scores[i]);
			}
		} else {
			logger.severe("Please Enter Input format: " + "X-X-X-X-X-X-X-X-X-XXX (or) 45-54-36-27- (or) 5/-5/-5/");
		}
	}
	
	public static int calculateFinalScore() {
		int totalScore = 0;
		for (int i = 0; i <= 9; i++) {
			BowlingFrame currentFrame = frameArray.get(i);
			if (i == 0) {//first frame
				if (!currentFrame.isStrike() && !currentFrame.isStrike()) {
					currentFrame.totalScore = currentFrame.getFrameScore();
				}
				if (currentFrame.isStrike()) {
					if (frameArray.get(i + 1).isStrike()) {
						currentFrame.totalScore = currentFrame.getFrameScore() + frameArray.get(i + 1).getFrameScore()
								+ frameArray.get(i + 2).noOfthrows[0];
					} else {
						currentFrame.totalScore = currentFrame.getFrameScore() + frameArray.get(i + 1).noOfthrows[0];
					}
				}
				if (currentFrame.isSpare()) {
					currentFrame.totalScore = currentFrame.getFrameScore() + frameArray.get(i + 1).noOfthrows[0];
				}
				
				//System.out.println("currentFrame:" + i + "  " + currentFrame.toString());
			} else if(i==9) {
                if (currentFrame.isSpare()) {
                    currentFrame.totalScore = frameArray.get(i - 1).totalScore +  10 + frameArray.get(i).noOfthrows[2];
                } 
                else if (currentFrame.isStrike()) {
                    currentFrame.totalScore = frameArray.get(i - 1).totalScore +  frameArray.get(i).noOfthrows[0]+frameArray.get(i).noOfthrows[1] + frameArray.get(i).noOfthrows[2];
                }
                else{
                	currentFrame.totalScore = frameArray.get(i - 1).totalScore +  frameArray.get(i).noOfthrows[0]+ frameArray.get(i).noOfthrows[1] ;
                }	
				//System.out.println("currentFrame:" + i + "  " + currentFrame.toString());
				totalScore = totalScore+currentFrame.totalScore;
				System.out.println("totalScore:" + i + "  " + totalScore);

			}
				else {
			
                if (!currentFrame.isStrike() && !currentFrame.isSpare()) {
                    currentFrame.totalScore = currentFrame.getFrameScore() + frameArray.get(i - 1).totalScore;
                }
                if (currentFrame.isStrike() && i != 8) {
                    if (frameArray.get(i + 1).isStrike()) {
                        currentFrame.totalScore = frameArray.get(i - 1).totalScore + currentFrame.getFrameScore() + frameArray.get(i + 1).getFrameScore() + frameArray.get(i + 2).noOfthrows[0];
                    } else {

                    		currentFrame.totalScore = frameArray.get(i - 1).totalScore + currentFrame.getFrameScore() + frameArray.get(i + 1).getFrameScore();
                    }
                }   
                if (currentFrame.isStrike() && currentFrame.isSpare() && i == 8) {
                    currentFrame.totalScore = currentFrame.getFrameScore() + frameArray.get(i - 1).totalScore;
                }
                else if (currentFrame.isStrike() && i == 8) {
                	  if (frameArray.get(i + 1).isSpare()) {
                		  currentFrame.totalScore = frameArray.get(i - 1).totalScore + currentFrame.getFrameScore() + 10;
                	  }                     	if (frameArray.get(i + 1).isSpare()) {//check if 10 frame has a spare
                          currentFrame.totalScore = frameArray.get(i - 1).totalScore + currentFrame.getFrameScore() + 10;
                  	}
                	  else {
                		  currentFrame.totalScore = frameArray.get(i - 1).totalScore + currentFrame.getFrameScore() + frameArray.get(i + 1).noOfthrows[0]+ frameArray.get(i + 1).noOfthrows[1];
                	  }
                }
                if (currentFrame.isSpare()) {
                    currentFrame.totalScore = frameArray.get(i - 1).totalScore + currentFrame.getFrameScore() + frameArray.get(i + 1).noOfthrows[0];
                }
				//System.out.println("currentFrame:" + i + "  " + currentFrame.toString());
			}

		}

		return totalScore;

	}
}
