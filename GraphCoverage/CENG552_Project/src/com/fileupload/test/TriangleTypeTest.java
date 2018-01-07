package com.fileupload.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.fileupload.cfg.TriangleTypeProblem;

public class TriangleTypeTest {

	@Test
	public void testInvalid1(){
		TriangleTypeProblem triangleTypeProblem = new TriangleTypeProblem();
		assertEquals("Not a Triangle", triangleTypeProblem.triangle_type(1, 2, 4));
	}
	
	@Test
	public void testInvalid2(){
		TriangleTypeProblem triangleTypeProblem = new TriangleTypeProblem();
		assertEquals("Not a Triangle", triangleTypeProblem.triangle_type(-1, 1, 1));
	}
	
	@Test
	public void testScalene(){
		TriangleTypeProblem triangleTypeProblem = new TriangleTypeProblem();
		assertEquals("Scalene", triangleTypeProblem.triangle_type(3,4 , 5));
	}
	
	@Test
	public void testIsoscalene(){
		TriangleTypeProblem triangleTypeProblem = new TriangleTypeProblem();
		assertEquals("Isosceles", triangleTypeProblem.triangle_type(3, 3, 5));
	}
	
	@Test
	public void testEquilateral(){
		TriangleTypeProblem triangleTypeProblem = new TriangleTypeProblem();
		assertEquals("Equilateral", triangleTypeProblem.triangle_type(3, 3, 3));
	}
	
}
