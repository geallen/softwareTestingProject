package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fileupload.cfg.CfgOperations;
import com.model.Edge;
import com.model.Node;

public class CfgOperationsTest {

	
	@Test
	public void testAddInitialNode(){
		CfgOperations edgeOperations = new CfgOperations();
		Node a = new Node("1");
		edgeOperations.addInitialNode(a);
		assertEquals(a, edgeOperations.starts.get(0));
	}
	
	@Test
	public void testAddEndingNode(){
		CfgOperations edgeOperations = new CfgOperations();
		Node endNode = new Node("2");
		edgeOperations.addEndingNode(endNode);
		for(Node a :edgeOperations.ends){
			assertEquals(endNode, a);
			break;
		}
		
	}
//	
//	@Test
//	public void testCreateNode(){
//		EdgeOperations edgeOperations = new EdgeOperations();
//		Node b = edgeOperations.createNode("3");
//		Node a = new Node("3");
//		int j = edgeOperations.nodes.size() - 1;
//		Node c = edgeOperations.nodes.get(j);
//		assertEquals(a, c);
//		//Assert.assertArrayEquals(new Object[]{a}, (new Object []{edgeOperations.nodes.get(j)}));
//		
//	}
//	
//	@Test
//	public void testCreateEdge(){
//		EdgeOperations edgeOperations = new EdgeOperations();
//		Node a = new Node(4);
//		Node b = new Node(5);
//		Edge edge = new Edge(a, b);
//		Edge c = edgeOperations.createEdge(edge);
//		Edge created = edgeOperations.edges.get(0);
//		assertEquals(edge, c);
//		
//	}
	
	@Test
	public void testAddEdge(){
		CfgOperations edgeOperations = new CfgOperations();
		Node a = new Node(4);
		Node b = new Node(5);
		Edge edge = new Edge(a, b);
		int prev = edgeOperations.edges.size();
		edgeOperations.addEdge(edge);
		int last = edgeOperations.edges.size();
		assertEquals(prev+1, last);
	}
	
//	[[1,2,4,6,8,9], [1,2,4,6,8,10,11], [1,2,4,6,8,10,12,14], [1,2,4,6,8,10,12,13], [1,2,3,4,6,8,9], [1,2,4,5,6,8,9], [1,2,4,6,7,8,9]]
//
//	@Test
//	public void testFindTestPaths(){
//		
//	}
}
