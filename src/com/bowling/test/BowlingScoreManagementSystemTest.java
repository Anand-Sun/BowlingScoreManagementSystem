/**
 * 
 */
package com.bowling.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Test;

import com.bowling.main.BowlingScoreManagementSystem;

import junit.framework.TestCase;

/**
 * Test Class for Bowling Score Management System
 *
 */
public class BowlingScoreManagementSystemTest extends TestCase {
	
	protected String score1;
	protected String score2;
	protected String score3;
	protected String score4;
	protected String score5;
	protected String score6;
	protected String score7;
	protected String score8;

    private ByteArrayInputStream inputData;
    private final InputStream sysIn = System.in;
    private final PrintStream sysOut = System.out;
    
	protected void setUp() {
		score1 = "X-X-X-X-X-X-X-X-X-XXX";
		score2 = "X-X-X-X-X-X-X-X-X-2/XX";
		score3 = "X-X-X-X-X-X-X-X-X-5/X7";
		score4 = "45-54-36-27-09-63-81-18-90-72";
		score5 = "5/-5/-5/-5/-5/-5/-5/-5/-5/-5/";
		score6 = "X-X-X-X-X-X-X-X-X-XXXX";
		score7 = "X-X-X-X-X-X-X-X-X-XXX7";
		score8 = "5/-5/-5/-5/-5/-5/-5/-5/-5/-5//8";
	}
	
    private void scoreInput(String data) {
    	inputData = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputData);
    }
    
	@After
    private void cleanUp(String data) {
        System.setIn(sysIn);
        System.setOut(sysOut);
    }
	
	@Test
	public void testPrintScore1() {	
		scoreInput(score1);
	    BowlingScoreManagementSystem.userInput();
		System.out.println(score1);
	    assertEquals(300, BowlingScoreManagementSystem.calculateFinalScore());

	}
	@Test
	public void testPrintScore2() {	
	    System.out.println();
		scoreInput(score2);
	    BowlingScoreManagementSystem.userInput();
		System.out.println(score2);
	    assertEquals(292, BowlingScoreManagementSystem.calculateFinalScore());
	}
	@Test
	public void testPrintScore3() {	
		System.out.println();
		scoreInput(score3);
	    BowlingScoreManagementSystem.userInput();
		System.out.println(score3);
	    assertEquals(295, BowlingScoreManagementSystem.calculateFinalScore());
	}
	@Test
	public void testPrintScore4() {	
		System.out.println();
		scoreInput(score4);
	    BowlingScoreManagementSystem.userInput();
		System.out.println(score4);
	    assertEquals(90, BowlingScoreManagementSystem.calculateFinalScore());
	}
	@Test
	public void testPrintScore5() {	
		System.out.println();
		scoreInput(score5);
	    BowlingScoreManagementSystem.userInput();
		System.out.println(score5);
	    assertEquals(150, BowlingScoreManagementSystem.calculateFinalScore());
	}
	
	@Test
	public void testPrintScore6() {	
		System.out.println();
		scoreInput(score6);
	    BowlingScoreManagementSystem.userInput();
		System.out.println(score6);
	    assertEquals(300, BowlingScoreManagementSystem.calculateFinalScore());
	}
	@Test
	public void testPrintScore7() {	
		System.out.println();
		scoreInput(score7);
	    BowlingScoreManagementSystem.userInput();
		System.out.println(score7);
	    assertEquals(300, BowlingScoreManagementSystem.calculateFinalScore());
	}
	@Test
	public void testPrintScore8() {	
		System.out.println();
		scoreInput(score8);
	    BowlingScoreManagementSystem.userInput();
		System.out.println(score8);
	    assertEquals(160, BowlingScoreManagementSystem.calculateFinalScore());
	}
}
