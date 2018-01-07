package com.fileupload.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.model.Edge;
import com.model.Node;



public class NodeTest {
	
	@Test
	public void testCreateDefaultNode(){
		Node node = new Node("2");
		assertEquals("2", node.getNodeVar());
	}
	
	@Test
	public void testAddGoingEdge(){
		Node node = new Node("2");
		Node node2 = new Node("3");
		Edge e = new Edge(node, node2);
		int prev = node.getGoingEdges().size();
		node.addGoing(e);
		assertEquals(prev+1, node.getGoingEdges().size());
	}
	
	@Test
	public void testRemoveGoingEdge(){
		Node node = new Node("2");
		Node node2 = new Node("3");
		Edge e = new Edge(node, node2);
		int prev = node.getGoingEdges().size();
		node.removeGoing(e);
		assertEquals(prev - 1, node.getGoingEdges().size());
	}
	
}
