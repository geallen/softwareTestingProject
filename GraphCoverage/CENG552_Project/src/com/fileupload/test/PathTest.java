package com.fileupload.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.model.Edge;
import com.model.Node;
import com.model.Path;


public class PathTest {

	@Test
	public void createDefaultPath() {
		Path path = new Path();
		int prev = path.path.size();
		Node node1 = new Node("1");
		Node node2 = new Node("2");
		Edge edge = new Edge(node1, node2);
		Path pathClass = new Path(node1);
		assertEquals(prev+1, pathClass.path.size());
		Path pathClass2 = new Path(edge);
		assertEquals(prev+2, pathClass2.path.size());
		Path pathClass3 = new Path(pathClass2);
		assertEquals(prev+2, pathClass3.path.size());
	}
	
	

}
