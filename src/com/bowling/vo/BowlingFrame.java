package com.bowling.vo;

import java.util.Arrays;

public class BowlingFrame {

	public BowlingFrame(int frame, String userFrameScoreInput) {
		super();
		this.frame = frame;
		this.userFrameScoreInput = userFrameScoreInput;
	}

	boolean strike = false;
	boolean spare = false;
	public int totalScore = 0;
	public int frameScore = 0;
	public int frame = 0;
	public String userFrameScoreInput;
	public int noOfTries =0;
	public int[] noOfthrows ;

	/**
	 * @return the userFrameScoreInput
	 */
	public String getUserFrameScoreInput() {
		return userFrameScoreInput;
	}

	/**
	 * @param userFrameScoreInput the userFrameScoreInput to set
	 */
	public void setUserFrameScoreInput(String userFrameScoreInput) {
		this.userFrameScoreInput = userFrameScoreInput;
	}

	/**
	 * @return the strike
	 */
	public boolean isStrike() {
		return strike;
	}

	/**
	 * @param strike the strike to set
	 */
	public void setStrike(boolean strike) {
		this.strike = strike;
	}

	/**
	 * @return the spare
	 */
	public boolean isSpare() {
		return spare;
	}

	/**
	 * @param spare the spare to set
	 */
	public void setSpare(boolean spare) {
		this.spare = spare;
	}

	/**
	 * @return the totalScore
	 */
	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * @param totalScore the totalScore to set
	 */
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * @return the frameScore
	 */
	public int getFrameScore() {
		return frameScore;
	}

	/**
	 * @param frameScore the frameScore to set
	 */
	public void setFrameScore(String frameScore) {
		if (frameScore != null && frameScore.length() <= 2) {
			noOfTries = frameScore.length();
			noOfthrows = new int[noOfTries];
			this.frameScore = getFrameScoreCalc(frameScore);
		} else {
			noOfTries = frameScore.length();
			noOfthrows = new int[noOfTries];
			this.frameScore = getFrameScoreCalc(frameScore);			
		}
	}
	
	@SuppressWarnings("unused")
	public int getFrameScoreCalc(String frameScore) {
		int score = 0;

			for (int throwCount = 0; throwCount < frameScore.length(); throwCount++) {
				char charc = frameScore.charAt(throwCount);
				if(charc=='X' || charc =='x') {
					score=10;
					noOfthrows[throwCount] = 10;
				} else if(charc=='/') {
					score=10;
					noOfthrows[throwCount] = 10;
				}else {
					score = score+ Character.getNumericValue(charc);
					noOfthrows[throwCount] = Character.getNumericValue(charc);
				}
			}
			return score;
	}
	
	@Override
	public String toString() {
		return "BowlingFrame [strike=" + strike + ", spare=" + spare + ", totalScore=" + totalScore + ", frameScore="
				+ frameScore + ", frame=" + frame + ", userFrameScoreInput=" + userFrameScoreInput + ", noOfTries="
				+ noOfTries + ", noOfthrows=" + Arrays.toString(noOfthrows) + "]";
	}
}
