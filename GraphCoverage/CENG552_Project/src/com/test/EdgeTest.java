package com.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.model.Edge;
import com.model.Node;

public class EdgeTest {
	
	@Test
	public void createDefaultEdge(){
		Node node1 = new Node("1");
		Node node2 = new Node("2");
		Edge edge = new Edge(node1, node2);
		assertEquals(node1, edge.getComingFrom());
		assertEquals(node2, edge.getGoingTo());
	}	
}
