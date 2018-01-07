package com.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.fileupload.cfg.NextDateProblem;

public class NextDateTest {

	@Test
	public void testGetNextDate() {
		NextDateProblem nextDateProblem = new NextDateProblem();
		assertEquals("1992/1/1", nextDateProblem.getNextDate("1991/12/31"));
		assertEquals("1991/2/22", nextDateProblem.getNextDate("1991/02/21"));
		assertEquals("1991/3/1", nextDateProblem.getNextDate("1991/02/28"));
		assertEquals("1992/2/29", nextDateProblem.getNextDate("1992/02/28"));
		assertEquals("1992/7/1", nextDateProblem.getNextDate("1992/06/30"));
		assertEquals("Invalid date. Day must be smaller than 31 !", nextDateProblem.getNextDate("1991/06/31"));
		assertEquals("Invalid date format !!!", nextDateProblem.getNextDate("1992/76/30"));
		assertEquals("Invalid date format !!!", nextDateProblem.getNextDate("1992/06/38"));	
		assertEquals("Invalid date format !!!", nextDateProblem.getNextDate("1992/06/-1"));	
		assertEquals("Invalid date format !!!", nextDateProblem.getNextDate("0/06/0"));	
	}

}
